package application;


import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.geometricModel.math.CoordinateAxis;
import com.kuka.roboticsAPI.geometricModel.math.CoordinateDirection;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.MotionBatch;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKey;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKeyBar;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKeyListener;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyAlignment;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyEvent;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyLED;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyLEDSize;

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
public class demo_motion extends RoboticsAPIApplication {
	@Inject
	private Controller myController;
	private LBR myLBR;
	private ObjectFrame myWorld;
	@Override
	public void initialize() {
		// initialize your application here
		myController = getController("KUKA_Sunrise_Cabinet_1");
		myLBR = (LBR) getDevice(myController,
				"LBR_iiwa_7_R800_1");
		myWorld = World.Current.getRootFrame();
	}

	@Override
	public void run() {
		// your application execution starts here
		IUserKeyBar stopBut = getApplicationUI().createUserKeyBar("Pause");
		IUserKeyListener listener=new IUserKeyListener() {
			
			@Override
			public void onKeyEvent(IUserKey key, UserKeyEvent event) {
				// TODO Auto-generated method stub
				if (event==UserKeyEvent.FirstKeyDown){
					getLogger().info("buton apasat");
				}
				
			}
		};
		IUserKey start_stop = stopBut.addDoubleUserKey(0, listener, true);
		start_stop.setText(UserKeyAlignment.TopMiddle, "On");
		start_stop.setLED(UserKeyAlignment.Middle, UserKeyLED.Grey,
				UserKeyLEDSize.Small);
		stopBut.publish();
		int res=0;
		getLogger().info("Starting FORCE DEMO");
		myLBR.move(ptpHome());
		do  {
			res=getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Pleaase select action", "Exit", "Demo torq");
			switch (res) {
			case 0:
				getLogger().info("Program terminated-exit");
				break;
			case 1:
				getLogger().info("Running demo application");
				demotrq();
				break;
				default :
					getLogger().info("No action selected");
					break;
			}
		} while (0!=res);
	}
	private void demotrq() {
		int res2=0;
		ForceCondition condf1=ForceCondition.createSpatialForceCondition(myWorld, 1);
		
		IMotionContainer mc= myLBR.move(ptp(getApplicationData().getFrame("/P201")).setJointVelocityRel(0.5).breakWhen(condf1));
//		if (mc.hasFired(condf1)){
//			res2=getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "Pleaase select action", "Exit", "Continue");
//			switch (res2) {
//			case 0:
//				getLogger().info("Program terminated-exit");
//				break;
//			case 1:
//				getLogger().info("continue");
//			
//				default :
//					getLogger().info("No action selected");
//					break;
//			}
//		}
		MotionBatch ntm =new MotionBatch(
				ptp(getApplicationData().getFrame("/P300")),
				ptp(getApplicationData().getFrame("/P301")),
				ptp(getApplicationData().getFrame("/P302")),
				ptp(getApplicationData().getFrame("/P303")),
				ptp(getApplicationData().getFrame("/P304")),
				ptp(getApplicationData().getFrame("/P305")),
				ptp(getApplicationData().getFrame("/P306")),
				ptp(getApplicationData().getFrame("/P307")),
				ptp(getApplicationData().getFrame("/P308")),
				ptp(getApplicationData().getFrame("/P309")),
				ptp(getApplicationData().getFrame("/P310"))
				);
	
	}
	

}