package application;


import javax.inject.Inject;

import com.kuka.common.ThreadUtil;
import com.kuka.generated.ioAccess.Robotiq3FIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKey;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKeyBar;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKeyListener;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyAlignment;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyEvent;

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
	private Robo_3f rr;
	private CartesianImpedanceControlMode cmode;    
	
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
		rr= new Robo_3f(myController);
		rr.deactivate();
		ThreadUtil.milliSleep(1000);
		rr.activate();
		
      
	}
	@Override
	public void run() {
		// your application execution starts here
		myLBR.move(ptpHome());
		int result=0;
		getLogger().info("Starting BlueGear");
		getLogger().info("grip");	
		myLBR.move(ptp(getApplicationData().getFrame("/Langa_deasupra")));
		myLBR.move(lin(getApplicationData().getFrame("/Langa_jos")));
		myLBR.move(lin(getApplicationData().getFrame("/Prindere")));
		
		rr.close_full();
		ThreadUtil.milliSleep(10000);
		myLBR.move(lin(getApplicationData().getFrame("/Ridicare_1")));
		myLBR.move(ptp(getApplicationData().getFrame("/Ridicare_impedance")));
		cmode=new CartesianImpedanceControlMode();
		cmode.parametrize(CartDOF.Z).setStiffness(3000);
		cmode.parametrize(CartDOF.X, CartDOF.Y).setStiffness(100);
				myLBR.move(lin(getApplicationData().getFrame("/Ridicare_impedance")).setMode(cmode));
		getLogger().info("ddd");	
		
		
	}
		
}