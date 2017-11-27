/**
 * 
 */
package tudu.service.notify.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tudu.domain.model.User;
import tudu.service.IUserService;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/service/notify/impl/test-context.xml"
})
public class NotifierUserManagerTest {

	
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
		User user = new User();
		user.setLogin("acogoluegnes");
		this.userService.createUser(user);
		Assert.assertEquals(1, this.notifier.getCount());
	}

	
	
}
