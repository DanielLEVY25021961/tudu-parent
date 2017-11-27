package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their due date.
 * 
 * @author Julien Dubois
 */
public class TodoByDueDateComparator implements Comparator<Todo> {

	
	
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
        if (pFirst.getDueDate() == null && pSecond.getDueDate() != null) {
            return 1;
        } else if (pFirst.getDueDate() != null && pSecond.getDueDate() == null) {
            return -1;
        } else if (pFirst.getDueDate() != null && pSecond.getDueDate() != null) {
            long compare = pFirst.getDueDate().getTime()
                    - pSecond.getDueDate().getTime();
            if (compare > 0) {
                order = 1;
            } else if (compare < 0) {
                order = -1;
            }
        }
        if (order == 0) {
            order = pFirst.compareTo(pSecond);
        }
        return order;
    }
    
    
}
