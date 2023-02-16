package com.luffschloss.shop.respository;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luffschloss.shop.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
//	@Query("update product ")
//	int updateProduct(Product p);
	
	
//	  @Transactional
//	  @Query(
//	  value="insert into product(name,attr,description,slug,createdate,price,category_id,shop_id) "
//		+ "values(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
//	  int createProduct(String name,String attr,String des,String slug,Calendar
//	  create_date,Double price,String cate_id,String Shop_id);
//	  
//	  @Transactional
//	  @Query(value="select * from product where id=?",nativeQuery = true)
//	  Optional<Product> findByIdNative(int id);
	  
	  // Iterable<Product> findAllByCategory(Pageable p);
	 }
