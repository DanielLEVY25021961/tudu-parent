package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import tudu.domain.dao.ITodoDAO;
import tudu.domain.model.Todo;

/**
 * Hibernate implementation of the tudu.domain.dao.ITodoDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class TodoDAOJpa implements ITodoDAO {

	
    /**
     * entityManager : EntityManager :<br/>
     * .<br/>
     */
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
	public Todo getTodo(
    		final String pTodoId) {
    	
        final Todo todo = this.entityManager.find(Todo.class, pTodoId);
        
        if (todo == null) {
            throw new ObjectRetrievalFailureException(Todo.class, pTodoId);
        }
        
        return todo;
       
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void updateTodo(
			final Todo pTodo) {
        this.entityManager.merge(pTodo);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void saveTodo(
			final Todo pTodo) {
        this.entityManager.persist(pTodo);
    }

    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void removeTodo(
			final String pTodoId) {
        this.entityManager.remove(getTodo(pTodoId));
    }
    
    
    
}
