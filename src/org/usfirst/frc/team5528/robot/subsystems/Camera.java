package org.usfirst.frc.team5528.robot.subsystems;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
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
	
	public static final int LARGEUR = 480;
	public static final int HAUTEUR = 360;
	
	
    public Camera() {
    	
    	camera = new UsbCamera("Camera principale", 0);
    	//camera.setResolution(LARGEUR, HAUTEUR);
    	//camera.setFPS(15);
    	
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
    	
    	CvSource sourceAvant = new CvSource ("SourceAvant", PixelFormat.kMJPEG, LARGEUR, HAUTEUR, 30);
    	cs.addCamera(sourceAvant);
    	MjpegServer serverAvant = cs.addServer("ServerAvant");
    	serverAvant.setSource(sourceAvant);
    	
    	Mat img = new Mat (LARGEUR , HAUTEUR , CvType.CV_8UC3, new Scalar(255, 0, 0));
    	
    	while(!Thread.interrupted()) {
    	
    		try {
    			sink.grabFrame(img);
    			sourceAvant.putFrame(img);
    			/*
    			if(runVision.get()) {
    				analyse(img);
    			}
    			*/
    			
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		
    	}
    	
    }
    
    
    
    public void analyse(Mat input) {
    	
    	GripPipeline.getInstance().process(input);
    	
    	ArrayList<MatOfPoint> contours = GripPipeline.getInstance().filterContoursOutput();
    
    	double centre = 0; 
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
    	
    	synchronized (lock) {
    		this.callback = callback;
    	}
    	runVision.set(true);
    	
    }
    
        
    public void stopVision() {
    	runVision.set(false);
    }
    
    
    public void initDefaultCommand() {
    }
}

