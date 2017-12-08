package org.usfirst.frc.team5528.robot.commands;


import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ViserTourelle extends Command {

	public static double MAX = 0.0945;
	public static double MIN = -0.0945;
	
	double setpoint, diff; 
	public Object lock;
	
	
    public ViserTourelle() {
        requires(Robot.camera);
        requires(Robot.tourelle); 
        
        lock = new Object();
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.startVision(this::setValeurs);
    	setpoint = Robot.tourelle.getPosition();
    	setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double error = setpoint - Robot.tourelle.getPosition(); 
    	
    	if(error > 0){
    		
    		if(error > 0.015 )
	    		Robot.tourelle.tournerGauche();
    		else
    			Robot.tourelle.tournerGaucheLent();
    		
    	} else {
    		if(error < -0.015)
    			Robot.tourelle.tournerDroite();
    		
    		else
    			Robot.tourelle.tournerDroiteLent();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() && Math.abs(setpoint - Robot.tourelle.getPosition()) < 0.008;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.camera.stopVision();
    	Robot.tourelle.stop();
    	OrienterTourelle.OFFSET = Robot.tourelle.getPosition() - 0.391;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    
    public void setValeurs(double centre, double hauteur) {
    	
    	synchronized (lock) {
    		diff = (MAX - MIN) / Camera.LARGEUR * (centre - Viser.OFFSET) + MIN;
    		setpoint = Robot.tourelle.getPosition() - diff;
    	}
    	
    	System.out.println(setpoint);
    	
    }
    
    
}
