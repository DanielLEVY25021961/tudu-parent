/**
 * 
 */
package tudu.security.voters;

import java.util.List;

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.IUserService;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class EmptyUserManager implements IUserService {


	@Override
	public void createUser(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub

	}


	@Override
	public void disableUser(String login) {
		// TODO Auto-generated method stub

	}


	@Override
	public void enableUser(String login) {
		// TODO Auto-generated method stub

	}


	@Override
	public User findUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findUsersByLogin(String loginStart) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getNumberOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void sendPassword(User user) {
		// TODO Auto-generated method stub

	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

}
