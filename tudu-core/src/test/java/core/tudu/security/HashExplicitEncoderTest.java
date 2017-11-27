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
		"/tudu/security/hash-explicit-encoder.xml"
})
public class HashExplicitEncoderTest {

	
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
		Assert.assertEquals("4938e0cb9a6dd7779f0a8f569aafd3fd21fe086eb7a9503d6cabb6f62350a85708c652bca1d92bb3b146c7b4e14b13c9a1e0157654c12ed749ccb429f0dff699", userDetails.getPassword());		
		
		Authentication auth = new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno");
		auth = this.authenticationProvider.authenticate(auth);
		
		auth = new UsernamePasswordAuthenticationToken("acogoluegnes","bad pwd");

		auth = this.authenticationProvider.authenticate(auth);
		Assert.fail("bad password, exception should have been thrown");

	}
	

	
}
