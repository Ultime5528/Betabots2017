package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	public static double TIMEOUT = 0.25;
	
	private int piston;

    public Shoot(int piston) {
    	requires(Robot.shooterPiston);
    	setTimeout(TIMEOUT);
    	this.piston = piston;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooterPiston.setForward(piston);
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
