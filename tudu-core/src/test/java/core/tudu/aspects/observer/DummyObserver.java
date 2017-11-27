/**
 * 
 */
package tudu.aspects.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;

import tudu.service.events.IEvent;

/**
 * @author Arnaud Cogoluegnes
 * 
 */
public class DummyObserver implements Observer {

	
	/**
	 * count : AtomicInteger :<br/>
	 * .<br/>
	 */
	private AtomicInteger count = new AtomicInteger();


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(
			final Observable pTarget
				, final Object pArg) {
		
		if (pTarget instanceof IEvent) {
			System.out.println("Evènement détecté : " + ((IEvent) pTarget).getType());
			this.count.incrementAndGet();
		}
	}

	
	
	/**
	 * method getCount() :<br/>
	 * Get the count of events.<br/>
	 * <br/>
	 *
	 * @return : int :  .<br/>
	 */
	public int getCount() {
		return this.count.get();
	}

	
	
}
