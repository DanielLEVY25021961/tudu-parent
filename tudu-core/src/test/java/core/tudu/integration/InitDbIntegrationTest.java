/**
 * 
 */
package tudu.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.test.CleanInsertTestExecutionListener;
import tudu.test.IDataSetLocator;
import tudu.test.DbUnitHelper;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/conf/jpa-dao-context.xml",
        "/tudu/conf/service-context.xml",
        "/tudu/conf/transaction-context.xml",
        "/tudu/conf/security-context.xml",
        "/tudu/integration/datasource-test-context.xml",        
        "/tudu/integration/test-context.xml"
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class InitDbIntegrationTest implements IDataSetLocator {
	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method initDB() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void initDB() {
		
		this.helper.compareTableCount("property",7);
		this.helper.compareTableCount("role",2);
		this.helper.compareTableCount("tuser", 1);
		this.helper.compareTableCount("role", 2);
		this.helper.compareTableCount("tuser_role", 2);
	}
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/integration/dataset.xml";
	}

	
	
}
