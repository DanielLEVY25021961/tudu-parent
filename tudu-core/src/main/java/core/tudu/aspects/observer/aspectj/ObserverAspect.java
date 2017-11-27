/**
 * 
 */
package tudu.aspects.observer.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import tudu.service.events.IEvent;



/**
 * class ObserverAspect :<br/>
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
public class ObserverAspect {

	
	
	/**
	 * event : IEvent :<br/>
	 * .<br/>
	 */
	private IEvent event;

	
	
	/**
	 * method getEvent() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	public IEvent getEvent() {
		return this.event;
	}

	
	
	/**
	 * method setEvent() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pEvent :  :  .<br/>
	 */
	public void setEvent(
			final IEvent pEvent) {
		this.event = pEvent;
	}

	
	
	/**
	 * method greffon() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@AfterReturning("execution(* tudu.domain.dao.ITodoDAO.*(...))")
	public void greffon() {
		this.event.fireEvent();
	}

	
	
}
