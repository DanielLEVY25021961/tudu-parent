package tudu.service.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tudu.domain.dao.ITodoDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.security.PermissionDeniedException;
import tudu.service.ITodoListsService;
import tudu.service.IUserService;

public class TodosServiceImplTest {

    /**
     * todo : Todo :<br/>
     * .<br/>
     */
    Todo todo = new Todo();
 
    
    /**
     * todoList : TodoList :<br/>
     * .<br/>
     */
    TodoList todoList = new TodoList();
    
    
    /**
     * user : User :<br/>
     * .<br/>
     */
    User user = new User();

    
    /**
     * todoDAO : ITodoDAO :<br/>
     * .<br/>
     */
    ITodoDAO todoDAO = null;
    
    
    /**
     * todoListsService : ITodoListsService :<br/>
     * .<br/>
     */
    ITodoListsService todoListsService = null;
    
    
    /**
     * userService : IUserService :<br/>
     * .<br/>
     */
    IUserService userService = null;

    
    /**
     * todosService : TodosServiceImpl :<br/>
     * .<br/>
     */
    TodosServiceImpl todosService = new TodosServiceImpl();

  
    
    
    /**
     * method setUp() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Before
    public void setUp() {
    	
        this.todo.setTodoId("0001");
        this.todo.setDescription("Test description");
        this.todo.setPriority(0);
        this.todo.setCompleted(false);

        this.todoList.setListId("001");
        this.todoList.setName("Test Todo List");
        this.todoList.setRssAllowed(false);

        this.user.setLogin("test_user");
        this.user.setFirstName("First name");
        this.user.setLastName("Last name");

        this.todoDAO = createMock(ITodoDAO.class);
        this.todoListsService = createMock(ITodoListsService.class);
        this.userService = createMock(IUserService.class);

        this.todosService.setTodoDAO(this.todoDAO);
        this.todosService.setTodoListsManager(this.todoListsService);
        this.todosService.setUserManager(this.userService);
    }

    
    
    /**
     * method tearDown() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @After
    public void tearDown() {
        verify(this.todoDAO);
        verify(this.todoListsService);
        verify(this.userService);
    }

    private void replay_() {
        replay(this.todoDAO);
        replay(this.todoListsService);
        replay(this.userService);
    }

    
    
    /**
     * method findTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void findTodo() {
    	
    	this.todo.setTodoList(this.todoList);
        expect(this.todoDAO.getTodo("0001")).andReturn(this.todo);

        this.user.getTodoLists().add(this.todoList);
        expect(this.userService.getCurrentUser()).andReturn(this.user);

        replay_();

        try {
            Todo testTodo = this.todosService.findTodo("0001");
            assertEquals(this.todo, testTodo);
        } catch (PermissionDeniedException pde) {
        	fail("Permission denied when looking for Todo.");
        }
    }

    
    
    /**
     * method failedFindTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void failedFindTodo() {
    	
        expect(this.todoDAO.getTodo("0001")).andReturn(this.todo);

        expect(this.userService.getCurrentUser()).andReturn(this.user);

        replay_();

        try {
        	this.todosService.findTodo("0001");
            fail("A PermissionDeniedException should have been thrown");
        } catch (PermissionDeniedException pde) {

        }
    }

    
    
    /**
     * method createTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void createTodo() {
    	
        expect(this.todoListsService.findTodoList("001")).andReturn(this.todoList);

        this.todoDAO.saveTodo(this.todo);

        this.todoListsService.updateTodoList(this.todoList);

        replay_();

        this.todosService.createTodo("001", this.todo);

        assertNotNull(this.todo.getCreationDate());
        assertEquals(this.todoList, this.todo.getTodoList());
        assertTrue(this.todoList.getTodos().contains(this.todo));
    }

    
    
    /**
     * method updateTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void updateTodo() {
    	
    	this.todoDAO.updateTodo(this.todo);
    	this.todoListsService.updateTodoList(this.todo.getTodoList());

        replay_();

        this.todo.setCompleted(true);
        this.todosService.updateTodo(this.todo);
        assertTrue(this.todo.isCompleted());
    }

    
    
    /**
     * method deleteTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void deleteTodo() {
    	
    	this.todo.setTodoList(this.todoList);
    	this.todoList.getTodos().add(this.todo);
        expect(this.todoDAO.getTodo("0001")).andReturn(this.todo);

        this.user.getTodoLists().add(this.todoList);
        expect(this.userService.getCurrentUser()).andReturn(this.user);

        this.todoDAO.removeTodo("0001");
        this.todoListsService.updateTodoList(this.todo.getTodoList());

        replay_();

        this.todosService.deleteTodo("0001");

        assertFalse(this.todoList.getTodos().contains(this.todo));
    }

    
    
    /**
     * method completeTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void completeTodo() {
    	
    	this.todo.setTodoList(this.todoList);
    	this.todoList.getTodos().add(this.todo);
        expect(this.todoDAO.getTodo("0001")).andReturn(this.todo);

        this.user.getTodoLists().add(this.todoList);
        expect(this.userService.getCurrentUser()).andReturn(this.user);

        this.todoListsService.updateTodoList(this.todo.getTodoList());

        replay_();

        Todo todoLocal = this.todosService.completeTodo("0001");

        assertTrue(todoLocal.isCompleted());
        assertNotNull(todoLocal.getCompletionDate());
        
    }

    
    
    /**
     * method reopenTodo() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void reopenTodo() {
    	
    	this.todo.setTodoList(this.todoList);
    	this.todoList.getTodos().add(this.todo);
        expect(this.todoDAO.getTodo("0001")).andReturn(this.todo);

        this.user.getTodoLists().add(this.todoList);
        expect(this.userService.getCurrentUser()).andReturn(this.user);

        this.todoListsService.updateTodoList(this.todo.getTodoList());

        replay_();

        Todo todoLocal = this.todosService.reopenTodo("0001");

        assertFalse(todoLocal.isCompleted());
        assertNull(todoLocal.getCompletionDate());
    }
    
    
}
