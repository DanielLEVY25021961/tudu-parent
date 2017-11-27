package tudu.service.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;


import static org.junit.Assert.*;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tudu.domain.dao.ITodoDAO;
import tudu.domain.dao.ITodoListDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.security.PermissionDeniedException;
import tudu.service.IUserService;

public class TodoListsServiceImplTest {

    /**
     * todoListBackup : String :<br/>
     * .<br/>
     */
    static String todoListBackup = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<todolist>" + " <title>test list</title>" + " <rss>true</rss>"
            + " <users>" + "  <user>test</user>" + " </users>" + " <todos>"
            + "  <todo id=\"0001\">"
            + "   <creationDate>1127860040000</creationDate>"
            + "   <description>test todo</description>"
            + "   <priority>10</priority>" + "   <completed>false</completed>"
            + "  </todo>" + " </todos>" + "</todolist>";

    
    
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
     * userService : IUserService :<br/>
     * .<br/>
     */
    IUserService userService = null;

    
    
    /**
     * todoListsService : TodoListsServiceImpl :<br/>
     * .<br/>
     */
    TodoListsServiceImpl todoListsService = new TodoListsServiceImpl();

    
    
    /**
     * method setUp() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Before
    public void setUp() {
        this.todoList.setListId("001");
        this.todoList.setName("Test Todo List");
        this.todoList.setRssAllowed(false);

        this.user.setLogin("test_user");
        this.user.setFirstName("First name");
        this.user.setLastName("Last name");

        this.todoListDAO = createMock(ITodoListDAO.class);
        this.userService = createMock(IUserService.class);

        this.todoDAO = createMock(ITodoDAO.class);

        this.todoListsService.setTodoListDAO(this.todoListDAO);
        this.todoListsService.setTodoDAO(this.todoDAO);
        this.todoListsService.setUserManager(this.userService);
    }

    
    
    /**
     * method tearDown() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @After
    public void tearDown() {
        verify(this.todoListDAO);
        verify(this.todoDAO);
        verify(this.userService);
    }

    
    
    
    /**
     * method replay_() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    private void replay_() {
        replay(this.todoListDAO);
        replay(this.todoDAO);
        replay(this.userService);
    }

 
    
    
    /**
     * method createTodoList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void createTodoList() {
    	
    	this.todoListDAO.saveTodoList(this.todoList);

        expect(this.userService.getCurrentUser()).andReturn(this.user);
        this.userService.updateUser(this.user);

        replay_();

        this.todoListsService.createTodoList(this.todoList);

        assertTrue(this.user.getTodoLists().contains(this.todoList));
    }

    
    
    
    /**
     * method testFindTodoList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void testFindTodoList() {
    	
    	this.todoList.getUsers().add(this.user);
    	this.user.getTodoLists().add(this.todoList);

        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);

        expect(this.userService.getCurrentUser()).andReturn(this.user);

        replay_();
        
        try {
            TodoList testTodoList = this.todoListsService.findTodoList("001");
            assertEquals(this.todoList, testTodoList);
        } catch (PermissionDeniedException pde) {
        	fail("Permission denied when looking for Todo.");
        }
    }

    
    
    /**
     * method failedFindTodoList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void failedFindTodoList() {
    	
        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);

        expect(this.userService.getCurrentUser()).andReturn(this.user);

        replay_();

        try {
        	this.todoListsService.findTodoList("001");
            fail("A PermissionDeniedException should have been thrown");
        } catch (PermissionDeniedException pde) {

        }
    }

    
    
    /**
     * method updateTodoList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void updateTodoList() {
    	
    	this.todoListDAO.updateTodoList(this.todoList);

        replay_();

        this.todoListsService.updateTodoList(this.todoList);
    }

    
    
    /**
     * method deleteTodoList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void deleteTodoList() {
    	
    	this.todoList.getUsers().add(this.user);
    	this.user.getTodoLists().add(this.todoList);

        expect(this.userService.getCurrentUser()).andReturn(this.user);

        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);

        this.userService.updateUser(this.user);

        this.todoListDAO.removeTodoList("001");

        replay_();

        this.todoListsService.deleteTodoList("001");

        assertFalse(this.user.getTodoLists().contains(this.todoList));
    }

    
        
    /**
     * method addTodoListUser() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void addTodoListUser() {
    	this.todoList.getUsers().add(this.user);
    	this.user.getTodoLists().add(this.todoList);

        expect(this.userService.getCurrentUser()).andReturn(this.user);

        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);

        User user2 = new User();
        user2.setLogin("another_user");
        expect(this.userService.findUser("another_user")).andReturn(user2);

        this.todoListDAO.updateTodoList(this.todoList);

        replay_();

        this.todoListsService.addTodoListUser("001", "another_user");

        assertTrue(this.todoList.getUsers().contains(user2));
        assertTrue(user2.getTodoLists().contains(this.todoList));
    }

       
    
    /**
     * method deleteTodoListUser() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void deleteTodoListUser() {
    	
    	this.todoList.getUsers().add(this.user);
    	this.user.getTodoLists().add(this.todoList);

        User user2 = new User();
        user2.setLogin("another_user");
        user2.getTodoLists().add(this.todoList);
        this.todoList.getUsers().add(user2);

        expect(this.userService.getCurrentUser()).andReturn(this.user);

        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);

        expect(this.userService.findUser("another_user")).andReturn(user2);

        this.todoListDAO.updateTodoList(this.todoList);

        replay_();

        this.todoListsService.deleteTodoListUser("001", "another_user");

        assertFalse(this.todoList.getUsers().contains(user2));
        assertFalse(user2.getTodoLists().contains(this.todoList));
    }

    
    
    
    /**
     * method backupTodoList() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test public void backupTodoList() {
    	
    	this.todoList.getUsers().add(this.user);

        Todo todo = new Todo();
        todo.setTodoId("0001");
        Calendar creationCal = Calendar.getInstance();
        creationCal.clear();
        creationCal.set(Calendar.YEAR, 2005);
        todo.setCreationDate(creationCal.getTime());
        todo.setDescription("Backup Test description");
        todo.setPriority(0);
        todo.setCompleted(false);

        this.todoList.getTodos().add(todo);

        replay_();

        final Document doc = this.todoListsService.backupTodoList(this.todoList);

        final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        
        String xmlContent = outputter.outputString(doc);

        assertTrue(xmlContent.indexOf("<title>Test Todo List</title>") > 0);
        assertTrue(xmlContent.indexOf("<todo id=\"0001\">") > 0);
        assertTrue(xmlContent.indexOf("<creationDate>"
                + creationCal.getTimeInMillis() + "</creationDate>") > 0);
        assertTrue(xmlContent
                .indexOf("<description>Backup Test description</description>") > 0);
        assertTrue(xmlContent.indexOf("<priority>0</priority>") > 0);
        assertTrue(xmlContent.indexOf("<completed>false</completed>") > 0);
    }

    
    
    /**
     * method restoreTodoListCreate() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception :  :  .<br/>
     */
    @Test public void restoreTodoListCreate() throws Exception {
    	
        InputStream content = new ByteArrayInputStream(todoListBackup
                .getBytes());

        expect(this.userService.getCurrentUser()).andReturn(this.user);
        
        TodoList todoListLocal = new TodoList();
        this.todoListDAO.saveTodoList(todoListLocal);
        this.userService.updateUser(this.user);
        Todo todo = new Todo();
        this.todoDAO.saveTodo(todo);

        replay_();

        this.todoListsService.restoreTodoList("create", "001", content);
    }

    
    
    
    /**
     * method restoreTodoListReplace() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception :  :  .<br/>
     */
    @Test public void restoreTodoListReplace() throws Exception {
    	
        InputStream content = new ByteArrayInputStream(todoListBackup
                .getBytes());

        this.todoList.getUsers().add(this.user);
        this.user.getTodoLists().add(this.todoList);

        Todo todo = new Todo();
        todo.setTodoId("0001");
        todo.setTodoList(this.todoList);
        this.todoList.getTodos().add(todo);

        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);
        expect(this.userService.getCurrentUser()).andReturn(this.user);
        this.todoDAO.removeTodo("0001");
        this.todoListDAO.updateTodoList(this.todoList);
        Todo createdTodo = new Todo();
        this.todoDAO.saveTodo(createdTodo);

        replay_();

        this.todoListsService.restoreTodoList("replace", "001", content);
    }

    
    
    
    /**
     * method restoreTodoListMerge() :<br/>
     * .<br/>
     * <br/>
     *
     * @throws Exception :  :  .<br/>
     */
    @Test public void restoreTodoListMerge() throws Exception {
    	
        InputStream content = new ByteArrayInputStream(todoListBackup
                .getBytes());

        this.todoList.getUsers().add(this.user);
        this.user.getTodoLists().add(this.todoList);

        expect(this.todoListDAO.getTodoList("001")).andReturn(this.todoList);
        expect(this.userService.getCurrentUser()).andReturn(this.user);

        Todo createdTodo = new Todo();
        this.todoDAO.saveTodo(createdTodo);

        this.todoListDAO.updateTodoList(this.todoList);

        replay_();

        this.todoListsService.restoreTodoList("merge", "001", content);

        assertNotNull(this.todoList.getLastUpdate());
    }
    
    
    
}
