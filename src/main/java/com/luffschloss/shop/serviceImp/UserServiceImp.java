package com.luffschloss.shop.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luffschloss.shop.model.User;
import com.luffschloss.shop.respository.UserRepository;
import com.luffschloss.shop.service.UserService;
@Service
public class UserServiceImp implements UserService{
	@Autowired
	UserRepository userRepos;
	
	@Override
	public User findByUserName(String username) {
		return userRepos.findByName(username);
	}

	@Override
	public boolean existByUserName(String username) {
		return userRepos.existsByName(username);
	}

	@Override
	public boolean existByEmail(String email) {
		return userRepos.existsByEmail(email);
	}

	@Override
	public boolean saveOrUpdate(User u) {
		try {
			userRepos.save(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public Set<User> findAllUser() {
		List<User> user = userRepos.findAll();
		if(user.isEmpty())return null;
		return new HashSet<>(user);
	}

}
