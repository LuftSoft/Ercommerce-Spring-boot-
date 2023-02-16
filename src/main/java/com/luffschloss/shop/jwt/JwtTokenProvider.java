package com.luffschloss.shop.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.luffschloss.shop.security.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	@Value("${com.luffschloss.shop.jwt.secret}")
	private String JWT_SECRET;
	@Value("${com.luffschloss.shop.jwt.expirated}")
	private int JWT_EXPIRATION;
	public String generateJwtToken(CustomUserDetails userDetail) {
		Date now = new Date();
		Date dateExpirated = new Date(now.getTime()+JWT_EXPIRATION);
		return Jwts.builder()
		.setSubject(userDetail.getUsername())
		.setIssuedAt(now)
		.setExpiration(dateExpirated)
		.signWith(SignatureAlgorithm.HS512,JWT_SECRET)
		.compact();
	}
	//lay thong tin user tu token
	
	public String getUsernameFromToken(String token) {
		Claims claim = Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return claim.getSubject();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parse(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error("MalformedJwtException");
		}
		catch (ExpiredJwtException e) {
			log.error("ExpiredJwtException");
		}
		catch (IllegalArgumentException e) {
			log.error("IllegalArgumentException");
		}
		catch (UnsupportedJwtException e) {
			log.error("UnsupportedJwtException");
		}
		catch (Exception e) {
			log.error("Exception");
		}
		return false;
	}
}
