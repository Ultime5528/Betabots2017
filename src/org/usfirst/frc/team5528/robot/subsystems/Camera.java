package org.usfirst.frc.team5528.robot.subsystems;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5528.robot.GripPipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *+
 */
public class Camera extends Subsystem {
	
	private UsbCamera camera;
	private AtomicBoolean runVision;
	private Object lock;
	private BiConsumer<Double, Double> callback;
	
	public static final int LARGEUR = 320;
	public static final int HAUTEUR = 240;
	
	public static final int LUM_LOW = 1;
	public static final int LUM_HIGH = 40;
	
	
    public Camera() {
    	
    	camera = new UsbCamera("Camera", 0);
    	camera.setResolution(LARGEUR, HAUTEUR);
    	camera.setFPS(20);
    	camera.setExposureManual(LUM_HIGH);
    	
    	runVision = new AtomicBoolean(false);
    	callback = null;
    	lock = new Object();
    }
    
    
    public void startLoop() {
    	Thread vision = new Thread(this::visionLoop);
    	vision.start();
    }
	
    
    private void visionLoop(){
    	
    	CameraServer cs = CameraServer.getInstance();
    	
    	CvSink sink = new CvSink ("SinkCam");
    	sink.setSource(camera);
    	cs.addServer(sink);
    	
    	CvSource sourceAvant = new CvSource ("SourceAvant", PixelFormat.kMJPEG, LARGEUR, HAUTEUR, 20);
    	cs.addCamera(sourceAvant);
    	MjpegServer serverAvant = cs.addServer("ServerAvant");
    	serverAvant.setSource(sourceAvant);
    	
    	Mat img = new Mat (HAUTEUR, LARGEUR, CvType.CV_8UC3, new Scalar(255, 0, 0));
    	
    	while(!Thread.interrupted()) {
    	
    		try {
    			sink.grabFrame(img);
    			
    			if(runVision.get()) {
    				analyse(img);
    			}
    			
    			sourceAvant.putFrame(img);
    			
    			
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		
    	}
    	
    }
    
    
    
    public void analyse(Mat input) {
    	
    	Mat output = new Mat();
    	ArrayList<Mat> channels = new ArrayList<>();
    	Core.split(input, channels);
    	//Mat brMat = new Mat();
    	
    	Core.add(channels.get(0), channels.get(2), channels.get(0));
    	
    	Core.addWeighted(channels.get(1), 1.0, channels.get(0), -0.8, 0.0, output);
    	
    	for(Mat c : channels)
    		c.release();
    	
    	
    	Core.inRange(output, new Scalar(20), new Scalar(255), output);
    	
    	Imgproc.dilate(output, output, new Mat(), new Point(-1, -1), 1);
    	Imgproc.erode(output, output, new Mat(), new Point(-1, -1), 1);
    	
    	output.copyTo(input);
    	Imgproc.cvtColor(input, input, Imgproc.COLOR_GRAY2BGR);
    	
    	ArrayList<MatOfPoint> allContours = new ArrayList<>(), contours = new ArrayList<>();
    	Imgproc.findContours(output, allContours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
    	
    	
    	for(MatOfPoint c : allContours) {
    		
    		Rect rect = Imgproc.boundingRect(c);
    		
    		System.out.println("Width : " + rect.width + "\tHeight : " + rect.height);
    		
    		double ratio = rect.width / (double) rect.height; 
    		if(rect.width <= 10 || rect.width >= 53 || rect.height >= 32 || rect.height <= 5 || ratio >3.5 || ratio < 1.5 )
    			continue;
    		
    		
    		contours.add(c);
    		Imgproc.rectangle(input, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
    		
    	}
    	
    	
    	
    	double centre = LARGEUR / 2.0; 
    	double hauteur = 0;
    	
    	if(contours.size() == 0) {
    		
   
    	}
    	
    	else if (contours.size() == 1) {
    		
    		Rect rect = Imgproc.boundingRect(contours.get(0));
    		centre = rect.x + rect.width/2.0;
    		hauteur = rect.height;
    	}
    	
    	else {
    		
    	}
    	
    	if(callback != null)
    		callback.accept(centre, hauteur);
    	
    	
    	
    }
    
    
    public void startVision(BiConsumer<Double, Double> callback) {
    	
    	camera.setExposureManual(LUM_LOW);
    	
    	synchronized (lock) {
    		this.callback = callback;
    	}
    	runVision.set(true);
    	
    }
    
        
    public void stopVision() {
    	
    	camera.setExposureManual(LUM_HIGH);
    	
    	runVision.set(false);
    }
    
    
    public void initDefaultCommand() {
    }
}

