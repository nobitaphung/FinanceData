package com.pmkha.stockdata.repository;

import com.pmkha.stockdata.model.ERole;
import com.pmkha.stockdata.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
