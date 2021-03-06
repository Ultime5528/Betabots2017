package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OrienterTourelle extends Command {

	public static double OFFSET = 0.0;
	
	private double setpoint;
	
    public OrienterTourelle(double setpoint) {
    	this.setpoint = setpoint;
        requires(Robot.tourelle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(setpoint + OFFSET - Robot.tourelle.getPosition() > 0)
    		Robot.tourelle.tournerGauche();
    	
    	else
    		Robot.tourelle.tournerDroite();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(setpoint + OFFSET - Robot.tourelle.getPosition()) < 0.01);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.tourelle.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
