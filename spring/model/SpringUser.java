// File: SpringUser.java
// SpringUser class is extentions of Spring User. Contains  two constructors, member firstname
// and getter and setter of this member.package spring.model;
package spring.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SpringUser extends User {

	private static final long serialVersionUID = 1L;
	private String firstname;

	public SpringUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public SpringUser(String firstname, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}
	
	

}
