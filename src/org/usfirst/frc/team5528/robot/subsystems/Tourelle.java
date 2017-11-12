package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class Tourelle extends Subsystem {

    private VictorSP moteurTourelle;
    private AnalogPotentiometer pot;
    public double tourellePos;
    
    

	public Tourelle(){
		moteurTourelle = new VictorSP(RobotMap.TOURELLE_MOTEUR);
		pot = new AnalogPotentiometer(RobotMap.TOURELLE_POTENTIOMETRE);
				
	}
	public void tournerGauche(){
		 moteurTourelle.set(0.25);
		
	}
	public void tournerDroite(){
		moteurTourelle.set(0.25 * -1);
		
	}
	
	public void stop(){
		moteurTourelle.set(0);
	}
	
	public double getPosition() {
		return pot.get();
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

