package tudu.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A user.
 * 
 * @author Julien Dubois
 */
@Entity(name="User")
@Table(name = "tuser")
@NamedQueries( {
        @NamedQuery(name = "User.getNumberOfUsers", query = "SELECT COUNT(user) FROM User user"),
        @NamedQuery(name = "User.findUsersByLogin", query = "SELECT user FROM User user where user.login LIKE :login") })
public class User implements Serializable, Comparable<User> {


    /**
     * serialVersionUID : long :<br/>
     * .<br/>
     */
    private static final long serialVersionUID = 4048798961366546485L;

    
    
    /**
     * login : String :<br/>
     * .<br/>
     */
    private String login;

    
    /**
     * password : String :<br/>
     * .<br/>
     */
    private String password;

       
    /**
     * firstName : String :<br/>
     * .<br/>
     */
    private String firstName;

    
    /**
     * lastName : String :<br/>
     * .<br/>
     */
    private String lastName;

    
    /**
     * email : String :<br/>
     * .<br/>
     */
    private String email;

    
    /**
     * creationDate : Date :<br/>
     * .<br/>
     */
    private Date creationDate;

    
    /**
     * lastAccessDate : Date :<br/>
     * .<br/>
     */
    private Date lastAccessDate;

    
    /**
     * enabled : boolean :<br/>
     * .<br/>
     */
    private boolean enabled;

    
    /**
     * dateFormat : String :<br/>
     * .<br/>
     */
    private String dateFormat;

    
    /**
     * roles : Set<Role> :<br/>
     * .<br/>
     */
    private Set<Role> roles = new HashSet<Role>();

    
    /**
     * todoLists : Set<TodoList> :<br/>
     * .<br/>
     */
    private Set<TodoList> todoLists = new HashSet<TodoList>();

    
    
    /**
     * method getLogin() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @Id
    public String getLogin() {
        return this.login;
    }

    
    
    /**
     * method setLogin() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pLogin :  :  .<br/>
     */
    public void setLogin(
    		final String pLogin) {
        this.login = pLogin;
    }

    
    
    /**
     * method getPassword() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getPassword() {
        return this.password;
    }

    
    
    /**
     * method setPassword() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pPassword :  :  .<br/>
     */
    public void setPassword(
    		final String pPassword) {
        this.password = pPassword;
    }

    
    
    /**
     * method getFirstName() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getFirstName() {
        return this.firstName;
    }

    
    
    /**
     * method setFirstName() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pFirstName :  :  .<br/>
     */
    public void setFirstName(
    		final String pFirstName) {
        this.firstName = pFirstName;
    }

    
    
    /**
     * method getLastName() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getLastName() {
        return this.lastName;
    }

    
    
    /**
     * method setLastName() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pLastName :  :  .<br/>
     */
    public void setLastName(
    		final String pLastName) {
        this.lastName = pLastName;
    }

    
    
    /**
     * method getEmail() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getEmail() {
        return this.email;
    }

    
    
    /**
     * method setEmail() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pEmail :  :  .<br/>
     */
    public void setEmail(
    		final String pEmail) {
        this.email = pEmail;
    }

    
    
    /**
     * method getCreationDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    
    
    /**
     * method setCreationDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pCreationDate :  :  .<br/>
     */
    public void setCreationDate(
    		final Date pCreationDate) {
        this.creationDate = pCreationDate;
    }

    
    
    /**
     * method getLastAccessDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public Date getLastAccessDate() {
        return this.lastAccessDate;
    }

    
    
    /**
     * method setLastAccessDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pLastAccessDate :  :  .<br/>
     */
    public void setLastAccessDate(
    		final Date pLastAccessDate) {
        this.lastAccessDate = pLastAccessDate;
    }

    
    
    /**
     * method isEnabled() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    
    
    /**
     * method setEnabled() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pEnabled :  :  .<br/>
     */
    public void setEnabled(
    		final boolean pEnabled) {
        this.enabled = pEnabled;
    }

    
    
    /**
     * method getDateFormat() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public String getDateFormat() {
        return this.dateFormat;
    }

    
    
    /**
     * method setDateFormat() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pDateFormat :  :  .<br/>
     */
    public void setDateFormat(
    		final String pDateFormat) {
        this.dateFormat = pDateFormat;
    }

    
    
    /**
     * method getRoles() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<Role> getRoles() {
        return this.roles;
    }

    
    
    /**
     * method setRoles() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pRoles :  :  .<br/>
     */
    public void setRoles(
    		final Set<Role> pRoles) {
        this.roles = pRoles;
    }

    
    
    /**
     * method getTodoLists() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<TodoList> getTodoLists() {
        return this.todoLists;
    }

    
    
    /**
     * method setTodoLists() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pTodoLists :  :  .<br/>
     */
    public void setTodoLists(
    		final Set<TodoList> pTodoLists) {
        this.todoLists = pTodoLists;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(
    		final Object pObject) {
        if (this == pObject) {
            return true;
        }
        if (!(pObject instanceof User)) {
            return false;
        }
        final User user = (User) pObject;

        if (!this.login.equals(user.login)) {
            return false;
        }
        return true;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.login.hashCode();
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public int compareTo(
			final User pOther) {
        return this.getLogin().compareTo(pOther.getLogin());
    }
    
    
    
}
