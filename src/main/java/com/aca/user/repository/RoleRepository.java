package com.aca.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aca.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
