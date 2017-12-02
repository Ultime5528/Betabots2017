package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TurnShootRetract extends CommandGroup {

    public TurnShootRetract(double position, int piston) {
    	
    	addSequential(new OrienterTourelle(position));
    	addSequential(new WaitCommand("Wait 0.1 sec", 0.1));
        addSequential(new ShootRetract(piston));
        
    }
}
