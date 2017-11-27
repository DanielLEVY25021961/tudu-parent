/**
 * 
 */
package tudu.service.notify.impl;

import tudu.service.notify.IMessage;

/**
 * String-based message.
 * @author Arnaud Cogoluegnes
 *
 */
public class StringMessage implements IMessage {

	
	/**
	 * message : String :<br/>
	 * .<br/>
	 */
	private String message;

	
	
	 /**
	 * method CONSTRUCTEUR StringMessage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pMessage
	 */
	public StringMessage(
			final String pMessage) {
		super();
		this.message = pMessage;
	}

	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.message;
	}
	
	
	
	/**
	 * method getMessage() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	
	
}
