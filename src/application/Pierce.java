package application;


import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;

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
		acu_1.move(ptp(getApplicationData().getFrame("/punct_deasupra_gel")));
		
		acu_1.move(lin(getApplicationData().getFrame("/gelu/Punct_gel")));
	}
}