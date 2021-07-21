package application;


import javax.inject.Inject;

import backgroundTask.ReadTorque;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
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
public class Pierce extends RoboticsAPIApplication {
	@Inject
	private LBR lBR_iiwa_7_R800_1;
	private Tool acu_1;
	private ObjectFrame tcpAc1;
	
	
	
	@Override
	public void initialize() {
		// initialize your application here
		acu_1=getApplicationData().createFromTemplate("Ac_nou");
		tcpAc1=acu_1.getFrame("Tcp_ac_nou");
		
	}

	@Override
	public void run() {
		// your application execution starts here
		lBR_iiwa_7_R800_1.move(ptpHome());
		acu_1.attachTo(lBR_iiwa_7_R800_1.getFlange());
		
		int resp=0;
		do {
			resp=getApplicationUI().displayModalDialog(ApplicationDialogType.INFORMATION, "Select action",  "Exit", "Gel", "Pulpa");
			switch (resp){
			case 0:{
				getLogger().info("gata");
				break;
			      }
			case 1:{
				getLogger().info("Ma duc in gelu");
				do_gel();
			      }
			case 2:{
				getLogger().info("Ma duc in pulpa");
				do_pulpa();
			     }
		}
		}
		while (0!=resp);
		
		
		
		
	}
	private void do_pulpa() {
		// TODO Auto-generated method stub\
		acu_1.move(ptp(getApplicationData().getFrame("/Punct_deasupra_pulpa")));
		int count=0;
		int count2=0;
		do
		{
		do 
		{
		acu_1.move(linRel(0, 0, 35)); 
		ThreadUtil.milliSleep(1000);
		acu_1.move(linRel(0, 0, -35));		
		ThreadUtil.milliSleep(1000);
		acu_1.move(linRel(0, 10, 0));
		ThreadUtil.milliSleep(1000);
        count=count+1;
		}while (count<=5);
		acu_1.move(linRel(10, 0, 0));
		ThreadUtil.milliSleep(1000);
		count2=count2+1;
		}while (count2<=3);
	}

	private void do_gel() {
		// TODO Auto-generated method stub
		acu_1.move(ptp(getApplicationData().getFrame("/punct_deasupra_gel")));
		acu_1.move(lin(getApplicationData().getFrame("/gelu/Punct_gel")));
	}

	public void dispose(){
		
	}
}