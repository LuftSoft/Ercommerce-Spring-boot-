package com.luffschloss.shop.controller.UserController;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luffschloss.shop.jwt.JwtAuthenticationFilter;
import com.luffschloss.shop.jwt.JwtTokenProvider;
import com.luffschloss.shop.model.ProductItem;
import com.luffschloss.shop.model.User;
import com.luffschloss.shop.payload.request.CartRequestDTO;
import com.luffschloss.shop.payload.response.MessageResponse;
import com.luffschloss.shop.respository.ProductRepository;
import com.luffschloss.shop.service.ProductItemService;
import com.luffschloss.shop.service.UserService;

@RestController
@RequestMapping("user/cart")
@PreAuthorize("hasRole('USER')")
public class CartController {
	@Autowired
	UserService userService;
	@Autowired
	ProductItemService productItemService;
	@Autowired
	ProductRepository productRepository;
	JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();  
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@GetMapping("/all")
	public ResponseEntity<?>  getUserCart(HttpServletRequest req) {
		//List<CartItem> listProc =  cartItemRepos.findByUserId("id");
		String userName = jwtTokenProvider.getUsernameFromToken(req.getHeader("Authorization"));
		User u = userService.findByUserName(userName);
		return ResponseEntity.ok(u.getSetProductItem());
	}
	//paging product in user cart
	@GetMapping("/main")
	public ResponseEntity<?>  getUserCartPaging(
			HttpServletRequest req,
			@RequestParam(name = "page") String pageNum ) {
		//List<CartItem> listProc =  cartItemRepos.findByUserId("id");
		String userName = jwtTokenProvider.getUsernameFromToken(req.getHeader("Authorization"));
		User u = userService.findByUserName(userName);
		return ResponseEntity.ok(u);
	}
	
	@PostMapping(value = "/add-to-cart",produces = "application/json")
	public ResponseEntity<?> addToCart(
			HttpServletRequest req,
			@RequestBody CartRequestDTO cartRequest
			) {
		//tim san pham
		ProductItem p =  productItemService.findById(cartRequest.id);
		if(p==null) {
			return ResponseEntity.badRequest().body(false);
		}
		//lay user tu jwt
		String userName = jwtTokenProvider.getUsernameFromToken(req.getHeader("Authorization"));
		User u = userService.findByUserName(userName);
		if(!u.getSetProductItem().contains(p)) {
			u.addToCart(p);
			userService.saveOrUpdate(u);
			return ResponseEntity.ok(true);			
		}
		return ResponseEntity.badRequest().body(new MessageResponse("product already exist"));
	}
	@DeleteMapping(path = "/delete")
	public ResponseEntity<?> deleteFromCart(
			@RequestBody CartRequestDTO cartRequest,
			HttpServletRequest req
			){
		//tim kiem san pham voi id tuong ung
		ProductItem p =  productItemService.findById(cartRequest.id);
		if(p==null) {
			return ResponseEntity.badRequest().body(false);
		}
		//tim kiem user thong qua jwt
		String userName = jwtTokenProvider.getUsernameFromToken(req.getHeader("Authorization"));
		User u = userService.findByUserName(userName);
		Set<ProductItem> sP = u.getSetProductItem();
		if(sP.contains(p)) {
			sP.remove(p);
			u.setSetProductItem(sP);
			userService.saveOrUpdate(u);
			return ResponseEntity.ok(true);			
		}
		return ResponseEntity.badRequest().body(new MessageResponse("can't delete product"));
	}
}
