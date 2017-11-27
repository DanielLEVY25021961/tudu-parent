/**
 * 
 */
package tudu.aspects.targetsource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tudu.aspects.targetsource.impl.TuduConnectionImpl;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/aspects/targetsource/swappable-target-source.xml"
})
public class SwappableTargetSourceTest implements ApplicationContextAware {
	
	/**
	 * appContext : ApplicationContext :<br/>
	 * .<br/>
	 */
	private ApplicationContext appContext;


	
	
	/**
	 * method swap() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test @DirtiesContext public void swap() throws Exception {
		
		final TuduConnectionUser tuduConnectionUser 
			= (TuduConnectionUser) this.appContext.getBean("tuduConnectionUser");
		
		Assert.assertEquals("originalBean",tuduConnectionUser.getTuduConnection().getName());
		
		final HotSwappableTargetSource swappableTargetSource 
			= (HotSwappableTargetSource) this.appContext.getBean("swapper");
		
		ITuduConnection newTuduConnection = new TuduConnectionImpl("newTuduConnection");
		
		swappableTargetSource.swap(newTuduConnection);
		
		Assert.assertEquals("newTuduConnection",tuduConnectionUser.getTuduConnection().getName());
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setApplicationContext(
			final ApplicationContext pApplicationContext)
			throws BeansException {
		this.appContext = pApplicationContext;		
	}
	
	
}
