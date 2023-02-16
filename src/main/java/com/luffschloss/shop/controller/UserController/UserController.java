package com.luffschloss.shop.controller.UserController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luffschloss.shop.model.User;
import com.luffschloss.shop.payload.request.LoginRequest;
import com.luffschloss.shop.payload.request.SignupRequest;
import com.luffschloss.shop.payload.response.MessageResponse;
@RestController()
	@RequestMapping("/api/v1/test")
@CrossOrigin("*")
public class UserController {
	@PostMapping("/signup")
	public ResponseEntity<?> UserControllerPostSignup(@RequestBody SignupRequest signupRequest) {
		return ResponseEntity.badRequest().body("login");
	}
	@PostMapping("/login")
	public ResponseEntity<?> UserControllerPostLogin(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.badRequest().body("login");
	}
	@GetMapping("/get-hello")
	public ResponseEntity<?> UserControllergetHello() {
		return ResponseEntity.ok().body(new MessageResponse("hello"));
	}
}
