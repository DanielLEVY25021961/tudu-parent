package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their description, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByDescriptionAscComparator implements Comparator<Todo> {

	
	
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
        int order;
        order = pSecond.getDescription().toLowerCase().compareTo(
                pFirst.getDescription().toLowerCase());

        if (order == 0) {
            order = pSecond.compareTo(pFirst);
        }
        return order;
    }
    
    
    
}
