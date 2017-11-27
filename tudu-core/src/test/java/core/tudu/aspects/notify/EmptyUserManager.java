/**
 * 
 */
package tudu.aspects.notify;

import java.util.List;

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.IUserService;

/**
 * Empty implementation for AOP testing.
 * @author Arnaud Cogoluegnes
 *
 */
public class EmptyUserManager implements IUserService {


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createUser(User pUser) throws UserAlreadyExistsException {}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableUser(String pLogin) {}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enableUser(String pLogin) {}

	
	 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findUser(String pLogin) {
		return null;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findUsersByLogin(String pLoginStart) {
		return null;
	}

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getCurrentUser() {
		return null;
	}

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getNumberOfUsers() {
		return 0;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendPassword(User pUser) {}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(User pUser) {}

	
	
}
