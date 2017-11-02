package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	private VictorSP moteur;
	private Encoder enc;

	
	public Shooter() {
		super("Shooter", 0.025, 0, 0, 0.3, 0.005);
		
		moteur = new VictorSP(7);
		LiveWindow.addActuator("Shooter","moteur",moteur );
		enc = new Encoder(RobotMap.enc1a, RobotMap.enc1b);
		enc.setDistancePerPulse(0.001544);
		LiveWindow.addSensor("Shooter","encoder", enc);
		LiveWindow.addActuator("Shooter","PIDcontroller", getPIDController());
		PIDController pid = getPIDController();
		pid.setInputRange(0.0, 100);
		pid.setOutputRange(0.0, 1.0);
		pid.setAbsoluteTolerance(2.0);
		
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
		return enc.getRate();
	}



	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		moteur.set(output);
		
	}
}
