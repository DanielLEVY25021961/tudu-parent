package tudu.domain.dao;

import java.util.List;

import tudu.domain.model.User;

/**
 * DAO for the User table.
 * 
 * @author Julien Dubois
 */
public interface IUserDAO {


    /**
     * method getNumberOfUsers() :<br/>
     * Get the number of users.<br/>
     * <br/>
     *
     * @return : long :  .<br/>
     */
    long getNumberOfUsers();

    
    
    /**
     * Get a specific user.
     * 
     * @param login
     *            The user login
     * @return A user
     */
    User getUser(String login);

    
    
    /**
     * method findUsersByLogin() :<br/>
     * Find all users with a login starting with the "loginStart" string.<br/>
     * <br/>
     *
     * @param pLoginStart
     * @return : List<User> :  .<br/>
     */
    List<User> findUsersByLogin(String pLoginStart);

    
    
    /**
     * Update a user.
     * 
     * @param user
     *            The user value object
     */
    void updateUser(User user);

    
    
    /**
     * Save a user.
     * 
     * @param user
     *            The user value object
     */
    void saveUser(User user);
    
    
}
