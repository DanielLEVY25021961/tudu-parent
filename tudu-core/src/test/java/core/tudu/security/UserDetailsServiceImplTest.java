package tudu.security;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tudu.domain.RolesEnum;
import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.IUserService;

/**
 * Test of the spring security user dao.
 * @author Julien Dubois
 * @author Arnaud Cogoluegnes
 *
 */
public class UserDetailsServiceImplTest {

	
	
	/**
	 * method testLoadUserByUsername() :<br/>
	 * .<br/>
	 * <br/>
	 * :  :  .<br/>
	 */
	@Test
    public void testLoadUserByUsername() {
		
        UserDetailsServiceImpl authenticationDAO = new UserDetailsServiceImpl();
        // the spring security DAO uses the main Tudu Lists service to effectively retrieve the user
        IUserService userManager = createMock(IUserService.class);
        // inject it
        authenticationDAO.setUserManager(userManager);

        // the user tested
        User user = new User();
        user.setLogin("test_user");
        user.setPassword("password");
        user.setEnabled(true);
        Role userRole = new Role();
        userRole.setRole(RolesEnum.ROLE_USER.toString());
        user.getRoles().add(userRole);
        
        // driving the mock behavior
        // expect to load a user and return the object
        expect(userManager.findUser("test_user")).andReturn(user);
        // the mock is still recording, this method will be called
        userManager.updateUser(user);

        // end the recording
        replay(userManager);

        // loading the user with 
        UserDetails acegiUser = authenticationDAO
                .loadUserByUsername("test_user");

        // checks the user and spring security user are the same
        assertEquals(user.getLogin(), acegiUser.getUsername());
        assertEquals(user.getPassword(), acegiUser.getPassword());
        // updated in the spring security DAO
        assertNotNull(user.getLastAccessDate());
        // check the role
        assertEquals(1, acegiUser.getAuthorities().size());
        
        @SuppressWarnings("unchecked")
		Collection<GrantedAuthority> collection 
			= (Collection<GrantedAuthority>) acegiUser.getAuthorities();
        
        final List<GrantedAuthority> listAuthorities 
        	= new ArrayList<GrantedAuthority>(collection);
        
        assertEquals(RolesEnum.ROLE_USER.toString(),
        		listAuthorities.get(0));

        // verify the mock has been used correctly
        verify(userManager);
    }
}
