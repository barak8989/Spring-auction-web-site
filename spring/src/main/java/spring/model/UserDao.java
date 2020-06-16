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

