package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomeJardin extends CommandGroup {

    public AutonomeJardin() {
        // Add Commands here:
        addSequential(new Avancer(1.05));
        addSequential(new Tourner(25));
        addSequential(new TournerViser());
      //  addSequential(new AvancerTemps(0.55, 2.0));
        addSequential(new ViserTourelle());
        addSequential(new SchedulePiston(3, 0.391));
        addSequential(new SchedulePiston(2, 0.266));
        
        // these will run in order.

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
