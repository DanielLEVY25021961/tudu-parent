package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import tudu.domain.dao.IRoleDAO;
import tudu.domain.model.Role;

/**
 * Hibernate implementation of the tudu.domain.dao.IRoleDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class RoleDAOJpa implements IRoleDAO {

	
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
	public Role getRole(
    		final String pRoleName) {
    	
        final Role role = this.entityManager.find(Role.class, pRoleName);
        
        if (role == null) {
            throw new ObjectRetrievalFailureException(Role.class, pRoleName);
        }
        return role;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void saveRole(
    		final Role pRole) {
    	
        this.entityManager.persist(pRole);
        this.entityManager.flush();
        
    }

    
    
}
