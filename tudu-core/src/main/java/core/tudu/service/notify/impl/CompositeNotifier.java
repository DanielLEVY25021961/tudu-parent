/**
 * 
 */
package tudu.service.notify.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import tudu.service.notify.IMessage;
import tudu.service.notify.INotifier;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class CompositeNotifier implements INotifier {

	
	/**
	 * notifiers : Set<INotifier> :<br/>
	 * .<br/>
	 */
	private Set<INotifier> notifiers = new LinkedHashSet<INotifier>();


	
	@Override
	public void notify(
			final IMessage pMessage) {
		for(INotifier notifier : this.notifiers) {
			notifier.notify(pMessage);
		}
	}
	

	
	/**
	 * method addNotifier() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pNotifier :  :  .<br/>
	 */
	public void addNotifier(
			final INotifier pNotifier) {
		this.notifiers.add(pNotifier);
	}

	
	
	/**
	 * method setNotifiers() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pNotifiers :  :  .<br/>
	 */
	public void setNotifiers(
			final Set<INotifier> pNotifiers) {
		this.notifiers = pNotifiers;
	}

	
	
}
