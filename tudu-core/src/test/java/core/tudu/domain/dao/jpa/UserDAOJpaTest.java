/**
 * 
 */
package tudu.domain.dao.jpa;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.domain.dao.IUserDAO;
import tudu.domain.model.User;
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
public class UserDAOJpaTest implements IDataSetLocator {

	
	/**
	 * userDAO : IUserDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private IUserDAO userDAO;

	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method getNumberOfUsers() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getNumberOfUsers() {
		assertEquals(3, this.userDAO.getNumberOfUsers());
	}

	
	
	/**
	 * method getUser() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getUser() {
		Assert.assertNotNull(this.userDAO.getUser("acogoluegnes"));
		Assert.assertNull(this.userDAO.getUser("unknown"));
	}

	
	
	/**
	 * method findUsersByLogin() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void findUsersByLogin() {
		Assert.assertEquals(2, this.userDAO.findUsersByLogin("acogo").size());
		Assert.assertEquals(1, this.userDAO.findUsersByLogin("acogolue").size());
		Assert.assertEquals(0, this.userDAO.findUsersByLogin("cogo").size());
		Assert.assertEquals(0, this.userDAO.findUsersByLogin("unknown").size());
	}

	
	
	/**
	 * method saveUser() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void saveUser() {
		
		this.helper.compareTableCount("tuser", 3);
		User toSave = new User();
		toSave.setLogin("jdoe");
		toSave.setPassword("john");
		toSave.setFirstName("john");
		toSave.setLastName("doe");
		toSave.setEnabled(true);
		this.userDAO.saveUser(toSave);
		this.helper.compareTableCount("tuser", 4);
	}

	
	
	/**
	 * method updateUser() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test
	public void updateUser() throws Exception {
		User toUpdate = new User();
		toUpdate.setLogin("acogoluegnes");
		toUpdate.setPassword("arno");
		toUpdate.setFirstName("Arnaud");
		toUpdate.setLastName("Cogoluegnes");
		toUpdate.setEnabled(true);
		this.userDAO.updateUser(toUpdate);
		this.helper.compareTableCount("tuser", 3);
		assertEquals("arno", this.helper.getTable("tuser").getValue(1, "password").toString());
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/user.xml";
	}
	
}
