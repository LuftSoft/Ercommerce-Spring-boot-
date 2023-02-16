package com.luffschloss.shop.payload.response;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	private String ResToken;
	private String Type = "Bearer";
	private String UserName;
	private Set<String> ListRoles;
	public JwtResponse(String user,String jwt,Set<String> li) {
		this.ResToken = jwt;this.UserName=user;this.ListRoles= li;
	}
}
