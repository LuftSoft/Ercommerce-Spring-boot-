package com.luffschloss.shop.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.luffschloss.shop.model.Admin;



public interface AdminRepository extends Repository<Admin, String>{
//	@Query("select a from Admin where a.email='hello world'")
//	@Transactional(timeout = 10)
//	Admin checkEmailAndPhone(@Param("email") String email,String phone);
}
