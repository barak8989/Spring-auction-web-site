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

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    private SiteUser currentUser;

    @Transactional
    public void register(SiteUser user) {
        user.setRole("ROLE_USER");
        //save
        userDao.save(user);
        //get user with generated id
        currentUser = userDao.findByEmail(user.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        currentUser = userDao.findByEmail(email);;
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String password = currentUser.getPassword();
        String firstname = currentUser.getFirstname();

        return new SpringUser(firstname, email, password, true, true, true, true, auth);
    }

    public SiteUser getUser(){
        return  currentUser;
    }
    
}
