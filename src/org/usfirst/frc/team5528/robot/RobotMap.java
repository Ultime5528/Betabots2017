package org.usfirst.frc.team5528.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public final static int joystick = 0;

	// Tourelle
	public final static int TOURELLE_MOTEUR = 2;
	public final static int TOURELLE_POTENTIOMETRE = 0;	
	
	// Pistons
	public final static int SHOOTER_PISTON1_A = 4;
	public final static int SHOOTER_PISTON1_B = 5;
	public final static int SHOOTER_PISTON2_A = 3;
	public final static int SHOOTER_PISTON2_B = 2;
	public final static int SHOOTER_PISTON3_A = 1;
	public final static int SHOOTER_PISTON3_B = 0;
	public final static int SHOOTER_PISTON4_A = 6;
	public final static int SHOOTER_PISTON4_B = 7;
	
	// Base pilotable
	public final static int moteurgauche = 0;
	public final static int moteurdroit = 1;
	public final static int BasePilotable_Encoder_A = 0;
	public final static int BasePilotable_Encoder_B = 1;
	
}
