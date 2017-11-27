package tudu.service;

import java.util.Collection;

import tudu.domain.model.Todo;

/**
 * Manage Todos.
 * 
 * @author Julien Dubois
 */
public interface ITodosService {


	
    /**
     * method findTodo() :<br/>
     * Find a Todo by ID.<br/>
     * <br/>
     *
     * @param pTodoId : String : The Todo ID
     * @return : Todo :  .<br/>
     */
    Todo findTodo(String pTodoId);

    
    
    /**
     * method findUrgentTodos() :<br/>
     * Create a collection containing all the urgent Todos of the current user.<br/>
     * <br/>
     *
     * @return : Collection<Todo> : The urgent Todos of the current user.<br/>
     */
    Collection<Todo> findUrgentTodos();

    
    
    /**
     * method findAssignedTodos() :<br/>
     * Create a collection containing all the Todos assigned to the current
     * user.<br/>
     * <br/>
     *
     * @return : Collection<Todo> : all the Todos assigned to the current User.<br/>
     */
    Collection<Todo> findAssignedTodos();

    
    
    /**
     * method createTodo() :<br/>
     * Create a new Todo.<br/>
     * <br/>
     *
     * @param pListId : String : The ID of the Todo List to which the Todo belongs
     * @param pTodo : Todo : The Todo to create.<br/>
     */
    void createTodo(String pListId, Todo pTodo);

    
    
    /**
     * method updateTodo() :<br/>
     * Update a Todo.<br/>
     * <br/>
     *
     * @param pTodo : Todo : The Todo to update.<br/>
     */
    void updateTodo(Todo pTodo);

    
    
    /**
     * method deleteTodo() :<br/>
     * Delete a Todo.<br/>
     * <br/>
     *
     * @param pTodoId : String : The ID of the Todo to delete.<br/>
     */
    void deleteTodo(String pTodoId);

    
    
    /**
     * method deleteAllCompletedTodos() :<br/>
     * Delete all the completed Todos of a given list.<br/>
     * <br/>
     *
     * @param pListId : String : The list ID.<br/>
     */
    void deleteAllCompletedTodos(String pListId);
    
    

    /**
     * method completeTodo() :<br/>
     * Complete a Todo.<br/>
     * <br/>
     *
     * @param pTodoId : String : The ID of the todo 
     * @return : Todo :  .<br/>
     */
    Todo completeTodo(String pTodoId);

    
    
    /**
     * method reopenTodo() :<br/>
     * Re-open a Todo.<br/>
     * <br/>
     *
     * @param pTodoId : String : The ID of the todo
     * @return : Todo :  .<br/>
     */
    Todo reopenTodo(String pTodoId);
    
    
}
