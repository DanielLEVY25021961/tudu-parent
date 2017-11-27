package tudu.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A user's role.
 * 
 * @author Julien Dubois
 */
@Entity(name="Role")
@Table(name = "role")
public class Role implements Serializable {

	
    /**
     * serialVersionUID : long :<br/>
     * .<br/>
     */
    private static final long serialVersionUID = -5636845397516495671L;

    
    /**
     * role : String :<br/>
     * .<br/>
     */
    private String role;

    
    
    /**
     * method getRole() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @Id
    public String getRole() {
        return this.role;
    }

    
    
    /**
     * method setRole() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pRole :  :  .<br/>
     */
    public void setRole(
    		final String pRole) {
        this.role = pRole;
    }
    
    
    
}
