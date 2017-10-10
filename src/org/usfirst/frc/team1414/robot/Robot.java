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
	final String defaultAuto = "Default"; //drive mode
	final String customAuto = "My Auto";  //vehicle definition
	Drive drive = new Drive(); //create instance of drive
	Intake intake = new Intake(); //create instance of intake
	Shooter shooter = new Shooter(); //create instance of shooter
	Climber climber = new Climber(); //create instance of climber
	LoadingPanel loadingPanel = new LoadingPanel();  ////create instance of loading panel
	
	Joystick stick = new Joystick(Constants.kJoystickPort); ////create instance of joystick
	Joystick steeringWheel = new Joystick(Constants.kSteeringWheelPort);   //create instance of steering wheel
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>(); //-∞/10

	@Override //overide
	public void robotInit() { //init void
		chooser.addDefault("Default Auto", defaultAuto); //part of the init
		chooser.addObject("My Auto", customAuto); //add object
		SmartDashboard.putData("Auto choices", chooser); //put data
		System.out.println("01001101 01100001 01100001 01101110 01101001 01110100 00100000 01001101 01100001 01100100 01100001 01101110"); //printing crazzzzzzyyyyyyy
	}

	@Override
	public void autonomousInit() { //init for autonomus modew
		autoSelected = chooser.getSelected(); //select something
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected); //print out information
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override //useless function
	public void autonomousPeriodic() { //start of automatic periodic
		
		switch (autoSelected) { //switch case
		case customAuto:
			// Put custom auto code here
			//empty code
			break;
		case defaultAuto:
		default:
			// Put default auto code here 
			//empty code
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		
		//drive.control(steeringWheel);
		drive.outputToSmartDashboard(); //get output to smart dashboard
		shooter.outputToSmartDashboard(); //get output to smart dashboard
		//set variables
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
	@Override //no thing
	public void testPeriodic() {
	}
}











/**
 * Comments by FABIAN BLANK - THE MOST USEFULL PERSON IN THE RUM
 * Copyright IHOT Team 1414
 * Powered by the amazing SAVAGE (System Architecture Vulnerability Analyst and Generic Engineer) Maanit Madan :)
**/

