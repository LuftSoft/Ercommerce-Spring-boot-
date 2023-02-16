package com.luffschloss.shop.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luffschloss.shop.model.ERole;
import com.luffschloss.shop.model.Role;
import com.luffschloss.shop.respository.RoleRepository;
import com.luffschloss.shop.service.RoleService;
@Service
public class RoleServiceImp implements RoleService{
	@Autowired 
	RoleRepository roleRepos;

	@Override
	public Optional<Role> findByRoleName(ERole roleName) {
		// TODO Auto-generated method stub
		return roleRepos.findByRolename(roleName);
	}
	
}
