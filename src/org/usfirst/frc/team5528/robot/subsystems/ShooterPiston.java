package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.RobotMap;
import org.usfirst.frc.team5528.robot.commands.PistonScheduler;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class ShooterPiston extends Subsystem {

	private DoubleSolenoid piston1;
	private DoubleSolenoid piston2;
	private DoubleSolenoid piston3;
	private DoubleSolenoid piston4;

	
	public ShooterPiston(){

		piston1 = new DoubleSolenoid(RobotMap.SHOOTER_PISTON1_A,RobotMap.SHOOTER_PISTON1_B);
		LiveWindow.addActuator("ShooterPiston", "Piston1", piston1);
		
		piston2 = new DoubleSolenoid(RobotMap.SHOOTER_PISTON2_A,RobotMap.SHOOTER_PISTON2_B);
		LiveWindow.addActuator("ShooterPiston", "Piston2", piston2);
		
		piston3 = new DoubleSolenoid(RobotMap.SHOOTER_PISTON3_A,RobotMap.SHOOTER_PISTON3_B);
		LiveWindow.addActuator("ShooterPiston", "Piston3", piston3);
		
		piston4 = new DoubleSolenoid(RobotMap.SHOOTER_PISTON4_A,RobotMap.SHOOTER_PISTON4_B);
		LiveWindow.addActuator("ShooterPiston", "Piston4", piston4);

	}

	
	public void initDefaultCommand() {
		setDefaultCommand(new PistonScheduler());
	}
	

	public void setForward(int piston){

		if(piston == 1){
			piston1.set(DoubleSolenoid.Value.kForward);

		}
		else if(piston == 2){
			piston2.set(DoubleSolenoid.Value.kForward);

		}
		else if (piston == 3){
			piston3.set(DoubleSolenoid.Value.kForward);

		}
		else if  (piston == 4){
			piston4.set(DoubleSolenoid.Value.kForward);

		}

	}


	public void setReverse(int piston) {

		if(piston == 1){
			piston1.set(DoubleSolenoid.Value.kReverse);

		}
		else if(piston == 2){
			piston2.set(DoubleSolenoid.Value.kReverse);

		}
		else if (piston == 3){
			piston3.set(DoubleSolenoid.Value.kReverse);

		}
		else if  (piston == 4){
			piston4.set(DoubleSolenoid.Value.kReverse);

		}
	}

	public void setAllOff() {
		piston1.set(DoubleSolenoid.Value.kOff);
		piston2.set(DoubleSolenoid.Value.kOff);
		piston3.set(DoubleSolenoid.Value.kOff);
		piston4.set(DoubleSolenoid.Value.kOff);
	}
	
}
