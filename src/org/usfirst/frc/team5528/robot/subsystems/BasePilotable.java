
package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.ADIS16448_IMU;
import org.usfirst.frc.team5528.robot.Robot;
import org.usfirst.frc.team5528.robot.RobotMap;
import org.usfirst.frc.team5528.robot.commands.Pilotage;

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
	private ADIS16448_IMU gyro;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public BasePilotable(){
		
		moteurGauche = new VictorSP(RobotMap.moteurgauche);
		LiveWindow.addActuator("BasePilotable","MoteurGauche", moteurGauche);
		
		moteurDroit = new VictorSP(RobotMap.moteurdroit);
		LiveWindow.addActuator("BasePilotable","MoteurDroit", moteurDroit);
		
		robotDrive = new RobotDrive(moteurGauche, moteurDroit);
		
		gyro = new ADIS16448_IMU();
		LiveWindow.addSensor("BasePilotable","Gyro", gyro);
		gyro.calibrate();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	
    public void drive() {
    	SmartDashboard.putNumber("AngleX", gyro.getAngleX());
    	SmartDashboard.putNumber("AngleY", gyro.getAngleY());
    	SmartDashboard.putNumber("AngleZ", gyro.getAngleZ());
    	robotDrive.arcadeDrive(Robot.oi.getJoystick().getY()*-1, Robot.oi.getJoystick().getX());
    }
    
  
    
	public double getAngle(){
		return gyro.getAngleX();
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

