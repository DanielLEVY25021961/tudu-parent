package tudu.security;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.IUserService;

/**
 * An implementation of Spring Security's AuthenticationDao.
 * 
 * @author Julien Dubois
 */
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	
    /**
     * log : Log :<br/>
     * .<br/>
     */
    private final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

    
    
    /**
     * userService : IUserService :<br/>
     * .<br/>
     */
    private IUserService userManager = null;

    
    
    /**
     * method setUserManager() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pUserManager :  :  .<br/>
     */
    public void setUserManager(
    		final IUserService pUserManager) {
        this.userManager = pUserManager;
    }

    
    
    /**
     * Load a user for Spring Security.
     */
    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException, DataAccessException {
    	
        login = login.toLowerCase();
        
        if (this.log.isDebugEnabled()) {
        	this.log.debug("Security verification for user '" + login + "'");
        }
        User user = null;
        try {
            user = this.userManager.findUser(login);
        } catch (ObjectRetrievalFailureException orfe) {
            throw new UsernameNotFoundException("User '" + login
                    + "' could not be found.");
        }
        
        user.setLastAccessDate(Calendar.getInstance().getTime());
        this.userManager.updateUser(user);

        Set<Role> roles = user.getRoles();
        
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(roles.size());
        
        for (Role role : roles) {
        	authList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(login,
                user.getPassword(), user.isEnabled(), true, true, true,
                authList);
    }
}
