package org.usfirst.frc.team1414.subsystems;
import com.ctre.CANTalon;
import org.usfirst.frc.team1414.robot.Constants;

public class Intake extends Subsystem {

	private final CANTalon intake;
	
	public Intake() {
		intake = new CANTalon(Constants.kIntakeId);
	}
	
	public void forwardIntake() {
		intake.set(1.0);
	}
	
	public void reverseIntake() {
		intake.set(-1.0);
	}
	
	public void stopIntake() {
		intake.set(0.0);
	}
	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		
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
