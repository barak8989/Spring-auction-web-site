// File: UserService.java
// UserService class is service provides ability to add/get users.
package spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.model.SiteUser;
import spring.model.SpringUser;
import spring.model.UserDao;

//  Service to access to users in DB
@Service
public class UserService implements UserDetailsService {

    //provides access
    @Autowired
    private UserDao userDao;

    private SiteUser currentUser;


    /**
     * Saves new user in DB.
     * @param user
     */
    @Transactional
    public void register(SiteUser user) {
        // Role is user
        user.setRole("ROLE_USER");
        //save
        userDao.save(user);
    }


    /**
     * Loads information of specific user finded by e-mail(unique).
     * @param email
     * @return user
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        currentUser = userDao.findByEmail(email);;
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String password = currentUser.getPassword();
        String firstname = currentUser.getFirstname();

        return new SpringUser(firstname, email, password, true, true, true, true, auth);
    }


    /**
     * Returns current user(without access to DB)
     * @return user
     */
    public SiteUser getUser(){
        return  currentUser;
    }
    
}
