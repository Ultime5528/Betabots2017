package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class ShooterPiston extends Subsystem {

    
	
	
	private DoubleSolenoid piston;
	

	public ShooterPiston(){
		
	piston = new DoubleSolenoid(RobotMap.SHOOTER_PISTON_A,RobotMap.SHOOTER_PISTON_B);
	LiveWindow.add
		
		
		
		
	}


	
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

