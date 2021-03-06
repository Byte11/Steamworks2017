package org.usfirst.frc.team2353.robot;

import org.usfirst.frc.team2353.commands.BallLeft;
import org.usfirst.frc.team2353.commands.BallRight;
import org.usfirst.frc.team2353.commands.DoNothing;
import org.usfirst.frc.team2353.commands.GearLeft;
import org.usfirst.frc.team2353.commands.GearRight;
import org.usfirst.frc.team2353.commands.TimedDrive;
import org.usfirst.frc.team2353.robot.subsystems.Chassis;
import org.usfirst.frc.team2353.robot.subsystems.Roller;
import org.usfirst.frc.team2353.robot.subsystems.Tray;
import org.usfirst.frc.team2353.robot.subsystems.Winch;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static Chassis chassis;
	public static Winch winch;
	public static Roller roller;
	public static Tray tray;
	public static OI oi;

	Command autonomousCommand;
	SendableChooser modeChooser;

	@Override
	public void robotInit() {
		oi = new OI();
		roller = new Roller();
		chassis = new Chassis();
		winch = new Winch();
		tray = new Tray();
		
		CameraServer.getInstance().startAutomaticCapture();
		
		modeChooser = new SendableChooser();
		
		modeChooser.addDefault("Move Forward", new TimedDrive(0, 0.6, 0, 0, 1));
		modeChooser.addObject("Gear Right", new GearRight());
		modeChooser.addObject("Gear Left", new GearLeft());
		modeChooser.addObject("Ball Right", new BallRight());
		modeChooser.addObject("Ball Left", new BallLeft());
		modeChooser.addObject("Do Nothing", new DoNothing());
		
		//modeChooser.addObject("Gear", "gear");
		SmartDashboard.putData("Auto mode", modeChooser);
	}

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
		autonomousCommand = (Command) modeChooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
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
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
