package com.luffschloss.shop.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luffschloss.shop.model.ProductItem;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, String>{
	boolean existsByName(String name);
}
