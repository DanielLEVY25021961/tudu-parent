/**
 * 
 */
package tudu.security.methods;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tudu.service.IUserService;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/tudu/security/methods/secured-annotation.xml")
public class UserManagerSecuredAnnotationTest {

	
	/**
	 * userService : IUserService :<br/>
	 * .<br/>
	 */
	@Autowired
	private IUserService userManager;

	
	/**
	 * authenticationManager : AuthenticationManager :<br/>
	 * .<br/>
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	/**
	 * method securedMethods() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test public void securedMethods() throws Exception {
		
		Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno"));
		SecurityContextImpl secureContext = new SecurityContextImpl(); 
		secureContext.setAuthentication(auth);
        SecurityContextHolder.setContext(secureContext);
        
        this.userManager.createUser(null);
        this.userManager.findUser(null);
        
        auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("templth","mdp4th"));
        secureContext = new SecurityContextImpl(); 
		secureContext.setAuthentication(auth);
        SecurityContextHolder.setContext(secureContext);
        try {
        	this.userManager.createUser(null);
			Assert.fail("method not allowed to ROLE_USER");
		} catch (Exception e) {
			// OK
		}
        this.userManager.findUser(null);
	}
	
}
