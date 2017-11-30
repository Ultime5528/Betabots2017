package org.usfirst.frc.team5528.robot.commands;


import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ViserTourelle extends Command {

	public static double MAX = 0.3;
	public static double MIN = 0.3;
	
	double setpoint, diff; 
	public Object lock;
	
	
    public ViserTourelle() {
        requires(Robot.camera);
        requires(Robot.tourelle); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.startVision(this::setValeurs);
    	setpoint = Robot.tourelle.getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(setpoint - Robot.tourelle.getPosition() > 0){
    		Robot.tourelle.tournerGauche();
    		
    	} else {
    		Robot.tourelle.tournerDroite();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.camera.stopVision();
    	Robot.tourelle.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    
    public void setValeurs(double centre, double hauteur) {
    	
    	synchronized (lock) {
    		diff = (MAX - MIN) / Camera.LARGEUR * centre + MIN;
    		setpoint += diff;
    	}
    	
    }
    
    
}
