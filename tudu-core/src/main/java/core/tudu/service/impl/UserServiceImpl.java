package tudu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.transaction.annotation.Transactional;

import tudu.Constants;
import tudu.domain.RolesEnum;
import tudu.domain.dao.IPropertyDAO;
import tudu.domain.dao.IRoleDAO;
import tudu.domain.dao.ITodoDAO;
import tudu.domain.dao.ITodoListDAO;
import tudu.domain.dao.IUserDAO;
import tudu.domain.model.PropertyConfiguration;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.IUserService;

/**
 * Implementation of the tudu.service.IUserService interface.
 * 
 * @author Julien Dubois
 */
@Transactional
public class UserServiceImpl implements IUserService {

    /**
     * log : Log :<br/>
     * .<br/>
     */
    private final Log log = LogFactory.getLog(UserServiceImpl.class);

    
    /**
     * userDAO : IUserDAO :<br/>
     * .<br/>
     */
    @Autowired
    private IUserDAO userDAO = null;

    
    /**
     * roleDAO : IRoleDAO :<br/>
     * .<br/>
     */
    @Autowired
    private IRoleDAO roleDAO = null;

    
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
     * propertyDAO : IPropertyDAO :<br/>
     * .<br/>
     */
    @Autowired
    private IPropertyDAO propertyDAO = null;

    
    /**
     * userCache : UserCache :<br/>
     * .<br/>
     */
    @Autowired
    private UserCache userCache = null;    

    
     
    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public long getNumberOfUsers() {
        return this.userDAO.getNumberOfUsers();
    }

    
     
    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public User findUser(
    		final String pLogin) {
    	
        User user = this.userDAO.getUser(pLogin);
        if (user == null) {
            if (this.log.isDebugEnabled()) {
            	this.log.debug("Could not find User ID = " + pLogin);
            }
            throw new ObjectRetrievalFailureException(User.class, pLogin);
        }
        if (this.log.isDebugEnabled()) {
        	this.log.debug("User ID = " + pLogin + " found, user is called "
                    + user.getFirstName() + " " + user.getLastName());
        }
        return user;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public List<User> findUsersByLogin(
    		final String pLoginStart) {
        return this.userDAO.findUsersByLogin(pLoginStart);
    }

    

    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        org.springframework.security.core.userdetails.User springSecurityUser = (org.springframework.security.core.userdetails.User) securityContext
                .getAuthentication().getPrincipal();

        return this.findUser(springSecurityUser.getUsername());
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void updateUser(
			final User pUser) {
        if (this.log.isDebugEnabled()) {
        	this.log.debug("Updating user '" + pUser.getLogin() + "'.");
        }
        this.userDAO.updateUser(pUser);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void enableUser(
			final String pLogin) {
        User user = this.findUser(pLogin);
        user.setEnabled(true);
        this.userCache.removeUserFromCache(pLogin);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void disableUser(
			final String pLogin) {
        User user = this.findUser(pLogin);
        user.setEnabled(false);
        this.userCache.removeUserFromCache(pLogin);
    }

    
    

    /**
     * {@inheritDoc}
     */
    @Override
	public void createUser(
			final User pUser) throws UserAlreadyExistsException {
    	
        if (this.log.isDebugEnabled()) {
        	this.log.debug("Creating user '" + pUser.getLogin() + "'.");
        }

        User testUser = this.userDAO.getUser(pUser.getLogin());
        if (testUser != null) {
            if (this.log.isDebugEnabled()) {
            	this.log.debug("User login '" + pUser.getLogin()
                        + "' already exists.");
            }
            throw new UserAlreadyExistsException("User already exists.");
        }
        pUser.setEnabled(true);
        Date now = Calendar.getInstance().getTime();
        pUser.setCreationDate(now);
        pUser.setLastAccessDate(now);
        pUser.setDateFormat(Constants.DATEFORMAT_US);
        Role role = this.roleDAO.getRole(RolesEnum.ROLE_USER.toString());
        pUser.getRoles().add(role);
        this.userDAO.saveUser(pUser);

        TodoList todoList = new TodoList();
        todoList.setName("Welcome!");
        todoList.setLastUpdate(Calendar.getInstance().getTime());
        this.todoListDAO.saveTodoList(todoList);
        pUser.getTodoLists().add(todoList);
        todoList.getUsers().add(pUser);

        Todo welcomeTodo = new Todo();
        welcomeTodo.setDescription("Welcome to Tudu Lists!");
        welcomeTodo.setPriority(100);
        welcomeTodo.setCreationDate(now);
        welcomeTodo.setCompletionDate(now);
        welcomeTodo.setTodoList(todoList);
        todoList.getTodos().add(welcomeTodo);
        this.todoDAO.saveTodo(welcomeTodo);
        this.todoListDAO.updateTodoList(todoList);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	@Transactional(readOnly = true)
    public void sendPassword(
    		final User pUser) {
    	
        if (this.log.isDebugEnabled()) {
        	this.log.debug("Send password of user '" + pUser.getLogin() + "'.");
        }
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        PropertyConfiguration smtpHost = this.propertyDAO.getProperty("smtp.host");
        sender.setHost(smtpHost.getValue());
        PropertyConfiguration smtpPort = this.propertyDAO.getProperty("smtp.port");
        int port = 25;
        try {
            port = Integer.parseInt(smtpPort.getValue());
        } catch (NumberFormatException e) {
        	this.log.error("The supplied SMTP port is not a number.");
        }
        sender.setPort(port);
        PropertyConfiguration smtpUser = this.propertyDAO.getProperty("smtp.user");
        sender.setUsername(smtpUser.getValue());
        PropertyConfiguration smtpPassword = this.propertyDAO.getProperty("smtp.password");
        sender.setPassword(smtpPassword.getValue());

        SimpleMailMessage message = new SimpleMailMessage();
        PropertyConfiguration smtpFrom = this.propertyDAO.getProperty("smtp.from");
        message.setTo(pUser.getEmail());
        message.setFrom(smtpFrom.getValue());
        message.setSubject("Your Tudu Lists password");
        message
                .setText("Dear "
                        + pUser.getFirstName()
                        + " "
                        + pUser.getLastName()
                        + ",\n\n"
                        + "Your Tudu Lists password is \""
                        + pUser.getPassword()
                        + "\".\n"
                        + "Now that this password has been sent by e-mail, we recommend that "
                        + "you change it as soon as possible.\n\n"
                        + "Regards,\n\n" + "Tudu Lists.");

        sender.send(message);
    }

    
    
	/**
	 * method setRoleDAO() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pRoleDAO :  :  .<br/>
	 */
	public void setRoleDAO(
			final IRoleDAO pRoleDAO) {
		this.roleDAO = pRoleDAO;
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
	 * method setPropertyDAO() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pPropertyDAO :  :  .<br/>
	 */
	public void setPropertyDAO(
			final IPropertyDAO pPropertyDAO) {
		this.propertyDAO = pPropertyDAO;
	}

	
	
	/**
	 * method setUserCache() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pUserCache :  :  .<br/>
	 */
	public void setUserCache(
			final UserCache pUserCache) {
		this.userCache = pUserCache;
	}

	
	
	/**
	 * method setUserDAO() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pUserDAO :  :  .<br/>
	 */
	public void setUserDAO(
			final IUserDAO pUserDAO) {
		this.userDAO = pUserDAO;
	}
	
	
}
