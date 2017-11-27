/**
 * 
 */
package tudu.aspects.notify.classic;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import tudu.service.notify.INotifier;
import tudu.service.notify.impl.StringMessage;


/**
 * class NotifierBeforeAdvice :<br/>
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
public class NotifierBeforeAdvice implements MethodBeforeAdvice {

	
	/**
	 * notifier : INotifier :<br/>
	 * .<br/>
	 */
	private INotifier notifier;
	

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void before(
			final Method pMethod
				, final Object[] pArgs
					, final Object pTarget)
								throws Throwable {
		this.notifier.notify(new StringMessage("appel de " + pMethod.getName()));		
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
