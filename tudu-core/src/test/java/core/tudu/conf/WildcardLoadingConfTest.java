/**
 * 
 */
package tudu.conf;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class WildcardLoadingConfTest {

	@Test public void directInstanciation() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{
						"classpath:tudu/conf/*-context.xml"
				}
		);
	}
	
	@Test public void importInstanciation() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{
						"classpath:tudu/conf/import.xml",
				}
		);
	}
	
}
