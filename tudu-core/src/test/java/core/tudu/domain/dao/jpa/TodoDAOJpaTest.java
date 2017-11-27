/**
 * 
 */
package tudu.domain.dao.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.domain.dao.ITodoDAO;
import tudu.domain.model.Todo;
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
public class TodoDAOJpaTest implements IDataSetLocator {

	
	/**
	 * todoDAO : ITodoDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private ITodoDAO todoDAO;

	
	/**
	 * helper : DbUnitHelper :<br/>
	 * .<br/>
	 */
	@Autowired
	private DbUnitHelper helper;

	
	
	/**
	 * method getTodoOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getTodoOK() {
		Todo t = this.todoDAO.getTodo("t one");
		Assert.assertNotNull(t);
		Assert.assertEquals("t one",t.getTodoId());
	}

	
	
	/**
	 * method getTodoNOK() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void getTodoNOK() {
		try {
			this.todoDAO.getTodo("t dummy");
			Assert.fail("todo does not exist, a exception should have been thrown");
		} catch (ObjectRetrievalFailureException e) {
			// OK
		}
	}

	
	
	/**
	 * method saveTodo() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void saveTodo() {
		this.helper.compareTableCount("todo",3);
		Todo todo = new Todo();
		TodoList list = new TodoList();
		list.setListId("tl one");
		todo.setTodoList(list);
		todo.setDescription("some todo");
		this.todoDAO.saveTodo(todo);
		this.helper.compareTableCount("todo", 4);
	}

	
	
	/**
	 * method updateTodo() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	@Test
	public void updateTodo() throws Exception {
		this.helper.compareTableCount("todo",3);
		Todo todo = new Todo();
		todo.setTodoId("t one");
		TodoList list = new TodoList();
		list.setListId("tl one");
		todo.setTodoList(list);
		todo.setDescription("some updated todo");
		this.todoDAO.updateTodo(todo);
		this.helper.compareTableCount("todo", 3);
		Assert.assertEquals("some updated todo", this.helper.getTable("todo").getValue(0, "description"));
	}

	
	
	/**
	 * method deleteTodo() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void deleteTodo() {
		this.helper.compareTableCount("todo",3);
		this.todoDAO.removeTodo("t one");
		this.helper.compareTableCount("todo",2);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/todo.xml";
	}

	
	
}
