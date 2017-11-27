package tudu.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * class PropertyConfiguration :<br/>
 * A property, used to hold the application configuration.<br/>
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
 * @author Julien Dubois
 * @version 1.0
 * @since 18 nov. 2017
 *
 */
@Entity(name="PropertyConfiguration")
@Table(name = "property")
public class PropertyConfiguration implements Serializable {

    /**
     * serialVersionUID : long :<br/>
     * .<br/>
     */
    private static final long serialVersionUID = 3434972458764657217L;

    
    
    /**
     * key : String :<br/>
     * .<br/>
     */
    @Id
    @Column(name = "pkey")
    private String key;

    
    
    /**
     * value : String :<br/>
     * .<br/>
     */
    @Column(name = "value")
    private String value;
 
    
    
    
    /**
     * method getKey() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getKey() {
        return this.key;
    }

    
    
    /**
     * method setKey() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pKey :  :  .<br/>
     */
    public void setKey(
    		final String pKey) {
        this.key = pKey;
    }

    
    
    /**
     * method getValue() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getValue() {
        return this.value;
    }

    
    
    /**
     * method setValue() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pValue :  :  .<br/>
     */
    public void setValue(
    		final String pValue) {
        this.value = pValue;
    }
    
    
    
}
