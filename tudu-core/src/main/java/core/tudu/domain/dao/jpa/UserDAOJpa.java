package tudu.domain.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import tudu.domain.dao.IUserDAO;
import tudu.domain.model.User;

/**
 * Hibernate implementation of the tudu.domain.dao.IUserDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class UserDAOJpa implements IUserDAO {

	
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
	public long getNumberOfUsers() {
        Query query = this.entityManager.createNamedQuery("User.getNumberOfUsers");
        return (Long) query.getSingleResult();
    }

    
    

    /**
     * {@inheritDoc}
     */
    @Override
	public final User getUser(
			final String pLogin) {
        return this.entityManager.find(User.class, pLogin);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public List<User> findUsersByLogin(
			final String pLoginStart) {
    	
        Query query = this.entityManager.createNamedQuery("User.findUsersByLogin");
        query.setParameter("login", pLoginStart + "%");
        query.setMaxResults(200);
        List<User> users = query.getResultList();
        return users;
    }

    
    
 
    /**
     * {@inheritDoc}
     */
    @Override
	public final void saveUser(
			final User pUser) {
        this.entityManager.persist(pUser);
    }

    
    

    /**
     * {@inheritDoc}
     */
    @Override
	public final void updateUser(
			final User pUser) {
        this.entityManager.merge(pUser);
    }
    
    
    
}
