/**
 * 
 */
package tudu.security.acl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Arnaud Cogoluegnes
 *
 */
/**
 * class AclTodo :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 19 nov. 2017
 *
 */
@Entity
@Table(name = "acltodo")
@SequenceGenerator(name="todo_seq",sequenceName="todo_seq",initialValue=1,allocationSize=1)
public class AclTodo {

	/**
	 * id : Long :<br/>
	 * .<br/>
	 */
	@Id
	@GeneratedValue(generator="todo_seq")
	private Long id;

	
	
	/**
	 * description : String :<br/>
	 * .<br/>
	 */
	private String description;	

	
	
	
	 /**
	 * method CONSTRUCTEUR AclTodo() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 */
	public AclTodo() {
		super();
	}

	
	
	
	 /**
	 * method CONSTRUCTEUR AclTodo() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pDescription
	 */
	public AclTodo(
			final String pDescription) {
		this();
		this.description = pDescription;
	}

	
		
	/**
	 * method getId() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return id : Long.<br/>
	 */
	public Long getId() {	
		return this.id;
	}


	
	/**
	* method setId(
	* Long pId) :<br/>
	* .<br/>
	* <br/>
	*
	* @param pId : Long : valeur à passer à id.<br/>
	*/
	public void setId(
			final Long pId) {	
		this.id = pId;
	}




	/**
	 * method getDescription() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	public String getDescription() {
		return this.description;
	}

	
	
	
	/**
	 * method setDescription() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pDescription :  :  .<br/>
	 */
	public void setDescription(
			final String pDescription) {
		this.description = pDescription;
	}	
	
	
}
