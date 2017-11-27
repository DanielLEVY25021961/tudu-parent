/**
 * 
 */
package tudu.security.acl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Arnaud Cogoluegnes
 *
 */
@Repository
@Transactional
public class TodoAclDAOImpl implements ITodoAclDAO {
	

	/**
	 * entityManager : EntityManager :<br/>
	 * .<br/>
	 */
	private EntityManager entityManager;

	
	/**
	 * mutableAclService : MutableAclService :<br/>
	 * .<br/>
	 */
	@Autowired
	private MutableAclService mutableAclService;

	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(
			final AclTodo pTodo) {
		
		this.entityManager.persist(pTodo);		
		
		ObjectIdentity oid = new ObjectIdentityImpl(AclTodo.class,pTodo.getId());
		MutableAcl acl = this.mutableAclService.createAcl(oid);
		acl.insertAce(0, BasePermission.ADMINISTRATION,new PrincipalSid(getUsername()),true);
		acl.insertAce(1, BasePermission.DELETE,new GrantedAuthoritySid("ROLE_ADMIN"),true);
		this.mutableAclService.updateAcl(acl);
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Secured("ACL_TODO_DELETE")
	public void delete(
			final AclTodo pTodo) {
		this.entityManager.remove(this.entityManager.find(AclTodo.class,pTodo.getId()));
		ObjectIdentity oid = new ObjectIdentityImpl(AclTodo.class,pTodo.getId());
		this.mutableAclService.deleteAcl(oid,false);
	}

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Secured({"ROLE_USER","ROLE_ADMIN","AFTER_ACL_READ"})
	public AclTodo get(
			final Long pId) {
		return this.entityManager.find(AclTodo.class,pId);
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Secured({"ROLE_USER","ROLE_ADMIN","AFTER_ACL_COLLECTION_READ"})
	public Collection<AclTodo> selectAll() {
		return this.entityManager.createQuery("from "+AclTodo.class.getName()).getResultList();
	}

	
	
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
	 * method getUsername() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	public String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	
}
