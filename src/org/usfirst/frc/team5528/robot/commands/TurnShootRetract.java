package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnShootRetract extends CommandGroup {

    public TurnShootRetract(double position, int piston) {
    	
    	addSequential(new OrienterTourelle(position));
        addSequential(new ShootRetract(piston));
        
    }
}
