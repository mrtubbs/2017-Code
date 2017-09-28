package org.usfirst.frc.team1414.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1414.robot.Constants;

public class Shooter extends Subsystem {
	protected static final int kVelocityControlShooterSlot = 0;

	private final CANTalon shooterMotorMaster;
	private final CANTalon shooterMotorSlave;
	
	public Shooter() {
		shooterMotorMaster = new CANTalon(Constants.kShooterMotorMasterId);
		shooterMotorSlave = new CANTalon(Constants.kShooterMotorSlaveId);
		shooterMotorMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterMotorMaster.reverseSensor(false);
		
		shooterMotorSlave.changeControlMode(TalonControlMode.Follower);
		shooterMotorSlave.set(Constants.kShooterMotorMasterId);
		shooterMotorMaster.setPID(Constants.kShooterP, Constants.kShooterI, Constants.kShooterD, Constants.kShooterF, 
				Constants.kShooterIZone, Constants.kShooterRampRate, kVelocityControlShooterSlot);
		
		
		shooterMotorMaster.setProfile(kVelocityControlShooterSlot);
		shooterMotorMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
		shooterMotorMaster.enableBrakeMode(false);
		shooterMotorSlave.enableBrakeMode(false);
		
		shooterMotorMaster.clearStickyFaults();
		shooterMotorSlave.clearStickyFaults();
	}
	
	public synchronized double getRpm() {
		return shooterMotorMaster.getSpeed();
	}
	
	public synchronized double getSetpoint() {
		return shooterMotorMaster.getSetpoint();
	}
	
	public synchronized void setRpm(double rpm) {
		shooterMotorMaster.changeControlMode(TalonControlMode.Speed);
		shooterMotorMaster.set(rpm);
	}
	
	public synchronized void setOpenLoop(double speed) {
		shooterMotorMaster.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotorMaster.set(speed);
	}
	
	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("shooterEncoder", shooterMotorMaster.getSpeed());

	}
	
	public synchronized boolean isOnTarget() {
        return (shooterMotorMaster.getControlMode() == CANTalon.TalonControlMode.Speed
                && Math.abs(getRpm() - getSetpoint()) < Constants.kFlywheelOnTargetTolerance);
    }

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
	}
	
}
