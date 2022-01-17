package com.pmkha.stockdata.repository;

import com.pmkha.stockdata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String userEmail);
}
