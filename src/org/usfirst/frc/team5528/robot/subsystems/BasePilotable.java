
package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.RobotMap;
import org.usfirst.frc.team5528.robot.commands.Pilotage;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class BasePilotable extends Subsystem {

	
	private VictorSP moteurGauche;
	private VictorSP moteurDroit; 
	private RobotDrive robotDrive;
	
	private ADXRS450_Gyro gyro;
	private Encoder enc;
	
	private PIDController pidAvance;
	private PIDController pidAngle;
	
	
	private double valeurAvance = 0.0;
	private double valeurAngle = 0.0;
	public Object lock;
	
	
	public static double P_Avance = 0;
	public static double I_Avance = 0;
	public static double D_Avance = 0;
	public static double TOLERANCE_ANGLE = 0.07;
	
	public static double P_Angle = -0.06;
	public static double I_Angle = -0.007;
	public static double D_Angle = -0.15;
	public static double Tolerance_AVANCE = 0.1;

	
	public BasePilotable(){
		
		moteurGauche = new VictorSP(RobotMap.moteurgauche);
		LiveWindow.addActuator("BasePilotable","MoteurGauche", moteurGauche);
		
		moteurDroit = new VictorSP(RobotMap.moteurdroit);
		LiveWindow.addActuator("BasePilotable","MoteurDroit", moteurDroit);
		
		robotDrive = new RobotDrive(moteurGauche, moteurDroit);
		robotDrive.setMaxOutput(0.7);
		
		enc = new Encoder(RobotMap.BasePilotable_Encoder_A,RobotMap.BasePilotable_Encoder_B);
		enc.setDistancePerPulse(0.00023456);
		
		gyro = new ADXRS450_Gyro();
		LiveWindow.addSensor("BasePilotable","Gyro", gyro);
		gyro.calibrate();
		
		lock = new Object();
		
		pidAvance = new PIDController(P_Avance, I_Avance, D_Avance, enc, this::setValeurAvance);
		pidAvance.setAbsoluteTolerance(Tolerance_AVANCE);
		pidAvance.setOutputRange(-1.0, 1.0);
		
		pidAngle = new PIDController(P_Angle, I_Angle, D_Angle, gyro, this::setValeurAngle);	
		pidAngle.setAbsoluteTolerance(TOLERANCE_ANGLE);
		pidAngle.setOutputRange(-1.0, 1.0);
		
	}
	
	
	public PIDController getPidAngle() {
		return pidAngle;
	}
	
	
	public PIDController getPidAvance(){
		return pidAvance;
	}
	
	
	public void setValeurAvance(double valeur) {
		
		synchronized (lock) {
			valeurAvance = valeur;
		}
		
	}
	 
	public void setValeurAngle(double valeur) {
		
		synchronized (lock) {
			valeurAngle = valeur;
		}
		
	}
	
	
	public void setSetpointAngle(double setpoint){
		pidAngle.setSetpoint(setpoint);
	}
	
	
	public void setSetpointDistance(double setpoint){
		pidAvance.setSetpoint(setpoint);
	}
	
	public void enableAngle(){
		pidAngle.enable();
	}
	
	public void enableDistance(){
		pidAvance.enable();
	}
	
	public void disableAngle(){
		pidAngle.disable();
	}
	
	public void disableDistance(){
		pidAvance.disable();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public void resetEnc(){
		enc.reset();
	}
	
    public void drive() {
    	robotDrive.arcadeDrive(-1.0 * Robot.oi.getJoystick().getY(), -1.0 * Robot.oi.getJoystick().getX());
    }
    
	public void drive(double move, double turn){
		robotDrive.arcadeDrive(move, turn);
	}
    
    public void drivePid(){
    	
    	double _valeurAngle, _valeurAvance;
    	
    	synchronized (lock) {
			_valeurAngle = valeurAngle;
			_valeurAvance = valeurAvance;
			
		}
    	
    	robotDrive.arcadeDrive(_valeurAvance, _valeurAngle);
    	
    }
  
    public boolean angleOnTarget(){
    	return pidAngle.onTarget();
    }
    
    public boolean avanceOnTarget(){
    	return pidAvance.onTarget();
    }
    
	public double getAngle(){
		return gyro.getAngle();
	}
	
	public double getDistance(){
		return enc.getDistance();
	}
    
	public void initDefaultCommand() {
        setDefaultCommand(new Pilotage());
    }

}

