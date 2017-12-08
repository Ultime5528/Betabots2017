package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomeJardin extends CommandGroup {

    public AutonomeJardin() {
        
        addSequential(new Avancer(1.15));
        addSequential(new Tourner(33));
        addSequential(new Avancer(0.45));
       // addSequential(new TournerViser());
        addSequential(new AvancerTemps(0.5, 1.2));
        addSequential(new ViserTourelle());
        addSequential(new TurnShootRetract(0.391, 3));
        addSequential(new TurnShootRetract(0.266, 2));
        addSequential(new Avancer(-0.50));
        addSequential(new Tourner(-80));
        addSequential(new Avancer(-0.50));


    }
}
