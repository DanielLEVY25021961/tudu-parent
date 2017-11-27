/**
 * 
 */
package tudu.domain.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;
import tudu.test.CleanInsertTestExecutionListener;
import tudu.test.IDataSetLocator;
import tudu.test.DbUnitHelper;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/domain/dao/init-db-context.xml",
		"/tudu/domain/dao/jdbc/test-context.xml"
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class PropertyDAOJdbcTest implements IDataSetLocator {
	
	/**
	 * propertyDAO : IPropertyDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private IPropertyDAO propertyDAO; 

	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method saveProperty() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void saveProperty() {
		// check we have only one row
		this.helper.compareTableCount("property", 1);
		PropertyConfiguration toSave = new PropertyConfiguration();
		toSave.setKey("some.key");
		toSave.setValue("some.value");
		this.propertyDAO.saveProperty(toSave);
		// insert ok
		this.helper.compareTableCount("property", 2);
	}	

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/property.xml";
	}

	
	
}
