/**
 * 
 */
package tudu.security;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/security/provider-manager.xml"
})
public class ProviderManagerTest {
	
	/**
	 * authenticationManager : AuthenticationManager :<br/>
	 * .<br/>
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	/**
	 * method authenticate() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void authenticate() {		
		Authentication auth = new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno");
		auth = this.authenticationManager.authenticate(auth);
		
		auth = new UsernamePasswordAuthenticationToken("acogoluegnes","bad pwd");
		try {
			auth = this.authenticationManager.authenticate(auth);
			fail("bad password, exception should have been thrown");
		} catch (AuthenticationException e) {
			// ok
		}
		
		auth = new UsernamePasswordAuthenticationToken("templth","mdp4th");
		auth = this.authenticationManager.authenticate(auth);
	}
	

	
}
