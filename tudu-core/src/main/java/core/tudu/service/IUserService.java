package tudu.service;

import java.util.List;

import tudu.domain.model.User;

/**
 * Manage the security: user authentification and autorizations.
 * 
 * @author Julien Dubois
 */
public interface IUserService {



    /**
     * method getNumberOfUsers() :<br/>
     * Get the number of users.<br/>
     * <br/>
     *
     * @return : long :  .<br/>
     */
    long getNumberOfUsers();
    
    

    /**
     * Find a user by login.
     * 
     * @param login
     *            The user login
     * @return The user value object
     */
    User findUser(String login);

 
    
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
     * Find the current user.
     * <p>
     * This method relies on Acegy Security.
     * </p>
     * 
     * @return The current user.
     */
    User getCurrentUser();

    
    
    /**
     * Update a user's information.
     * 
     * @param pUser
     *            The user to update
     */
    void updateUser(User pUser);

    
    
    /**
     * Enable a user account.
     * 
     * @param pLogin
     *            The user login
     */
    void enableUser(String pLogin);

    
    
    /**
     * Disable a user account.
     * 
     * @param pLogin
     *            The user login
     */
    void disableUser(String pLogin);

    
    
    /**
     * method createUser() :<br/>
     * Create a new user.<br/>
     * <br/>
     *
     * @param pUser : User : The user to create
     * @throws UserAlreadyExistsException :  :  .<br/>
     */
    void createUser(User pUser) throws UserAlreadyExistsException;

    
    
    /**
     * Send a user's password by email.
     * 
     * @param pUser
     *            The user
     */
    void sendPassword(User pUser);
    
    
    
}
