package com.piper.valley.models.repository;
import com.piper.valley.models.entity.Role;
import com.piper.valley.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
