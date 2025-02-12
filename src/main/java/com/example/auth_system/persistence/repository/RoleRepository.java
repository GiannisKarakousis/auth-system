package com.example.auth_system.persistence.repository;

import com.example.auth_system.persistence.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
