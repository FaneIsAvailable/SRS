package backgroundTask;


import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.tasks.CycleBehavior;
import com.kuka.roboticsAPI.applicationModel.tasks.RoboticsAPICyclicBackgroundTask;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.sensorModel.ForceSensorData;

/**
 * Implementation of a cyclic background task.
 * <p>
 * It provides the {@link RoboticsAPICyclicBackgroundTask#runCyclic} method 
 * which will be called cyclically with the specified period.<br>
 * Cycle period and initial delay can be set by calling 
 * {@link RoboticsAPICyclicBackgroundTask#initializeCyclic} method in the 
 * {@link RoboticsAPIBackgroundTask#initialize()} method of the inheriting 
 * class.<br>
 * The cyclic background task can be terminated via 
 * {@link RoboticsAPICyclicBackgroundTask#getCyclicFuture()#cancel()} method or 
 * stopping of the task.
 * @see UseRoboticsAPIContext
 * 
 */
public class ReadTorque extends RoboticsAPICyclicBackgroundTask {
	@Inject
	private Controller Kuka_Sunrise_Cabinet_1;
	private LBR lbr;
	ForceSensorData allforce;


	@Override
	public void initialize() {
		Kuka_Sunrise_Cabinet_1 = getController("KUKA_Sunrise_Cabinet_1");
		lbr = (LBR) getDevice(Kuka_Sunrise_Cabinet_1, "LBR_iiwa_7_R800_1");
		
		// initialize your task here
		initializeCyclic(0, 100, TimeUnit.MILLISECONDS,
				CycleBehavior.BestEffort);
	}

	@Override
	public void runCyclic() {
		// your task execution starts here
		
		allforce=lbr.getExternalForceTorque(lbr.getFlange());
		getLogger().info(allforce.getForce().toString()+allforce.getTorque().toString());
		
}
}