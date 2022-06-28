package com.biblioteca.apirest.repository;


import com.biblioteca.apirest.models.Role;
import com.biblioteca.apirest.models.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(TypeRole name);
}
