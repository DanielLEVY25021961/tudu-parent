/**
 * 
 */
package tudu.service.notify.impl;

import java.util.List;

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.IUserService;
import tudu.service.notify.IMessage;
import tudu.service.notify.INotifier;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class NotifierUserManager implements IUserService {

	
	/**
	 * notifier : INotifier :<br/>
	 * .<br/>
	 */
	private INotifier notifier;


	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createUser(
			final User pUser) throws UserAlreadyExistsException {
		IMessage message = new StringMessage("Création de l'utilisateur " + pUser.getLogin());
		this.notifier.notify(message);		
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

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableUser(String login) {}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enableUser(String login) {}

	
	@Override
	public User findUser(String login) {		
		return null;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findUsersByLogin(String loginStart) {		
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
	public void sendPassword(User user) {}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(User user) {}
	
	
		
}
