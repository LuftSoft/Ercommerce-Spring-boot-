package com.luffschloss.shop.service;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.luffschloss.shop.model.Category;
import com.luffschloss.shop.model.Product;
import com.luffschloss.shop.respository.ProductRepository;




@Service
public class ProductService{
	@Autowired 
	private EntityManager entityManager;
	
//	@Autowired
//	private ProductRepository productRepo;
	
	public void search() {
		entityManager.persist(new Product());
	} 
	public Iterable<Product> getProductByCategoryId(String cateId){
		String StringQuery = "select p from Product p where p.category_id=:cate_id";
		TypedQuery<Product> JpqlQuery = entityManager.createQuery(StringQuery,Product.class);
		JpqlQuery.setParameter("cate_id", cateId);
		
		return JpqlQuery.getResultList();
	}
	//
	public Iterable<Product> getProductByCategoryName(String cateName){
		String StringQuery = "select p from Product p join Category c on p.category_id=c.id and c.name=:cate_name";
		TypedQuery<Product> JpqlQuery = entityManager.createQuery(StringQuery, Product.class);
		JpqlQuery.setParameter("cate_name", cateName);

		
		return JpqlQuery.getResultList();
	}
	/*implements*/
	
}
