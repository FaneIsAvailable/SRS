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
	/*  0000 0000
	 * 1st bit  = activate - 1 / deactivate - 0 
	 * 2nd bit and 3rd = gripper gasping mode:
	 * 				   = 0 - basic mode
	 *  			   = 1 - pinch mode
	 * 				   = 2 - wide mode
	 *      		   = 3 - scissor mode
	 * 4th bit = go to action - 1 go to pos/ - 0 stop
	 * 5th bit = emergency release - 1/ normal - 0
	 * bits 6,7,8 - reserved (000)
	*/
	public void deactivate() {
		this.setAct_req(0); //<-- 0000 0000
	} 
	public void activate() {
		System.out.println("Activating Gripper..."); 
		this.setAct_req(9);//<-- 0000 1001 
	}
	public void setBasicMode(){
		this.setAct_req(9);//<-- 0000 1001
	}
	public void setPinchMode(){
		this.setAct_req(11);//<-- 0000 1011
	}
	public void setWideMode(){
		this.setAct_req(13);//<-- 0000 1101
	}
	public void setScissorMode(){
		this.setAct_req(15);//<-- 0000 1111
	}
	public void emergencyStop(){
		this.setAct_req(25);//<-- 0001 1001
	}
	
	public void setIndividual(){
		this.setGripper_opt(4);//<-- 0000 0100
	}
	
	public void setIndividualScissor(){
		this.setGripper_opt(8);//-<< 0000 1000
	}


	
	
	//************************************************** 
	public void close_full(){
		
		this.setSpeed_A(255);
		this.setSpeed_B(255);
		this.setSpeed_C(255);
		this.setPos_req_A(255);
		this.setPos_req_B(255);
		this.setPos_req_C(255);
		//this.setPos_req_scissor(250);
		//this.setAct_req(9);
	
	}
	public void open_full()
	{
		
		this.setSpeed_A(255);
		this.setSpeed_B(255);
		this.setSpeed_C(255);
		this.setPos_req_A(0);
		this.setPos_req_B(0);
		this.setPos_req_C(0);
	}
	
}