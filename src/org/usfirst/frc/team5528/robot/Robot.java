
package org.usfirst.frc.team5528.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Queue;

import org.usfirst.frc.team5528.robot.commands.AutonomeCour;
import org.usfirst.frc.team5528.robot.commands.AutonomeJardin;
import org.usfirst.frc.team5528.robot.commands.Viser;
import org.usfirst.frc.team5528.robot.subsystems.BasePilotable;
import org.usfirst.frc.team5528.robot.subsystems.Tourelle;
import org.usfirst.frc.team5528.robot.util.RestrictedQueue;
import org.usfirst.frc.team5528.robot.subsystems.Camera;
import org.usfirst.frc.team5528.robot.subsystems.ShooterPiston;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final int MAX_COMMANDES = 3;
	
	public static final BasePilotable basePilotable = new BasePilotable();
	public static final Camera camera = new Camera();
	public static final Tourelle tourelle = new Tourelle();
	public static final ShooterPiston shooterPiston = new ShooterPiston();
	
	public static final Queue<Command> fileCommandes = new RestrictedQueue<>(MAX_COMMANDES);

	public static OI oi;
	
	private Command autonome;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = new OI();	
		camera.startLoop();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonome = new AutonomeJardin();
		autonome.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
		if(autonome != null){
			autonome.cancel();
		}
			
		
		update();
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		Robot.basePilotable.resetEnc();
		Robot.basePilotable.resetGyro();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("pot", Robot.tourelle.getPosition());
		SmartDashboard.putNumber("angle", Robot.basePilotable.getAngle());
		SmartDashboard.putNumber("distance",Robot.basePilotable.getDistance());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	
	
	public void update() {
		
		//Shoot.TIMEOUT = Preferences.getInstance().getDouble("shoot_timeout", Shoot.TIMEOUT);
		/*
		BasePilotable.P_Angle = Preferences.getInstance().getDouble("p_angle", BasePilotable.P_Angle);
		BasePilotable.I_Angle = Preferences.getInstance().getDouble("i_angle", BasePilotable.I_Angle);
		BasePilotable.D_Angle = Preferences.getInstance().getDouble("d_angle", BasePilotable.D_Angle);
		BasePilotable.TOLERANCE_ANGLE = Preferences.getInstance().getDouble("tolerance_angle", BasePilotable.TOLERANCE_ANGLE);
		Robot.basePilotable.getPidAngle().setPID(BasePilotable.P_Angle, BasePilotable.I_Angle, BasePilotable.D_Angle);
		Robot.basePilotable.getPidAngle().setAbsoluteTolerance(BasePilotable.TOLERANCE_ANGLE);
		*/
		BasePilotable.P_Avance = Preferences.getInstance().getDouble("p_avance", BasePilotable.P_Avance);
		BasePilotable.I_Avance = Preferences.getInstance().getDouble("i_avance", BasePilotable.I_Avance);
		BasePilotable.D_Avance = Preferences.getInstance().getDouble("d_avance", BasePilotable.D_Avance);
		BasePilotable.Tolerance_AVANCE = Preferences.getInstance().getDouble("tolerance_avance", BasePilotable.Tolerance_AVANCE);
		Robot.basePilotable.getPidAvance().setPID(BasePilotable.P_Avance, BasePilotable.I_Avance, BasePilotable.D_Avance);
		Robot.basePilotable.getPidAvance().setAbsoluteTolerance(BasePilotable.Tolerance_AVANCE);
		
		Viser.TOLERANCE = Preferences.getInstance().getDouble("viser_tolerance", Viser.TOLERANCE);
		Viser.MIN = Preferences.getInstance().getDouble("viser_min", Viser.MIN);
		Viser.MAX = Preferences.getInstance().getDouble("viser_max", Viser.MAX);
		Viser.P = Preferences.getInstance().getDouble("viser_p", Viser.P);
		Viser.MOVE = Preferences.getInstance().getDouble("viser_move", Viser.MOVE);
		Viser.OFFSET = Preferences.getInstance().getDouble("viser_offset", Viser.OFFSET);
		
		
	}
	
	
	
}
