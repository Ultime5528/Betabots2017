package org.usfirst.frc.team5528.robot.subsystems;

import org.usfirst.frc.team5528.robot.RobotMap;
import org.usfirst.frc.team5528.robot.commands.MoveTourelle;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Tourelle extends Subsystem {

	public static double VITESSE = 0.3;
	
    private VictorSP moteurTourelle;
    private AnalogPotentiometer pot;
    
	public Tourelle(){
		moteurTourelle = new VictorSP(RobotMap.TOURELLE_MOTEUR);
		LiveWindow.addActuator("Tourelle", "moteur", moteurTourelle);
		
		pot = new AnalogPotentiometer(RobotMap.TOURELLE_POTENTIOMETRE);
		LiveWindow.addSensor("Tourelle", "potentiometre", pot);		
	}
	
	
	public void tournerGauche(){
		if(getPosition() <= 0.65)
			moteurTourelle.set(VITESSE);	
		else
			stop();
	}
	
	public void tournerDroite(){
		if(getPosition() >= 0.05)
			moteurTourelle.set(-1.0 * VITESSE);	
		else
			stop();
	}
	
	public void stop(){
		moteurTourelle.set(0.0);
	}
	
	public double getPosition() {
		return pot.get();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new MoveTourelle());
    }
}

