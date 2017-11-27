/**
 * 
 */
package tudu.domain.dao.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.domain.RolesEnum;
import tudu.domain.dao.IRoleDAO;
import tudu.domain.model.Role;
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
public class RoleDAOJpaTest implements IDataSetLocator {
	
	/**
	 * roleDAO : IRoleDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private IRoleDAO roleDAO;
	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method getRoleOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getRoleOK() {
		Role role = this.roleDAO.getRole("role_admin");
		Assert.assertEquals("role_admin",role.getRole());
	}

	
	
	/**
	 * method getRoleNOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getRoleNOK() {
		try {
			this.roleDAO.getRole("role_missing");			
			Assert.fail("role does not exist, a exception should have been thrown");
		} catch (ObjectRetrievalFailureException e) {
			// OK
		}
	}

	
	
	/**
	 * method saveRole() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test
	public void saveRole() throws Exception {
		this.helper.compareTableCount("role", 1);
		Role role = new Role();
		role.setRole(RolesEnum.ROLE_USER.name());
		this.roleDAO.saveRole(role);
		this.helper.compareTableCount("role", 2);
		Assert.assertEquals(RolesEnum.ROLE_USER.name(), this.helper.getTable("role").getValue(0, "role"));
	}
	
	
	
	/**
	 * method getDataSet() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/role.xml";
	}

	
	
}
