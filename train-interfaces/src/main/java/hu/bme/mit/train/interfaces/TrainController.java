package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();
	
	void tFollowspeedRun(boolean run);

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

}
