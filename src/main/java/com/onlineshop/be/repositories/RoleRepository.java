package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.Role;
import com.onlineshop.be.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRoleType(RoleType roleType);
}
