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
		"/tudu/security/hash.xml"
})
public class HashTest {
	
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
		Assert.assertEquals("bd19c801b24f23ff73f2e2aeac0577a8131b92b2", userDetails.getPassword());		
		
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
