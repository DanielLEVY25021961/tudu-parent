/**
 * 
 */
package tudu.aspects.targetsource;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/aspects/targetsource/poolable-target-source.xml"
})
public class PoolableTargetSourceTest implements ApplicationContextAware {
	
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
		
		/* some code to test the pooling feature 
		 * if the number of threads is high and the getName is long to run,
		 * the pool is used (new instances are created and used).
		 * 
		 */
		/*
		ExecutorService executor = Executors.newFixedThreadPool(20);
		
		
		for(int i=0;i<30;i++) {
			final ITuduConnection connection = (ITuduConnection) appContext.getBean("tuduConnection");
			executor.execute(new Runnable() {
				public void run() {
					connection.getName();					
				}
			});
		}
		
		Thread.sleep(10000);
		*/
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
