package org.usfirst.frc.team1414.subsystems;

import com.ctre.CANTalon;
import org.usfirst.frc.team1414.subsystems.Intake;

public class Climber extends Subsystem {

	public CANTalon climberMotorMaster, climberMotorSlave;
	

	
	public Climber() {
		climberMotorMaster = new CANTalon(3);
		climberMotorSlave = new CANTalon(9);
		
	}
	
	public void startClimb() {
		climberMotorMaster.set(-1.0);
		climberMotorSlave.set(1.0);

	}
	
	public void stopClimb() {
		climberMotorMaster.set(0.0);
		climberMotorSlave.set(0.0);
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
