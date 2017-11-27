/**
 * 
 */
package tudu.aspects.notify.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

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
@Aspect
public class NotifierAfterThrowingAdvice {

	
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
	 * @param pJointPoint :  :  .<br/>
	 */
	@AfterThrowing("execution(* tudu.aspects.notify.ExceptionUserManager.createUser(..))")
	public void handleNotification(
			final JoinPoint pJointPoint) {
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
