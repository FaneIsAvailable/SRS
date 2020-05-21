package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>Robotiq3F</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * ./.
 */
@Singleton
public class Robotiq3FIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'Robotiq3F'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'Robotiq3F'
	 */
	@Inject
	public Robotiq3FIOGroup(Controller controller)
	{
		super(controller, "Robotiq3F");

		addDigitalOutput("Act_req", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Gripper_opt", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Gripper_opt_2", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Pos_req_A", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Speed_A", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Force_A", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Pos_req_B", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Speed_B", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Force_B", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Pos_req_C", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Speed_C", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Force_C", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Pos_req_scissor", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Speed_Scissor", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Force_scissor", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Gripper_status", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Obj_detect", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Fault_state", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_A", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Cur_A", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_req_echo_A", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_req_echo_B", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_B", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_C", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_req_echo_C", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Cur_B", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Cur_C", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_req_echo_scissor", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Pos_scissor", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Cur_scissor", IOTypes.UNSIGNED_INTEGER, 8);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Act_req</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Act_req'
	 */
	public java.lang.Integer getAct_req()
	{
		return getNumberIOValue("Act_req", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Act_req</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Act_req'
	 */
	public void setAct_req(java.lang.Integer value)
	{
		setDigitalOutput("Act_req", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Gripper_opt</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Gripper_opt'
	 */
	public java.lang.Integer getGripper_opt()
	{
		return getNumberIOValue("Gripper_opt", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Gripper_opt</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Gripper_opt'
	 */
	public void setGripper_opt(java.lang.Integer value)
	{
		setDigitalOutput("Gripper_opt", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Gripper_opt_2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Gripper_opt_2'
	 */
	public java.lang.Integer getGripper_opt_2()
	{
		return getNumberIOValue("Gripper_opt_2", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Gripper_opt_2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Gripper_opt_2'
	 */
	public void setGripper_opt_2(java.lang.Integer value)
	{
		setDigitalOutput("Gripper_opt_2", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Pos_req_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Pos_req_A'
	 */
	public java.lang.Integer getPos_req_A()
	{
		return getNumberIOValue("Pos_req_A", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Pos_req_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Pos_req_A'
	 */
	public void setPos_req_A(java.lang.Integer value)
	{
		setDigitalOutput("Pos_req_A", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Speed_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Speed_A'
	 */
	public java.lang.Integer getSpeed_A()
	{
		return getNumberIOValue("Speed_A", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Speed_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Speed_A'
	 */
	public void setSpeed_A(java.lang.Integer value)
	{
		setDigitalOutput("Speed_A", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Force_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Force_A'
	 */
	public java.lang.Integer getForce_A()
	{
		return getNumberIOValue("Force_A", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Force_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Force_A'
	 */
	public void setForce_A(java.lang.Integer value)
	{
		setDigitalOutput("Force_A", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Pos_req_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Pos_req_B'
	 */
	public java.lang.Integer getPos_req_B()
	{
		return getNumberIOValue("Pos_req_B", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Pos_req_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Pos_req_B'
	 */
	public void setPos_req_B(java.lang.Integer value)
	{
		setDigitalOutput("Pos_req_B", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Speed_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Speed_B'
	 */
	public java.lang.Integer getSpeed_B()
	{
		return getNumberIOValue("Speed_B", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Speed_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Speed_B'
	 */
	public void setSpeed_B(java.lang.Integer value)
	{
		setDigitalOutput("Speed_B", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Force_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Force_B'
	 */
	public java.lang.Integer getForce_B()
	{
		return getNumberIOValue("Force_B", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Force_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Force_B'
	 */
	public void setForce_B(java.lang.Integer value)
	{
		setDigitalOutput("Force_B", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Pos_req_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Pos_req_C'
	 */
	public java.lang.Integer getPos_req_C()
	{
		return getNumberIOValue("Pos_req_C", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Pos_req_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Pos_req_C'
	 */
	public void setPos_req_C(java.lang.Integer value)
	{
		setDigitalOutput("Pos_req_C", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Speed_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Speed_C'
	 */
	public java.lang.Integer getSpeed_C()
	{
		return getNumberIOValue("Speed_C", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Speed_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Speed_C'
	 */
	public void setSpeed_C(java.lang.Integer value)
	{
		setDigitalOutput("Speed_C", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Force_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Force_C'
	 */
	public java.lang.Integer getForce_C()
	{
		return getNumberIOValue("Force_C", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Force_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Force_C'
	 */
	public void setForce_C(java.lang.Integer value)
	{
		setDigitalOutput("Force_C", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Pos_req_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Pos_req_scissor'
	 */
	public java.lang.Integer getPos_req_scissor()
	{
		return getNumberIOValue("Pos_req_scissor", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Pos_req_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Pos_req_scissor'
	 */
	public void setPos_req_scissor(java.lang.Integer value)
	{
		setDigitalOutput("Pos_req_scissor", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Speed_Scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Speed_Scissor'
	 */
	public java.lang.Integer getSpeed_Scissor()
	{
		return getNumberIOValue("Speed_Scissor", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Speed_Scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Speed_Scissor'
	 */
	public void setSpeed_Scissor(java.lang.Integer value)
	{
		setDigitalOutput("Speed_Scissor", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Force_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Force_scissor'
	 */
	public java.lang.Integer getForce_scissor()
	{
		return getNumberIOValue("Force_scissor", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Force_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Force_scissor'
	 */
	public void setForce_scissor(java.lang.Integer value)
	{
		setDigitalOutput("Force_scissor", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Gripper_status</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Gripper_status'
	 */
	public java.lang.Integer getGripper_status()
	{
		return getNumberIOValue("Gripper_status", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Obj_detect</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Obj_detect'
	 */
	public java.lang.Integer getObj_detect()
	{
		return getNumberIOValue("Obj_detect", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Fault_state</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Fault_state'
	 */
	public java.lang.Integer getFault_state()
	{
		return getNumberIOValue("Fault_state", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_A'
	 */
	public java.lang.Integer getPos_A()
	{
		return getNumberIOValue("Pos_A", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Cur_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Cur_A'
	 */
	public java.lang.Integer getCur_A()
	{
		return getNumberIOValue("Cur_A", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_req_echo_A</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_req_echo_A'
	 */
	public java.lang.Integer getPos_req_echo_A()
	{
		return getNumberIOValue("Pos_req_echo_A", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_req_echo_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_req_echo_B'
	 */
	public java.lang.Integer getPos_req_echo_B()
	{
		return getNumberIOValue("Pos_req_echo_B", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_B'
	 */
	public java.lang.Integer getPos_B()
	{
		return getNumberIOValue("Pos_B", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_C'
	 */
	public java.lang.Integer getPos_C()
	{
		return getNumberIOValue("Pos_C", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_req_echo_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_req_echo_C'
	 */
	public java.lang.Integer getPos_req_echo_C()
	{
		return getNumberIOValue("Pos_req_echo_C", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Cur_B</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Cur_B'
	 */
	public java.lang.Integer getCur_B()
	{
		return getNumberIOValue("Cur_B", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Cur_C</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Cur_C'
	 */
	public java.lang.Integer getCur_C()
	{
		return getNumberIOValue("Cur_C", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_req_echo_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_req_echo_scissor'
	 */
	public java.lang.Integer getPos_req_echo_scissor()
	{
		return getNumberIOValue("Pos_req_echo_scissor", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Pos_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Pos_scissor'
	 */
	public java.lang.Integer getPos_scissor()
	{
		return getNumberIOValue("Pos_scissor", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Cur_scissor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Cur_scissor'
	 */
	public java.lang.Integer getCur_scissor()
	{
		return getNumberIOValue("Cur_scissor", false).intValue();
	}

}
