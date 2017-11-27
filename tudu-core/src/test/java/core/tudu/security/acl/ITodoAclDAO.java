/**
 * 
 */
package tudu.security.acl;

import java.util.Collection;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public interface ITodoAclDAO {

	/**
	 * method save() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pTodo :  :  .<br/>
	 */
	public void save(AclTodo pTodo);

	
	
	/**
	 * method delete() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pTodo :  :  .<br/>
	 */
	public void delete(AclTodo pTodo);

	
	
	/**
	 * method get() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pId
	 * @return :  :  .<br/>
	 */
	public AclTodo get(Long pId);

	
	
	/**
	 * method selectAll() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	public Collection<AclTodo> selectAll();

	
	
}
