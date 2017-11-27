/**
 * 
 */
package tudu.service.notify.impl;

import java.util.concurrent.atomic.AtomicInteger;

import tudu.service.notify.IMessage;
import tudu.service.notify.INotifier;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class CountNotifier implements INotifier {
	
	/**
	 * count : AtomicInteger :<br/>
	 * .<br/>
	 */
	private AtomicInteger count = new AtomicInteger();


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notify(
			final IMessage pMessage) {
		this.count.incrementAndGet();
	}

	
	
	/**
	 * method getCount() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	public int getCount() {
		return this.count.get();
	}

}
