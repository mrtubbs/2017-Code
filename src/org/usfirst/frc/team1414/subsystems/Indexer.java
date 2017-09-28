package org.usfirst.frc.team1414.subsystems;

import com.ctre.CANTalon;
import org.usfirst.frc.team1414.robot.Constants;


public class Indexer extends Subsystem {

	CANTalon indexer;
	
	public Indexer() {
		indexer = new CANTalon(Constants.kIndexerId);
	}
	
	public void startIndexer() {
		indexer.set(Constants.kIndexerSpeed);
	}
	
	public void reverseIndexer() {
		indexer.set(-Constants.kIndexerSpeed);
	}
	
	public void stopIndexer() {
		indexer.set(0.0);
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
