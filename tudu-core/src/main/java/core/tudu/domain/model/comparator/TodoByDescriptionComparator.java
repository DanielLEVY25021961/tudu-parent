package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their description.
 * 
 * @author Julien Dubois
 */
public class TodoByDescriptionComparator implements Comparator<Todo> {

	
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
        order = pFirst.getDescription().toLowerCase().compareTo(
                pSecond.getDescription().toLowerCase());

        if (order == 0) {
            order = pFirst.compareTo(pSecond);
        }
        return order;
    }
    
    
}
