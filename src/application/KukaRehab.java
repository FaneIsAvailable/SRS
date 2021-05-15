package application;


import javax.inject.Inject;
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
public class KukaRehab extends RoboticsAPIApplication {
	@Inject
	private LBR lBR_iiwa_7_R800_1;
	private Controller myController;
	private LBR myLBR;
	private ObjectFrame myWorld;
	private Tool myAnkle;
	private ObjectFrame myAnkleTcp;
	private int Flexion_amp;
	private int Dorsiflexion_amp;
	private int Inversion_amp;
	private int Eversion_amp;
	private int Adduction_amp;
	private int Abduction_amp;

	@Override
	public void initialize() {
		// initialize your application here
		myController = getController("KUKA_Sunrise_Cabinet_1");
		myLBR = (LBR) getDevice(myController,"LBR_iiwa_7_R800_1");
		myWorld = World.Current.getRootFrame();
		myAnkle=getApplicationData().createFromTemplate("Kuka_ankle_tool");
		myAnkleTcp=myAnkle.getFrame("Ankle_TCP");
		myAnkle.attachTo(myLBR.getFlange());
	}

	@Override
	public void run() {
		// your application execution starts here
		lBR_iiwa_7_R800_1.move(ptpHome());
		
		myAnkleTcp.move(ptp(getApplicationData().getFrame("/Mount_patient")));
		myMainMenu();
		
		}
	public void myMainMenu(){
		int resp=0;
		do {
			resp=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select action", "Perform flexion/dorsiflexion", "Perform inversion/eversion", "Perform adducion/abduction","Exit");
			switch (resp){
			case 0:{
				getLogger().info("Perform Flexion/Dorsiflexion");
				myFlexionDorsiflexion();
				break;}
			case 1:{
				getLogger().info("Perform Inversion/Eversion");
				myInversionEversion();
				break;}
			case 2:{
				getLogger().info("Perform Adduction/Abduction");
				myAdductionAbduction();
				break;}
			case 3:{
				getLogger().info("Exiting");
				break;}
			}
		}
		while (3!=resp);
		
	}

	private void myAdductionAbduction() {
	
		
		
		// TODO Auto-generated method stub
		
	}

	private void myInversionEversion() {
		myMainMenu();
		// TODO Auto-generated method stub
		
	}

	private void myFlexionDorsiflexion() {
		Flexion_amp=0;
		int flex=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select flexion angle", "5", "10", "15","20","25","30","Exit");
		switch (flex){
		case 0:Flexion_amp=5;
		case 1:Flexion_amp=10;
		case 2:Flexion_amp=15;
		case 3:Flexion_amp=20;
		case 4:Flexion_amp=25;
		case 5:Flexion_amp=30;
		case 6:myMainMenu();
		}
		
		if (Flexion_amp!=0){
		int dorsi=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select dorsiflexion angle", "5", "10", "15","20","25","30","Ëxit");
		switch (dorsi){
		case 0:Dorsiflexion_amp=5;
		case 1:Dorsiflexion_amp=10;
		case 2:Dorsiflexion_amp=15;
		case 3:Dorsiflexion_amp=20;
		case 4:Dorsiflexion_amp=25;
		case 5:Dorsiflexion_amp=30;
		case 6:myMainMenu();}
		// TODO Auto-generated method stub
		
	}
	
	}}
