package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveTourelle extends Command {

    public MoveTourelle() {
        requires(Robot.tourelle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double stick = Robot.oi.getGamepad().getX(Hand.kLeft);
    	
    	if(stick > 0.5)
    		Robot.tourelle.tournerDroite();
    	
    	else if(stick < -0.5)
    		Robot.tourelle.tournerGauche();
    	
    	else
    		Robot.tourelle.stop();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
