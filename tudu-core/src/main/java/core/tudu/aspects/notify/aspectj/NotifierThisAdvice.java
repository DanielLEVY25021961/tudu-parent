/**
 * 
 */
package tudu.aspects.notify.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import tudu.service.notify.INotifier;
import tudu.service.notify.impl.StringMessage;


/**
 * class NotifierThisAdvice :<br/>
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
@Aspect
public class NotifierThisAdvice {

	
	/**
	 * notifier : INotifier :<br/>
	 * .<br/>
	 */
	private INotifier notifier;

	
	
	/**
	 * method handleNotification() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pJointPoint
	 * @param pProxy :  :  .<br/>
	 */
	@AfterReturning(
		"execution(* tudu.aspects.notify.EmptyUserManager.createUser(..)) and this(proxy)"
	)
	public void handleNotification(
			final JoinPoint pJointPoint, final Object pProxy) {
		this.notifier.notify(new StringMessage("appel de " + pJointPoint.getSignature().getName()));
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
