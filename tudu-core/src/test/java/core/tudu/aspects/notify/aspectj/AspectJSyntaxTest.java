/**
 * 
 */
package tudu.aspects.notify.aspectj;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import tudu.domain.dao.IUserDAO;
import tudu.domain.model.User;
import tudu.service.IUserService;
import tudu.service.impl.TodosServiceImpl;
import tudu.service.impl.UserServiceImpl;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class AspectJSyntaxTest {
	

	/**
	 * method methodAndClassName() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test public void methodAndClassName() throws Exception {
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public void tudu.service.impl.UserServiceImpl.*(String))");
		
		Method enableUserMethod = UserServiceImpl.class.getMethod("enableUser", String.class);
		Method disableUserMethod = UserServiceImpl.class.getMethod("disableUser", String.class);
		Method findUserMethod = UserServiceImpl.class.getMethod("findUser", String.class);
		
		Assert.assertTrue(pointcut.matches(enableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(disableUserMethod,UserServiceImpl.class));
		// not the good return type:
		Assert.assertFalse(pointcut.matches(findUserMethod,UserServiceImpl.class));
		
		// more classes in the same package
		pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public void tudu.service.impl.*.*(String))");
		
		Method deleteTodoMethod = TodosServiceImpl.class.getMethod("deleteTodo",String.class);
		
		Assert.assertTrue(pointcut.matches(enableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(disableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(deleteTodoMethod,TodosServiceImpl.class));
		// not the good return type:
		Assert.assertFalse(pointcut.matches(findUserMethod,UserServiceImpl.class));		
	}

	
	
	/**
	 * method parameters() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test public void parameters() throws Exception {
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public void tudu.service.impl.UserServiceImpl.*(..))");
		
		// user manager
		Method enableUserMethod = UserServiceImpl.class.getMethod("enableUser", String.class);
		Method disableUserMethod = UserServiceImpl.class.getMethod("disableUser", String.class);
		Method createUserMethod = UserServiceImpl.class.getMethod("createUser", User.class);
		Method findUserMethod = UserServiceImpl.class.getMethod("findUser", String.class);
		// todos manager
		Method deleteTodoMethod = TodosServiceImpl.class.getMethod("deleteTodo",String.class);
		
		Assert.assertTrue(pointcut.matches(enableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(disableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(createUserMethod,UserServiceImpl.class));
		Assert.assertFalse(pointcut.matches(findUserMethod,UserServiceImpl.class));
		Assert.assertFalse(pointcut.matches(deleteTodoMethod,TodosServiceImpl.class));		
		
		// no visibility and return type restriction
		/* wildcard for visibility modifier does not work??
		pointcut = new AspectJExpressionPointcut();		
		pointcut.setExpression("execution(* * tudu.service.impl.UserServiceImpl.*(..))");
		
		Assert.assertTrue(pointcut.matches(enableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(disableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(createUserMethod,UserServiceImpl.class));
		// now this one is ok, we don't care about the return type
		Assert.assertTrue(pointcut.matches(createUserMethod,UserServiceImpl.class));
		Assert.assertFalse(pointcut.matches(deleteTodoMethod,TodosServiceImpl.class));
		*/
		
		// visibility omission and no return type restriction
		pointcut = new AspectJExpressionPointcut();		
		pointcut.setExpression("execution(* tudu.service.impl.UserServiceImpl.*(..))");
		
		Assert.assertTrue(pointcut.matches(enableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(disableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(createUserMethod,UserServiceImpl.class));
		// now this one is ok, we don't care about the return type
		Assert.assertTrue(pointcut.matches(createUserMethod,UserServiceImpl.class));
		Assert.assertFalse(pointcut.matches(deleteTodoMethod,TodosServiceImpl.class));
	}

	
	
	/**
	 * method packages() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test public void packages() throws Exception {
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();		
		pointcut.setExpression("execution(* tudu.service.User*.*(..))");
		
		Method enableUserMethodFromService = IUserService.class.getMethod("enableUser", String.class);
		Method updateUserFromDao = IUserDAO.class.getMethod("updateUser", User.class);		
		
		Assert.assertTrue(pointcut.matches(enableUserMethodFromService,IUserService.class));
		Assert.assertFalse(pointcut.matches(updateUserFromDao,IUserDAO.class));
		
		pointcut = new AspectJExpressionPointcut();		
		pointcut.setExpression("execution(* tudu..User*.*(..))");
		
		Assert.assertTrue(pointcut.matches(enableUserMethodFromService,IUserService.class));
		Assert.assertTrue(pointcut.matches(updateUserFromDao,IUserDAO.class));
	}

	
	
	/**
	 * method operators() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test public void operators() throws Exception {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(
				"execution(* tudu.service.impl.UserServiceImpl.*(..)) and not " +
				"execution(* tudu.service.impl.UserServiceImpl.disableUser(..))");
		
		// user manager
		Method enableUserMethod = UserServiceImpl.class.getMethod("enableUser", String.class);
		Method disableUserMethod = UserServiceImpl.class.getMethod("disableUser", String.class);
		Method createUserMethod = UserServiceImpl.class.getMethod("createUser", User.class);
		Method findUserMethod = UserServiceImpl.class.getMethod("findUser", String.class);
		// todos manager
		Method deleteTodoMethod = TodosServiceImpl.class.getMethod("deleteTodo",String.class);
		
		Assert.assertTrue(pointcut.matches(enableUserMethod,UserServiceImpl.class));
		Assert.assertFalse(pointcut.matches(disableUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(createUserMethod,UserServiceImpl.class));
		Assert.assertTrue(pointcut.matches(findUserMethod,UserServiceImpl.class));
		Assert.assertFalse(pointcut.matches(deleteTodoMethod,TodosServiceImpl.class));	
	}

	
	
}
