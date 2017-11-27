package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their due date, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByDueDateAscComparator implements Comparator<Todo> {


	
	
    /**
     * {@inheritDoc}
     */
    @Override
	public int compare(
    		final Todo pFirst, final Todo pSecond) {
    	
        if (pFirst.isCompleted() && !pSecond.isCompleted()) {
            return 1;
        } else if (!pFirst.isCompleted() && pSecond.isCompleted()) {
            return -1;
        }
        int order = 0;
        if (pSecond.getDueDate() == null && pFirst.getDueDate() != null) {
            return 1;
        } else if (pSecond.getDueDate() != null && pFirst.getDueDate() == null) {
            return -1;
        } else if (pSecond.getDueDate() != null && pFirst.getDueDate() != null) {
            long compare = pSecond.getDueDate().getTime()
                    - pFirst.getDueDate().getTime();
            if (compare > 0) {
                order = 1;
            } else if (compare < 0) {
                order = -1;
            }
        }
        if (order == 0) {
            order = pSecond.compareTo(pFirst);
        }
        return order;
    }
    
    
    
}
