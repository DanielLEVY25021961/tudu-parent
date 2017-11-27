package tudu.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
	
	private Todo todo1,todo2,todo3,todo4,todo5,todo6,todo7;
	
	@Before
	public void setUp() {
		this.todo1 = new Todo();
		this.todo1.setTodoId("01");
		this.todo1.setCompleted(false);
		this.todo1.setDescription("Description");
		this.todo1.setPriority(0);

		this.todo2 = new Todo();
		this.todo2.setTodoId("02");
		this.todo2.setCompleted(true);
		this.todo2.setDescription("Description");
		this.todo2.setPriority(0);

		this.todo3 = new Todo();
		this.todo3.setTodoId("03");
		this.todo3.setCompleted(false);
		this.todo3.setDescription("Description");
		this.todo3.setPriority(10);

		this.todo4 = new Todo();
		this.todo4.setTodoId("04");
		this.todo4.setCompleted(false);
		this.todo4.setDescription("AA");
		this.todo4.setPriority(10);

		this.todo5 = new Todo();
		this.todo5.setTodoId("05");
		this.todo5.setCompleted(true);
		this.todo5.setDescription("Description");
		this.todo5.setPriority(10);

		this.todo6 = new Todo();
		this.todo6.setTodoId("06");
		this.todo6.setCompleted(false);
		this.todo6.setDescription("Description");
		this.todo6.setPriority(10);
        
		this.todo7 = new Todo();
		this.todo7.setTodoId("01");
		this.todo7.setCompleted(false);
		this.todo7.setDescription("Description");
		this.todo7.setPriority(0);
	}
	

	/**
	 * method compareTo() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
    public void compareTo() {
		assertTrue(this.todo1.compareTo(this.todo7) == 0);

		try {
			this.todo1.compareTo(null);
			Assert.fail();
		} catch (NullPointerException e) {
			// OK
		}
		
        Collection<Todo> sortedTodos = new TreeSet<Todo>();
        sortedTodos.add(this.todo2);
        sortedTodos.add(this.todo6);
        sortedTodos.add(this.todo4);
        sortedTodos.add(this.todo1);
        sortedTodos.add(this.todo5);
        sortedTodos.add(this.todo3);

        Assert.assertEquals(6, sortedTodos.size());
        Iterator<Todo> iterator = sortedTodos.iterator();

        Todo testTodo = iterator.next();
        assertEquals("04", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("03", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("06", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("01", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("05", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("02", testTodo.getTodoId());
    }

	
	
	/**
	 * method equals() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
    public void equals() {
		assertFalse(this.todo1.equals(null));
        assertEquals(this.todo1, this.todo7);
        assertNotSame(this.todo1, this.todo7);
    }
	
	
	
}
