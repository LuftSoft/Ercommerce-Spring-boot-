package com.luffschloss.shop.controller.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luffschloss.shop.jwt.JwtTokenProvider;
import com.luffschloss.shop.model.ERole;
import com.luffschloss.shop.model.Role;
import com.luffschloss.shop.model.User;
import com.luffschloss.shop.payload.request.LoginRequest;
import com.luffschloss.shop.payload.request.SignupRequest;
import com.luffschloss.shop.payload.request.testDTO;
import com.luffschloss.shop.payload.response.JwtResponse;
import com.luffschloss.shop.payload.response.MessageResponse;
import com.luffschloss.shop.respository.UserRepository;
import com.luffschloss.shop.security.CustomUserDetails;
import com.luffschloss.shop.security.passwordEncoder;
import com.luffschloss.shop.service.RoleService;
import com.luffschloss.shop.service.UserService;



@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class UserLoginController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired 
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider tokenProvider;
	

	@GetMapping(path = "/{id}", produces = "application/json")
    public String getBook(@PathVariable int id) {
        return String.format("Id bang: %d", id);
    }
	
	@PostMapping(path="test")
	public ResponseEntity<?> testPost(@RequestBody Map<String,String> input){
		List<String> li = new ArrayList<>();
		for(String s:input.keySet()) {
			li.add(input.get(s));
		}
		return ResponseEntity.ok().body(li);
	}
	@GetMapping(path="/all")
	public ResponseEntity<?> getAllUser(){
		Set<User> setUser = userService.findAllUser();
		return ResponseEntity.ok(setUser);
	}
	@PostMapping(path ="/signup",produces = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
		try {
			if(userService.existByUserName(signupRequest.getUserName())) {
				return ResponseEntity.badRequest().body(new MessageResponse("username already exist"));
			}
			if(userService.existByEmail(signupRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("email already exist"));
			}
			User user = new User();
			user.setName(signupRequest.getUserName());
			user.setEmail(signupRequest.getEmail());
			user.setPassword(encoder.encode(signupRequest.getPassword()));
			user.setPhone(signupRequest.getPhone());
			//user.set(signupRequest.getCreateDate());
			user.setName(signupRequest.getUserName());
			user.setName(signupRequest.getUserName());
			Set<String> strRole = signupRequest.getListRoles();
			Set<Role> listRole = new HashSet<>();
			if(strRole == null) {
				Role userRole = roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("ERR: Role is not found"));
				listRole.add(userRole);
			}
			else {
				 strRole.forEach(r->{
					 switch(r) {
					 	case "admin":
					 		Role adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN)
					 			.orElseThrow(()->new RuntimeException("ERR: Admin role is not found"));
					 		listRole.add(adminRole);
					 		break;
					 	case "moderator":
					 		Role moderatorRole = roleService.findByRoleName(ERole.ROLE_MODERATOR)
					 			.orElseThrow(() -> new RuntimeException("Error: Moderator role is not found"));
					 		listRole.add(moderatorRole);
					 		break;
					 	case "user":
					 		Role userRole = roleService.findByRoleName(ERole.ROLE_USER)
				 			.orElseThrow(() -> new RuntimeException("Error: User role is not found"));
					 		listRole.add(userRole);
							 break;
					 }
				 });
			}
			user.setSetRole(listRole);
			userService.saveOrUpdate(user);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("sign up failed"));
		}
	}
	@PostMapping(path = "/signin",produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> UserLogin(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
			//return new ResponseEntity<>(customUser,HttpStatus.OK);
			//SInh ma JWT
			String jwt = tokenProvider.generateJwtToken(customUser);
			//return new ResponseEntity<>(jwt,HttpStatus.OK);
			/*Lay cac quyen cua user*/
			List<String> listRoles = customUser.getAuthorities().stream()
					.map(auth->auth.getAuthority()).collect(Collectors.toList());
			Set<String> setRoles = new HashSet<>();
			listRoles.forEach(item->setRoles.add(item));
			/*System.out.println(jwt);*/
			return ResponseEntity.ok(new JwtResponse(customUser.getUsername(),jwt,setRoles));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Sign in failed: "+e.getMessage())); 
		}
	}
	
	@PostMapping("/testqpi")
	public ResponseEntity<?> testcase(@RequestBody testDTO test) {
		return ResponseEntity.ok().body(test);
	}
}
