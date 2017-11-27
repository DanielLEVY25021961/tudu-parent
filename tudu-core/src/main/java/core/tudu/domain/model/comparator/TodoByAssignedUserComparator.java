package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their assigned user.
 * 
 * @author Julien Dubois
 */
public class TodoByAssignedUserComparator implements Comparator<Todo> {

	
	
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
            return -1;
        } else if (pFirst.getAssignedUser() == null
                && pSecond.getAssignedUser() != null) {
            return 1;
        }

        int order = 0;
        if (pFirst.getAssignedUser() != null && pSecond.getAssignedUser() != null) {
            order = pFirst.getAssignedUser().getLogin().compareTo(
                    pSecond.getAssignedUser().getLogin());
        }
        if (order == 0) {
            order = pFirst.compareTo(pSecond);
        }
        return order;
    }
    
    
}
