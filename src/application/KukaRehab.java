package application;


import javax.inject.Inject;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.MotionBatch;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

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
	private IMotionContainer mc;

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
	
		
    Abduction_amp=Abduction_amp=0;
int flex=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select Abduction angle", "5", "10", "15","20","25","30","35","40","45","Exit");
		switch (flex){
		case 0:{Abduction_amp=5;break;
		}
		case 1:{Abduction_amp=10;break;
		}
		case 2:{Abduction_amp=15;break;
		}
		case 3:{Abduction_amp=20;break;
		}
		case 4:{Abduction_amp=25;break;
		}
		case 5:{Abduction_amp=30;break;
		}
		case 6:{Abduction_amp=35;break;
		}
		case 7:{Abduction_amp=40;break;
		}
		case 8:{Abduction_amp=45;break;
		}
		case 9:break;
		}
		
		if (Abduction_amp!=0){
		int dorsi=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select Adduction angle", "5", "10", "15","20","25","30","35","40","45","Exit");
		switch (dorsi){
		case 0:{Adduction_amp=5;break;
		}
		case 1:{Adduction_amp=10;break;
		}
		case 2:{Adduction_amp=15;break;
		}
		case 3:{Adduction_amp=20;break;
		}
		case 4:{Adduction_amp=25;break;
		}
		case 5:{Adduction_amp=30;break;
		}
		case 6:{Adduction_amp=35;break;
		}
		case 7:{Adduction_amp=40;break;
		}
		case 8:{Adduction_amp=45;break;
		}
		case 9:break;
		}
	
		
		
		if (Abduction_amp!=0 && Adduction_amp!=0){
			getLogger().info("Abduction = "+Abduction_amp+ "Adduction = " +Adduction_amp);
			
			myAnkleTcp.move(linRel(0, 0, 0, (Adduction_amp*Math.PI/180), 0,0));
			
		for (int i=0;i<5;i++){
			myAnkleTcp.move(linRel(0, 0, 0,  -((Adduction_amp+Abduction_amp)*Math.PI/180), 0,0));
			myAnkleTcp.move(linRel(0, 0, 0,  ((Adduction_amp+Abduction_amp)*Math.PI/180), 0,0));
			}
		myAnkleTcp.move(linRel(0, 0, 0, -(Adduction_amp*Math.PI/180), 0,0));
		}
				
	}
		
	}

	private void myInversionEversion() {
		
		Inversion_amp=0;
		int flex=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select Inversion angle", "5", "10", "15","20","Exit");
		switch (flex){
		case 0:{Inversion_amp=5;break;
		}
		case 1:{Inversion_amp=10;break;
		}
		case 2:{Inversion_amp=15;break;
		}
		case 3:{Inversion_amp=20;break;
		}
		case 4:break;
		}
		
		if (Inversion_amp!=0){
		int dorsi=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select dorsiflexion angle", "5", "10", "15","20","�xit");
		switch (dorsi){
		case 0:{Eversion_amp=5;
		break;
		}
		case 1:{Eversion_amp=10;break;
		}
		case 2:{Eversion_amp=15;break;
		}
		case 3:{Eversion_amp=20;break;
		}
		
		case 4:break;}
			
		
		if (Inversion_amp!=0 && Eversion_amp!=0){
			getLogger().info("Inversion = "+Inversion_amp+ "Eversion = " +Eversion_amp);
			
			myAnkleTcp.move(linRel(0, 0, 0, 0,0,(Inversion_amp*Math.PI/180)));
			
		for (int i=0;i<5;i++){
			myAnkleTcp.move(linRel(0, 0, 0, 0,0, -((Inversion_amp+Eversion_amp)*Math.PI/180)));
			myAnkleTcp.move(linRel(0, 0, 0, 0,0, ((Inversion_amp+Eversion_amp)*Math.PI/180)));
			}
		myAnkleTcp.move(linRel(0, 0, 0, 0,0,(Inversion_amp*Math.PI/180)));
		}
				
	}
		
	}

	private void myFlexionDorsiflexion() {
		Flexion_amp=0;
		int flex=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select flexion angle", "5", "10", "15","20","25","30","Exit");
		switch (flex){
		case 0:{Flexion_amp=5;break;
		}
		case 1:{Flexion_amp=10;break;
		}
		case 2:{Flexion_amp=15;break;
		}
		case 3:{Flexion_amp=20;break;
		}
		case 4:{Flexion_amp=25;break;
		}
		case 5:{Flexion_amp=30;break;
		}
		case 6:break;
		}
		
		if (Flexion_amp!=0){
		int dorsi=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select dorsiflexion angle", "5", "10", "15","20","25","30","�xit");
		switch (dorsi){
		case 0:{Dorsiflexion_amp=5;
		break;
		}
		case 1:{Dorsiflexion_amp=10;break;
		}
		case 2:{Dorsiflexion_amp=15;break;
		}
		case 3:{Dorsiflexion_amp=20;break;
		}
		case 4:{Dorsiflexion_amp=25;break;
		}
		case 5:{Dorsiflexion_amp=30;break;
		}
		case 6:break;}
		
	
		
		//myLBR.move(linRel(Transformation.ofDeg(10, 10, 10, 10, 0, 0),getApplicationData().getFrame("/Mount_patient")));
		if (Flexion_amp!=0 && Dorsiflexion_amp!=0){
			getLogger().info("Flexion = "+Flexion_amp+ "Dorsifelxion = " +Dorsiflexion_amp);
			
			myAnkleTcp.move(linRel(0, 0, 0, 0, (Flexion_amp*Math.PI/180), 0));
			
		for (int i=0;i<5;i++){
			myAnkleTcp.move(linRel(0, 0, 0, 0, -((Flexion_amp+Dorsiflexion_amp)*Math.PI/180), 0));
			myAnkleTcp.move(linRel(0, 0, 0, 0, ((Flexion_amp+Dorsiflexion_amp)*Math.PI/180), 0));
			}
		myAnkleTcp.move(linRel(0, 0, 0, 0, -(Flexion_amp*Math.PI/180), 0));
		}
				
	}
		}
		 
		
	
	}
