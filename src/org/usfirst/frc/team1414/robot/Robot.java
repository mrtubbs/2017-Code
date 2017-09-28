package org.usfirst.frc.team1414.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team1414.subsystems.Intake;
import org.usfirst.frc.team1414.subsystems.Climber;
import org.usfirst.frc.team1414.subsystems.Drive;
import org.usfirst.frc.team1414.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1414.robot.Constants;
import org.usfirst.frc.team1414.subsystems.LoadingPanel;
import com.ctre.CANTalon;


public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto"; 
	Drive drive = new Drive();
	Intake intake = new Intake();
	Shooter shooter = new Shooter();
	Climber climber = new Climber();
	LoadingPanel loadingPanel = new LoadingPanel();
	
	Joystick stick = new Joystick(Constants.kJoystickPort);
	Joystick steeringWheel = new Joystick(Constants.kSteeringWheelPort); 
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		System.out.println("01001101 01100001 01100001 01101110 01101001 01110100 00100000 01001101 01100001 01100100 01100001 01101110");
	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		
		//drive.control(steeringWheel);
		drive.outputToSmartDashboard();
		shooter.outputToSmartDashboard();
		
		if (steeringWheel.getRawButton(9)) {
			drive.setHighGear(false);
		}
		if (steeringWheel.getRawButton(10)) {
			drive.setHighGear(true);
		}
		
		if (stick.getRawButton(2)) {
			intake.reverseIntake();
		}
		else {
			intake.stopIntake();
		}
		
		if (stick.getTrigger()) {
			shooter.setRpm(-7000);
		}
		else {
			shooter.setOpenLoop(0.0);
		}
		
		if (stick.getRawButton(7)) {
			climber.startClimb();
			intake.forwardIntake();
		}
		else {
			climber.stopClimb();
			intake.stopIntake();
		}
		
		if (stick.getRawButton(11)) {
			loadingPanel.extendPanel();
		}
		else if (stick.getRawButton(10)) {
			loadingPanel.retractPanel();
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}











/**
 * Copyright IHOT Team 1414
 * Powered by the amazing SAVAGE (System Architecture Vulnerability Analyst and Generic Engineer) Maanit Madan :)
**/

