package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their priority, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByPriorityAscComparator implements Comparator<Todo> {

	
	
    /**
     * {@inheritDoc}
     */
    @Override
	public int compare(
			final Todo pFirst, final Todo pSecond) {
    	
        int order = pFirst.getPriority() - pSecond.getPriority();
        if (pFirst.isCompleted()) {
            order += 10000;
        }
        if (pSecond.isCompleted()) {
            order -= 10000;
        }
        if (order == 0) {
            order = (pSecond.getDescription() + pSecond.getTodoId())
                    .compareTo(pFirst.getDescription() + pFirst.getTodoId());
        }
        return order;
    }
    
    
}
