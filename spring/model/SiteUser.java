// File: SiteUser.java
// SiteUser class represents user. Contains getters and setters.
package spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

//This class represents entity in table of users.
@Entity
@Table(name = "users")
public class SiteUser {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private Long id;


@Column(name = "email", unique=true,length = 200)
@Email(message="{register.email.invalid}")
@NotBlank(message="{register.email.invalid}")
private String email;


@NotNull
@Column(name = "firstname", length = 20)
@Size(min = 2, max = 20, message = "{register.firstname.size}")
private String firstname;

@NotNull
@Column(name = "surename", length = 25)
@Size(min = 2, max = 25, message = "{register.surename.size}")
private String surename;


@Transient
@NotNull
@Size(min=5, max=15, message="{register.password.size}")
private String plainPassword;

// Relation between two tables(users and auctions)
@OneToMany(mappedBy="seller", fetch = FetchType.LAZY)
private List<AuctionUpdate> auctions;

@Column(name = "password")
private String password;

@Column(name="role", length=20)
private String role;

/////////////// 						GETTERS AND SETTERS										/////////////

public Long getId() {
	return id;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getSurename() {
	return surename;
}

public void setSurename(String surename) {
	this.surename = surename;
}

public void setId(Long id) {
	this.id = id;
}


public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public String getEmail(Long buyer) {
	return email;
}


public String getPlainPassword() {
	return plainPassword;
}
public void setPlainPassword(String plainPassword) {
	this.password = new BCryptPasswordEncoder().encode(plainPassword);
	this.plainPassword = plainPassword;
}

}
