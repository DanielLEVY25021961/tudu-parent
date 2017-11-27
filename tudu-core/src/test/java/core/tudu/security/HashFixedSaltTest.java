/**
 * 
 */
package tudu.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/security/hash-fixed-salt.xml"
})
public class HashFixedSaltTest {

	
	/**
	 * authenticationProvider : AuthenticationProvider :<br/>
	 * .<br/>
	 */
	@Autowired
	private AuthenticationProvider authenticationProvider;

	
	/**
	 * userDetailsService : UserDetailsService :<br/>
	 * .<br/>
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	
	
	/**
	 * method authenticate() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void authenticate() {
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername("acogoluegnes");
		Assert.assertEquals("acogoluegnes", userDetails.getUsername());
		Assert.assertEquals("1af4b424fce834b8ea1cefd17cef7c53a511f8ba", userDetails.getPassword());		
		
		Authentication auth = new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno");
		auth = this.authenticationProvider.authenticate(auth);
		
		auth = new UsernamePasswordAuthenticationToken("acogoluegnes","bad pwd");
		try {
			auth = this.authenticationProvider.authenticate(auth);
			Assert.fail("bad password, exception should have been thrown");
		} catch (AuthenticationException e) {
			// ok
		}
	}
	

	
}
