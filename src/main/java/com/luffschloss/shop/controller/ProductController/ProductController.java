package com.luffschloss.shop.controller.ProductController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luffschloss.shop.model.Category;
import com.luffschloss.shop.model.Product;
import com.luffschloss.shop.model.Shop;
import com.luffschloss.shop.respository.CategoryRepository;
import com.luffschloss.shop.respository.ProductRepository;
import com.luffschloss.shop.respository.ShopRepository;
import com.luffschloss.shop.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin("*")
public class ProductController {
	final int NUM_OF_PAGING=5;
	static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("get-by-id/{id}")
	public Product getBySlug(@PathVariable(name = "id",required = false) Optional<Integer> id) {
		Optional<Product> p = productRepository.findById(id.get());
		if(p.isEmpty()) return null;
		else return p.get();
	}
	//get product and paging
	@GetMapping("list")
	public Iterable<Product> getListProduct(
			@RequestParam(value="page",required = false) String pageNum,
			@RequestParam(value = "cate_id",required = false) String cate_id,
			@RequestParam(value = "cate_id",required = false) String cate_name
			){
		//@PageableDefault(size = 5,sort = "id",direction = Direction.ASC) Pageable pageable
		int p = 0;
		try {p = Integer.parseInt(pageNum);} catch (Exception e) {}
		Pageable pageable = PageRequest.of(p, NUM_OF_PAGING,Sort.by("id").ascending());
		return productRepository.findAll(pageable).getContent();
	}
	
	
	//
	@GetMapping("find-by-category/{cate}")
	public Iterable<Product> getListProductByCateId(
			@RequestParam(value="page",required = false) String pageNum,
			@PathVariable(name = "cate",required = true)String cate
			){
		int p=0;
		try {p = Integer.valueOf(pageNum);} catch (Exception e) {}
		Pageable page = PageRequest.of(p, NUM_OF_PAGING, Sort.by("id").ascending());
		Iterable<Product> listP = productService.getProductByCategoryName(cate);
		return listP;
	}
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PostMapping(value="create",produces = "application/json")
	@ResponseBody
	public String postCreateProduct(@RequestBody Map<String,String> productInfo) {
		try {
			Optional<Category> cid = categoryRepository.findById(productInfo.get("product_cate"));
			Optional<Shop> sid = shopRepository.findById(productInfo.get("product_shop"));
			Product p = new Product();
			p.setAttr(productInfo.get("product_attr"));
			p.setCreatedate(Calendar.getInstance());
			p.setDescription(productInfo.get("product_des"));
			p.setName(productInfo.get("product_name"));
			if(!cid.isEmpty()) {p.setCategory_id(cid.get());}
			p.setPrice(Double.parseDouble(productInfo.get("product_price")));
			p.setSlug(productInfo.get("product_slug"));
			p.setSold(0);
			p.setStillsale(true);
			if(!sid.isEmpty()) {p.setShop_id(sid.get());}
			productRepository.save(p);
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PostMapping(value="update",produces = "application/json")
	@ResponseBody
	public String postUpdateProductInfo(@RequestBody Map<String,String> productInfo) {
		try {
			Optional<Category> cid = categoryRepository.findById(productInfo.get("product_cate"));
			Optional<Shop> sid = shopRepository.findById(productInfo.get("product_shop"));
			Product p = new Product();
			p.setAttr(productInfo.get("product_attr"));
			p.setCreatedate(Calendar.getInstance());
			p.setDescription(productInfo.get("product_des"));
			p.setName(productInfo.get("product_name"));
			if(!cid.isEmpty()) {p.setCategory_id(cid.get());}
			p.setPrice(Double.parseDouble(productInfo.get("product_price")));
			p.setSlug(productInfo.get("product_slug"));
			p.setSold(0);
			p.setStillsale(true);
			if(!sid.isEmpty()) {p.setShop_id(sid.get());}
			productRepository.save(p);
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}
}
