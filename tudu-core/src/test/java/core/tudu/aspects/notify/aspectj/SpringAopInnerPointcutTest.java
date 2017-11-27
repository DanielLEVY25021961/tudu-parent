/**
 * 
 */
package tudu.aspects.notify.aspectj;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tudu.service.IUserService;
import tudu.service.notify.impl.CountNotifier;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/aspects/notify/aspectj/spring-aop-inner-pointcut-context.xml"
})
public class SpringAopInnerPointcutTest {

	
	/**
	 * userService : IUserService :<br/>
	 * .<br/>
	 */
	@Autowired
	private IUserService userService;

	
	/**
	 * notifier : CountNotifier :<br/>
	 * .<br/>
	 */
	@Autowired
	private CountNotifier notifier;


	
	/**
	 * method count() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test @DirtiesContext public void count() throws Exception {
		
		Assert.assertEquals(0, this.notifier.getCount());
		this.userService.createUser(null);
		Assert.assertEquals(1, this.notifier.getCount());
		
		this.userService.disableUser(null);
		Assert.assertEquals(1, this.notifier.getCount());
		
		this.userService.createUser(null);
		Assert.assertEquals(2, this.notifier.getCount());
	}

	
	
}
