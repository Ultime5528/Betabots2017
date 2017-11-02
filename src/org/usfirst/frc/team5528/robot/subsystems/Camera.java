package org.usfirst.frc.team5528.robot.subsystems;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
	
	private UsbCamera camera ;
	
	
    public Camera(){
    	
    	camera = new UsbCamera("Camera principal", 0);
    	
    }
    
    
    public void startLoop() {
    	Thread vision = new Thread(this::visionLoop);
    	vision.start();
    	
    }
	
    private void visionLoop(){
    	
    	CameraServer cs = CameraServer.getInstance();
    	
    	CvSink sink = new CvSink ("SinkCam");
    	sink.setSource(camera);
    	
    	CvSource sourceAvant = new CvSource ("SourceAvant", PixelFormat.kMJPEG, 480, 360, 30);
    	cs.addCamera(sourceAvant);
    	MjpegServer serverAvant = cs.addServer("ServerAvant");
    	serverAvant.setSource(sourceAvant);
    	
    	Mat img = new Mat (480 , 360 , CvType.CV_8UC3, new Scalar(255, 0, 0));
    	
    	while(!Thread.interrupted()) {
    		
    		try {
    			sink.grabFrame(img);
    			sourceAvant.putFrame(img);
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		
    		
    	}
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

