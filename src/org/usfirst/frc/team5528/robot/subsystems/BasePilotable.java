
package org.usfirst.frc.team5528.robot.subsystems;

import javax.sql.rowset.spi.SyncResolver;

import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.RobotMap;
import org.usfirst.frc.team5528.robot.commands.Pilotage;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BasePilotable extends Subsystem {

	
	private VictorSP moteurGauche;
	private VictorSP moteurDroit; 
	private RobotDrive robotDrive;
	private ADXRS450_Gyro gyro;
	private PIDController pidAvance;
	private PIDController pidAngle;
	private Encoder enc;
	
	
	private double valeurAvance = 0.0;
	private double valeurAngle = 0.0;
	public Object lock;
	
	
	public static double P_Avance = 0;
	public static double I_Avance = 0;
	public static double D_Avance = 0;
	public static double P_Angle = 0;
	public static double I_Angle = 0;
	public static double D_Angle = 0;
	public static double TOLERANCE_ANGLE = 0.2;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public BasePilotable(){
		
		moteurGauche = new VictorSP(RobotMap.moteurgauche);
		LiveWindow.addActuator("BasePilotable","MoteurGauche", moteurGauche);
		
		moteurDroit = new VictorSP(RobotMap.moteurdroit);
		LiveWindow.addActuator("BasePilotable","MoteurDroit", moteurDroit);
		
		robotDrive = new RobotDrive(moteurGauche, moteurDroit);
		robotDrive.setMaxOutput(0.55);
		
		enc = new Encoder(RobotMap.BasePilotable_Encoder_A,RobotMap.BasePilotable_Encoder_B);
		
		gyro = new ADXRS450_Gyro();
		LiveWindow.addSensor("BasePilotable","Gyro", gyro);
		gyro.calibrate();
		
		lock = new Object();
		
		pidAvance = new PIDController(P_Avance, I_Avance, D_Avance, enc, this::setValeurAvance);
		
		pidAngle = new PIDController(P_Angle, I_Angle, D_Angle, gyro, this::setValeurAngle);	
		pidAngle.setAbsoluteTolerance(TOLERANCE_ANGLE);
		pidAngle.setOutputRange(-0.6, 0.6);
		
	}
	
	public PIDController getPidAngle() {
		return pidAngle;
	}
	
	private void setValeurAvance(double valeur) {
		
		synchronized (lock) {
			valeurAvance = valeur;
		}
		
	}
	 
	private void setValeurAngle(double valeur) {
		
		synchronized (lock) {
			valeurAngle = valeur;
		}
		
	}
	
	
	public void setSetpointAngle(double setpoint){
		pidAngle.setSetpoint(setpoint);
	}
	
	public void enableAngle(){
		pidAngle.enable();
	}
	
	public void disableAngle(){
		pidAngle.disable();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	
    public void drive() {
    	SmartDashboard.putNumber("Angle", gyro.getAngle());
    	robotDrive.arcadeDrive(Robot.oi.getJoystick().getY()*-1, -1* Robot.oi.getJoystick().getX());
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
    
	public double getAngle(){
		return gyro.getAngle();
	}
	
	public void drive(double move, double turn){
		
		robotDrive.arcadeDrive(move, turn);
	}
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Pilotage());
    }

}

