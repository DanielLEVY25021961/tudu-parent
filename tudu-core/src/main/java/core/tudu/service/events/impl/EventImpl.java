/**
 * 
 */
package tudu.service.events.impl;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import tudu.service.events.IEvent;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class EventImpl extends Observable implements IEvent {

	
	/**
	 * type : String :<br/>
	 * .<br/>
	 */
	private String type;


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(
			final Observer pObserver) {
		super.addObserver(pObserver);
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireEvent() {
		super.setChanged();
		super.notifyObservers();
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {		
		return this.type;
	}

	
	
	/**
	 * method setListeners() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pListeners :  :  .<br/>
	 */
	public void setListeners(
			final Set<Observer> pListeners) {
		Iterator<Observer> ite = pListeners.iterator();
		while (ite.hasNext()) {
			addObserver(ite.next());
		}
	}

	
	
	/**
	 * method setType() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pType :  :  .<br/>
	 */
	public void setType(
			final String pType) {
		this.type = pType;
	}
	
	

}
