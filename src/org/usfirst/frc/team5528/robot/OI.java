package org.usfirst.frc.team5528.robot;

import org.usfirst.frc.team5528.robot.commands.Avancer;
import org.usfirst.frc.team5528.robot.commands.OrienterTourelle;
import org.usfirst.frc.team5528.robot.commands.SchedulePiston;
import org.usfirst.frc.team5528.robot.commands.Tourner;
import org.usfirst.frc.team5528.robot.commands.TournerViser;
import org.usfirst.frc.team5528.robot.commands.Viser;
import org.usfirst.frc.team5528.robot.commands.Vision;
import org.usfirst.frc.team5528.robot.triggers.ArrowCombination;
import org.usfirst.frc.team5528.robot.triggers.ArrowCombination.Arrow;
import org.usfirst.frc.team5528.robot.triggers.ArrowCombination.XboxButton;

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
	
	ArrowCombination rb;
	ArrowCombination lb;
	ArrowCombination droiteB;
	ArrowCombination centreB;
	ArrowCombination centreX;
	ArrowCombination gaucheX;
	ArrowCombination droiteA;
	ArrowCombination droiteY;
	ArrowCombination centreY;
	ArrowCombination gaucheY;
	ArrowCombination gaucheA;
	
	
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
		button5.toggleWhenPressed(new Vision());
		
		button6 = new JoystickButton(joystick, 6);
		button6.toggleWhenPressed(new TournerViser());
		
		
		// Tourelle (bonne position)
		
		button7 = new JoystickButton(joystick, 7);
		button7.whenPressed(new OrienterTourelle(0.322)); // Avec 2 a gauche
		
		button8 = new JoystickButton(joystick, 8);
		button8.whenPressed(new OrienterTourelle(0.226)); // Avec 2 a droite
		
		button9 = new JoystickButton(joystick, 9);
		button9.whenPressed(new OrienterTourelle(0.478)); // Camera centree
		
		button11 = new JoystickButton(joystick, 11);
		button11.whenPressed(new OrienterTourelle(0.458)); // Cotes libres
	
		 
		gaucheA = new ArrowCombination(gamepad, Arrow.LEFT, XboxButton.A);
		gaucheA.whenPressed(new SchedulePiston(3, 0.52));
		
		
		gaucheY = new ArrowCombination(gamepad, Arrow.LEFT, XboxButton.Y);
		gaucheY.whenPressed(new SchedulePiston(3, 0.459));
		
		
		centreY = new ArrowCombination(gamepad, Arrow.NONE, XboxButton.Y );
		centreY.whenPressed(new SchedulePiston(3, 0.391));
		
		
		droiteY = new ArrowCombination(gamepad, Arrow.RIGHT, XboxButton.Y);
		droiteY.whenPressed(new SchedulePiston(3, 0.327));
		
		
		droiteA = new ArrowCombination(gamepad, Arrow.RIGHT, XboxButton.A);
		droiteA.whenPressed(new SchedulePiston(3, 0.26));
		
		
		gaucheX = new ArrowCombination(gamepad, Arrow.LEFT, XboxButton.X);
		gaucheX.whenPressed(new SchedulePiston(2, 0.34));
		
		
		centreX = new ArrowCombination(gamepad, Arrow.NONE, XboxButton.X);
		centreX.whenPressed(new SchedulePiston(2, 0.266));
		//a essayer
		
		centreB = new ArrowCombination(gamepad, Arrow.NONE, XboxButton.B);
		centreB.whenPressed(new SchedulePiston(2, 0.185));
		
		
		droiteB = new ArrowCombination(gamepad, Arrow.RIGHT, XboxButton.B);
		droiteB.whenPressed(new SchedulePiston(2, 0.088));
		
		
		lb = new ArrowCombination(gamepad, Arrow.NONE, XboxButton.LB);
		lb.whenPressed(new SchedulePiston(1, 0.391));
		
		
		rb = new ArrowCombination(gamepad, Arrow.NONE, XboxButton.RB);
		rb.whenPressed(new SchedulePiston(4, 0.391));
		
		
		
		// Commandes test PID
		
		SmartDashboard.putData("Tourner 45", new Tourner(45.0));
		SmartDashboard.putData("Tourner 90", new Tourner(90.0));
		SmartDashboard.putData("Tourner -20", new Tourner(-20.0));
		SmartDashboard.putData("Avancer 0.45", new Avancer(0.45));
		SmartDashboard.putData("Avancer 1.0", new Avancer(1.0));
		SmartDashboard.putData("Avancer -0.25", new Avancer(-0.25));
		
		
	}
	
	
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	
	public XboxController getGamepad() {
		return gamepad;
	}
	
}
	

