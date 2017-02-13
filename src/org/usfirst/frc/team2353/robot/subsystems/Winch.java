package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.commands.WinchMove;
import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {

	private TalonSRX winchRight, winchLeft;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Winch(){
		winchRight = new TalonSRX(RobotMap.winchRight);
		winchLeft = new TalonSRX(RobotMap.winchLeft);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new WinchMove());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void moveWinch(){
    	winchLeft.set(OI.getTriggerValue());
    	winchRight.set(OI.getTriggerValue());
    }
}

