/**
 * 
 */
package tudu.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.test.CleanInsertTestExecutionListener;
import tudu.test.IDataSetLocator;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/domain/dao/jpa/test-context.xml",
		"/tudu/conf/jpa-dao-context.xml",
		"/tudu/security/jdbc-user-details.xml"
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class JdbcUserDetailsServiceTest implements IDataSetLocator {
	
	/**
	 * userDetailsService : UserDetailsService :<br/>
	 * .<br/>
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	
	/**
	 * authenticationProvider : AuthenticationProvider :<br/>
	 * .<br/>
	 */
	@Autowired
	private AuthenticationProvider authenticationProvider;

	
	
	/**
	 * method loadUserByUsername() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void loadUserByUsername() {
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername("acogoluegnes");
		Assert.assertEquals("acogoluegnes", userDetails.getUsername());
		Assert.assertEquals("mdp4arno", userDetails.getPassword());
		Assert.assertTrue(userDetails.isEnabled());
		Assert.assertEquals(2, userDetails.getAuthorities().size());
		
		userDetails = this.userDetailsService.loadUserByUsername("templth");
		Assert.assertEquals("templth", userDetails.getUsername());
		Assert.assertEquals("mdp4th", userDetails.getPassword());
		Assert.assertFalse(userDetails.isEnabled());
		Assert.assertEquals(1, userDetails.getAuthorities().size());
		
		try {
			userDetails = this.userDetailsService.loadUserByUsername("dummy");
			Assert.fail("no user dummy, exception should have been thrown");
		} catch (UsernameNotFoundException e) {
			// ok
		} catch (DataAccessException e) {
			Assert.fail("error while retrieving user");
		}		
	}
	
	@Test public void authenticate() {
		Authentication auth = new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno");
		auth = this.authenticationProvider.authenticate(auth);
		
		auth = new UsernamePasswordAuthenticationToken("acogoluegnes","bad pwd");
		try {
			auth = this.authenticationProvider.authenticate(auth);
			Assert.fail("bad password, exception should have been thrown");
		} catch (AuthenticationException e) {
			// ok
		}
		
		// expired account
		auth = new UsernamePasswordAuthenticationToken("templth","mdp4th");
		try {
			auth = this.authenticationProvider.authenticate(auth);
			Assert.fail("expired account, exception should have been thrown");
		} catch (AuthenticationException e) {
			// ok
		}
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/security/user-details-dataset.xml";
	}
	
}
