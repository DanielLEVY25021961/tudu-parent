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

import tudu.domain.model.User;
import tudu.service.IUserService;
import tudu.service.notify.impl.CountNotifier;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/aspects/notify/aspectj/aspectj-args-context.xml"
})
public class AspectJAnnotationArgsTest {
	
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


	
	@Test @DirtiesContext public void count() throws Exception {
		
		Assert.assertEquals(0, this.notifier.getCount());
		this.userService.createUser(null);
		Assert.assertEquals(1, this.notifier.getCount());
		
		// the pointcut is on all methods but this one does not the good parameter
		// according to the advice
		this.userService.disableUser(null);
		Assert.assertEquals(1, this.notifier.getCount());
		
		this.userService.createUser(new User());
		Assert.assertEquals(2, this.notifier.getCount());
	}
	
	
}
