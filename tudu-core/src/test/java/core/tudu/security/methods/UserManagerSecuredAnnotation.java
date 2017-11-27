/**
 * 
 */
package tudu.security.methods;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.IUserService;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class UserManagerSecuredAnnotation implements IUserService {


	@Override
	@Secured("ROLE_ADMIN")
	public void createUser(User user) throws UserAlreadyExistsException {}
	

	@Override
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public User findUser(String login) {
		return null;
	}


	@Override
	public void disableUser(String login) {}


	@Override
	public void enableUser(String login) {}

	
	@Override
	public List<User> findUsersByLogin(String loginStart) {
		return null;
	}


	@Override
	public User getCurrentUser() {
		return null;
	}


	@Override
	public long getNumberOfUsers() {
		return 0;
	}


	@Override
	public void sendPassword(User user) {}


	@Override
	public void updateUser(User user) {}

}
