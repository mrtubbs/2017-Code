package org.usfirst.frc.team1414.robot;

import jaci.pathfinder.Waypoint;


public class Constants {

	// CAN IDs
	public static final int kLeftDriveMasterId = 4;
	public static final int kLeftDriveSlaveId = 2;
	public static final int kRightDriveMasterId = 10;
	public static final int kRightDriveSlaveId = 1;
	public static final int kIntakeId = 6;
	public static final int kIndexerId = 7;
	public static final int kShooterMotorMasterId = 5;
	public static final int kShooterMotorSlaveId = 8;
	
	// PCM
	public static final int kShifterForwardPort = 0; //UPDATE
	public static final int kShifterReversePort = 1; //UPDATE
	
	//Turn Controller
	public static final double kTurnToleranceDegrees = 2.0f;
	public static final double kPTurn = 0.03;
	public static final double kITurn = 0.00;
	public static final double kDTurn = 0.00;
	public static final double kFTurn = 0.00;
	
	//Base Lock
	public static double kDriveBaseLockKp = 0.5;
    public static double kDriveBaseLockKi = 0;
    public static double kDriveBaseLockKd = 0;
    public static double kDriveBaseLockKf = 0;
    public static int kDriveBaseLockIZone = 0;
    public static double kDriveBaseLockRampRate = 0;
	public static final int kDriveBaseLockAllowableError = 10;
	
	public static double kWheelDiameter = 4; 
	public static double kMaxVelocity = 18; 
	
	//Shooter
	public static double kShooterP = 0.06; // 0.12
	public static double kShooterI = 0.0; // 0
	public static double kShooterD = 9.2; //10.2
	public static double kShooterF = 0.021407645089286;
	public static double kShooterRampRate = 1;
	public static int kShooterIZone = 0;
	public static final double kFlywheelOnTargetTolerance = 100.0;
	
	//Controllers
	public static final int kSteeringWheelPort = 2;
	public static final int kJoystickPort = 1; 
	
	//Speed Constants
	public static final double kIndexerSpeed = 1.0;
	public static final double kIntakeSpeed = 1.0;
	
	
}
