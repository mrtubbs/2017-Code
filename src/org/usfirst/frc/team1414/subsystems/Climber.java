package org.usfirst.frc.team1414.subsystems;

import com.ctre.CANTalon; //importing stuff
import org.usfirst.frc.team1414.subsystems.Intake; //and more stuff

public class Climber extends Subsystem {

	public CANTalon climberMotorMaster, climberMotorSlave; //public thing
	

	
	public Climber() { //function to climb
		climberMotorMaster = new CANTalon(3); //start motor instance
		climberMotorSlave = new CANTalon(9); //start motor slave instance
		
	}
	
	public void startClimb() {
		climberMotorMaster.set(-1.0); //start motor
		climberMotorSlave.set(1.0); //start motor

	}
	
	public void stopClimb() {
		climberMotorMaster.set(0.0); //stop motor
		climberMotorSlave.set(0.0);//stop motor
	}
	
	@Override
	public void outputToSmartDashboard() { //output with nothing to do
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() { //no stop
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zeroSensors() { //nothing
		// TODO Auto-generated method stub
		
	}
	
}
