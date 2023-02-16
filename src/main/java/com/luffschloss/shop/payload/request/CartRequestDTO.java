package com.luffschloss.shop.payload.request;

import org.springframework.beans.factory.annotation.Autowired;

import com.luffschloss.shop.model.ProductItem;
import com.luffschloss.shop.respository.ProductRepository;
import com.luffschloss.shop.service.ProductService;

public class CartRequestDTO {
	public String id;
	public int quantity;
}

