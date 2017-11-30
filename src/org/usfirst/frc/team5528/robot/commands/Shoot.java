package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	public static double TIMEOUT = 0.5;
	
	private int piston;


    public Shoot(int piston) {
    	this.piston = piston;
    	
    	requires(Robot.shooterPiston);
    	setTimeout(TIMEOUT);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    //	Robot.shooter.setSetpoint(60);
    	Robot.shooterPiston.setForward(piston);
    	//Robot.shooter.enable();
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterPiston.setReverse(piston);
    		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooterPiston.setAllOff();
    	
    }
}
