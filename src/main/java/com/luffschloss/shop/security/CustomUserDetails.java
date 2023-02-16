package com.luffschloss.shop.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luffschloss.shop.model.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@AllArgsConstructor
@NoArgsConstructor
//custom info from UserDetail default in spring security
public class CustomUserDetails implements UserDetails{
	private int id;
	private String name;
	private String phone;
	@JsonIgnore
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> listAuth;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.listAuth;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	//method help map User to role
	public static CustomUserDetails mapUserToUserDetail(User u) {
		List<GrantedAuthority> listAuth =  u.getSetRole().stream()
				.map(role-> new SimpleGrantedAuthority(role.getRolename().name()))
				.collect(Collectors.toList());
		//return new userdetail 
		return new CustomUserDetails(
				u.getId(),
				u.getName(),
				u.getPhone(),
				u.getPassword(),
				u.getEmail(),
				listAuth
				);
	}
}
