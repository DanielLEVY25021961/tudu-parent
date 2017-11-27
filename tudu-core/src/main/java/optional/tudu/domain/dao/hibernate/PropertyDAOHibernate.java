/**
 * 
 */
package tudu.domain.dao.hibernate;



import org.springframework.orm.hibernate5.HibernateTemplate;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class PropertyDAOHibernate implements IPropertyDAO {

	
	
	/**
	 * hibernateTemplate : HibernateTemplate :<br/>
	 * .<br/>
	 */
	private HibernateTemplate hibernateTemplate;


	

	@Override
	public PropertyConfiguration getProperty(
			final String pKey) {
		return this.hibernateTemplate.get(PropertyConfiguration.class, pKey);
	}


	@Override
	public void saveProperty(
			final PropertyConfiguration pProperty) {
		this.hibernateTemplate.saveOrUpdate(pProperty);
	}

	
	

	@Override
	public void updateProperty(
			final PropertyConfiguration pProperty) {
		this.hibernateTemplate.saveOrUpdate(pProperty);
	}

	
	
	/**
	 * method setHibernateTemplate() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pHibernateTemplate :  :  .<br/>
	 */
	public void setHibernateTemplate(
			final HibernateTemplate pHibernateTemplate) {
		this.hibernateTemplate = pHibernateTemplate;
	}

	
	
}
