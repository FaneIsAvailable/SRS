package application;


import javax.inject.Inject;

import com.kuka.generated.ioAccess.Robotiq3FIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

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
public class PushDa extends RoboticsAPIApplication {
	@Inject
	private LBR lBR_iiwa_7_R800_1;
	private Controller myController;
	private LBR myLBR;
	private ObjectFrame myWorld;
	private Tool acu;
	private ObjectFrame tcpAc;
	private Robotiq3FIOGroup robo;

	@Override
	public void initialize() {
		// initialize your application here
		myController = getController("KUKA_Sunrise_Cabinet_1");
		myLBR = (LBR) getDevice(myController,
				"LBR_iiwa_7_R800_1");
		myWorld = World.Current.getRootFrame();
		acu=getApplicationData().createFromTemplate("DaNeedle");
		tcpAc=acu.getFrame("DaTCP");
		acu.attachTo(myLBR.getFlange());
		
	

	}

	@Override
	public void run() {
		// your application execution starts here
		myLBR.move(ptpHome());
		int result=0;
		getLogger().info("Starting BlueGear");
		robo.setAct_req(0);

		
	}

	private void zece() {
		// TODO Auto-generated method stub
	for (int i=0;i<=10;i++){
	
		getLogger().info(""+i);
		getLogger().info("target");
		tcpAc.move(lin(getApplicationData().getFrame("/Target")));
		getLogger().info("ins/extract");
		tcpAc.move(lin(getApplicationData().getFrame("/Insertion")));
	}
	}

	private void extract() {
		// TODO Auto-generated method stub
		getLogger().info("ins/extract");
		tcpAc.move(lin(getApplicationData().getFrame("/Insertion")));
	}

	private void movetarg() {
		// TODO Auto-generated method stub
		getLogger().info("target");
		tcpAc.move(lin(getApplicationData().getFrame("/Target")));
		
	}

	private void moveins() {
		// TODO Auto-generated method stub
		getLogger().info("ins");
		tcpAc.move(lin(getApplicationData().getFrame("/Insertion")));
		
	}

	private void movepreins() {
		// TODO Auto-generated method stub
		getLogger().info("preins");
		tcpAc.move(ptp(getApplicationData().getFrame("/preinsertion1")));
		tcpAc.move(ptp(getApplicationData().getFrame("/preinsertion")));
		
	}
}