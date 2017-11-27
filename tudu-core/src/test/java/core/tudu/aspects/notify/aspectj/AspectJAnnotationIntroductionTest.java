/**
 * 
 */
package tudu.aspects.notify.aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tudu.service.IUserService;
import tudu.service.notify.INotifier;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/aspects/notify/aspectj/aspectj-annotation-introduction-context.xml"
})
public class AspectJAnnotationIntroductionTest {
	
	/**
	 * userService : IUserService :<br/>
	 * .<br/>
	 */
	@Autowired
	private IUserService userService;

	
	/**
	 * notifier : INotifier :<br/>
	 * .<br/>
	 */
	@Autowired
	private INotifier notifier;

	
	/**
	 * method count() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test @DirtiesContext public void count() throws Exception {	
		// the fact that user service and the notifier are
		// correctly wired is just the test (they are actually the
		// same object!)
	}

	
	
}
