package org.usfirst.frc.team5528.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	private VictorSP moteur;
	
	public Shooter() {
		super("Shooter", 0, 0, 0, 0, 0.005);
		
		moteur = new VictorSP(7);
	}
	
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}



	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}
