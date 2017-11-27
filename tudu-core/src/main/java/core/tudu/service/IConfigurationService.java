package tudu.service;

import tudu.domain.model.PropertyConfiguration;

/**
 * Manage the application configuration.
 * 
 * @author Julien Dubois
 */
public interface IConfigurationService {


	
    /**
     * method initDatabase() :<br/>
     * Initialize the database.<br/>
     * <br/>
     * :  :  .<br/>
     */
    void initDatabase();

    
 
    /**
     * method initApplicationProperties() :<br/>
     * Initialize the application properties.<br/>
     * <br/>
     * :  :  .<br/>
     */
    void initApplicationProperties();

    
    
    /**
     * method getProperty() :<br/>
     * Find a property by key.<br/>
     * <br/>
     *
     * @param pKey : String : The property key
     * @return : String : Value (Property).<br/>
     */
    PropertyConfiguration getProperty(String pKey);

    

    /**
     * Update email properties.
     * 
     * @param pSmtpHost
     *            SMTP host
     * @param pSmtpPort
     *            SMTP port
     * @param pSmtpUser
     *            SMTP user
     * @param pSmtpPassword
     *            SMTP password
     * @param pSmtpFrom
     *            From address of the emails sent by the application
     */
    void updateEmailProperties(String pSmtpHost, String pSmtpPort,
            String pSmtpUser, String pSmtpPassword, String pSmtpFrom);

    
    
    /**
     * Update the application properties.
     * 
     * @param pStaticPath
     *            The path to the static files
     * @param pGoogleKey
     *            The Google Analytics key used to track users
     */
    void updateApplicationProperties(String pStaticPath, String pGoogleKey);
    

    
}
