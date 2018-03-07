package com.piper.valley.models.repository;
import com.piper.valley.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/*Hibernate w JPA hayt3mlo fe el implementation*/
	User findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsernameOrEmail(String username, String email);
	boolean findByEmail(String email);
}
