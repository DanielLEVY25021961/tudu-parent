package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their assigned user, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByAssignedUserAscComparator implements Comparator<Todo> {

	
	
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

        if (pFirst.getAssignedUser() != null && pSecond.getAssignedUser() == null) {
            return 1;
        } else if (pFirst.getAssignedUser() == null
                && pSecond.getAssignedUser() != null) {
            return -1;
        }

        int order = 0;
        if (pFirst.getAssignedUser() != null && pSecond.getAssignedUser() != null) {
            order = pSecond.getAssignedUser().getLogin().compareTo(
                    pFirst.getAssignedUser().getLogin());
        }
        if (order == 0) {
            order = pSecond.compareTo(pFirst);
        }
        return order;
    }
    
    
    
}
