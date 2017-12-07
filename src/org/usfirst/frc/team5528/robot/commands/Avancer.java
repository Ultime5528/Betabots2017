package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Avancer extends Command {
	
	private double distance;
	
    public Avancer(double distance) {
    	this.distance = distance; 
    	requires(Robot.basePilotable);
    	setTimeout(4);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.basePilotable.resetEnc();
    	Robot.basePilotable.setSetpointDistance(distance);
    	Robot.basePilotable.setValeurAngle(0.0);
    	Robot.basePilotable.enableDistance();
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.basePilotable.drivePid();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.basePilotable.avanceOnTarget() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.basePilotable.disableDistance();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
