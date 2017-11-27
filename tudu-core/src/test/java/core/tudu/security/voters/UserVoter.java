/**
 * 
 */
package tudu.security.voters;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import tudu.domain.model.User;


/**
 * @author Arnaud Cogoluegnes
 *
 */
public class UserVoter implements AccessDecisionVoter<User> {
	
	/**
	 * USER_PREFIX : String :<br/>
	 * .<br/>
	 */
	private static final String USER_PREFIX = "USER_";


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return attribute.getAttribute() != null && attribute.getAttribute().startsWith(USER_PREFIX);
	}

	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int vote(Authentication pAuthentication, User pObject, Collection<ConfigAttribute> pAttributes) {
		
		String username = pAuthentication.getName();
		
		int result = ACCESS_ABSTAIN;
		
		for(Object element : pAttributes) {
			
			ConfigAttribute attribute = (ConfigAttribute) element;
			
			if(this.supports(attribute)) {
				
				result = ACCESS_DENIED;
				
				if(attribute.getAttribute().substring(USER_PREFIX.length(), attribute.getAttribute().length()).equalsIgnoreCase(username)) {
					return ACCESS_GRANTED;
				}				
			}
		}
		return result;
	}
	
	
}
