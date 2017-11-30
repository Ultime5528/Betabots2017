package org.usfirst.frc.team5528.robot;

import org.usfirst.frc.team5528.robot.commands.Avancer;
import org.usfirst.frc.team5528.robot.commands.OrienterTourelle;
import org.usfirst.frc.team5528.robot.commands.SchedulePiston;
import org.usfirst.frc.team5528.robot.commands.Tourner;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick joystick; 
	XboxController gamepad;
	
	JoystickButton button1;
	JoystickButton button2;
	JoystickButton button3;
	JoystickButton button4;
	JoystickButton button5;
	JoystickButton button6;
	JoystickButton button7;
	JoystickButton button8;
	JoystickButton button9;
	
	JoystickButton button11;
	JoystickButton button12;
	
	public OI() {
		
		// Joystick et manette
		joystick = new Joystick(RobotMap.joystick);
		gamepad = new XboxController(1);
		
		
		// Pistons
		
		button1 = new JoystickButton(joystick, 1);
		button1.whenPressed(new SchedulePiston(1));
		
		button2 = new JoystickButton(joystick, 2);
		button2.whenPressed(new SchedulePiston(2));
		
		button3 = new JoystickButton(joystick, 3);
		button3.whenPressed(new SchedulePiston(3));
		
		button4 = new JoystickButton(joystick, 4);
		button4.whenPressed(new SchedulePiston(4));
		
		
		// Tourelle (mauvaises positions)
		
		button5 = new JoystickButton(joystick, 5);
		button5.whenPressed(new OrienterTourelle(0.43));
		
		button6 = new JoystickButton(joystick, 6);
		button6.whenPressed(new OrienterTourelle(0.35));
		
		
		// Tourelle (bonne position)
		
		button7 = new JoystickButton(joystick, 7);
		button7.whenPressed(new OrienterTourelle(0.322)); // Avec 2 a gauche
		
		button8 = new JoystickButton(joystick, 8);
		button8.whenPressed(new OrienterTourelle(0.226)); // Avec 2 a droite
		
		button9 = new JoystickButton(joystick, 9);
		button9.whenPressed(new OrienterTourelle(0.478)); // Camera centree
		
		button11 = new JoystickButton(joystick, 11);
		button11.whenPressed(new OrienterTourelle(0.458)); // Cotes libres
	
		
		// Commandes test PID
		
		SmartDashboard.putData("Tourner 45", new Tourner(45.0));
		SmartDashboard.putData("Tourner 90", new Tourner(90.0));
		SmartDashboard.putData("Tourner -20", new Tourner(-20.0));
		SmartDashboard.putData("Avancer 0.45", new Avancer(0.45));
		SmartDashboard.putData("Avancer 0.90", new Avancer(1.0));
		SmartDashboard.putData("Avancer -0.20", new Avancer(-0.25));
		
		
	}
	
	
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	
	public XboxController getGamepad() {
		return gamepad;
	}
	
}
	

