/**
 * 
 */
package tudu.aspects.notify.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import tudu.service.notify.INotifier;
import tudu.service.notify.impl.CountNotifier;


/**
 * class NotifierIntroduction :<br/>
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
public class NotifierIntroduction {

	
	/**
	 * mixin : INotifier :<br/>
	 * .<br/>
	 */
	@DeclareParents(
		value="tudu.aspects.notify.EmptyUserManager",
		defaultImpl=CountNotifier.class
	)
	public static INotifier mixin;
	
}
