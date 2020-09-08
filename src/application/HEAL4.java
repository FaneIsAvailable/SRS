package application;


import javax.inject.Inject;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;

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


public class HEAL4 extends RoboticsAPIApplication {
	@Inject
	private LBR lBR_iiwa_7_R800_1;
	
	private Controller myController;
	private LBR myLBR;
	private ObjectFrame myWorld;
	private Tool acuHEAL;
	private ObjectFrame tcpAcHEAL;

	@Override
	public void initialize() {
		myController = getController("KUKA_Sunrise_Cabinet_1");
		myLBR = (LBR) getDevice(myController,
				"LBR_iiwa_7_R800_1");
		myWorld = World.Current.getRootFrame();
		myWorld = World.Current.getRootFrame();
		acuHEAL=getApplicationData().createFromTemplate("acuHEAL");
		tcpAcHEAL=acuHEAL.getFrame("tcpAcHEAL");
		acuHEAL.attachTo(myLBR.getFlange());
		// initialize your application here
	}

	@Override
	public void run() {
		// your application execution starts here
		lBR_iiwa_7_R800_1.move(ptpHome());
		int res;
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Punct_insertie_HEAL")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/P1")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/P2")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/P3")));
		ThreadUtil.milliSleep(10000);
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/P2")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/P1")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Punct_insertie_HEAL")));
		lBR_iiwa_7_R800_1.move(ptpHome());
	}
}