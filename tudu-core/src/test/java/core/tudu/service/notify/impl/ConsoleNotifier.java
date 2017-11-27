/**
 * 
 */
package tudu.service.notify.impl;

import tudu.service.notify.IMessage;
import tudu.service.notify.INotifier;

/**
 * Console-based notifier.
 * @author Arnaud Cogoluegnes
 *
 */
public class ConsoleNotifier implements INotifier {	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notify(
			final IMessage pMessage) {
		
		System.out.println(pMessage.toString());
	}

	
	
}
