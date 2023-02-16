package com.luffschloss.shop.testing;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luffschloss.shop.payload.response.MessageResponse;

@RestController
@RequestMapping("/api/v1/test")
@CrossOrigin("*")
public class testController {
	@GetMapping(path = "/for-all")
	public ResponseEntity<?> getforall(){
		return ResponseEntity.status(200).body(new MessageResponse("get for all user is ok"));
	}
	@GetMapping(path = "/for-admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getforadmin(){
		return ResponseEntity.status(200).body(new MessageResponse("getforadmin is ok"));
	}
	@GetMapping(path = "/for-moderator")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> getformoderator(){
		return ResponseEntity.status(200).body(new MessageResponse("getformoderator is ok"));
	}
	@GetMapping(path = "/for-user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getforuser(){
		return ResponseEntity.status(200).body(new MessageResponse("getforuser is ok"));
	}
	
}
