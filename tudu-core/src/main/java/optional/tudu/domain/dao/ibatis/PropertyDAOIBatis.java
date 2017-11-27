/**
 * 
 */
package tudu.domain.dao.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class PropertyDAOIBatis implements IPropertyDAO {
	
	/**
	 * sqlMapClientTemplate : SqlMapClientTemplate :<br/>
	 * .<br/>
	 */
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyConfiguration getProperty(
			final String pKey) {
		return (PropertyConfiguration) 
				this.sqlMapClientTemplate.queryForObject("getProperty", pKey);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveProperty(
			final PropertyConfiguration pProperty) {
		this.sqlMapClientTemplate.update("saveProperty", pProperty);
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateProperty(
			final PropertyConfiguration pProperty) {
		this.sqlMapClientTemplate.update("updateProperty", pProperty);
	}

	
	
	/**
	 * method setSqlMapClientTemplate() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pSqlMapClientTemplate :  :  .<br/>
	 */
	public void setSqlMapClientTemplate(
			final SqlMapClientTemplate pSqlMapClientTemplate) {
		this.sqlMapClientTemplate = pSqlMapClientTemplate;
	}

	
	
}
