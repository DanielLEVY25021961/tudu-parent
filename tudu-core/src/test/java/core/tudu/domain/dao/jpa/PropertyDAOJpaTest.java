/**
 * 
 */
package tudu.domain.dao.jpa;

import org.dbunit.dataset.ITable;
import org.junit.Assert;
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
		"/tudu/domain/dao/jpa/test-context.xml",
		"/tudu/conf/jpa-dao-context.xml"		
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class PropertyDAOJpaTest implements IDataSetLocator {

	
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
	 * method getPropertyOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getPropertyOK() {
		
		PropertyConfiguration prop = this.propertyDAO.getProperty("smtp.host");
		Assert.assertNotNull(prop);
		Assert.assertEquals("smtp.host",prop.getKey());
		Assert.assertEquals("some.url.com",prop.getValue());
	}

	
	
	/**
	 * method getPropertyNOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getPropertyNOK() {
		PropertyConfiguration prop = this.propertyDAO.getProperty("some.dummy.key");
		Assert.assertNull(prop);
	}

	
	
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
	 * method updateProperty() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test
	public void updateProperty() throws Exception {
		PropertyConfiguration toUpdate = new PropertyConfiguration();
		toUpdate.setKey("smtp.host");
		toUpdate.setValue("some.other.host");
		this.propertyDAO.updateProperty(toUpdate);
		ITable table = this.helper.getTable("property");
		Assert.assertEquals("some.other.host",table.getValue(0, "value").toString());
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/property.xml";
	}
	
	
}
