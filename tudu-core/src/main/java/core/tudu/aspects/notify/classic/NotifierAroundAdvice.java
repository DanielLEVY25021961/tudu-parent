/**
 * 
 */
package tudu.aspects.notify.classic;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import tudu.service.notify.INotifier;
import tudu.service.notify.impl.StringMessage;


/**
 * class NotifierAroundAdvice :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 18 nov. 2017
 *
 */
public class NotifierAroundAdvice implements MethodInterceptor {

	
	
	/**
	 * notifier : INotifier :<br/>
	 * .<br/>
	 */
	private INotifier notifier;

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(
			final MethodInvocation pInvocation) throws Throwable {
		Object ret = pInvocation.proceed();
		this.notifier.notify(new StringMessage("appel de " + pInvocation.getMethod().getName()));
		return ret;
	}

	
	
	/**
	 * method setNotifier() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pNotifier :  :  .<br/>
	 */
	public void setNotifier(
			final INotifier pNotifier) {
		this.notifier = pNotifier;
	}	

	
	
}
