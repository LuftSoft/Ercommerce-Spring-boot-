package com.luffschloss.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luffschloss.shop.model.User;
import com.luffschloss.shop.respository.UserRepository;
import com.luffschloss.shop.service.UserService;


//custom userdetailservice from spring security
@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	UserService userRepos;
	
	//check if username exits in db
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepos.findByUserName(username);
		if(u==null) {
			System.out.println("from customuserdetailservice: user is null");
		}
		//map user to role
		return CustomUserDetails.mapUserToUserDetail(u);
	}

}
