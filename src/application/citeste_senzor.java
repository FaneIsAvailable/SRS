package application;


import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.sensorModel.ForceSensorData;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a 
 * {@link RoboticsAPITask#run()} method, which will be called successively in 
 * the application lifecycle. The application will terminate automatically after 
 * the {@link RoboticsAPITask#run()} method has finished or after stopping the 
 * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an 
 * exception is thrown during initialization or run. 
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the 
 * {@link RoboticsAPITask#dispose()} method.</b> 
 * 
 * @see UseRoboticsAPIContext
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public class citeste_senzor extends RoboticsAPIApplication {
	@Inject
	private Controller Kuka_Sunrise_Cabinet_1;
	private LBR lBR_iiwa_7_R800_1;
	ForceSensorData allforce;

	@Override
	public void initialize() {
		// initialize your application here
		Kuka_Sunrise_Cabinet_1 = getController("KUKA_Sunrise_Cabinet_1");
		lBR_iiwa_7_R800_1 = (LBR) getDevice(Kuka_Sunrise_Cabinet_1, "LBR_iiwa_7_R800_1");
		
	}

	@Override
	public void run() {
		// your application execution starts here
		allforce=lBR_iiwa_7_R800_1.getExternalForceTorque(lBR_iiwa_7_R800_1.getFlange());
		getLogger().info(allforce.getForce().toString()+allforce.getTorque().toString());
	}
}