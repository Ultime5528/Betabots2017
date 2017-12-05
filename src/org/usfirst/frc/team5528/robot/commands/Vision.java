package org.usfirst.frc.team5528.robot.commands;

import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Command {

	public Vision() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.camera);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.camera.startVision(this::setValeurs);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.camera.stopVision();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	public void setValeurs(double centre, double hauteur) {

		SmartDashboard.putNumber("centre", centre);
		SmartDashboard.putNumber("hauteur", hauteur);
	}
}
