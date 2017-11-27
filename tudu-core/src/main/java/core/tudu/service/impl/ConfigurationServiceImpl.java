package tudu.service.impl;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import tudu.domain.RolesEnum;
import tudu.domain.dao.IPropertyDAO;
import tudu.domain.dao.IRoleDAO;
import tudu.domain.model.PropertyConfiguration;
import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.IConfigurationService;
import tudu.service.UserAlreadyExistsException;
import tudu.service.IUserService;

/**
 * Implementation of the tudu.service.IConfigurationService interface.
 * 
 * @author Julien Dubois
 */
@Transactional
public class ConfigurationServiceImpl implements IConfigurationService,
        ApplicationListener<ApplicationEvent> {

    /**
     * staticFilesPath : String :<br/>
     * .<br/>
     */
    public static String staticFilesPath = "";

    
    /**
     * googleAnalyticsKey : String :<br/>
     * .<br/>
     */
    public static String googleAnalyticsKey = "";

    
    /**
     * log : Log :<br/>
     * .<br/>
     */
    private final Log log = LogFactory.getLog(ConfigurationServiceImpl.class);

    
    /**
     * propertyDAO : IPropertyDAO :<br/>
     * .<br/>
     */
    @Autowired
    private IPropertyDAO propertyDAO = null;

    
    /**
     * roleDAO : IRoleDAO :<br/>
     * .<br/>
     */
    @Autowired
    private IRoleDAO roleDAO = null;

    
    /**
     * userService : IUserService :<br/>
     * .<br/>
     */
    @Autowired
    private IUserService userManager = null;   

    
    
    
    @Override
	public void onApplicationEvent(
			final ApplicationEvent pEvent) {
    	
        if (pEvent instanceof ContextRefreshedEvent) {
            this.log.warn("Spring context is started : " + pEvent.toString());
            this.initDatabase();
            this.initApplicationProperties();
        }
    }

 
    
    /**
     * method initDatabase() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Override
	public void initDatabase() {
    	
        try {
        	this.log.warn("Testing Database.");
        	this.roleDAO.getRole(RolesEnum.ROLE_USER.name());
        } catch (ObjectRetrievalFailureException erfe) {
        	this.log.warn("Database is empty : populating with default values.");

        	this.log.warn("Populating HSQLDB database.");
            PropertyConfiguration hostProperty = new PropertyConfiguration();
            hostProperty.setKey("smtp.host");
            hostProperty.setValue("");
            this.propertyDAO.saveProperty(hostProperty);
            PropertyConfiguration portProperty = new PropertyConfiguration();
            portProperty.setKey("smtp.port");
            portProperty.setValue("");
            this.propertyDAO.saveProperty(portProperty);
            PropertyConfiguration userProperty = new PropertyConfiguration();
            userProperty.setKey("smtp.user");
            userProperty.setValue("");
            this.propertyDAO.saveProperty(userProperty);
            PropertyConfiguration passwordProperty = new PropertyConfiguration();
            passwordProperty.setKey("smtp.password");
            passwordProperty.setValue("");
            this.propertyDAO.saveProperty(passwordProperty);
            PropertyConfiguration fromProperty = new PropertyConfiguration();
            fromProperty.setKey("smtp.from");
            fromProperty.setValue("");
            this.propertyDAO.saveProperty(fromProperty);

            Role userRole = new Role();
            userRole.setRole(RolesEnum.ROLE_USER.name());
            this.roleDAO.saveRole(userRole);
            Role adminRole = new Role();
            adminRole.setRole(RolesEnum.ROLE_ADMIN.name());
            this.roleDAO.saveRole(adminRole);

            User adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setPassword("admin");
            adminUser.setFirstName("Albert");
            adminUser.setLastName("Dmin");
            adminUser.setDateFormat("MM/dd/yyyy");
            try {
            	this.userManager.createUser(adminUser);
            } catch (UserAlreadyExistsException e) {
            	this.log.error("Error while creating the admin user :"
                        + " the user already exists.");
            }
            Set<Role> roles = adminUser.getRoles();
            roles.add(adminRole);
            this.userManager.updateUser(adminUser);

            User user = new User();
            user.setLogin("user");
            user.setPassword("user");
            user.setFirstName("");
            user.setDateFormat("MM/dd/yyyy");
            try {
            	this.userManager.createUser(user);
            } catch (UserAlreadyExistsException e) {
            	this.log.error("Error while creating the admin user : "
                        + "the user already exists.");
            }
        }
    }

    
    
    /**
     * method initApplicationProperties() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Override
	public void initApplicationProperties() {
        PropertyConfiguration staticFilesPathProperty = this
                .getProperty("application.static.path");

        if (staticFilesPathProperty != null) {
            staticFilesPath = staticFilesPathProperty.getValue();

        } else {
            staticFilesPathProperty = new PropertyConfiguration();
            staticFilesPathProperty.setKey("application.static.path");
            staticFilesPathProperty.setValue(staticFilesPath);
            this.setProperty(staticFilesPathProperty);
        }

        PropertyConfiguration googleAnalyticsKeyProperty = this
                .getProperty("google.analytics.key");

        if (googleAnalyticsKeyProperty != null) {
            googleAnalyticsKey = googleAnalyticsKeyProperty.getValue();

        } else {
            googleAnalyticsKeyProperty = new PropertyConfiguration();
            googleAnalyticsKeyProperty.setKey("google.analytics.key");
            googleAnalyticsKeyProperty.setValue(googleAnalyticsKey);
            this.setProperty(googleAnalyticsKeyProperty);
        }
    }

    
    
    /**
     * method getProperty() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pKey
     * @return :  :  .<br/>
     */
    @Override
	@Transactional(readOnly = true)
    public PropertyConfiguration getProperty(
    		final String pKey) {
        return this.propertyDAO.getProperty(pKey);
    }

    
    
 
    /**
     * method updateEmailProperties() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pSmtpHost
     * @param pSmtpPort
     * @param pSmtpUser
     * @param pSmtpPassword
     * @param pSmtpFrom :  :  .<br/>
     */
    @Override
	public void updateEmailProperties(
    		final String pSmtpHost, final String pSmtpPort
    		, final String pSmtpUser, final String pSmtpPassword
    			, final String pSmtpFrom) {

        PropertyConfiguration hostProperty = this.propertyDAO.getProperty("smtp.host");
        hostProperty.setValue(pSmtpHost);
        this.propertyDAO.updateProperty(hostProperty);
        PropertyConfiguration portProperty = this.propertyDAO.getProperty("smtp.port");
        portProperty.setValue(pSmtpPort);
        this.propertyDAO.updateProperty(portProperty);
        PropertyConfiguration userProperty = this.propertyDAO.getProperty("smtp.user");
        userProperty.setValue(pSmtpUser);
        this.propertyDAO.updateProperty(userProperty);
        PropertyConfiguration passwordProperty = this.propertyDAO.getProperty("smtp.password");
        passwordProperty.setValue(pSmtpPassword);
        this.propertyDAO.updateProperty(passwordProperty);
        PropertyConfiguration fromProperty = this.propertyDAO.getProperty("smtp.from");
        fromProperty.setValue(pSmtpFrom);
        this.propertyDAO.updateProperty(fromProperty);
    }

 
    
    /**
     * method updateApplicationProperties() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pStaticPath
     * @param pGoogleKey :  :  .<br/>
     */
    @Override
	public void updateApplicationProperties(
			final String pStaticPath, final String pGoogleKey) {
    	
        PropertyConfiguration pathProperty = new PropertyConfiguration();
        pathProperty.setKey("application.static.path");
        pathProperty.setValue(pStaticPath);
        this.setProperty(pathProperty);
        staticFilesPath = pStaticPath;

        PropertyConfiguration googleProperty = new PropertyConfiguration();
        googleProperty.setKey("google.analytics.key");
        googleProperty.setValue(pGoogleKey);
        this.setProperty(googleProperty);
        googleAnalyticsKey = pGoogleKey;
    }

    
    
    /**
     * method setProperty() :<br/>
     * If the property doesn't exist yet, it is created.<br/>
     * <br/>
     *
     * @param pProperty : PropertyConfiguration :  .<br/>
     */
    private void setProperty(
    		final PropertyConfiguration pProperty) {
    	
        PropertyConfiguration databaseProperty = this.getProperty(pProperty.getKey());
        if (databaseProperty == null) {
            this.propertyDAO.saveProperty(pProperty);
        } else {
            databaseProperty.setValue(pProperty.getValue());
        }
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
	 * method setUserManager() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pUserManager :  :  .<br/>
	 */
	public void setUserManager(
			final IUserService pUserManager) {
		this.userManager = pUserManager;
	}
	
	
	
}
