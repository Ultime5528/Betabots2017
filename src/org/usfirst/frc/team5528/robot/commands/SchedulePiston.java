package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SchedulePiston extends InstantCommand {

	private int piston;
	private double position;
	
    public SchedulePiston(int piston) {
    	this(piston, Double.NaN);
    }
    
    
    public SchedulePiston(int piston, double position) {
    	this.piston = piston;
    	this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Command command;
    	
    	if(Double.isNaN(position))
    		command = new ShootRetract(piston);
    	
    	else
    		command = new TurnShootRetract(position, piston);
    	
    	if(!Robot.fileCommandes.offer(command)) {
    		DriverStation.reportWarning("Failed to offer Command to Queue", false);
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
