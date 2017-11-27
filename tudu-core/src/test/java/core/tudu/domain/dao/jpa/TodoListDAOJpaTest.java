/**
 * 
 */
package tudu.domain.dao.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.domain.dao.ITodoListDAO;
import tudu.domain.model.TodoList;
import tudu.test.CleanInsertTestExecutionListener;
import tudu.test.IDataSetLocator;
import tudu.test.DbUnitHelper;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/domain/dao/jpa/test-context.xml",
		"/tudu/conf/jpa-dao-context.xml"		
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class TodoListDAOJpaTest implements IDataSetLocator {

	
	/**
	 * todoListDAO : ITodoListDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private ITodoListDAO todoListDAO;

	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method getTodoListOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getTodoListOK() {
		TodoList tl = this.todoListDAO.getTodoList("tl one");
		Assert.assertNotNull(tl);
		Assert.assertEquals("tl one",tl.getListId());
	}

	
	
	/**
	 * method getTodoListNOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getTodoListNOK() {
		try {
			this.todoListDAO.getTodoList("tl dummy");
			Assert.fail("todo list does not exist, a exception should have been thrown");
		} catch (ObjectRetrievalFailureException e) {
			// OK
		}
	}

	
	
	/**
	 * method saveTodoList() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void saveTodoList() {
		this.helper.compareTableCount("todo_list", 3);
		TodoList tl = new TodoList();
		tl.setName("some todo list");
		this.todoListDAO.saveTodoList(tl);
		this.helper.compareTableCount("todo_list", 4);
	}

	
	
	/**
	 * method updateTodoList() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test
	public void updateTodoList() throws Exception {
		this.helper.compareTableCount("todo_list", 3);
		TodoList tl = new TodoList();
		tl.setListId("tl one");
		tl.setName("modified todo list");
		this.todoListDAO.updateTodoList(tl);
		this.helper.compareTableCount("todo_list", 3);
		Assert.assertEquals("modified todo list", this.helper.getTable("todo_list").getValue(0, "name"));
	}

	
	
	/**
	 * method deleteTodoListOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void deleteTodoListOK() {
		this.helper.compareTableCount("todo_list", 3);
		// no todo for this list
		this.todoListDAO.removeTodoList("tl three");
		this.helper.compareTableCount("todo_list", 2);
	}

	
	
	/**
	 * method deleteTodoListNOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void deleteTodoListNOK() {
		
		this.helper.compareTableCount("todo_list", 3);
		// some todo for this list
		try {
			this.todoListDAO.removeTodoList("tl one");
			Assert.fail("this list has some todo, it should not be deleted");
		} catch (DataAccessException e) {
			// OK
		}
		this.helper.compareTableCount("todo_list", 3);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/todolist.xml";
	}

	
	
}
