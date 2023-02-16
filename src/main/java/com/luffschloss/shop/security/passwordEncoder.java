package com.luffschloss.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
//class help inject passwordEncoder auto to project
public class passwordEncoder {
	private PasswordEncoder encoder;
	
	
}
