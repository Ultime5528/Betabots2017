package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootRetract extends CommandGroup {

    public ShootRetract(int piston) {
        
    	addSequential(new Shoot(piston));
        addSequential(new StopPiston(piston));
        
    }
}
