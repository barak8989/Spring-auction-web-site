// File: UserDao.java
// UserDao interface gets ability find user by email, delete by email,
// check if user exists by email.
package spring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//This is a DAO interface for users in DB
@Repository
public interface UserDao extends CrudRepository<SiteUser, Long> {
	SiteUser findByEmail(String email);
	void deleteByEmail(String email);
	boolean existsByEmail(String email);
}

