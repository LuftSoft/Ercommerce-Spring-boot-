package com.luffschloss.shop.service;

import java.beans.JavaBean;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@JavaBean
public class corsFilter {
	final UrlBasedCorsConfigurationSource source =     new UrlBasedCorsConfigurationSource();     
	final CorsConfiguration config = new CorsConfiguration();
	//@Bean
	public WebMvcConfigurer corsConfiger() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/").allowedOriginPatterns("http://localhost:4200");
			}
			
		};
	}
//	config.setAllowCredentials(true);
//	config.addAllowedOrigin("*");
//	config.addAllowedHeader("*");
//	config.addAllowedMethod("OPTIONS");
//	config.addAllowedMethod("HEAD");
//	config.addAllowedMethod("GET");
//	config.addAllowedMethod("PUT");
//	config.addAllowedMethod("POST");
//	config.addAllowedMethod("DELETE");
//	config.addAllowedMethod("PATCH");
//	source.registerCorsConfiguration("/**", config); return new CorsFilter(source);
}
