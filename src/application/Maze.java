package application;


import java.io.PrintWriter;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.conditionModel.BooleanIOCondition;
import com.kuka.roboticsAPI.conditionModel.ForceComponentCondition;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.conditionModel.ICondition;
import com.kuka.roboticsAPI.conditionModel.IConditionListener;
import com.kuka.roboticsAPI.conditionModel.MotionCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.controllerModel.IControllerStateListener;
import com.kuka.roboticsAPI.controllerModel.sunrise.SunriseExecutionService;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.Workpiece;
import com.kuka.roboticsAPI.geometricModel.math.ITransformation;
import com.kuka.roboticsAPI.ioModel.AbstractIO;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.IMotionOverlay;
import com.kuka.roboticsAPI.motionModel.MotionBatch;
import com.kuka.roboticsAPI.motionModel.PositionHold;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.sensorModel.DataRecorder;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.roboticsAPI.motionModel.HandGuidingMotion;
import com.kuka.roboticsAPI.params.MotionOverlayParameter;
import com.kuka.roboticsAPI.persistenceModel.IPersistenceEngine;
import com.kuka.roboticsAPI.persistenceModel.XmlApplicationDataSource;
import com.kuka.roboticsAPI.persistenceModel.templateModel.FramesTemplate;
import com.kuka.common.ThreadUtil;
import com.kuka.generated.ioAccess.MediaFlangeIOGroup;


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
public class Maze extends RoboticsAPIApplication {
	@Inject
	private LBR myLbr;
	private Controller myController;
	private Tool mySphereTip;
	private Tool myDullTip;
	MediaFlangeIOGroup ioFlange;
	//	private static final int stiffnessZ = 3500;
//	private static final int stiffnessY = 500;
//	private static final int stiffnessX = 500;
	private MediaFlangeIOGroup led;
    public Frame[] pozes=new Frame[100];
    private ICondition ic;
    private BooleanIOCondition bc;
    private IMotionContainer mc;
    public int i=0;
    private long nou,acum;
    private DataRecorder datarec;
    private ObjectFrame sphere_TIP;
   
  
    

	@Override
	public void initialize() {
			
	datarec=new DataRecorder("datas.txt", 100, TimeUnit.MILLISECONDS, 100);
	
		// initialize your application here
		myController = getController("KUKA_Sunrise_Cabinet_1");
		myLbr.getContext().getDeviceFromType(LBR.class);
	led=new MediaFlangeIOGroup(myController);
	mySphereTip=getApplicationData().createFromTemplate("SphereTip");
	sphere_TIP=mySphereTip.getFrame("sph_tip");	
	AbstractIO gb=led.getInput("UserButton");
	//BooleanIOCondition bc=new BooleanIOCondition(gb, true);
 
	
	}

	@Override
	public void run() {
		// your application execution starts here
		Frame[] pozes=new Frame[100];	
		getLogger().info(led.toString());
		//led.setLEDBlue(false);
		int resp=0;
		getLogger().info("Going Home");
		myLbr.move(ptpHome());
		do {
			resp=getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, "What TODO", "Exit", "Solo Maze", "Play MazeEScape","Manual _tech");
			switch (resp){
			case 0: {
				getLogger().info("Exit");
				break;}
			case 1:{
				getLogger().info("Look mama no hands");
				do_solo_small_maze();
					break;}
			case 2:{
				getLogger().info("Lets Play");
				learn_points();
				break;}
			case 3 :{
				getLogger().info("Slave mode");
				teach_points();
				break;
			}
			default:{
				getLogger().info("Nothing to do here");
				break;}
						
			}
		}while (0!=resp);
		}  
	public void teach_points(){
		mySphereTip.attachTo(myLbr.getFlange());
		sphere_TIP.move(ptp(getApplicationData().getFrame("/PAboveStart")));
		sphere_TIP.move(lin(getApplicationData().getFrame("/PStart")));
		getLogger().info("i= "+i);
		getLogger().info(pozes[i].toString());
		getLogger().info("Press Green Buton to Start");
		while (!led.getUserButton()){
	    	  ThreadUtil.milliSleep(100);
	       }
		ThreadUtil.milliSleep(1500);
			
		for (int j=0;j<i;j++){
			sphere_TIP.move(lin(pozes[j]));
		}
		sphere_TIP.move(linRel(0, 0, -50));
		//sphere_TIP.move(ptp(getApplicationData().getFrame("/Small_Maze/PStart")));
		sphere_TIP.move(ptpHome());
	}
	public void learn_points(){
		mySphereTip.attachTo(myLbr.getFlange());
		//HandGuidingMotion handguide=new HandGuidingMotion();
		//mySphereTip.attachTo(myLbr.getFlange());
	    sphere_TIP.move(ptp(getApplicationData().getFrame("/PAboveStart")));
		sphere_TIP.move(lin(getApplicationData().getFrame("/PStart")));
		getLogger().info("Press Green Buton to Start");
		while (!led.getUserButton()){
	    	  ThreadUtil.milliSleep(100);
	       }
		ThreadUtil.milliSleep(1500);
		getLogger().info("Play");
		i=0;
		  AbstractIO ab=led.getInput("UserButton");
	       BooleanIOCondition bcc=new BooleanIOCondition(ab,true);
	      
	     	CartesianImpedanceControlMode ctrMode = new CartesianImpedanceControlMode();
	     	ctrMode.parametrize(CartDOF.X,CartDOF.Y).setStiffness(15);
	        PositionHold posHold = new PositionHold(ctrMode, -1, null).breakWhen(bcc);
		do {
		nou=System.currentTimeMillis();
	    led.setLEDBlue(true);
	           
	            	   mc= sphere_TIP.move(posHold);
	   
	   if (mc.hasFired(bcc))
	   {
		   acum=System.currentTimeMillis();
		   mc.cancel();
		   bcc.invert();
		  // pozes[i]=myLbr.getCurrentCartesianPosition(myLbr.getFlange());
	    	// getLogger().info(pozes[i].toString());
	    	 led.setLEDBlue(false);
			 //  led.setLEDBlue(true);
		  sphere_TIP.move(ptp(myLbr.getCurrentCartesianPosition(sphere_TIP)));
		     pozes[i]=myLbr.getCurrentCartesianPosition(sphere_TIP);
		    // datarec.addCurrentCartesianPositionXYZ(getApplicationData().getFrame("/Small_Maze"), getApplicationData().getFrame("/Small_Maze"));
		    // datarec.startRecording();
		   // datarec.addCommandedCartesianPositionXYZ(mySphereTip.getFrame("dul_tip" ), getApplicationData().getFrame("Small_Maze"));
		    // datarec.stopRecording();
		     i=i+1;
	    	 }
	   
		}while(acum-nou>800);
		   		   
	    
	 sphere_TIP.move(ptpHome());
	   
	    	   		
		
	}
	public void do_solo_small_maze(){
		mySphereTip.attachTo(myLbr.getFlange());
		sphere_TIP.move(ptp(getApplicationData().getFrame("/PAboveStart")));
		sphere_TIP.move(lin(getApplicationData().getFrame("/PStart")));
		MotionBatch mc= new MotionBatch(
				lin(getApplicationData().getFrame("/P1001")).setCartVelocity(10),
				lin(getApplicationData().getFrame("/P1002")),
				lin(getApplicationData().getFrame("/P1003")),
				lin(getApplicationData().getFrame("/P1004")),
				lin(getApplicationData().getFrame("/P1005")),
				lin(getApplicationData().getFrame("/P1006")),
				lin(getApplicationData().getFrame("/P1007")),
				lin(getApplicationData().getFrame("/P1008")),
				lin(getApplicationData().getFrame("/P1009")),
				lin(getApplicationData().getFrame("/P1010")),
				lin(getApplicationData().getFrame("/P1011")),
				lin(getApplicationData().getFrame("/P1012")),
				lin(getApplicationData().getFrame("/P1013")),
				lin(getApplicationData().getFrame("/P1014")),
				lin(getApplicationData().getFrame("/P1015")),
				lin(getApplicationData().getFrame("/P1016")),
				lin(getApplicationData().getFrame("/P1017")),
				lin(getApplicationData().getFrame("/P1018")),
				lin(getApplicationData().getFrame("/P1019")),
				lin(getApplicationData().getFrame("/P1020")),
				lin(getApplicationData().getFrame("/P1021"))			
								);
		sphere_TIP.move(mc);
	   sphere_TIP.move(linRel(0, 0, -20));
		sphere_TIP.move(ptpHome());
		mySphereTip.detach();
	}
}