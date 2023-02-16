package com.luffschloss.shop.service;


import java.util.Set;


import com.luffschloss.shop.model.User;



public interface UserService {
	User findByUserName(String username);
	boolean existByUserName(String username);
	boolean existByEmail(String email);
	boolean saveOrUpdate(User u);
	Set<User> findAllUser();
}
