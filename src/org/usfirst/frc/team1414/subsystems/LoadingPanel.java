package org.usfirst.frc.team1414.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;

	
public class LoadingPanel extends Subsystem {
	
	public DoubleSolenoid loadingSolenoid;
	private boolean isExtended = false;
	
	public LoadingPanel() {
		loadingSolenoid = new DoubleSolenoid(2,3);
	}
	
	public void extendPanel() {
		loadingSolenoid.set(DoubleSolenoid.Value.kForward);
		
	}
	
	public void retractPanel() {
		loadingSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void outputToSmartDashboard() {
		
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
