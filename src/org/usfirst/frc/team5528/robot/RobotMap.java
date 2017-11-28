package org.usfirst.frc.team5528.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	public final static int moteurgauche = 0;
	public final static int moteurdroit = 1;
	//public final static int moteur = 7;
	public final static int joystick = 0;

	public final static int TOURELLE_MOTEUR = 2;
	public final static int TOURELLE_POTENTIOMETRE = 0;	
	public final static int SHOOTER_PISTON1_A = 4;
	public final static int SHOOTER_PISTON1_B = 5;
	public final static int SHOOTER_PISTON2_A = 3;
	public final static int SHOOTER_PISTON2_B = 2;
	public final static int SHOOTER_PISTON3_A = 1;
	public final static int SHOOTER_PISTON3_B = 0;
	public final static int SHOOTER_PISTON4_A = 6;
	public final static int SHOOTER_PISTON4_B = 7;
	public final static int BasePilotable_Encoder_A = 0;
	public final static int BasePilotable_Encoder_B = 1;
	
	
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
