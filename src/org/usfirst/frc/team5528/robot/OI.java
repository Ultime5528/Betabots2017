package org.usfirst.frc.team5528.robot;

import org.usfirst.frc.team5528.robot.commands.Avancer;
import org.usfirst.frc.team5528.robot.commands.OrienterTourelle;
import org.usfirst.frc.team5528.robot.commands.SchedulePiston;
import org.usfirst.frc.team5528.robot.commands.Shoot;
import org.usfirst.frc.team5528.robot.commands.Tourner;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	Joystick joystick; 
	JoystickButton button2;
	JoystickButton button3, button4;
	JoystickButton button11, button12;
	JoystickButton button1;
	JoystickButton button9, button6, button5, button7, button8;
	public OI() {
		
		joystick = new Joystick(RobotMap.joystick);
		
		button1 = new JoystickButton(joystick, 1);
		button1.whenPressed(new SchedulePiston(1));
		
		button2 = new JoystickButton(joystick, 2);
		button2.whenPressed(new SchedulePiston(2));
		
		button3 = new JoystickButton(joystick, 3);
		button3.whenPressed(new SchedulePiston(3));
		
		button4 = new JoystickButton(joystick, 4);
		button4.whenPressed(new SchedulePiston(4));
		
		button5 = new JoystickButton(joystick, 5);
		button5.whenPressed(new OrienterTourelle(0.43));
		
		button6 = new JoystickButton(joystick, 6);
		button6.whenPressed(new OrienterTourelle(0.35));
		
		button7 = new JoystickButton(joystick, 7);
		button7.whenPressed(new OrienterTourelle(0.322)); // Avec 2 a gauche
		
		button8 = new JoystickButton(joystick, 8);
		button8.whenPressed(new OrienterTourelle(0.226)); // Avec 2 a droite
		
		
		button9 = new JoystickButton(joystick, 9);
		button9.whenPressed(new OrienterTourelle(0.478)); // Camera centree
		
		button11 = new JoystickButton(joystick, 11);
		button11.whenPressed(new OrienterTourelle(0.458)); // Cotes libres
		
		
		
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
}
	

