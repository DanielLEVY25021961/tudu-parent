/**
 * 
 */
package tudu.domain.dao.jdbc;



import org.springframework.jdbc.core.support.JdbcDaoSupport;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class PropertyDAOJdbc extends JdbcDaoSupport implements IPropertyDAO {

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyConfiguration getProperty(
			final String pKey) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveProperty(
			final PropertyConfiguration pProperty) {
		
		getJdbcTemplate().update(
				"insert into property (pkey,value) values (?,?)", 
				pProperty.getKey(),pProperty.getValue()
		);
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperty(
			final PropertyConfiguration pProperty) {
		// TODO Auto-generated method stub
		
	}

	
	
}
