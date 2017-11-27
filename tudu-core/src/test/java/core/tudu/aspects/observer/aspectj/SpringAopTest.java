/**
 * 
 */
package tudu.aspects.observer.aspectj;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import tudu.aspects.observer.DummyObserver;
import tudu.domain.dao.ITodoDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.test.CleanInsertTestExecutionListener;
import tudu.test.IDataSetLocator;

/**
 * 
 * @author Arnaud Cogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/tudu/domain/dao/jpa/test-context.xml",
		"/tudu/conf/jpa-dao-context.xml",
		"/tudu/aspects/observer/aspectj/spring-aop-context.xml"
})
@TestExecutionListeners({CleanInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class SpringAopTest implements IDataSetLocator {
	
	/**
	 * todoDAO : ITodoDAO :<br/>
	 * .<br/>
	 */
	@Autowired
	private ITodoDAO todoDAO;
	
	/**
	 * observer : DummyObserver :<br/>
	 * .<br/>
	 */
	@Autowired
	private DummyObserver observer;

	
	
	/**
	 * method count() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
	public void count() {
		
		Assert.assertEquals(0, this.observer.getCount());
		Todo todo = new Todo();
		TodoList list = new TodoList();
		list.setListId("tl one");
		todo.setTodoList(list);
		todo.setDescription("some todo");
		this.todoDAO.saveTodo(todo);
		Assert.assertEquals(1, this.observer.getCount());
	}

	
	
	
	/**
	 * method updateTodo() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception :  :  .<br/>
	 */
	public void updateTodo() throws Exception {

		Todo todo = new Todo();
		todo.setTodoId("t one");
		TodoList list = new TodoList();
		list.setListId("tl one");
		todo.setTodoList(list);
		todo.setDescription("some updated todo");
		this.todoDAO.updateTodo(todo);
	
	}	

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataSet() {
		return "/tudu/domain/dao/dataset/todo.xml";
	}
	
	
	
}
