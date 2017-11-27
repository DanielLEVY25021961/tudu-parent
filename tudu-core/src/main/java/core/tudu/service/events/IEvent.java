package tudu.service.events;

import java.util.Observer;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
public interface IEvent {


	/**
	 * method getType() :<br/>
	 * IEvent type..<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	public String getType();

	
	/**
	 * Fire the event.
	 */
	public void fireEvent();

	
	/**
	 * Add a {@link Observer}.
	 * @param pObserver
	 */
	public void addListener(Observer pObserver);

	
}
