package tudu.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tudu.domain.dao.ITodoDAO;
import tudu.domain.dao.ITodoListDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.security.PermissionDeniedException;
import tudu.service.IUserService;
import tudu.service.ITodoListsService;

/**
 * Implementation of the tudu.service.ITodoListsService interface.
 * 
 * @author Julien Dubois
 */
@Transactional
public class TodoListsServiceImpl implements ITodoListsService {

    /**
     * log : Log :<br/>
     * .<br/>
     */
    private final Log log = LogFactory.getLog(TodoListsServiceImpl.class);

    
    /**
     * todoListDAO : ITodoListDAO :<br/>
     * .<br/>
     */
    @Autowired
    private ITodoListDAO todoListDAO = null;

    
    /**
     * todoDAO : ITodoDAO :<br/>
     * .<br/>
     */
    @Autowired
    private ITodoDAO todoDAO = null;

    
    /**
     * userService : IUserService :<br/>
     * .<br/>
     */
    @Autowired
    private IUserService userService = null;

    

    /**
     * {@inheritDoc}
     */
    @Override
	public void createTodoList(
			final TodoList pTodoList) {
    	
        if (this.log.isDebugEnabled()) {
        	this.log.debug("Creating a new Todo List with name "
                    + pTodoList.getName());
        }
        pTodoList.setLastUpdate(Calendar.getInstance().getTime());
        User user = this.userService.getCurrentUser();
        pTodoList.getUsers().add(user);
        this.todoListDAO.saveTodoList(pTodoList);
        user.getTodoLists().add(pTodoList);
        this.userService.updateUser(user);
    }

    

    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public TodoList findTodoList(
    		final String pListId) {
    	
        TodoList todoList = this.todoListDAO.getTodoList(pListId);
        User user = this.userService.getCurrentUser();
        if (!user.getTodoLists().contains(todoList)) {
            if (this.log.isInfoEnabled()) {
            	this.log.info("Permission denied when finding Todo List ID '"
                        + pListId + "' for User '" + user.getLogin() + "'");
            }
            throw new PermissionDeniedException(
                    "Permission denied to access this Todo List.");
        }
        return todoList;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public TodoList unsecuredFindTodoList(
    		final String pListId) {
        return this.todoListDAO.getTodoList(pListId);
    }

        
 
    /**
     * {@inheritDoc}
     */
    @Override
	public void updateTodoList(
			final TodoList pTodoList) {
        pTodoList.setLastUpdate(Calendar.getInstance().getTime());
        this.todoListDAO.updateTodoList(pTodoList);
    }

    

    /**
     * {@inheritDoc}
     */
    @Override
	public void deleteTodoList(
			final String pListId) {
        TodoList todoList = this.findTodoList(pListId);
        for (User user : todoList.getUsers()) {
            user.getTodoLists().remove(todoList);
            this.userService.updateUser(user);
        }
        for (Todo todo : todoList.getTodos()) {
        	this.todoDAO.removeTodo(todo.getTodoId());
        }
        this.todoListDAO.removeTodoList(pListId);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void addTodoListUser(
			final String pListId, String pLogin) {
    	
        TodoList todoList = this.findTodoList(pListId);
        User targetUser = this.userService.findUser(pLogin);
        todoList.getUsers().add(targetUser);
        targetUser.getTodoLists().add(todoList);
        this.updateTodoList(todoList);
    }


    
    /**
     * {@inheritDoc}
     */
    @Override
	public void deleteTodoListUser(
			final String pListId, final String pLogin) {
        TodoList todoList = this.findTodoList(pListId);
        User targetUser = this.userService.findUser(pLogin);
        for (Todo todo : todoList.getTodos()) {
            if (todo.getAssignedUser() != null
                    && todo.getAssignedUser().equals(targetUser)) {

                todo.setAssignedUser(null);
            }
        }
        todoList.getUsers().remove(targetUser);
        targetUser.getTodoLists().remove(todoList);
        this.updateTodoList(todoList);
    }

    
     
    /**
     * {@inheritDoc}
     */
    @Override
	public Document backupTodoList(
			final TodoList pTodoList) {
    	
        final Document doc = new Document();

        Element todoListElement = new Element("todolist");
        todoListElement.addContent(new Element("title").addContent(pTodoList
                .getName()));

        todoListElement.addContent(new Element("rss").addContent(String
                .valueOf(pTodoList.isRssAllowed())));

        Element todosElement = new Element("todos");
        for (Todo todo : pTodoList.getTodos()) {
            Element todoElement = new Element("todo");
            todoElement.setAttribute("id", todo.getTodoId());
            todoElement.addContent(new Element("creationDate").addContent(Long
                    .toString(todo.getCreationDate().getTime())));

            todoElement.addContent(new Element("description").addContent(todo
                    .getDescription()));

            todoElement.addContent(new Element("priority").addContent(Integer
                    .toString(todo.getPriority())));

            if (todo.getDueDate() != null) {
                todoElement.addContent(new Element("dueDate").addContent(Long
                        .toString(todo.getDueDate().getTime())));
            }
            todoElement.addContent(new Element("completed").addContent(Boolean
                    .toString(todo.isCompleted())));

            if (todo.isCompleted() && todo.getCompletionDate() != null) {
                todoElement.addContent(new Element("completionDate")
                        .addContent(Long.toString(todo.getCompletionDate()
                                .getTime())));
            }

            todoElement.addContent(new Element("notes").addContent(todo
                    .getNotes()));

            todosElement.addContent(todoElement);
        }
        todoListElement.addContent(todosElement);

        doc.addContent(todoListElement);

        return doc;
    }

    

    /**
     * {@inheritDoc}
     */
    @Override
	public void restoreTodoList(
    		final String pRestoreChoice
    			, final String pListId
    				, final InputStream todoListContent) 
    						throws JDOMException, IOException {

        SAXBuilder saxBuilder = new SAXBuilder();
        Document doc = saxBuilder.build(todoListContent);
        Element rootElement = doc.getRootElement();
        String title = rootElement.getChildText("title");
        String rss = rootElement.getChildText("rss");
        if (pRestoreChoice.equals("create")) {
            TodoList todoList = new TodoList();
            todoList.setName(title);
            todoList.setRssAllowed(Boolean.parseBoolean(rss));
            this.createTodoList(todoList);
            importTodosFromXml(todoList, rootElement);
        } else if (pRestoreChoice.equals("replace")) {
            TodoList todoList = this.findTodoList(pListId);
            for (Todo todo : todoList.getTodos()) {
            	this.todoDAO.removeTodo(todo.getTodoId());
            }
            todoList.getTodos().clear();
            todoList.setName(title);
            todoList.setRssAllowed(Boolean.parseBoolean(rss));
            importTodosFromXml(todoList, rootElement);
            this.updateTodoList(todoList);
        } else if (pRestoreChoice.equals("merge")) {
            TodoList todoList = this.findTodoList(pListId);
            importTodosFromXml(todoList, rootElement);
            this.updateTodoList(todoList);
        } else {
        	this.log.error("Wrong choice of restore option");
        }
    }


    
    /**
     * method importTodosFromXml() :<br/>
     * Import Todos from a JDOM document.<br/>
     * <br/>
     *
     * @param pTodoList : TodoList : The current Todo List
     * @param pRootElement : Element : The root element of the JDOM document<br/>
     */
    private void importTodosFromXml(
    		final TodoList pTodoList
    			, final Element pRootElement) {
    	
        final Element todosElement = pRootElement.getChild("todos");
        
        final List<Element> todos = todosElement.getChildren();
        
        for (Object todoObject : todos) {
        	
            Element todoElement = (Element) todoObject;
            Todo todo = new Todo();
            Date creationDate = new Date(Long.parseLong(todoElement
                    .getChildText("creationDate")));
            todo.setCreationDate(creationDate);
            todo.setDescription(todoElement.getChildText("description"));
            todo.setPriority(Integer.valueOf(todoElement
                    .getChildText("priority")));
            todo.setCompleted(Boolean.parseBoolean(todoElement
                    .getChildText("completed")));
            String completionDate = todoElement.getChildText("completionDate");
            if (completionDate != null) {
                todo
                        .setCompletionDate(new Date(Long
                                .parseLong(completionDate)));
            }
            String dueDate = todoElement.getChildText("dueDate");
            if (dueDate != null) {
                todo.setDueDate(new Date(Long.parseLong(dueDate)));
            }
            String notes = todoElement.getChildText("notes");
            if (notes == null || notes.length() == 0) {
                todo.setHasNotes(false);
            } else {
                todo.setNotes(notes);
                todo.setHasNotes(true);
            }
            todo.setTodoList(pTodoList);
            pTodoList.getTodos().add(todo);
            this.todoDAO.saveTodo(todo);
        }
    }
 
    
     
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TodoList> findByLogin(
    		final String pLogin) {
    	
    	// initializing the list, to avoid session closed exception in the view
    	Set<TodoList> res = new HashSet<TodoList>();
    	for(TodoList list : this.userService.getCurrentUser().getTodoLists()) {
    		res.add(list);
    	}
    	return res;
    }

    
    
	/**
	 * method setTodoListDAO() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pTodoListDAO :  :  .<br/>
	 */
	public void setTodoListDAO(
			final ITodoListDAO pTodoListDAO) {
		this.todoListDAO = pTodoListDAO;
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
