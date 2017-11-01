package org.usfirst.frc.team5528.robot.commands;


import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Tourner extends Command {

    public Tourner() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.basePilotable);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.basePilotable.drive(0, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.basePilotable.getAngle() >= 90);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
