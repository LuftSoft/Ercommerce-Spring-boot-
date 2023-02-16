package com.luffschloss.shop.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
	public String UserName;
	public String Password;
}
