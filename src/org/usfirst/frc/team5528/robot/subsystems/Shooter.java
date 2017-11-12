package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Shooter extends Subsystem {
	
	private VictorSP moteur;
	private Encoder enc;
	private PIDController controller;

	
	public Shooter() {
		super("Shooter");
		
		moteur = new VictorSP(7);
		LiveWindow.addActuator("Shooter","moteur",moteur );
		enc = new Encoder(RobotMap.enc1a, RobotMap.enc1b);
		enc.setDistancePerPulse(0.001544);
		enc.setPIDSourceType(PIDSourceType.kRate);
		LiveWindow.addSensor("Shooter","encoder", enc);
		
		controller = new PIDController(0.025, 0, 0, 0.3, enc ,moteur , 0.005);
		
		LiveWindow.addActuator("Shooter","PIDcontroller", controller);
		/*
		pid.setInputRange(0.0, 100);
		pid.setOutputRange(0.0, 1.0);
		pid.setAbsoluteTolerance(2.0);
		*/
	}
	
	
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}



	
	
}
