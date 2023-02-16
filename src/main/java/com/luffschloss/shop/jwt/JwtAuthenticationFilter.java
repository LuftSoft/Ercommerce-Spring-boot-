package com.luffschloss.shop.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luffschloss.shop.security.CustomUserDetailService;
import com.luffschloss.shop.security.CustomUserDetails;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailService customUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			if(StringUtils.hasText(jwt)&& jwtTokenProvider.validateToken(jwt)) {
				//lay username tu jwt
				String username = jwtTokenProvider.getUsernameFromToken(jwt);
				//lay thong tin nguoi dung tu userid
				UserDetails userDetail = customUserDetailsService.loadUserByUsername(username);
				if(userDetail!=null) {
					//neu nguoi dung hop le se set thong tin cho security context.
					UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(userDetail, null,userDetail.getAuthorities());
					authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticate);
				}
			}
		} catch (Exception e) {
			log.error("fail to set authentication");
		}
		filterChain.doFilter(request, response);
	}
	//lay chuoi jwt tu` request
	public String getJwtFromRequest(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}	
		return null;
	}
}
