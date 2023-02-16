package com.luffschloss.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luffschloss.shop.model.ProductItem;

@Service
public interface ProductItemService {
	ProductItem findById(String id);
	boolean existByName(String name);
	boolean saveOrUpdate(ProductItem p);
}
