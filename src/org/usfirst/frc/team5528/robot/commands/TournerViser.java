package org.usfirst.frc.team5528.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TournerViser extends CommandGroup {

    public TournerViser() {
        addSequential(new OrienterTourelle(0.362));
        addSequential(new Viser());
    }
}
