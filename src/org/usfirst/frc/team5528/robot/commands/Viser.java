package org.usfirst.frc.team5528.robot.commands;

import java.util.concurrent.atomic.AtomicBoolean;

import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Viser extends Command {

	public static double TOLERANCE = 0.5;
	public static double MIN = 0.2;
	public static double MAX = 0.4;
	public static double P = 0.2;
	
	public static double FOV_X = 46.98;
	
	double _centre, _hauteur, angle, hauteur; 
	private AtomicBoolean hasChanged;
	public Object lock;
	
	
    public Viser() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.basePilotable);
        requires(Robot.camera);
        
        hasChanged = new AtomicBoolean(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.startVision(this::setValeurs);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(hasChanged.getAndSet(false)) {
    		
    		double centre, hauteur;
    		
    		synchronized (lock) {
				centre = _centre;
				hauteur = _hauteur;
			}
    		
    		SmartDashboard.putNumber("Centre", centre);
        	SmartDashboard.putNumber("Hauteur", hauteur);
        	
        	double angle = FOV_X * ( centre / Camera.LARGEUR - 0.5 );
        	SmartDashboard.putNumber("Setpoing angle", angle);
        	
        	Robot.basePilotable.resetGyro();
        	
        	this.angle = angle;
        	this.hauteur = hauteur;
        	
    	}
    	
    	
    	double error = angle - Robot.basePilotable.getAngle();
    	double absError = Math.abs(error);
    	double rotate = 0.0, move = 0.0;
    	
    	if(absError >= TOLERANCE) {
    		rotate = P * (absError - TOLERANCE) + MIN;
    		
    		if(rotate > MAX)
    			rotate = MAX;
    		
    		rotate *= Math.signum(error);
    		
    	}
    	
    	if(hauteur != 0.0 )
    	
    	SmartDashboard.putNumber("Rotate", rotate);
    	SmartDashboard.putNumber("error", error);
    	Robot.basePilotable.drive(0.0, rotate);
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void setValeurs(double centre, double hauteur) {
    	
    	synchronized (lock) {
    		_centre = centre;
    		_hauteur = hauteur;
    	}
    	
    	hasChanged.set(true);
    	
    }
}
