package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SchedulePiston extends InstantCommand {

	private int piston;
	
    public SchedulePiston(int piston) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.piston = piston;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!Robot.shooterPiston.getQueue().offer(new ShootRetract(piston))) {
    		DriverStation.reportError("Failed to offer Command to Queue", true);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
