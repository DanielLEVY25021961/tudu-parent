/**
 * 
 */
package tudu.integration;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


import tudu.domain.RolesEnum;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.ITodoListsService;
import tudu.service.ITodosService;
import tudu.service.IUserService;
import tudu.service.UserAlreadyExistsException;
import tudu.test.CleanInsertTestExecutionListener;
import tudu.test.DbUnitHelper;
import tudu.test.IDataSetLocator;

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
public class IntegrationTest implements IDataSetLocator {

	
	/**
	 * userService : IUserService :<br/>
	 * .<br/>
	 */
	@Autowired
	private IUserService userService;

	
	/**
	 * userDetailsService : UserDetailsService :<br/>
	 * .<br/>
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	
	/**
	 * todoListsService : ITodoListsService :<br/>
	 * .<br/>
	 */
	@Autowired
	private ITodoListsService todoListsService;

	
	/**
	 * todosService : ITodosService :<br/>
	 * .<br/>
	 */
	@Autowired
	private ITodosService todosService;

		
	/**
	 * authenticationManager : ProviderManager :<br/>
	 * .<br/>
	 */
	@Autowired
	private ProviderManager authenticationManager;

	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method createUser() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void createUser() {
		
		try {
			this.userService.findUser("test_user");
			fail("User already exists in the database.");
		} catch (ObjectRetrievalFailureException orfe) {
			// User should not already exist in the database.
		}

		User user = new User();
		user.setLogin("test_user");
		user.setPassword("password");
		user.setFirstName("First name");
		user.setLastName("Last name");
		
		try {
			this.userService.createUser(user);
			assertTrue(user.isEnabled());
			assertNotNull(user.getCreationDate());
			assertNotNull(user.getLastAccessDate());
			assertEquals(1, user.getRoles().size());
			Role testRole = user.getRoles().iterator().next();
			assertEquals(RolesEnum.ROLE_USER.toString(), testRole
					.getRole());
			assertEquals(1, user.getTodoLists().size());
			TodoList testTodoList = user.getTodoLists().iterator().next();
			assertNotNull(testTodoList.getLastUpdate());
			assertEquals(1, testTodoList.getTodos().size());
			
		} catch (UserAlreadyExistsException e) {
			fail("User already exists in the database.");
		}

		try {
			User userFoundInDatabase = this.userService.findUser("test_user");
			assertEquals("First name", userFoundInDatabase
					.getFirstName());
			assertEquals("Last name", userFoundInDatabase.getLastName());
		} catch (ObjectRetrievalFailureException orfe) {
			fail("User should have been found in the database.");
		}
	}

	
	
	/**
	 * method createTodoList() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void createTodoList() {
        createAuthenticatedUser();

        TodoList todoList = new TodoList();
        todoList.setName("test_list");

        // check the created user has one todo list
        this.helper.compareTableCount("todo_list", 1);

        this.todoListsService.createTodoList(todoList);

        assertEquals(1, todoList.getUsers().size());
        assertEquals("test_user", todoList.getUsers().iterator().next().getLogin());
        // check the list has been created
        this.helper.compareTableCount("todo_list", 1+1);

        TodoList todoListFromDatabase = this.todoListsService.findTodoList(todoList.getListId());
        assertEquals("test_list", todoListFromDatabase.getName());
    }

	
	
	
	/**
	 * method deleteTodoList() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void deleteTodoList() {
		
        createAuthenticatedUser();

        TodoList todoList = new TodoList();
        todoList.setName("test_list");

        // check the created user has one todo list
        this.helper.compareTableCount("todo_list", 1);
        this.todoListsService.createTodoList(todoList);
        
        // check the list has been created
        this.helper.compareTableCount("todo_list", 1+1);
        this.todoListsService.deleteTodoList(todoList.getListId());
        
        // check the list has been deleted
        this.helper.compareTableCount("todo_list", 1);

        try {
        	this.todoListsService.findTodoList(todoList.getListId());
            fail("The todo list should have been deleted");
        } catch (ObjectRetrievalFailureException orfe) {
            // The todo list should not exist.
        }
    }

	
	
	/**
	 * method createTodo() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test public void createTodo() {
		
        createAuthenticatedUser();
        // one todo created when user created
        this.helper.compareTableCount("todo", 1);
        TodoList todoList = new TodoList();
        todoList.setName("some todo list");
        this.todoListsService.createTodoList(todoList);

        Todo todo = new Todo();
        todo.setDescription("test_todo");        

        this.todosService.createTodo(todoList.getListId(), todo);
        assertNotNull(todo.getCreationDate());
        // check todo has been created
        this.helper.compareTableCount("todo", 1+1);
    }

	
	
	
	@Test public void deleteTodo() {
        createAuthenticatedUser();
        // one todo created when user created
        this.helper.compareTableCount("todo", 1);
        
        TodoList todoList = new TodoList();
        todoList.setName("some todo list");
        this.todoListsService.createTodoList(todoList);

        Todo todo = new Todo();
        todo.setDescription("test_todo");

        this.todosService.createTodo(todoList.getListId(), todo);
        // check todo has been created
        this.helper.compareTableCount("todo", 1+1);
        this.todosService.deleteTodo(todo.getTodoId());
        // check todo has been deleted
        this.helper.compareTableCount("todo", 1);
    }

	
	
	
    /**
     * method sharedList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void sharedList() {
        createAuthenticatedUser();
        User user2 = new User();
        user2.setLogin("test_user2");
        user2.setPassword("test_password");
        try {
        	this.userService.createUser(user2);
        } catch (UserAlreadyExistsException e) {
            fail("User already exists in the database.");
        }
        
        // one todo list for each user (after creation)
        this.helper.compareTableCount("todo_list", 2);
        
        TodoList todoList = new TodoList();
        todoList.setName("shared todo list");
        this.todoListsService.createTodoList(todoList);
        this.todoListsService.addTodoListUser(todoList.getListId(), "test_user2");
        // one todo list added 
        this.helper.compareTableCount("todo_list", 2+1);
        // one todo list for each + the shared todo list
        this.helper.compareTableCount("tuser_todo_list", 2+1+1);
        
        Todo todo = new Todo();
        todo.setDescription("test_todo");
        this.todosService.createTodo(todoList.getListId(), todo);
        // one todo each user + one just created
        this.helper.compareTableCount("todo", 2+1);

        this.todoListsService.deleteTodoList(todoList.getListId());
        // the user creation todo list
        this.helper.compareTableCount("tuser_todo_list", 2);
    }

    
    
    /**
     * method createAuthenticatedUser() :<br/>
     * Authenticate the current user using Acegi Security.<br/>
     * <br/>
     * :  :  .<br/>
     */
    private void createAuthenticatedUser() {
    	
        User user = new User();
        user.setLogin("test_user");
        user.setPassword("test_password");
        try {
            this.userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
            fail("User already exists in the database.");
        }

        SecurityContextImpl secureContext = new SecurityContextImpl();       

        UserDetails userDetails = this.userDetailsService.loadUserByUsername("test_user");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, "test_password"
        );       

        this.authenticationManager.authenticate(token);
        secureContext.setAuthentication(token);
        SecurityContextHolder.setContext(secureContext);
    }

    
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/integration/dataset.xml";
	}

	
	
}
