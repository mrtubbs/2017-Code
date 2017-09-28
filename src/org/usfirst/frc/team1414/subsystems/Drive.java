package org.usfirst.frc.team1414.subsystems;

import org.usfirst.frc.team1414.robot.Constants;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Drive extends Subsystem implements PIDOutput {
	protected static final int kTurnControlSlot = 0;
	protected static final int kBaseLockControlSlot = 1;
	
	private double magnitudeValue = 0; 
	private boolean isHighGear = false;
	private boolean isBaseLock = false;
	private boolean isBrakeMode = false;
	private boolean isNavX = false; 
	double rotateToAnalgeRate; 
	private final DoubleSolenoid shifter; 
	private final CANTalon leftMaster, leftSlave, rightMaster, rightSlave; 
	private final RobotDrive robot;
	private final PIDController turnController;
	AHRS ahrs;
	
	public Drive() {
		shifter = new DoubleSolenoid(Constants.kShifterForwardPort, Constants.kShifterReversePort);
		leftMaster = new CANTalon(Constants.kLeftDriveMasterId);
		leftSlave = new CANTalon(Constants.kLeftDriveSlaveId);
		rightMaster = new CANTalon(Constants.kRightDriveMasterId);
		rightSlave = new CANTalon(Constants.kRightDriveSlaveId);
		
		leftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		
		
		robot = new RobotDrive(leftMaster, rightMaster);
		
		leftMaster.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);
		rightMaster.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);
		
		setBrakeMode(true);
		setHighGear(true); 
		
		leftMaster.setPID(Constants.kPTurn, Constants.kITurn, Constants.kDTurn,
                Constants.kFTurn, Constants.kDriveBaseLockIZone, Constants.kDriveBaseLockRampRate,
                kTurnControlSlot);
        rightMaster.setPID(Constants.kPTurn, Constants.kITurn, Constants.kDTurn,
                Constants.kFTurn, Constants.kDriveBaseLockIZone, Constants.kDriveBaseLockRampRate,
                kTurnControlSlot);
        // Load base lock control gains
        leftMaster.setPID(Constants.kDriveBaseLockKp, Constants.kDriveBaseLockKi, Constants.kDriveBaseLockKd,
                Constants.kDriveBaseLockKf, Constants.kDriveBaseLockIZone, Constants.kDriveBaseLockRampRate,
                kBaseLockControlSlot);
        rightMaster.setPID(Constants.kDriveBaseLockKp, Constants.kDriveBaseLockKi, Constants.kDriveBaseLockKd,
                Constants.kDriveBaseLockKf, Constants.kDriveBaseLockIZone, Constants.kDriveBaseLockRampRate,
                kBaseLockControlSlot);
		
		leftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	    leftMaster.set(0);
	    leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
	    leftSlave.set(Constants.kLeftDriveMasterId);
	    rightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	    rightMaster.set(0);
	    rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
	    rightSlave.set(Constants.kRightDriveMasterId);
	    
	    leftMaster.reverseSensor(false);
	    rightMaster.reverseSensor(false);
	    
	    try {
	    	ahrs = new AHRS(SPI.Port.kMXP);
	    	isNavX = true;
	    }
	    catch (RuntimeException ex) {
	    	DriverStation.reportError("Error Connecting to navX" + ex.getMessage(), true);
	    	isNavX = false; 
	    }
	    
	    turnController = new PIDController(Constants.kPTurn, Constants.kITurn, Constants.kDTurn, Constants.kFTurn, ahrs, this);
	    turnController.setInputRange(-180.0f,  180.0f);
	    turnController.setOutputRange(-1.0, 1.0);
	    turnController.setAbsoluteTolerance(Constants.kTurnToleranceDegrees);
	    turnController.setContinuous(true);
	    ahrs.reset();
	}
	
	public void setBrakeMode(boolean on) {
        if (isBrakeMode != on) {
            leftMaster.enableBrakeMode(on);
            leftSlave.enableBrakeMode(on);
            rightMaster.enableBrakeMode(on);
            rightSlave.enableBrakeMode(on);
            isBrakeMode = on;
        }
    }
	
	public synchronized void setBaseLockOn() {
            leftMaster.setProfile(kBaseLockControlSlot);
            leftMaster.changeControlMode(CANTalon.TalonControlMode.Position);
            leftMaster.setAllowableClosedLoopErr(Constants.kDriveBaseLockAllowableError);
            leftMaster.set(leftMaster.getPosition());
            rightMaster.setProfile(kBaseLockControlSlot);
            rightMaster.changeControlMode(CANTalon.TalonControlMode.Position);
            rightMaster.setAllowableClosedLoopErr(Constants.kDriveBaseLockAllowableError);
            rightMaster.set(rightMaster.getPosition());
            setBrakeMode(true);
            setHighGear(false);
            isBaseLock = true; 
    }
	
	public synchronized void setBaseLockOff() {
        leftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        setBrakeMode(true);
        setHighGear(true);
        isBaseLock = true; 
	}
	
	public void turnToAngle(float angle) {
		turnController.setSetpoint(angle);
		turnController.enable();
	}
	
	public void setHighGear(boolean on) {
		if (on == true) {
			shifter.set(DoubleSolenoid.Value.kForward);
			isHighGear = true;
		}
		else if(on == false) {
			shifter.set(DoubleSolenoid.Value.kReverse);
			isHighGear = false;
		}
	}
	
	public void freeAngle() {
		turnController.disable();
	}
	
	public void control(Joystick steeringWheel) {
		if(steeringWheel.getZ() < 0.9) {
			magnitudeValue =  (-steeringWheel.getZ() +1) /2;
		}
		else {
			magnitudeValue = (steeringWheel.getY() - 1) / 2;
		}
	
		robot.drive(-(magnitudeValue), steeringWheel.getX());	
	}
	
	public boolean getBrake() {
		return isBrakeMode; 
	}
	
	public boolean isHighGear() {
		return isHighGear;
	}
	
	public boolean getBrakeMode() {
		return isBrakeMode;
	}
	
	public boolean isNavX() {
		return isNavX;
	}
	
	public void genPath(Waypoint[] points) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
		Trajectory trajectory = Pathfinder.generate(points, config);
		TankModifier modifier = new TankModifier(trajectory).modify(0.5);
		EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
		EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());
		
		left.configureEncoder(leftMaster.getEncPosition(), 1000, Constants.kWheelDiameter);
		right.configureEncoder(rightMaster.getEncPosition(), 1000, Constants.kWheelDiameter);
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("encoderLeft", leftMaster.getEncPosition());
		SmartDashboard.putNumber("encoderRight", rightMaster.getEncPosition());
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
	}
	
}
