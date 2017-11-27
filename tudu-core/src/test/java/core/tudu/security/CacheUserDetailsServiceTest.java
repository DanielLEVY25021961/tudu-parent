/**
 * 
 */
package tudu.security;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import net.sf.ehcache.Cache;
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
		"/tudu/security/cache-user-details.xml"
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class CacheUserDetailsServiceTest implements IDataSetLocator {
	
	/**
	 * authenticationProvider : AuthenticationProvider :<br/>
	 * .<br/>
	 */
	@Autowired
	private AuthenticationProvider authenticationProvider;

	
	
	/**
	 * cache : Cache :<br/>
	 * .<br/>
	 */
	@Autowired
	private Cache cache;

	
	
	/**
	 * method authenticate() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void authenticate() {
		
		assertEquals(0L, this.cache.getStatistics().cacheHitCount());
		Authentication auth = new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno");
		
		auth = this.authenticationProvider.authenticate(auth);
		Assert.assertEquals(0, this.cache.getStatistics().cacheHitCount());
		auth = new UsernamePasswordAuthenticationToken("acogoluegnes","mdp4arno");
		auth = this.authenticationProvider.authenticate(auth);
		Assert.assertEquals(1, this.cache.getStatistics().cacheHitCount());
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/security/user-details-dataset.xml";
	}

	
	
}
