package com.luffschloss.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luffschloss.shop.model.Product;
import com.luffschloss.shop.service.ProductService;

@SpringBootTest
class LabShop1ApplicationTests {
	@Autowired
	private ProductService productService;
	@Test
	public void testProduct() {
//		Product p = productService.getProductDetailById(1);
//		System.out.println(p.getName());
	} 

}
