package org.usfirst.frc.team5528.robot.commands;


import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.subsystems.BasePilotable;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Tourner extends Command {
	
	private double angle;
	private boolean aDepasse;
	private double tempsDepasse;
	
    public Tourner(double angle) {
       this.angle = angle;
       requires(Robot.basePilotable);
       setTimeout(3.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.basePilotable.resetGyro();
    	Robot.basePilotable.setSetpointAngle(angle);
    	Robot.basePilotable.setValeurAvance(0.0);
    	Robot.basePilotable.enableAngle();
    	aDepasse = false;
    	tempsDepasse = 0.0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(!aDepasse && Math.abs(-1 * Math.signum(Robot.basePilotable.getPidAngle().getError()) - Math.signum(angle)) < 0.001) {
    		aDepasse = true;
    		tempsDepasse = timeSinceInitialized();
    		System.out.println("Tourner depasse");
    	}
    		
    	
    	Robot.basePilotable.drivePid();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.basePilotable.angleOnTarget() || isTimedOut() || (aDepasse && (timeSinceInitialized() - tempsDepasse) > 0.5 && Math.abs(Robot.basePilotable.getPidAngle().getError()) < BasePilotable.TOLERANCE_ANGLE * 5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.basePilotable.disableAngle();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
