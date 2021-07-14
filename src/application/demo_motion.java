package application;


import java.util.concurrent.TimeUnit;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.conditionModel.ICallbackAction;
import com.kuka.roboticsAPI.conditionModel.ICondition;
import com.kuka.roboticsAPI.conditionModel.JointTorqueCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.JointEnum;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.PositionInformation;
import com.kuka.roboticsAPI.executionModel.IFiredTriggerInfo;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
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
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public class demo_motion extends RoboticsAPIApplication {
	private Controller kuka_Sunrise_Cabinet_1;
	private LBR lbr;
	private IMotionContainer mcSense, mcBreak;
	private ForceCondition forceWorldZ;
	private CartesianImpedanceControlMode impModeHard;
	
	public void initialize() {
		kuka_Sunrise_Cabinet_1 = getController("KUKA_Sunrise_Cabinet_1");
		lbr = (LBR) getDevice(kuka_Sunrise_Cabinet_1, "LBR_iiwa_7_R800_1");
		forceCond();
		impMode();
	}
	
	public void impMode(){

		impModeHard = new CartesianImpedanceControlMode();
		impModeHard.parametrize(CartDOF.X).setStiffness(5000);
		impModeHard.parametrize(CartDOF.Y).setStiffness(5000);
		impModeHard.parametrize(CartDOF.Z).setStiffness(5000);
		impModeHard.parametrize(CartDOF.A).setStiffness(300);
		impModeHard.parametrize(CartDOF.B).setStiffness(300);
		impModeHard.parametrize(CartDOF.C).setStiffness(300);
		impModeHard.parametrize(CartDOF.ALL).setDamping(0.9);
	}
	
	public void forceCond(){
		forceWorldZ = ForceCondition.createNormalForceCondition(lbr.getFrame("Frame"), World.Current.getRootFrame(), CoordinateAxis.Z, 25.0);
	}
	
	public void run() {
		sensitivityICallBack();
	}
		
	//***********************************   Sensitivity ICallBack   ************************************
	
		public void sensitivityICallBack(){
			
			ICallbackAction actionYDir = new ICallbackAction() {
				
				@Override
				public void onTriggerFired(IFiredTriggerInfo triggerInformation) {
					triggerInformation.getMotionContainer().cancel();
					PositionInformation trigPosInf = triggerInformation.getPositionInformation();
					Frame trigPos = trigPosInf.getCurrentCartesianPosition();
					Frame targetPos = triggerInformation.getPositionInformation().getCommandedCartesianPosition();
					getLogger().info("Break the robot by sensing collision.");
					getLogger().info("TriggPos: "+trigPos);
					getLogger().info("Push the tool in World Z direction to continue!");
					mcBreak = lbr.getFlange().move(positionHold(impModeHard, -1, TimeUnit.SECONDS).breakWhen(forceWorldZ));
					if (mcBreak.hasFired(forceWorldZ)){
						getLogger().info("Restart movement");
						getLogger().info("DestPos: "+targetPos);
						ThreadUtil.milliSleep(1000);
					}
				}
			};
			
				JointTorqueCondition condA1 = new JointTorqueCondition(JointEnum.J1, -3, 3);
				JointTorqueCondition condA2 = new JointTorqueCondition(JointEnum.J2, -3, 3);
				JointTorqueCondition condA3 = new JointTorqueCondition(JointEnum.J3, -5, 5);
				JointTorqueCondition condA4 = new JointTorqueCondition(JointEnum.J4, -5, 5);
				JointTorqueCondition condA5 = new JointTorqueCondition(JointEnum.J5, -5, 5);
				JointTorqueCondition condA6 = new JointTorqueCondition(JointEnum.J6, -5, 5);
				JointTorqueCondition condA7 = new JointTorqueCondition(JointEnum.J7, -5, 5);
				ICondition complexCond = condA1.or(condA2, condA3, condA4, condA5, condA6, condA7);
			
			while (true){
				//lbr.move(ptp(getApplicationData().getFrame("/Base/P1")).setJointVelocityRel(0.2));
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P1"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P1")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P2"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P2")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P3"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P3")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P4"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P4")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P5"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P5")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P6"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P6")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
				while (!lbr.getCurrentCartesianPosition(lbr.getFlange()).isCloseTo(getApplicationData().getFrame("/Base/P1"), 5, Math.toRadians(3))){
					mcSense = lbr.move(lin(getApplicationData().getFrame("/Base/P1")).setCartVelocity(77).triggerWhen(complexCond, actionYDir));
				}
			}
		}


	/**
	 * Auto-generated method stub. Do not modify the contents of this method.
	 */
	public static void main(String[] args) {
		demo_motion app = new demo_motion();
		app.runApplication();
	}
}
