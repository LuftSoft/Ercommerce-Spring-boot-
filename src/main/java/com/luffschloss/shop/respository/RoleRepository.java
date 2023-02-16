package com.luffschloss.shop.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luffschloss.shop.model.ERole;
import com.luffschloss.shop.model.Role;
public interface RoleRepository extends JpaRepository<Role,Integer>{
	Optional<Role> findByRolename(ERole roleName);
}
