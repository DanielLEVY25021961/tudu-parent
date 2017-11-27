package tudu.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * A Todo.
 * 
 * @author Julien Dubois
 */
@Entity(name="Todo")
@Table(name = "todo")
public class Todo implements Serializable, Comparable<Todo> {

 
    /**
     * serialVersionUID : long :<br/>
     * .<br/>
     */
    private static final long serialVersionUID = 4048798961366546485L;

    
    
    /**
     * todoId : String :<br/>
     * .<br/>
     */
    private String todoId;

    
    /**
     * todoList : TodoList :<br/>
     * .<br/>
     */
    private TodoList todoList;

    
    /**
     * creationDate : Date :<br/>
     * .<br/>
     */
    private Date creationDate;

    
    /**
     * description : String :<br/>
     * .<br/>
     */
    private String description;

       
    /**
     * priority : int :<br/>
     * .<br/>
     */
    private int priority;

    
    /**
     * completed : boolean :<br/>
     * .<br/>
     */
    private boolean completed;

    
    /**
     * completionDate : Date :<br/>
     * .<br/>
     */
    private Date completionDate;

    
    /**
     * dueDate : Date :<br/>
     * .<br/>
     */
    private Date dueDate;

    
    /**
     * assignedUser : User :<br/>
     * .<br/>
     */
    private User assignedUser;

    
    /**
     * notes : String :<br/>
     * .<br/>
     */
    private String notes;

    
    /**
     * hasNotes : boolean :<br/>
     * .<br/>
     */
    private boolean hasNotes;

    
    /**
     * subTodos : Set<Todo> :<br/>
     * .<br/>
     */
    private Set<Todo> subTodos = new HashSet<Todo>();

    
    
    /**
     * method getTodoId() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getTodoId() {
        return this.todoId;
    }

    
    
    /**
     * method setTodoId() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pTodoId :  :  .<br/>
     */
    public void setTodoId(
    		final String pTodoId) {
        this.todoId = pTodoId;
    }

    
    
    /**
     * method isCompleted() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public boolean isCompleted() {
        return this.completed;
    }

    
    
    /**
     * method setCompleted() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pCompleted :  :  .<br/>
     */
    public void setCompleted(
    		final boolean pCompleted) {
        this.completed = pCompleted;
    }

    
    
    /**
     * method getCompletionDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public Date getCompletionDate() {
        return this.completionDate;
    }

    
    
    /**
     * method setCompletionDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pCompletionDate :  :  .<br/>
     */
    public void setCompletionDate(
    		final Date pCompletionDate) {
        this.completionDate = pCompletionDate;
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

    
    
    /**
     * method getDueDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public Date getDueDate() {
        return this.dueDate;
    }

    
       
    /**
     * method setDueDate() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pDueDate :  :  .<br/>
     */
    public void setDueDate(
    		final Date pDueDate) {
        this.dueDate = pDueDate;
    }

    
    
    /**
     * method getPriority() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public int getPriority() {
        return this.priority;
    }

    
    
    /**
     * method setPriority() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pPriority :  :  .<br/>
     */
    public void setPriority(
    		final int pPriority) {
        this.priority = pPriority;
    }

    
    
    
    /**
     * method getAssignedUser() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @ManyToOne
    public User getAssignedUser() {
        return this.assignedUser;
    }

    
    
    /**
     * method setAssignedUser() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pAssignedUser :  :  .<br/>
     */
    public void setAssignedUser(
    		final User pAssignedUser) {
        this.assignedUser = pAssignedUser;
    }

    
    
    /**
     * method isHasNotes() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public boolean isHasNotes() {
        return this.hasNotes;
    }

    
    
    /**
     * method setHasNotes() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pHasNotes :  :  .<br/>
     */
    public void setHasNotes(
    		final boolean pHasNotes) {
        this.hasNotes = pHasNotes;
    }

    
    
    /**
     * The length of this field is 10000, which is OK with MySQL but which will
     * cause trouble with other databases (Oracle is limited at 4000 characters,
     * SQL Server at 8000).
     */
    /**
     * method getNotes() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 10000)
    public String getNotes() {
        return this.notes;
    }

    
    
    
    /**
     * method setNotes() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pNotes :  :  .<br/>
     */
    public void setNotes(
    		final String pNotes) {
        this.notes = pNotes;
    }

    
    
    /**
     * method getSubTodos() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @Transient
    public Set<Todo> getSubTodos() {
        return this.subTodos;
    }

    
    
    /**
     * method setSubTodos() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pSubTodos :  :  .<br/>
     */
    public void setSubTodos(
    		final Set<Todo> pSubTodos) {
        this.subTodos = pSubTodos;
    }

    
    
    /**
     * method getTodoList() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    @ManyToOne
    public TodoList getTodoList() {
        return this.todoList;
    }

    
    
    /**
     * method setTodoList() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pTodoList :  :  .<br/>
     */
    public void setTodoList(
    		final TodoList pTodoList) {
        this.todoList = pTodoList;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public int compareTo(
    		final Todo pOther) {
    	
        int order = pOther.getPriority() - this.getPriority();
        if (this.isCompleted()) {
            order += 10000;
        }
        if (pOther.isCompleted()) {
            order -= 10000;
        }
        if (order == 0) {
            order = (this.getDescription() + this.getTodoId()).compareTo(pOther
                    .getDescription()
                    + pOther.getTodoId());
        }
        return order;
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
        if (!(pObject instanceof Todo)) {
            return false;
        }

        final Todo todo = (Todo) pObject;

        if (this.todoId != null ? !this.todoId.equals(todo.todoId) : todo.todoId != null) {
            return false;
        }

        return true;
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (this.todoId != null ? this.todoId.hashCode() : 0);
    }
    
    
    
}
