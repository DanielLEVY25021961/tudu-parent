/**
 * 
 */
package tudu.aspects.notify.classic;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

import tudu.service.notify.INotifier;
import tudu.service.notify.impl.StringMessage;




/**
 * class NotifierAfterThrowingAdvice :<br/>
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
public class NotifierAfterThrowingAdvice implements ThrowsAdvice {
	

	/**
	 * notifier : INotifier :<br/>
	 * .<br/>
	 */
	private INotifier notifier;

	
	
	/**
	 * method afterThrowing() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pMethod
	 * @param pArgs
	 * @param pTarget
	 * @param pEx :  :  .<br/>
	 */
	public void afterThrowing(
			final Method pMethod
				, final Object[] pArgs
					, final Object pTarget
						, final Exception pEx) {
		this.notifier.notify(new StringMessage("exception lancée dans la méthode " + pMethod.getName()));
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
