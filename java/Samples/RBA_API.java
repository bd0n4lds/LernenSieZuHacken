/**
 * \defgroup sdk_java_api API for Java
 * \addtogroup sdk_java_api
 * <!-- Define this group and the rba_sdk package only once, in RBA_API.java. -->
 *  @{
 */

/**
 * Contains the classes and interfaces for the Java wrapper for the \libraryName.
 */
package rba_sdk;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 *  Provides a Java wrapper for the \libraryName.
 */
public class RBA_API 
{
    static  {
        //System.getProperties().list(System.out);
		
		if( System.getProperty("java.vendor.url").toLowerCase().equals("http://www.android.com/"))
            System.loadLibrary("gnustl_shared");
        //try{System.loadLibrary("gnustl_shared");}catch(java.lang.UnsatisfiedLinkError e){}
        System.loadLibrary("RBA_SDK");
    }

	/***************************************************************************************************************************************
										Messaging API
	 ***************************************************************************************************************************************/

	/**
	 * Gets the SDK version number of the \libraryName.
	 *
	 * @return A string that represents the library version number.
	 */
	public native static String GetVersion() throws  java.lang.UnsatisfiedLinkError;
	
	/**
	 * Gets the SDK version number plus used version of Secure protocols.
	 *
	 * @return A string that represents the version numbers.
	 */
	//public native static String GetVersionExt() throws  java.lang.UnsatisfiedLinkError;

	/**
	 * Initializes the \libraryName.
	 * <p>
	 * With the exception of the {@link RBA_API.SetDefaultLogLevel} and 
	 * {@link RBA_API.GetVersion} methods, an application must call this 
	 * method before it can call other methods in the library.
	 * <p>
	 * \aboutInitializing
	 *
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.Connect
	 * @see RBA_API.Shutdown
	 */
	public static ERROR_ID Initialize() throws  java.lang.UnsatisfiedLinkError
	{
        if( CheckVersion() )
		    return ERROR_ID.fromInteger(RBA_SDK_Initialize());
        return ERROR_ID.RESULT_ERROR_NOT_AVAILABLE;
	}

	public static int Initialize(String name) throws  java.lang.UnsatisfiedLinkError
	{
        if( CheckVersion() )
            return RBA_SDK_InitializeInstance(name);
        return ERROR_ID.toInteger(ERROR_ID.RESULT_ERROR_NOT_AVAILABLE);
	}

	/**
	 * Change Instance Name.
	 * <p>
	 *
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.Initialize
	 */
	public static int SetInstanceName(int instanceId, String name) throws  java.lang.UnsatisfiedLinkError
	{
        return RBA_SDK_SetInstanceName(instanceId, name);
	}
    
    private static boolean CheckVersion()
    {
        LogOut("RBA_API_JAVA", LOG_LEVEL.LTL_INFO, "Version: " + m_wrapper_version);
        if(GetVersion().equals(m_wrapper_version))
            return true;
        
        LogOut("RBA_API_JAVA", LOG_LEVEL.LTL_ERROR, "Version inconsistency: RBA_SDK.jar version is " + m_wrapper_version + ", RBA_SDK native library's version is " + GetVersion());
        return false;
    }

	/**
	 * Prepares the the \libraryName to be unloaded by freeing all internal resources.
	 * <p>
	 * This method frees all internal resources managed by the library, including 
	 * message parameters, so that the application can unload the library from memory. 
	 * <p>
	 * \aboutInitializing
	 *
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * of the error codes defined in the {@link ERROR_ID} class.
	 *
	 * @see RBA_API.Disconnect
	 * @see RBA_API.Initialize
	 */
	public static ERROR_ID Shutdown() throws  java.lang.UnsatisfiedLinkError
	{
		RBA_SDK_SetLogCallBack(0);
		return ERROR_ID.fromInteger(RBA_SDK_Shutdown());
	}

	public static ERROR_ID Shutdown(int instanceId) throws  java.lang.UnsatisfiedLinkError
	{
		//RBA_SDK_SetLogCallBack(0);
		return ERROR_ID.fromInteger(RBA_SDK_ShutdownInstance(instanceId));
	}

	/**
	 * Sets a parameter for a PIN pad request message.
	 * <p>
	 * Use this method to provide the \libraryName with a value for each of the 
	 * required parameters for a PIN pad message that is to be processed on the 
	 * device. The library constructs a request messages for the device from 
	 * the message ID and the associated parameter values.
	 * <p>
	 * This method overwrites any previous value for the parameter. However, 
	 * some parameters allow multiple values. To set multiple values on a 
	 * parameter, use the {@link RBA_API.AddParam} method.
	 * <p>
	 * \aboutMessaging
	 * For information about specific messages and parameters, see the \baseGuide 
	 * and the {@link MESSAGE_ID} class.
	 * </blockquote>
	 * 
	 * @param id The ID of parameter to set.
	 * @param in_data A pointer to a buffer that contains the value to which 
	 * 	to set the parameter.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * of the error codes defined in the {@link ERROR_ID} class.
	 * <p>
	 * <b>Example</b>
	 * {@code
RBA_API.SetParam(MESSAGE_ID.P01_REQ_APPID, "0000");
	 	}
	 * @see RBA_API.AddParam
	 * @see RBA_API.GetParam
	 * @see RBA_API.ProcessMessage
	 * @see RBA_API.ResetParam
	 */
	public static ERROR_ID SetParam( PARAMETER_ID id, String in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetParam(PARAMETER_ID.toInteger(id), in_data ));
	}
	public static ERROR_ID SetParam( int instId, PARAMETER_ID id, String in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetParamFor(instId, PARAMETER_ID.toInteger(id), in_data ));
	}

	/**
	 * Adds to or constructs an array of values for a parameter for a PIN pad 
	 * request message.
	 * <p>
	 * Call this method to build an array of values for a parameter to send to 
	 * the connected device when your application calls the 
	 * {@link RBA_API.ProcessMessage} method.
	 * <ol>
	 * 	<li>To set the first value, call the {@link RBA_API.SetParam} method.</li>
	 * 	<li>Then call the {@link RBA_API.AddParam} method to add subsequent values to 
	 * 		the array.</li>
	 * </ol>
	 * <blockquote>
	 * 	<b>Note</b>: Any call to the {@link RBA_API.SetParam} method overwrites any 
	 * 	previous values that may have been set on the associated parameter.
	 * </blockquote>
	 * <blockquote>
	 * 	<b>Warning</b>: When building an array of values for a specific 
	 * 	parameter, the order in which the application adds these 
	 * 	values is significant.
	 * </blockquote>
	 * <p>
	 * Only a few messages support a variable number of parameters.
	 * \aboutMessaging
	 * For more information about specific messages and parameters, see the \baseGuide.
	 * <p>
	 * 
	 * @param id The ID of the value to add to the parameter value array.
	 * @param in_data A pointer to a buffer that contains the value to add to 
	 * 	the parameter value array.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * of the error codes defined in the {@link ERROR_ID} class.
	 * <p>
	 * <b>Example</b>
	 * {@code
RBA_API.SetParam(PARAMETER_ID.P13_REQ_AMOUNT, "100");
RBA_API.AddParam(PARAMETER_ID.P13_REQ_AMOUNT, "200");
RBA_API.AddParam(PARAMETER_ID.P13_REQ_AMOUNT, "3000");
		}
	 * 
	 * @see RBA_API.GetParam
	 * @see RBA_API.ProcessMessage
	 * @see RBA_API.SetParam
	 */
	public static ERROR_ID AddParam( PARAMETER_ID id, String in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_AddParam(PARAMETER_ID.toInteger(id), in_data));
	}

	public static ERROR_ID AddParam( int instId, PARAMETER_ID id, String in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_AddParamFor(instId, PARAMETER_ID.toInteger(id), in_data));
	}

	/**
	 * Gets a parameter from a message that the \libraryName received from 
	 * a PIN pad device, in a string builder.
	 * <p>
	 * Use this method to retrieve the values for parameters for a PIN pad 
	 * message that was sent from the device to the library. The library parses 
	 * each message it receives from the device to extract the parameter values. 
	 * To notify the library that it can free the memory for its copy of the 
	 * parameter data, use the {@link RBA_API.ResetParam} method.
	 * <p>
	 * To send a message to the device, see the {@link RBA_API.ProcessMessage} 
	 * method. To register an event listener to receive messages from the 
	 * device, see the {@link RBA_API.SetMessageCallBack} method.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param id The ID of the parameter value to get.
	 * 
	 * @return If successful, a string builder that contains the value of the 
	 * parameter; otherwise, the empty string builder.
	 * 
	 * {@code
StringBuilder data = RBA_API.GetMutableParam(PARAMETER_ID.P00_RES_REASON_CODE);
if(data.length() > 0 )
{
	// Success
}
else
{
	// Failure
}
	 	}
	 * 
	 * @see RBA_API.GetParam
	 * @see RBA_API.ProcessMessage
	 * @see RBA_API.ResetParam
	 * @see RBA_API.SetMessageCallBack
	 * @see RBA_API.SetParam
	 */
	public static synchronized StringBuilder GetMutableParam( PARAMETER_ID id ) throws  java.lang.UnsatisfiedLinkError
	{
		StringBuilder out_data = new StringBuilder();
		if( ERROR_ID.fromInteger(RBA_SDK_Get_Param( PARAMETER_ID.toInteger(id) )) == ERROR_ID.RESULT_SUCCESS )
			out_data =  out_data.append(RBA_SDK_Get_Param_String());        
		return out_data;
	}

	public static synchronized StringBuilder GetMutableParam(int instId, PARAMETER_ID id ) throws  java.lang.UnsatisfiedLinkError
	{
		StringBuilder out_data = new StringBuilder();
		if( ERROR_ID.fromInteger(RBA_SDK_Get_ParamFor(instId, PARAMETER_ID.toInteger(id) )) == ERROR_ID.RESULT_SUCCESS )
			out_data =  out_data.append(RBA_SDK_Get_Param_StringFor(instId));
		return out_data;
	}

	/**
	 * Gets a parameter from a message that the \libraryName received from 
	 * a PIN pad device, in a string.
	 * <p>
	 * Use this method to retrieve the values for parameters for a PIN pad 
	 * message that was sent from the device to the library. The library parses 
	 * each message it receives from the device to extract the parameter values. 
	 * To notify the library that it can free the memory for its copy of the 
	 * parameter data, use the {@link RBA_API.ResetParam} method.
	 * <p>
	 * To send a message to the device, see the {@link RBA_API.ProcessMessage} 
	 * method. To register an event listener to receive messages from the 
	 * device, see the {@link RBA_API.SetMessageCallBack} method.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param id The ID of the parameter value to get.
	 * 
	 * @return If successful, a string that contains the value of the 
	 * parameter; otherwise, the empty string.
	 * 
	 * {@code
String data = RBA_API.GetParam(PARAMETER_ID.P00_RES_REASON_CODE);
if(data.length() > 0 )
{
	// Success
}
else
{
	// Failure
}
	 	}
	 * 
	 * @see RBA_API.GetMutableParam
	 * @see RBA_API.ProcessMessage
	 * @see RBA_API.ResetParam
	 * @see RBA_API.SetMessageCallBack
	 * @see RBA_API.SetParam
	 */
	public static String GetParam( PARAMETER_ID id ) throws  java.lang.UnsatisfiedLinkError
	{
		return GetMutableParam(id).toString();
	}

	public static String GetParam(int instId, PARAMETER_ID id ) throws  java.lang.UnsatisfiedLinkError
	{
		return GetMutableParam(instId, id).toString();
	}

	public static ERROR_ID LockParam() throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_LockParam());
	}
	public static ERROR_ID LockParam(int instId) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_LockParamFor(instId));
	}

	public static ERROR_ID UnlockParam() throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_UnlockParam());
	}

	public static ERROR_ID UnlockParam(int instId) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_UnlockParamFor(instId));
	}

	/**
	 * Clears a parameter value stored by the \libraryName.
	 * <p>
	 * This method notifies the library that it should clear and free the 
	 * memory for its copy of the parameter data. This method can free memory 
	 * for parameters that were set either by the application or by the library 
	 * when it received a message from the device.
	 * <blockquote>
	 * <b>Note</b>: Some parameters hold sensitive card holder information. An 
	 * 	application should call this method as soon as the data is no longer 
	 * 	needed.
	 * </blockquote>
	 * <p>
	 * \aboutMessaging
	 *  
	 * @param paramid The ID of the parameter, or -1, to clear library memory 
	 * 	for all parameters.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.GetParam
	 * @see RBA_API.SetParam
	 */
	public static ERROR_ID ResetParam(PARAMETER_ID paramid ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ResetParam(PARAMETER_ID.toInteger(paramid)));
	}

	public static ERROR_ID ResetParam(int instId, PARAMETER_ID paramid ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ResetParamFor(instId, PARAMETER_ID.toInteger(paramid)));
	}

	/**
	 * Adds to or constructs an array of values for the EMV tag data for an 
	 * applicable PIN pad request message.
	 * <p>
	 * Use the {@link RBA_API.ResetTagParam} method to clear previous EMV tag 
	 * data. Use this method to set the first and each additional tag parameter 
	 * for an EMV message.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param forMsgId The ID of the message for which to include the tag parameter.
	 * @param tagId The ID of the EMV tag parameter.
	 * @param in_data The value for the EMV tag parameter.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.GetTagParam
	 * @see RBA_API.ResetTagParam
	 */
	public static ERROR_ID AddTagParam( MESSAGE_ID forMsgId, int tagId, byte[] in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_AddTagParam(MESSAGE_ID.toInteger(forMsgId), tagId, in_data ));
	}

	public static ERROR_ID AddTagParam(int instId,  MESSAGE_ID forMsgId, int tagId, byte[] in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_AddTagParamFor(instId, MESSAGE_ID.toInteger(forMsgId), tagId, in_data ));
	}

	/**
	 * Gets the next tag parameter associated with an EMV message that the 
	 * \libraryName received from a PIN pad device.
	 * <p>
	 * Use this method to retrieve values for tag parameters that were provided 
	 * by the device in an EMV response message. Messages can contain multiple 
	 * tag parameters. Call this method multiple times to retrieve all of the 
	 * tag parameters for the message.
	 * <p>
	 * To notify the library that it can free the memory for its copy of the 
	 * tag parameter data, use the {@link RBA_API.ResetTagParam} method.
	 * <p>
	 * Data for an individual tag can only be retrieved once, as after 
	 * providing the tag parameter value to the calling application, the 
	 * library clears and frees the associated memory.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param forMsgId The ID of the EMV message.
	 * @param out_data When the method returns, contains the value of the 
	 * 		tag parameter on success, or the empty string builder on failure.
	 * 
	 * @return On success, the tag id; otherwise, a value that corresponds to 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.ResetTagParam
	 * @see RBA_API.AddTagParam
	 */
	public static int GetTagParam( MESSAGE_ID forMsgId, ByteArrayOutputStream out_data ) throws java.lang.UnsatisfiedLinkError
	{
		int tagId = RBA_SDK_Get_Tag_Param( MESSAGE_ID.toInteger(forMsgId) );
		if( tagId > 0 )
		{
			byte[] src = RBA_SDK_Get_Tag_Param_Bytes();
			DataOutputStream dout = new DataOutputStream(out_data);
			try {
				dout.write(src,0,src.length);
			} catch (IOException e) {
				e.printStackTrace();
				tagId = ERROR_ID.toInteger(ERROR_ID.RESULT_ERROR);
			}
		}
		return tagId;
	}
	public static int GetTagParam(int instId,  MESSAGE_ID forMsgId, ByteArrayOutputStream out_data ) throws java.lang.UnsatisfiedLinkError
	{
		int tagId = RBA_SDK_Get_Tag_ParamFor(instId, MESSAGE_ID.toInteger(forMsgId) );
		if( tagId > 0 )
		{
			byte[] src = RBA_SDK_Get_Tag_Param_BytesFor(instId);
			DataOutputStream dout = new DataOutputStream(out_data);
			try {
				dout.write(src,0,src.length);
			} catch (IOException e) {
				e.printStackTrace();
				tagId = ERROR_ID.toInteger(ERROR_ID.RESULT_ERROR);
			}
		}
		return tagId;
	}

	/**
	 * Clears memory for an EMV tag parameter stored by the \libraryName for 
	 * an EMV message.
	 * <p>
	 * This method notifies the library that it should clear and free the 
	 * memory for its copy of the tag parameter data. This method can free 
	 * memory for parameters that were set either by the application or by the 
	 * library when it received a message from the device.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param msgId The ID of the message that contains the tag parameters, or 
	 * 		-1 to clear all tag data for all messages.
	 * @param tagId The ID of the tag parameter to clear, or -1 to clear all 
	 * 		tag parameter data for the message.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.AddTagParam
	 * @see RBA_API.GetTagParam
	 */
	public static ERROR_ID ResetTagParam(MESSAGE_ID msgId, int tagId ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ResetTagParam(MESSAGE_ID.toInteger(msgId), tagId));
	}
	public static ERROR_ID ResetTagParam(int instId, MESSAGE_ID msgId, int tagId ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ResetTagParamFor(instId, MESSAGE_ID.toInteger(msgId), tagId));
	}

	/**
	 * Sends an RBA message to the device.
	 * <p>
	 * An application calls this method to send an RBA message to the device. All
	 * the low-level details related to the connection, protocol, and message 
	 * acknowledgements are handled by the \libraryName.
	 * <p>
	 * \aboutMessaging
	 *
	 * <blockquote><b>Note</b>: If the device responds immediately (synchronous mode has been set and the
	 * message is blocking), then this method handles the response message from the 
	 * device. If the device does not respond immediately (asynchronous mode has been set or the
	 * message is non-blocking or callback only), then the message callback is called when the response is
	 * ready. Use the {@link RBA_API.SetMessageCallBack} method to set the 
	 * message callback.
	 * </blockquote>
	 * 
	 * @param msgID The unique message ID to be processed, from the
	 * 	{@link MESSAGE_ID} class.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * 	of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.GetParam
	 * @see RBA_API.SetMessageCallBack
	 * @see RBA_API.SetParam
	 */
	public static ERROR_ID ProcessMessage( MESSAGE_ID msgID ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ProcessMessage(MESSAGE_ID.toInteger(msgID)));
	}

	public static ERROR_ID ProcessMessage(int instId, MESSAGE_ID msgID ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ProcessMessageFor(instId, MESSAGE_ID.toInteger(msgID)));
	}

	/**
	 *  Sets the communication timeout values that the \libraryName uses 
	 *  during communication with a device.
	 * <p>
	 * Applications are expected to set proper values for the communication 
	 * interface they are using, see the {@link Comm_Settings}
	 * structure. If the value for a timeout interval is 0, then then the 
	 * library will set the default value for this interval. If value is 
	 * invalid, then the library will set the minimum interval for the 
	 * interface.
	 * <blockquote><b>Note</b>:  For all interfaces, the
	 * 	{@link Comm_Timeout.receiveTimeOut} value refers to the time it takes 
	 * 	to receive each character, not the time it takes to receive a full 
	 * 	packet.
	 * </blockquote>
	 * <p>
	 * \aboutConnecting
	 *
	 * @param Timeouts The timeout values to be used during device communication.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, one 
	 * 	of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see Comm_Timeout
	 * @see Comm_Settings
	 */
	public static ERROR_ID SetCommTimeouts(Comm_Timeout Timeouts)throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetCommTimeouts(Timeouts));
	}
	public static ERROR_ID SetCommTimeouts(int instId, Comm_Timeout Timeouts)throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetCommTimeoutsFor(instId, Timeouts));
	}

	public static Comm_Timeout GetCommTimeouts() throws java.lang.UnsatisfiedLinkError
	{
        Comm_Timeout timeouts = new Comm_Timeout();
        timeouts.sendTimeOut    = RBA_SDK_GetCommTimeoutSend();
        timeouts.receiveTimeOut = RBA_SDK_GetCommTimeoutReceive();
        timeouts.connectTimeOut = RBA_SDK_GetCommTimeoutConnect();
        return timeouts;
	}
	public static Comm_Timeout GetCommTimeouts(int instId) throws  java.lang.UnsatisfiedLinkError
	{
        Comm_Timeout timeouts = new Comm_Timeout();
        timeouts.sendTimeOut    = RBA_SDK_GetCommTimeoutSendFor(instId);
        timeouts.receiveTimeOut = RBA_SDK_GetCommTimeoutReceiveFor(instId);
        timeouts.connectTimeOut = RBA_SDK_GetCommTimeoutConnectFor(instId);
        return timeouts;
	}

	/**
	 * Initiates a connection to a device.
	 * <p>
	 * <blockquote><b>Note</b>:  The library must be properly initialized using 
	 * the {@link RBA_API.Initialize} method before calling this method.
	 * <p>
	 * The communication settings used by your application must match the 
	 * configuration of the device being used. In other words, you cannot 
	 * configure the library to communicate via Ethernet if the device is 
	 * configured to use a serial connection.
	 * </blockquote>
	 * <p>
	 * After the library establishes a connection and end to end and 
	 * communication is possible, the library calls the application's 
	 * connected event handler. For more information, see the 
	 * {@link RBA_API.SetNotifyRbaConnected} method and the
	 * {@link ConnectedHandlerInterface} interface.
	 * <p>
	 * \aboutConnecting
	 * 
	 * @param settings The communication settings for the connection.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.Disconnect
	 * @see RBA_API.Reconnect
	 * @see RBA_API.SetCommTimeouts
	 */
	public static ERROR_ID Connect(Comm_Settings  settings) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_Connect(settings));
	}

	public static ERROR_ID Connect(int instId, Comm_Settings  settings) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ConnectFor(instId, settings));
	}

	/**
	 * Reconnects to a device, using the previous communication settings.
	 * <p>
	 * You can call this method to reestablish a connection with a device 
	 * after the {@link RBA_API.Connect} and {@link RBA_API.Disconnect} 
	 * methods were called.
	 * <ul>
	 * <li>If the application calls this method without previously calling 
	 * the {@link RBA_API.Connect} method, then this method tries to connect 
	 * using the default settings, which are defined by the 
	 * {@link Comm_Settings} class with all values set to binary zero.</li>
	 * <li>If the device is already connected, then this method returns an 
	 * {@link ERROR_ID.RESULT_ERROR_ALREADY_CONNECTED} error.</li>
	 * </ul>
	 * <p>
	 * To conserve power if your application is running on a mobile or battery 
	 * powered device, you can call the {@link RBA_API.Disconnect} method
	 *  between transactions or after a period of inactivity, and then call 
	 *  the {@link RBA_API.Reconnect} method before sending the next request.
	 * <p>
	 * \aboutConnecting
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.Connect
	 * @see RBA_API.Disconnect
	 * @see RBA_API.SetCommTimeouts
	 */
	public static ERROR_ID Reconnect() throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_Reconnect());
	}

	public static ERROR_ID Reconnect(int instId) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_ReconnectFor(instId));
	}

	/**
	 * Closes the connection to the device.
	 * <p>
	 * \aboutConnecting
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.Connect
	 * @see RBA_API.GetConnectionStatus
	 * @see RBA_API.Reconnect
	 */
	public static ERROR_ID Disconnect() throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_Disconnect());
	}

	public static ERROR_ID Disconnect(int instId) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_DisconnectFor(instId));
	}

	/**
	 * Returns the connection status.
	 *
	 *	\aboutConnecting
	 * 
	 * @return The current state of the device connection.
	 * 
	 * @see RBA_API.ConnectionStatus
	 * @see RBA_API.Connect
	 * @see RBA_API.Disconnect
	 * @see RBA_API.Reconnect
	 */
	public static ConnectionStatus GetConnectionStatus() throws  java.lang.UnsatisfiedLinkError
	{
		return ConnectionStatus.fromInteger(RBA_SDK_Get_Connection_Status());
	}

	public static ConnectionStatus GetConnectionStatus(int instId) throws  java.lang.UnsatisfiedLinkError
	{
		return ConnectionStatus.fromInteger(RBA_SDK_Get_Connection_StatusFor(instId));
	}

	/**
	 * Sets an attribute of the \libraryName.
	 * <p>
	 * Use this method to configure an attribute of the library. The 
	 * attribute IDs are defined in the {@link ATTRIBUTE_ID} class.
	 * 
	 * <blockquote><b>Note</b>:  For numeric values, the value must be a 
	 * positive integer, represented as a decimal string.
	 * </blockquote>
	 * 
	 * @param id The ID of the attribute to set.
	 * @param in_data The value to assign to the attribute.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * <b>Example</b>
	 * {@code
ERROR_ID ret = RBA_API.SetAttribute(
    ATTRIBUTE_ID.BATTERY_TIMER_INTERVAL_MINUTES, "5");
	 	}
	 *
	 * @see ATTRIBUTE_ID
	 * @see RBA_API.GetAttribute
	 */
	public static ERROR_ID SetAttribute( ATTRIBUTE_ID id, String in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetAttribute(ATTRIBUTE_ID.toInteger(id),in_data));
	}
	public static ERROR_ID SetAttribute(int instId,  ATTRIBUTE_ID id, String in_data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetAttributeFor(instId, ATTRIBUTE_ID.toInteger(id),in_data));
	}

	/**
	 * Sends a custom message of a type which is supported on the PIN pad 
	 * device, but not yet supported by the \libraryName.
	 * <p>
	 * If <b>bReceive</b> is true, the message response is handled as blocking 
	 * (synchronous), and the method waits for the device to respond before 
	 * returning. If <b>bReceive</b> is false, the message response is handled 
	 * as non-blocking (asynchronous), and the message response is returned in 
	 * the message callback with a message ID of 
	 * {@link MESSAGE_ID.RAW_PINPAD_RESPONSE}. The custom message
	 * that is received can be read by using the {@link RBA_API.GetParam} 
	 * method with parameter ID of 
	 * {@link MESSAGE_ID.RAW_PINPAD_RESPONSE_DATA}.
	 * <p>
	 * It is also possible to use this method to send a request for a supported 
	 * message ID to be received asynchronously (<b>bReceive</b>=false). In 
	 * this case, the library recognizes the response as a supported message 
	 * and issues a callback with the standard supported message ID. You can 
	 * then use the {@link RBA_API.GetParam} method to retrieve the parameters 
	 * using the supported parameter IDs.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param rawData The data to be sent to the device.
	 * @param bReceive True to have the library handle the message response as 
	 * 		blocking; false to have the library handle the message response as 
	 * 		non-blocking.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * 		one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.ProcessMessage
	 */
	public  static ERROR_ID SendCustomMessage( String rawData, boolean bReceive ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SendCustomMessage(rawData, bReceive));
	}

	public  static ERROR_ID SendCustomMessage(int instId, String rawData, boolean bReceive ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SendCustomMessageFor(instId, rawData, bReceive));
	}

	/**
	 * Gets an attribute of the \libraryName, in a string builder.
	 * <p>
	 * Use this method to retrieve the current value of an attribute of the 
	 * library. The attribute IDs are defined in the 
	 * {@link ATTRIBUTE_ID} class.
	 * 
	 * <blockquote><b>Note</b>:  For numeric values, the value is a positive 
	 * integer, represented as a decimal string.
	 * </blockquote>
	 * 
	 * @param id The ID of the attribute to get.
	 * @param out_data A string builder into which the library can copy the 
	 * attribute value.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * <p><b>Example</b>
	 * {@code
StringBuilder interval  = new StringBuilder();
ERROR_ID retValue = RBA_API.GetAttribute(
	ATTRIBUTE_ID.BATTERY_TIMER_INTERVAL_MINUTES, interval);
if(retValue != ERROR_ID.RESULT_SUCCESS )
{
	return  new String("Error");
}
	 	}
	 * 
	 * @see RBA_API.GetAttribute(ATTRIBUTE_ID)
	 * @see RBA_API.SetAttribute
	 */
	public static ERROR_ID GetAttribute( ATTRIBUTE_ID id, StringBuilder out_data ) throws  java.lang.UnsatisfiedLinkError
	{
		out_data.delete( 0 , out_data.length());
		ERROR_ID error = ERROR_ID.fromInteger(RBA_SDK_Get_Attribute( ATTRIBUTE_ID.toInteger(id) )); 
		if( error == ERROR_ID.RESULT_SUCCESS )
			out_data.append(RBA_SDK_Get_Attribute_String());        
		return error;
	}

	/**
	 * Gets an attribute of the \libraryName, in a string.
	 * <p>
	 * Use this method to retrieve the current value of an attribute of the 
	 * library. The attribute IDs are defined in the 
	 * {@link ATTRIBUTE_ID} class.
	 * 
	 * <blockquote><b>Note</b>:  For numeric values, the value is a positive 
	 * integer, represented as a decimal string.
	 * </blockquote>
	 * 
	 * @param id The ID of the attribute to get.
	 * 
	 * @return A string containing the attribute value if successful; otherwise, 
	 * the empty string.
	 * 
	 * <p><b>Example</b>
	 * {@code
String interval = RBA_API.GetAttribute(
	ATTRIBUTE_ID.BATTERY_TIMER_INTERVAL_MINUTES);
if(intervale.length() > 0)
{
    // Success
}
else
{
	// Failure
}
	 	}
	 * 
	 * @see RBA_API.GetAttribute(ATTRIBUTE_ID, StringBuilder)
	 * @see RBA_API.SetAttribute
	 */
	public static String GetAttribute(ATTRIBUTE_ID id) throws  java.lang.UnsatisfiedLinkError
	{
		StringBuilder out_data = new StringBuilder();
		if( ERROR_ID.fromInteger(RBA_SDK_Get_Attribute( ATTRIBUTE_ID.toInteger(id) )) == ERROR_ID.RESULT_SUCCESS )
			out_data =  out_data.append(RBA_SDK_Get_Attribute_String());        
		return out_data.toString();
	}

	/**
	 * Sets the event listener that the \libraryName calls to notify your 
	 * application asynchronously that the library has processed a PIN pad 
	 * device message.
	 * <p>
	 * \javaThreadWarning
	 * <p>
	 * After the library receives and parses a device message for which it 
	 * will notify you application asynchronously, it calls the application's 
	 * PIN pad message handler.
	 * <p>
	 * To retrieve the response parameters associated with a device message, 
	 * call the {@link RBA_API.GetParam} method from within the application's 
	 * message event listener.
	 * <p>
	 * \aboutMessaging
	 * 
	 * @param handler The application's event listener.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * <p><b>Example</b>
	 * {@code
{
	//...
	EventCallBackHandler eventHandler = new EventCallBackHandler();
	ERROR_ID ret = RBA_API.SetMessageCallBack(eventHandler);
	//...
}

public class EventCallBackHandler implements EventHandlerInterface
{
	public void PinPadMessageCallBack(MESSAGE_ID MsgId)
	{
		switch (MsgId)
		{
			case MESSAGE_ID.M31_PIN_ENTRY:
			    String status = GetParam(MESSAGE_ID.P31_RES_STATUS);
			    String pin = GetParam(MESSAGE_ID.P31_RES_PIN_DATA);
				break;
			//...
		}
	}
}
	 	}
	 * 
	 * @see MESSAGE_ID
	 * @see RBA_API.GetParam
	 */
	public  static ERROR_ID SetMessageCallBack(EventHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		eventHandler = handler;
		return ERROR_ID.fromInteger(RBA_SDK_SetMessageCallBack());
	}

	public  static ERROR_ID SetMessageCallBack(int instId, EventHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		// Enable
        if( m_eventHandlers.containsKey(instId) )
            m_eventHandlers.remove(instId);
        m_eventHandlers.put(instId,handler);
        
        return ERROR_ID.fromInteger(RBA_SDK_SetMessageCallBackFor(instId));	
	}
	
	/**
	 * Sets the battery level threshold and registers an event handler that the
	 * \libraryName calls when the threshold is crossed.
	 * <p>
	 * The library calls the <b>handler</b> object's
	 * {@link BatteryEventHandlerInterface.BatteryCallBack} method when the 
	 * battery level crosses the threshold, either below or above.
	 * <p>
	 * \javaThreadWarning
	 *	
	 * @param threshold  Battery threshold as the percent of total battery capacity.
	 * @param handler  Event handler that is called when the threshold is crossed.
	 *
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see BatteryEventHandlerInterface
	 */
	public static ERROR_ID SetBatteryNotifyThreshold(int threshold, BatteryEventHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		batteryEventHandler = handler;
		return ERROR_ID.fromInteger(RBA_SDK_SetBatteryNotifyThreshold(threshold));
	}

	/**
	 * Sets the event handler that the \libraryName calls when the library 
	 * establishes a connection and end-to-end communication is possible with 
	 * a PIN pad device.
	 * <p>
	 * The device is not ready for communication until the library calls the 
	 * connected callback event handler or the library returns the 
	 * {@link RBA_API.ConnectionStatus.CONNECTED} value from the 
	 * {@link RBA_API.GetConnectionStatus} method.
	 * <p>
	 * \javaThreadWarning
	 * <p>
	 * Some versions of the library do not support the connected callback 
	 * feature. If the version you are using does not support this feature, 
	 * then the library returns the {@link ERROR_ID.RESULT_ERROR_UNSUPPORTED}
	 *  error code.
	 * <p>
	 * \aboutConnecting
	 *	
	 * @param handler The application's connected callback event listener.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see ConnectedHandlerInterface
	 * @see RBA_API.Connect
	 * @see RBA_API.GetConnectionStatus
	 */
	public static ERROR_ID SetNotifyRbaConnected(ConnectedHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		m_connectedHandler = handler;
		if( m_connectedHandler == null)
			return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaConnected(0));	// Disable
		
        return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaConnected(1));	// Enable
	}

	public static ERROR_ID SetNotifyRbaConnected(int instId, ConnectedHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		if( handler == null) {
            if( m_connectedHandlers.containsKey(instId) )
                m_connectedHandlers.remove(instId);
			return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaConnectedFor(instId, 0));	// Disable
        }
        
		// Enable
        if( m_connectedHandlers.containsKey(instId) )
            m_connectedHandlers.remove(instId);
        m_connectedHandlers.put(instId,handler);
        
        return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaConnectedFor(instId, 1));	
	}

	/**
	 * Sets the event handler that the \libraryName calls when the library 
	 * loosed a connection and end-to-end communication is not possible with 
	 * a PIN pad device.
	 * \aboutConnecting
	 *	
	 * @param handler The application's disconnected callback event listener.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see DisconnectedHandlerInterface
	 * @see RBA_API.Connect
	 * @see RBA_API.GetConnectionStatus
	 */
	public static ERROR_ID SetNotifyRbaDisconnected(DisconnectedHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		m_disconnectedHandler = handler;
		if( m_disconnectedHandler == null)
			return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaDisconnected(0));	// Disable

		return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaDisconnected(1));	// Enable
	}

	public static ERROR_ID SetNotifyRbaDisconnected(int instId, DisconnectedHandlerInterface handler) throws  java.lang.UnsatisfiedLinkError
	{
		if( handler == null) {
            if( m_disconnectedHandlers.containsKey(instId) )
                m_disconnectedHandlers.remove(instId);
			return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaDisconnectedFor(instId, 0));	// Disable
        }
        
		// Enable
        if( m_disconnectedHandlers.containsKey(instId) )
            m_disconnectedHandlers.remove(instId);
        m_disconnectedHandlers.put(instId,handler);
        
        return ERROR_ID.fromInteger(RBA_SDK_SetNotifyRbaDisconnectedFor(instId, 1));	
	}

	/**
	 * Places the \libraryName in the synchronous message processing mode.
	 * <p>
	 * <blockquote><b>Note</b>: Depending on the message ID, synchronous mode 
	 * 		may result in blocking (synchronous) messages or non-blocking 
	 * 		(asynchronous) messages.
	 * <p>
	 * 		For non-blocking messages, when the message finishes processing, the
	 * 		library calls the application's 
	 * 		{@link EventHandlerInterface.PinPadMessageCallBack} method, which  
	 * 		the application can set using the 
	 * 		{@link RBA_API.SetMessageCallBack} method.
	 * </blockquote>
	 * <p>
	 *	\aboutMessaging
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see EventHandlerInterface
	 * @see RBA_API.ProcessMessage
	 * @see RBA_API.SetProcessMessageAsyncMode
	 */
     public static ERROR_ID SetProcessMessageSyncMode()  	throws  java.lang.UnsatisfiedLinkError
	 {
         return ERROR_ID.fromInteger(RBA_SDK_SetProcessMessageSyncMode());
	 }
     public static ERROR_ID SetProcessMessageSyncMode(int instId)  	throws  java.lang.UnsatisfiedLinkError
	 {
         return ERROR_ID.fromInteger(RBA_SDK_SetProcessMessageSyncModeFor(instId));
	 }
	
	/**
	 * Places the \libraryName in the asynchronous message processing mode.
	 * <p>
	 * <blockquote><b>Note</b>: In asynchronous mode, all messages as asynchronous. 
	 *	When the message finishes processing, the library calls the application's 
	 * 	{@link EventHandlerInterface.PinPadMessageCallBack} method, which  
	 * 	the application can set using the 
	 * 	{@link RBA_API.SetMessageCallBack} method.
	 * </blockquote>
	 * <blockquote><b>Warning</b>: Using synchronous mode (see 
	 *	{@link RBA_API.SetProcessMessageSyncMode}) is recommended over asynchronous mode. 
	 *	However, depending on the message ID, synchronous mode may result in blocking 
	 *	(synchronous) messages or non-blocking (asynchronous) messages.
	 * </blockquote>
	 * <p>
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see EventHandlerInterface
	 * @see RBA_API.ProcessMessage
	 * @see RBA_API.SetProcessMessageSyncMode
	 */
	 public static ERROR_ID SetProcessMessageAsyncMode() 	throws  java.lang.UnsatisfiedLinkError
	 {
		return ERROR_ID.fromInteger(RBA_SDK_SetProcessMessageAsyncMode());
	 }

	 public static ERROR_ID SetProcessMessageAsyncMode(int instId) 	throws  java.lang.UnsatisfiedLinkError
	 {
		return ERROR_ID.fromInteger(RBA_SDK_SetProcessMessageAsyncModeFor(instId));
	 }

	/**
	 * The current state of the communication interface to the device.
	 * <p>
	 * \aboutConnecting
	 * 
	 * @see RBA_API.GetConnectionStatus
	 */
	public enum ConnectionStatus
	{
		/**
		 * Device is not connected to the specified communication interface.
		 */
		DISCONNECTED,
		/**
		 * Device is connected to the communication interface and is ready for requests.
		 */
		CONNECTED,
		/**
		 * Device is connected, but is not ready for requests.
		 * This is an intermittent status that occurs during the connecting process. 
		 * The application should wait for a CONNECTED status before attempting to communicate.
		 */
		CONNECTED_NOT_READY,;

		/**
		 * Returns the {@link RBA_API.ConnectionStatus} value that
		 * corresponds to an integer code from the \libraryName.
		 * 
		 *  @param status A connection status code (0, 1, or 2).
		 *  
		 *  @return 0 returns DISCONNECTED, 1 returns CONNECTED,
		 *          and 2 returns CONNECTED_NOT_READY.
		 */
		static ConnectionStatus fromInteger(int status)
		{
			switch(status)
			{
				case 0: return ConnectionStatus.DISCONNECTED;
				case 1:	return ConnectionStatus.CONNECTED;
				case 2: return ConnectionStatus.CONNECTED_NOT_READY;
				default: return null;
			}
		}
	};

	private static void InvokeConnectedHandler()
	{
		m_connectedHandler.ConnectedCallBack();
	}
	private static void InvokeConnectedHandlerFor(int instId)
	{
        if(m_connectedHandlers.containsKey(instId))
            ((ConnectedHandlerInterface)m_connectedHandlers.get(instId)).ConnectedCallBack();
	}
	private static void InvokeDisconnectedHandler()
	{
		m_disconnectedHandler.DisconnectedCallBack();
	}
	private static void InvokeDisconnectedHandlerFor(int instId)
	{
        if(m_disconnectedHandlers.containsKey(instId))
            ((DisconnectedHandlerInterface)m_disconnectedHandlers.get(instId)).DisconnectedCallBack();
	}
	private static void InvokeBatteryEventHandler(int level, int battery_state, int battery_level_state)
	{
		BatteryEventHandlerInterface.BATTERY_STATE batterState = BatteryEventHandlerInterface.BATTERY_STATE.fromInteger(battery_state);
		BatteryEventHandlerInterface.BATTERY_LEVEL_STATE batteryLevelState = BatteryEventHandlerInterface.BATTERY_LEVEL_STATE.fromInteger(battery_level_state);
		batteryEventHandler.BatteryCallBack(level, batterState, batteryLevelState);
	}
	private static void InvokeBatteryEventHandlerFor(int instId, int level, int battery_state, int battery_level_state)
	{
		BatteryEventHandlerInterface.BATTERY_STATE batterState = BatteryEventHandlerInterface.BATTERY_STATE.fromInteger(battery_state);
		BatteryEventHandlerInterface.BATTERY_LEVEL_STATE batteryLevelState = BatteryEventHandlerInterface.BATTERY_LEVEL_STATE.fromInteger(battery_level_state);
		batteryEventHandler.BatteryCallBack(level, batterState, batteryLevelState);
	}
	private static void InvokeEventHandler(int MsgId)
	{
		try{
			eventHandler.PinPadMessageCallBack(MESSAGE_ID.fromInteger(MsgId));
		}
		catch(Exception e){
			e.printStackTrace();	
		}
	}
	private static void InvokeEventHandlerFor(int instId, int MsgId)
	{
		try{
            if(m_eventHandlers.containsKey(instId))
                ((EventHandlerInterface)m_eventHandlers.get(instId)).PinPadMessageCallBack(MESSAGE_ID.fromInteger(MsgId));
		}
		catch(Exception e){
			e.printStackTrace();	
		}
	}
	private static EventHandlerInterface        eventHandler;
	private static BatteryEventHandlerInterface batteryEventHandler;
	private static ConnectedHandlerInterface 	m_connectedHandler;
	private static DisconnectedHandlerInterface m_disconnectedHandler;
    
    //private static Map                          m_eventHandlers        = new Hashtable<Integer,EventHandlerInterface>();
    //private static Map                          m_connectedHandlers    = new Hashtable<Integer,ConnectedHandlerInterface>();
    //private static Map                          m_disconnectedHandlers = new Hashtable<Integer,DisconnectedHandlerInterface>();

    private static Map                          m_eventHandlers        = new Hashtable();
    private static Map                          m_connectedHandlers    = new Hashtable();
    private static Map                          m_disconnectedHandlers = new Hashtable();

	private native static int 		RBA_SDK_Initialize() 				throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_InitializeInstance(String name)     throws  java.lang.UnsatisfiedLinkError;
    private native static int       RBA_SDK_SetInstanceName(int instanceId, String name) throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Shutdown() 					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ShutdownInstance(int instanceId)    throws  java.lang.UnsatisfiedLinkError;
	private native static int  		RBA_SDK_SetParam( int id, String in_data ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int  		RBA_SDK_SetParamFor(int instId, int id, String in_data ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int  		RBA_SDK_AddParam( int id, String in_data ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int  		RBA_SDK_AddParamFor( int instId,int id, String in_data ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_Param( int id ) 		throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_ParamFor(int instId, int id )	throws  java.lang.UnsatisfiedLinkError;
	private native static String 	RBA_SDK_Get_Param_String() 			throws  java.lang.UnsatisfiedLinkError;
	private native static String 	RBA_SDK_Get_Param_StringFor(int instId)    	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_Connection_Status() 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_Connection_StatusFor(int instId) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ResetParam(int paramid ) 								throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ResetParamFor(int instId, int paramid )					throws  java.lang.UnsatisfiedLinkError;
    
    private native static int       RBA_SDK_LockParam() throws  java.lang.UnsatisfiedLinkError;
    private native static int       RBA_SDK_LockParamFor(int instanceId) throws  java.lang.UnsatisfiedLinkError;
    private native static int       RBA_SDK_UnlockParam() throws  java.lang.UnsatisfiedLinkError;
    private native static int       RBA_SDK_UnlockParamFor(int instanceId) throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_ProcessMessage( int msgID ) 							throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ProcessMessageFor(int instId, int msgID ) 							throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SendCustomMessage( String rawData, boolean bReceive ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SendCustomMessageFor(int instId, String rawData, boolean bReceive ) 	throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_Connect(Comm_Settings  settings) 						throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ConnectFor(int instId, Comm_Settings  settings)			throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Reconnect() 											throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ReconnectFor(int instId)								throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Disconnect() 											throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_DisconnectFor(int instId)								throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_SetMessageCallBack()  						            throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetMessageCallBackFor(int instId)           			throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetNotifyRbaConnected(int enable)  	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetNotifyRbaConnectedFor(int instId, int enable)  	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetNotifyRbaDisconnected(int enable)  	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetNotifyRbaDisconnectedFor(int instId, int enable)  	throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_AddTagParam( int forMsgId, int tagId, byte[] in_data ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_AddTagParamFor(int instId, int forMsgId, int tagId, byte[] in_data ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_Tag_Param( int msgId)	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_Tag_ParamFor(int instId, int msgId)	throws  java.lang.UnsatisfiedLinkError;
	private native static byte[] 	RBA_SDK_Get_Tag_Param_Bytes()		throws  java.lang.UnsatisfiedLinkError;
	private native static byte[] 	RBA_SDK_Get_Tag_Param_BytesFor(int instId)		throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ResetTagParam(int msgId, int tagId ) 					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_ResetTagParamFor(int instId, int msgId, int tagId ) 					throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_SetCommTimeouts(Comm_Timeout Timeouts)					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetCommTimeoutsFor(int instId, Comm_Timeout Timeouts)					throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_GetCommTimeoutConnect()					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_GetCommTimeoutConnectFor(int instId)	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_GetCommTimeoutSend()					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_GetCommTimeoutSendFor(int instId)   	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_GetCommTimeoutReceive()					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_GetCommTimeoutReceiveFor(int instId)	throws  java.lang.UnsatisfiedLinkError;


	private native static int 		RBA_SDK_SetAttribute( int id, String in_data ) 					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetAttributeFor(int instId,  int id, String in_data ) 					throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_Get_Attribute( int id ) 	throws  java.lang.UnsatisfiedLinkError;
	private native static String 	RBA_SDK_Get_Attribute_String() 		throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_SetBatteryNotifyThreshold(int threshold)  	throws  java.lang.UnsatisfiedLinkError;

	private native static int 		RBA_SDK_SetProcessMessageSyncMode() 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetProcessMessageSyncModeFor(int instId) 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetProcessMessageAsyncMode() 	throws  java.lang.UnsatisfiedLinkError;
	private native static int 		RBA_SDK_SetProcessMessageAsyncModeFor(int instId) 	throws  java.lang.UnsatisfiedLinkError;

	/***************************************************************************************************************************************
  									LOG API
	 ***************************************************************************************************************************************/

	/**
	 * The level of detail of information to be logged.
	 * <p>
	 * \aboutLogging
	 *
	 * @see RBA_API.SetDefaultLogLevel
	 * @see RBA_API.SetModuleLogLevel
	 */
	public  enum LOG_LEVEL{
		/**
		 *  No logging.
		 */
		LTL_NONE,
		/**
		 * Errors only.
		 */
		LTL_ERROR,
		/**
		 * Errors and warnings.
		 */
		LTL_WARNING,
		/**
		 * Errors, warnings, and general information.
		 */
		LTL_INFO,
		/**
		 *  Errors, warnings, general information, and trace information.
		 */
		LTL_TRACE,
		/**
		 * Errors, warnings, general information, trace information, and debugging information.
		 */
		LTL_DEBUG,;
		private static int toInteger(LOG_LEVEL level)
		{
			switch(level)
			{
				case LTL_NONE: 		return -1;
				case LTL_ERROR: 	return 0; 
				case LTL_WARNING:	return 1;
				case LTL_INFO:		return 2; 
				case LTL_TRACE:		return 3;
				case LTL_DEBUG:		return 4;
				default:			return -1;
			}
		}

	}
	
	/**
	 * Options to define what appears in the logging output.
	 * <p>
	 * \aboutLogging
	 *
	 * @see RBA_API.SetTraceOutputFormatOption
	 */
	public enum LOG_OUTPUT_FORMAT_OPTIONS {
		/**
		 * Date is included
		 */	
	    LOFO_ADD_DATE,
		/**
		 * Date is not included
		 */
	    LOFO_NO_DATE,
		/**
		 * Module name is included	
		 */
	    LOFO_ADD_MODULE_NAME,
		/**
		 * Module name is not included.
		 */
	    LOFO_NO_MODULE_NAME,
		/**
		 * Severity of the information is not included.
		 */
	    LOFO_NO_SEVERITY,
		/**
		 * Severity of the information is not included.
		 */
	    LOFO_ADD_SEVERITY,
		/**
		 * ID for the instance is included.
		 */
	    LOFO_ADD_INSTANCE_ID,
		/**
		 * ID for the instance is not included.
		 */
	    LOFO_NO_INSTANCE_ID,;
		static int toInteger(LOG_OUTPUT_FORMAT_OPTIONS options)
		{
			switch( options )
			{
				case LOFO_ADD_DATE: 		return 0;
				case LOFO_NO_DATE:  		return 1;
				case LOFO_ADD_MODULE_NAME: 	return 2;
				case LOFO_NO_MODULE_NAME:	return 3;
				case LOFO_NO_SEVERITY:		return 4;
				case LOFO_ADD_SEVERITY:		return 5;
				case LOFO_ADD_INSTANCE_ID:	return 6;
				case LOFO_NO_INSTANCE_ID:	return 7;
				default: return -1;
			}
		}
	}

	/**
	 * Sets the event listener for logging message events from the 
	 * \libraryName.
	 * <p>
	 * \javaThreadWarning
	 * <p>
	 * \aboutLogging
	 * 
	 * @param handle The event listener.
	 * 
	 * @see LogTraceInterface
	 * @see RBA_API.LOG_LEVEL
	 * @see RBA_API.SetDefaultLogLevel
	 * @see RBA_API.SetModuleLogLevel
	 * @see RBA_API.SetTraceOutputFormatOption
	 */
	public static void SetLogCallBack(LogTraceInterface handle) throws  java.lang.UnsatisfiedLinkError
	{
		lt_handle = handle;	    	
		RBA_SDK_SetLogCallBack(1);
	}   

	/**
	 * Sets the default level of detail of information to be logged.
	 * <p>
	 * \aboutLogging
	 * 
	 * @param level The level of logging detail.
	 * 
	 * @see RBA_API.LOG_LEVEL
	 * @see RBA_API.SetLogCallBack
	 * @see RBA_API.SetModuleLogLevel
	 * @see RBA_API.SetTraceOutputFormatOption
	 */
	public static void SetDefaultLogLevel(LOG_LEVEL level) throws  java.lang.UnsatisfiedLinkError
	{
		RBA_SDK_SetDefaultLogLevel(LOG_LEVEL.toInteger(level));
	}

	/**
	 * Sets the level of detail of information to be  
	 * logged for the specified module.
	 * <p>
	 * \aboutLogging
	 * 
	 * @param moduleName The name of the module.
	 * @param level The level of logging detail.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.LOG_LEVEL
	 * @see RBA_API.SetDefaultLogLevel
	 * @see RBA_API.SetLogCallBack
	 * @see RBA_API.SetTraceOutputFormatOption
	 */
	public static ERROR_ID SetModuleLogLevel(String moduleName, LOG_LEVEL level) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_SetModuleLogLevel(moduleName,LOG_LEVEL.toInteger(level)));
	}
	
	/**
	 * Sets what information is included in the logging output.
	 * <p>
	 * \aboutLogging
	 * 
	 * @param option What information appears in the logging output.
	 *
	 * @see RBA_API.LOG_OUTPUT_FORMAT_OPTIONS
	 * @see RBA_API.SetDefaultLogLevel
	 * @see RBA_API.SetLogCallBack
	 * @see RBA_API.SetModuleLogLevel
	 */
	public static void SetTraceOutputFormatOption(LOG_OUTPUT_FORMAT_OPTIONS option) throws  java.lang.UnsatisfiedLinkError
	{
		RBA_SDK_SetTraceOutputFormatOption(LOG_OUTPUT_FORMAT_OPTIONS.toInteger(option));
	}

	/**
	 * Triggers the \libraryName to output log and trace data.
	 * <p>
	 * Use this function to have the library generate a log message.
	 * <p>
	 * Use the {@link RBA_API.SetLogCallBack} method to set the event listener 
	 * that the library calls to provide logging information to your application.
	 * <p>
	 * \aboutLogging
	 * 
	 * @param moduleName The name of the module to which to attribute the logging event.
	 * @param severity The level of the logging detail.
	 * @param data The logging message.
	 * 
	 * @return {@link ERROR_ID.RESULT_SUCCESS} if successful; otherwise, 
	 * one of the error codes defined in the {@link ERROR_ID} class.
	 * 
	 * @see RBA_API.SetDefaultLogLevel
	 * @see RBA_API.SetLogCallBack
	 * @see RBA_API.SetModuleLogLevel
	 */
	public static ERROR_ID LogOut( String moduleName, LOG_LEVEL severity, String data ) throws  java.lang.UnsatisfiedLinkError
	{
		return ERROR_ID.fromInteger(RBA_SDK_LogOut(moduleName, LOG_LEVEL.toInteger(severity), data));
	}
	private static void LogOutCallBack(String LOut)
	{
		if(lt_handle != null)
			lt_handle.Log(LOut);
	}

	private static native void RBA_SDK_SetLogCallBack(int enable)						throws  java.lang.UnsatisfiedLinkError;
	private static native void RBA_SDK_SetDefaultLogLevel(int level) 					throws  java.lang.UnsatisfiedLinkError;
	private static native int  RBA_SDK_SetModuleLogLevel(String moduleName, int level) 	throws  java.lang.UnsatisfiedLinkError;
	private static native int  RBA_SDK_LogOut( String module, int severity, String data) throws  java.lang.UnsatisfiedLinkError;
	private static native void RBA_SDK_SetTraceOutputFormatOption(int option)		throws  java.lang.UnsatisfiedLinkError;	

	private static LogTraceInterface lt_handle = null;
    
    private static String m_wrapper_version = "5.2.11";
}

/** @} */
