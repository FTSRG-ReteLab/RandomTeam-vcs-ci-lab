package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainSensorImpl trainSensor;
    TrainController trainController;
    TrainUser trainUser;

    @Before
    public void before() {
        trainUser = mock(TrainUser.class);
        trainController = mock(TrainController.class);
        trainSensor = new TrainSensorImpl(trainController, trainUser);
    }

    @Test
    public void SetSpeedLimit_Test(){
        trainSensor.overrideSpeedLimit(100);
        verify(trainController,times(1)).setSpeedLimit(100);
    }

    @Test
    public void SpeedLimitOver500_Test() {
        trainSensor.overrideSpeedLimit(9001);
        verify(trainUser,times(1)).setAlarmState(true);
    }

    @Test
    public void SpeedLimitNegative_Test(){
        trainSensor.overrideSpeedLimit(-100);
        verify(trainUser,times(1)).setAlarmState(true);
    }

    @Test
    public void RelativeMarginTest(){
        when(trainController.getReferenceSpeed()).thenReturn(100);
        trainSensor.overrideSpeedLimit(25);
        verify(trainUser,times(1)).setAlarmState(true);
    }
}
