package hu.bme.mit.train.sensor;

import com.sun.org.apache.regexp.internal.RE;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
		user.setAlarmState(AbsoluteMargin() || Relativemargin());
	}

	@Override
	public boolean AbsoluteMargin(){
		return (speedLimit < 0 || speedLimit > 500);
	}

	@Override
	public boolean Relativemargin(){
		return (speedLimit < controller.getReferenceSpeed()/2);
	}
}
