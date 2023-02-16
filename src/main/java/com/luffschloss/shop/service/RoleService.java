package com.luffschloss.shop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luffschloss.shop.model.ERole;
import com.luffschloss.shop.model.Role;

@Service
public interface RoleService {
	Optional<Role> findByRoleName(ERole roleName);
}
