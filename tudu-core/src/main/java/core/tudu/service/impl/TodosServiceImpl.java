package tudu.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tudu.domain.dao.ITodoDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.domain.model.comparator.TodoByDueDateComparator;
import tudu.security.PermissionDeniedException;
import tudu.service.ITodoListsService;
import tudu.service.ITodosService;
import tudu.service.IUserService;

/**
 * Implementation of the tudu.service.ITodosService interface.
 * 
 * @author Julien Dubois
 */
@Transactional
public class TodosServiceImpl implements ITodosService {

    /**
     * log : Log :<br/>
     * .<br/>
     */
    private final Log log = LogFactory.getLog(TodosServiceImpl.class);

    
    /**
     * todoDAO : ITodoDAO :<br/>
     * .<br/>
     */
    @Autowired
    private ITodoDAO todoDAO = null;

    
    /**
     * todoListsService : ITodoListsService :<br/>
     * .<br/>
     */
    @Autowired
    private ITodoListsService todoListsService = null;

       
    /**
     * userService : IUserService :<br/>
     * .<br/>
     */
    @Autowired
    private IUserService userService = null;

    
    

    @Override
	@Transactional(readOnly = true)
    public Todo findTodo(
    		final String pTodoId) {
    	
        if (this.log.isDebugEnabled()) {
        	this.log.debug("Finding Todo with ID " + pTodoId);
        }
        Todo todo = this.todoDAO.getTodo(pTodoId);
        TodoList todoList = todo.getTodoList();
        User user = this.userService.getCurrentUser();
        if (!user.getTodoLists().contains(todoList)) {
            if (this.log.isInfoEnabled()) {
            	this.log.info("Permission denied when finding Todo ID '" + pTodoId
                        + "' for User '" + user.getLogin() + "'");
            }

            throw new PermissionDeniedException(
                    "Permission denied to access this Todo.");

        }
        return todo;
    }

    
    
 
    @Override
	@Transactional(readOnly = true)
    public Collection<Todo> findUrgentTodos() {
        User user = this.userService.getCurrentUser();
        Calendar urgentCal = Calendar.getInstance();
        urgentCal.add(Calendar.DATE, 4);
        Date urgentDate = urgentCal.getTime();
        Set<Todo> urgentTodos = new TreeSet<Todo>(new TodoByDueDateComparator());
        for (TodoList todoList : user.getTodoLists()) {
            for (Todo todo : todoList.getTodos()) {
                if (todo.getDueDate() != null
                        && todo.getDueDate().before(urgentDate)
                        && !todo.isCompleted()) {

                    urgentTodos.add(todo);
                }
            }
        }
        return urgentTodos;
    }


    
    
    @Override
	@Transactional(readOnly = true)
    public Collection<Todo> findAssignedTodos() {
        User user = this.userService.getCurrentUser();
        Set<Todo> assignedTodos = new TreeSet<Todo>();
        for (TodoList todoList : user.getTodoLists()) {
            for (Todo todo : todoList.getTodos()) {
                if (todo.getAssignedUser() != null
                        && todo.getAssignedUser().equals(user)
                        && !todo.isCompleted()) {

                    assignedTodos.add(todo);
                }
            }
        }
        return assignedTodos;
    }


    
    @Override
	public void createTodo(final String pListId, final Todo pTodo) {

        Date now = Calendar.getInstance().getTime();
        pTodo.setCreationDate(now);
        TodoList todoList = this.todoListsService.findTodoList(pListId);
        pTodo.setTodoList(todoList);
        todoList.getTodos().add(pTodo);
        this.todoDAO.saveTodo(pTodo);
        this.todoListsService.updateTodoList(todoList);
    }


    
    
    @Override
	public void updateTodo(final Todo pTodo) {
    	this.todoDAO.updateTodo(pTodo);
    	this.todoListsService.updateTodoList(pTodo.getTodoList());
    }


    
    
    @Override
	public void deleteTodo(
    		final String pTodoId) {
        Todo todo = this.findTodo(pTodoId);
        TodoList todoList = todo.getTodoList();
        todoList.getTodos().remove(todo);
        this.todoDAO.removeTodo(pTodoId);
        this.todoListsService.updateTodoList(todoList);
    }


    
    @Override
	public void deleteAllCompletedTodos(
			final String pListId) {
        TodoList todoList = this.todoListsService.findTodoList(pListId);
        List<Todo> todosToRemove = new ArrayList<Todo>();
        for (Todo todo : todoList.getTodos()) {
            if (todo.isCompleted()) {
                todosToRemove.add(todo);
            }
        }
        todoList.getTodos().removeAll(todosToRemove);
        for (Todo todo : todosToRemove) {
        	this.todoDAO.removeTodo(todo.getTodoId());
        }
        this.todoListsService.updateTodoList(todoList);
    }


    
    
    @Override
	public Todo completeTodo(String pTodoId) {
    	
        Todo todo = this.findTodo(pTodoId);
        todo.setCompleted(true);
        todo.setCompletionDate(Calendar.getInstance().getTime());
        this.todoListsService.updateTodoList(todo.getTodoList());
        return todo;
    }


    
    

    @Override
	public Todo reopenTodo(
    		final String pTodoId) {
        Todo todo = this.findTodo(pTodoId);
        todo.setCompleted(false);
        todo.setCompletionDate(null);
        this.todoListsService.updateTodoList(todo.getTodoList());
        return todo;
    }

    
        
	/**
	 * method setTodoDAO() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pTodoDAO :  :  .<br/>
	 */
	public void setTodoDAO(
			final ITodoDAO pTodoDAO) {
		this.todoDAO = pTodoDAO;
	}

	
	
	/**
	 * method setTodoListsManager() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pTodoListsManager :  :  .<br/>
	 */
	public void setTodoListsManager(
			final ITodoListsService pTodoListsManager) {
		this.todoListsService = pTodoListsManager;
	}

	
	
	/**
	 * method setUserManager() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pUserManager :  :  .<br/>
	 */
	public void setUserManager(
			final IUserService pUserManager) {
		this.userService = pUserManager;
	}
	
	
	
}
