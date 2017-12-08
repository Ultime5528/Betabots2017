package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.subsystems.BasePilotable;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Avancer extends Command {
	
	private double distance;
	private boolean aDepasse = false;
	private double tempsDepasse;
	
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
    	tempsDepasse = 0.0;
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(!aDepasse && Math.abs(-1 * Math.signum(Robot.basePilotable.getPidAvance().getError()) - Math.signum(distance)) < 0.001) {
    		aDepasse = true;
    		tempsDepasse = timeSinceInitialized();
    		System.out.println("Avancer depasse");
    	}
    		
    	
    	SmartDashboard.putNumber("ValeurAvance", Robot.basePilotable.getValeurAvance());
    	Robot.basePilotable.drivePid();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.basePilotable.avanceOnTarget() || isTimedOut() || (aDepasse && (timeSinceInitialized() - tempsDepasse) > 0.5 && Math.abs(Robot.basePilotable.getPidAvance().getError()) < BasePilotable.Tolerance_AVANCE * 5);
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
