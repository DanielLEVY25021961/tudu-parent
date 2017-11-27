package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import tudu.domain.dao.ITodoListDAO;
import tudu.domain.model.TodoList;

/**
 * Hibernate implementation of the tudu.domain.dao.ITodoListDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class TodoListDAOJpa implements ITodoListDAO {

    private EntityManager entityManager;

    
    
    /**
     * method setEntityManager() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pEntityManager :  :  .<br/>
     */
    @PersistenceContext
    public void setEntityManager(
    		final EntityManager pEntityManager) {
        this.entityManager = pEntityManager;
    }

    
    

    /**
     * {@inheritDoc}
     */
    @Override
	public final TodoList getTodoList(
			final String pListId) {
    	
        TodoList todoList = this.entityManager.find(TodoList.class, pListId);
        
        if (todoList == null) {
            throw new ObjectRetrievalFailureException(TodoList.class, pListId);
        }
        
        return todoList;
    }

    
    
    
 
    /**
     * {@inheritDoc}
     */
    @Override
	public final void saveTodoList(
			final TodoList pTodoList) {
        this.entityManager.persist(pTodoList);
    }

    
    
 
    /**
     * {@inheritDoc}
     */
    @Override
	public final void updateTodoList(
			final TodoList pTodoList) {
        this.entityManager.merge(pTodoList);
    }

    
    

    /**
     * {@inheritDoc}
     */
    @Override
	public final void removeTodoList(
			final String pListId) {
        final TodoList todoList = this.getTodoList(pListId);
        this.entityManager.remove(todoList);
    }

    
    
}
