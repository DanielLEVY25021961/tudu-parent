/**
 * 
 */
package tudu.aspects.notify;

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;

/**
 * Empty implementation for AOP testing.
 * @author Arnaud Cogoluegnes
 *
 */
public class ExceptionUserManager extends EmptyUserManager {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createUser(User pUser) throws UserAlreadyExistsException {
		
		if(pUser == null) {
			throw new IllegalArgumentException("user cannot be null");
		}		
	}


}
