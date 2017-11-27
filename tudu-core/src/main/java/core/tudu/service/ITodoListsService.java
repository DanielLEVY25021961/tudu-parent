package tudu.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.JDOMException;

import tudu.domain.model.TodoList;

/**
 * Manage Todo Lists.
 * 
 * @author Julien Dubois
 */
public interface ITodoListsService {


    /**
     * method createTodoList() :<br/>
     * Create a new Todo List.<br/>
     * <br/>
     *
     * @param pTodoList : TodoList : The Todo List to create.<br/>
     */
    void createTodoList(TodoList pTodoList);

    
    
    /**
     * method findTodoList() :<br/>
     * Find a Todo List by ID.<br/>
     * <br/>
     *
     * @param pListId
     * @return : String : The Todo List ID.<br/>
     */
    TodoList findTodoList(String pListId);

    
    
    /**
     * method unsecuredFindTodoList() :<br/>
     * Find a Todo List by ID, without any security check.
     * <p>
     * This method is used for the RSS feed, which is not secured.
     * </p>
     * <br/>
     *
     * @param pListId : String : The Todo List ID
     * @return : TodoList : The Todo List.<br/>
     */
    TodoList unsecuredFindTodoList(String pListId);

    
    
    /**
     * method updateTodoList() :<br/>
     * Update a Todo List.<br/>
     * <br/>
     *
     * @param pTodoList : TodoList : The Todo List to update.<br/>
     */
    void updateTodoList(TodoList pTodoList);
    
    

    /**
     * method deleteTodoList() :<br/>
     * Delete a Todo List.<br/>
     * <br/>
     *
     * @param pListId : String : The ID of the Todo List to delete.<br/>
     */
    void deleteTodoList(String pListId);

    
    
    /**
     * method addTodoListUser() :<br/>
     * Add a user to a Todo List.<br/>
     * <br/>
     *
     * @param pListId : String : The ID of the Todo List
     * @param pLogin : String : The user login.<br/>
     */
    void addTodoListUser(String pListId, String pLogin);

    
    
    /**
     * method deleteTodoListUser() :<br/>
     * Delete a user from Todo List.<br/>
     * <br/>
     *
     * @param pListId : String : The ID of the Todo List
     * @param pLogin : String : The user login.<br/>
     */
    void deleteTodoListUser(String pListId, String pLogin);

    
    
    /**
     * method backupTodoList() :<br/>
     * Backup a Todo List..<br/>
     * <br/>
     *
     * @param pTodoList
     * @return : Document : A JDOM document ready for backup.<br/>
     */
    Document backupTodoList(TodoList pTodoList);

    
    
    /**
     * method restoreTodoList() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pRestoreChoice : String : The type of restore (create, replace or merge)
     * @param pListId : String : The Todo List ID
     * @param pTodoListContent : InputStream : The content to restore
     * 
     * @throws JDOMException
     * @throws IOException :  :  .<br/>
     */
    void restoreTodoList(String pRestoreChoice
    		, String pListId
    			, InputStream pTodoListContent) throws JDOMException, IOException;

    

    /**
     * method findByLogin() :<br/>
     * Find the user todo lists.<br/>
     * <br/>
     *
     * @param pLogin : String : 
     * @return : Set<TodoList> :  .<br/>
     */
    Set<TodoList> findByLogin(String pLogin);
    
    

}
