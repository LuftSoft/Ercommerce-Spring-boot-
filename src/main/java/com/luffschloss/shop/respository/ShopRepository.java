package com.luffschloss.shop.respository;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luffschloss.shop.model.Category;
import com.luffschloss.shop.model.Product;
import com.luffschloss.shop.model.Shop;



@Repository
public interface ShopRepository extends JpaRepository<Shop, String>{ }
