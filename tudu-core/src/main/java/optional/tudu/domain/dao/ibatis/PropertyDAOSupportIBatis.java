/**
 * 
 */
package tudu.domain.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class PropertyDAOSupportIBatis extends SqlMapClientDaoSupport implements IPropertyDAO {
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyConfiguration getProperty(
			final String pKey) {
		return (PropertyConfiguration) getSqlMapClientTemplate().queryForObject("getProperty", pKey);
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveProperty(
			final PropertyConfiguration pProperty) {
		getSqlMapClientTemplate().update("saveProperty", pProperty);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperty(PropertyConfiguration pProperty) {
		getSqlMapClientTemplate().update("updateProperty", pProperty);
	}
	


}
