package application;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.generated.ioAccess.Robotiq3FIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.conditionModel.ForceComponentCondition;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.conditionModel.ICallbackAction;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.PositionInformation;
import com.kuka.roboticsAPI.executionModel.IFiredTriggerInfo;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.geometricModel.math.CoordinateAxis;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;

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
public class RunAway extends RoboticsAPIApplication {
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
	private Frame currentPos;
	private Frame commandPos;
	

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
		rr.close_full();
		cmode=new CartesianImpedanceControlMode();
		cmode.parametrize(CartDOF.Z).setStiffness(3000);
		cmode.parametrize(CartDOF.X, CartDOF.Y).setStiffness(3000);
		
		}

	@Override
	public void run() {
		// your application execution starts here
		lBR_iiwa_7_R800_1.move(ptpHome());
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Puct1")));
		ICallbackAction getPosaction =new ICallbackAction() {
			
			@Override
			public void onTriggerFired(IFiredTriggerInfo triggerInformation) {
				// TODO Auto-generated method stub
				PositionInformation posinfo=triggerInformation.getPositionInformation();
				currentPos=posinfo.getCurrentCartesianPosition();
				commandPos=posinfo.getCommandedCartesianPosition();
				
			}
		};
		//acu.attachTo(lBR_iiwa_7_R800_1.getFlange());
		
		ForceCondition forta_Z=ForceCondition.createNormalForceCondition(tcpAc,myWorld, CoordinateAxis.Z, 10);
		ForceCondition forta_Y=ForceCondition.createNormalForceCondition(tcpAc, myWorld,CoordinateAxis.Y, 10);
		ForceCondition forta_X=ForceCondition.createNormalForceCondition(tcpAc, myWorld,CoordinateAxis.X, 10);
		
		//tcpAc.move(linRel(0,0,50).setMode(cmode).triggerWhen(forta_Z, getPosaction));
		
		IMotionContainer mc= tcpAc.move(positionHold(cmode, 0, TimeUnit.SECONDS).breakWhen(forta_Z).breakWhen(forta_X).breakWhen(forta_Y));
		getLogger().info("Forta:"+myLBR.getExternalForceTorque(tcpAc, myWorld).getForce().getZ());
		 if (mc.hasFired(forta_Z)){
			 getLogger().info("gata:nuu");
			 tcpAc.move(linRel(0,0,50));
			 getLogger().info("gata:");
			 }
	
	}
	
}
