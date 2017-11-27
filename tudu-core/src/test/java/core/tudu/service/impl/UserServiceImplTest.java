package tudu.service.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tudu.domain.RolesEnum;
import tudu.domain.dao.IRoleDAO;
import tudu.domain.dao.ITodoDAO;
import tudu.domain.dao.ITodoListDAO;
import tudu.domain.dao.IUserDAO;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;

public class UserServiceImplTest {

	
    /**
     * user : User :<br/>
     * .<br/>
     */
    User user = new User();

    /**
     * userDAO : IUserDAO :<br/>
     * .<br/>
     */
    IUserDAO userDAO = null;
    
    
    /**
     * roleDAO : IRoleDAO :<br/>
     * .<br/>
     */
    IRoleDAO roleDAO = null;
    
    
    /**
     * todoListDAO : ITodoListDAO :<br/>
     * .<br/>
     */
    ITodoListDAO todoListDAO = null;
    
    
    /**
     * todoDAO : ITodoDAO :<br/>
     * .<br/>
     */
    ITodoDAO todoDAO = null;

    
    /**
     * userService : UserServiceImpl :<br/>
     * .<br/>
     */
    UserServiceImpl userService = new UserServiceImpl();

    
    
    
    /**
     * method setUp() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Before
    public void setUp() {
    	
        this.user.setLogin("test_user");
        this.user.setFirstName("First name");
        this.user.setLastName("Last name");

        this.userDAO = createMock(IUserDAO.class);
        this.roleDAO = createMock(IRoleDAO.class);
        this.todoListDAO = createMock(ITodoListDAO.class);
        this.todoDAO = createMock(ITodoDAO.class);

        this.userService.setUserDAO(this.userDAO);
        this.userService.setRoleDAO(this.roleDAO);
        this.userService.setTodoListDAO(this.todoListDAO);
        this.userService.setTodoDAO(this.todoDAO);
    }

    
    
    /**
     * method tearDown() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @After
    public void tearDown() {
        verify(this.userDAO);
        verify(this.roleDAO);
        verify(this.todoListDAO);
        verify(this.todoDAO);
    }

    
    
    /**
     * method replay_() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    private void replay_() {
        replay(this.userDAO);
        replay(this.roleDAO);
        replay(this.todoListDAO);
        replay(this.todoDAO);
    }

    
    
    /**
     * method findUser() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void findUser() {
    	
        expect(this.userDAO.getUser("test_user")).andReturn(this.user);

        replay_();

        User testUser = this.userService.findUser("test_user");
        assertEquals(testUser, this.user);
    }

    
    
    
    /**
     * method updateUser() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void updateUser() {
    	
    	this.userDAO.updateUser(this.user);

        replay_();

        this.userService.updateUser(this.user);
    }

    
    
    /**
     * method createUser() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void createUser() {
    	
        expect(this.userDAO.getUser("test_user")).andReturn(null);

        Role role = new Role();
        role.setRole(RolesEnum.ROLE_USER.toString());
        
        expect(this.roleDAO.getRole(RolesEnum.ROLE_USER.toString())).andReturn(role);

        this.userDAO.saveUser(this.user);

        TodoList todoList = new TodoList();
        this.todoListDAO.saveTodoList(todoList);

        Todo todo = new Todo();
        this.todoDAO.saveTodo(todo);
        this.todoListDAO.updateTodoList(todoList);

        replay_();

        try {
        	
        	this.userService.createUser(this.user);
            assertTrue(this.user.isEnabled());
            assertNotNull(this.user.getCreationDate());
            assertNotNull(this.user.getLastAccessDate());
            assertEquals(1, this.user.getRoles().size());
            Role testRole = this.user.getRoles().iterator().next();
            assertEquals(RolesEnum.ROLE_USER.toString(), testRole.getRole());
            assertEquals(1, this.user.getTodoLists().size());
            TodoList testTodoList = this.user.getTodoLists().iterator().next();
            assertNotNull(testTodoList.getLastUpdate());
            assertEquals(1, testTodoList.getTodos().size());
        } catch (UserAlreadyExistsException e) {
        	fail("A UserAlreadyExistsException should not have been thrown.");
        }
    }

    
    
    /**
     * method failedCreateUser() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void failedCreateUser() {
    	
        expect(this.userDAO.getUser("test_user")).andReturn(this.user);

        replay_();

        try {
        	this.userService.createUser(this.user);
            fail("A UserAlreadyExistsException should have been thrown.");
        } catch (UserAlreadyExistsException e) {

        }
    }
}
