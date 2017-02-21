package org.usfirst.frc.team2353.commands;

import org.usfirst.frc.team2353.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DumpBalls extends Command {

    public DumpBalls(double time) {
    	requires(Robot.tray);
    	
    	setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.tray.moveTray(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
