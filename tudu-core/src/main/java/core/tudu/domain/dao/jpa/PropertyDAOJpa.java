package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;

/**
 * Hibernate implementation of the tudu.domain.dao.IPropertyDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class PropertyDAOJpa implements IPropertyDAO {

	
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
	public PropertyConfiguration getProperty(
    		final String pKey) {
        return this.entityManager.find(PropertyConfiguration.class, pKey);
    }

  
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void updateProperty(
    		final PropertyConfiguration pPropertyConfiguration) {
        this.entityManager.merge(pPropertyConfiguration);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void saveProperty(
    		final PropertyConfiguration pPropertyConfiguration) {
        this.entityManager.persist(pPropertyConfiguration);
    }

    
    
}
