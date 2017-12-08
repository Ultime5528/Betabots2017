package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomeCour extends CommandGroup {

    public AutonomeCour() {
        // Add Commands here:
    	// Add Commands here:
        addSequential(new Avancer(1.15));
        addSequential(new Tourner(-33));
        addSequential(new Avancer(0.45));
       // addSequential(new TournerViser());
        addSequential(new AvancerTemps(0.45, 1.2));
        addSequential(new ViserTourelle());
        addSequential(new TurnShootRetract(0.391, 3));
        addSequential(new TurnShootRetract(0.266, 2));
        addSequential(new Avancer(-0.50));
        addSequential(new Tourner(80));
        addSequential(new Avancer(-0.50));
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
