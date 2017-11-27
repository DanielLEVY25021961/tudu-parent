/**
 * 
 */
package tudu.security.acl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/security/acl/persistance-context.xml",
		"/tudu/security/acl/security.xml",
		"/tudu/security/acl/security-acl.xml"
})
public class TodoAclTest {

	
	
	/**
	 * dao : ITodoAclDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private ITodoAclDAO dao;

	
	
	/**
	 * authenticationManager : AuthenticationManager :<br/>
	 * .<br/>
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	/**
	 * method creatingAndAclVoter() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void creatingAndAclVoter() {
		// same user creating and deleting
		authenticateUser("acogoluegnes","mdp4arno");		
		AclTodo todo = new AclTodo("test");
		this.dao.save(todo);	
		this.dao.delete(todo);
		
		// one user creating, other user trying to delete
		todo = new AclTodo("test");
		this.dao.save(todo);	
		authenticateUser("templth","mdp4th");		
		
		this.dao.delete(todo);
		Assert.fail("user not allowed to delete");
		 		
		// admin deleting
		authenticateUser("admin","mdp4admin");	
		this.dao.delete(todo);
	}

	
	
	/**
	 * method aclEntryAfter() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void aclEntryAfter() {
		
		authenticateUser("acogoluegnes","mdp4arno");		
		AclTodo todo = new AclTodo("test 1");
		this.dao.save(todo);	
		Long firstTodo = todo.getId();
		todo = new AclTodo("test 2");
		this.dao.save(todo);	
		todo = new AclTodo("test 3");
		this.dao.save(todo);	
		
		authenticateUser("templth","mdp4th");		
		todo = new AclTodo("test 1");
		this.dao.save(todo);	
		todo = new AclTodo("test 2");
		this.dao.save(todo);
		
		// filtering the results
		authenticateUser("acogoluegnes","mdp4arno");	
		Assert.assertEquals(3, this.dao.selectAll().size());
		authenticateUser("templth","mdp4th");		
		Assert.assertEquals(2, this.dao.selectAll().size());
		
		// the first todo
		authenticateUser("acogoluegnes","mdp4arno");
		this.dao.get(firstTodo);
		authenticateUser("templth","mdp4th");	
		try {
			this.dao.get(firstTodo);
			Assert.fail("no access to this domain object");
		} catch (Exception e) {
			// OK
		}
		authenticateUser("admin","mdp4admin");	
		try {
			this.dao.get(firstTodo);
			Assert.fail("no access to this domain object");
		} catch (Exception e) {
			// OK
		}
	}

	
		
	/**
	 * method authenticateUser() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param username
	 * @param password :  :  .<br/>
	 */
	private void authenticateUser(String username,String password) {
		SecurityContextImpl secureContext = new SecurityContextImpl();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
               username,password
        ); 
		
		Authentication auth = this.authenticationManager.authenticate(token);
		secureContext.setAuthentication(auth);
        SecurityContextHolder.setContext(secureContext);
	}

	
	
}
