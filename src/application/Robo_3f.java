package application;


import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.generated.ioAccess.Robotiq3FIOGroup;

public class Robo_3f extends Robotiq3FIOGroup {  
	public static final int gripperMaxWidth = 85;
	public static final int gripperMinWidth = 0;  
	public static final int gripperMaxHexPosition = 227;
	public static final int gripperMinHexPosition = 4;    
	public static final int maxSpeed = 255;
	public static final int avgSpeed = 128;
	public static final int lowSpeed = 15;
	public static final int minSpeed = 0;
	public static final int maxForce = 255;
	public static final int avgForce = 128;
	public static final int lowForce = 32;
	public static final int minForce = 1; 
	public static final int waitDelayMS = 50;
	public static final int initDelayMS = 500;
	//***************************************** 
	// Constructor  
	public Robo_3f(Controller controller) {
		super(controller);
		this.activate() ;
		
	}
	
	public void deactivate() {
		this.setAct_req(0); //"ActionRequest" byte <-- ‭0000 0000: rACT bit =  0
	} 
	//************************************************** 
	public void activate() {
		System.out.println("Activating Gripper..."); 
		this.setAct_req(1);//"ActionRequest" byte <-- ‭‭0000 0001 : rACT bit = 1‬
	}
	//************************************************** 
	

}