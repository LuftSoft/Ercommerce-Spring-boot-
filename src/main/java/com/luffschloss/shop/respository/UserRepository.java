package com.luffschloss.shop.respository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.luffschloss.shop.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByName(String name);
	User findByPassword(String password);
	User findByPhoneAndPassword(String phone,String password);
	User findByPhone(String phone);
	
	boolean existsByEmail(String email);
	boolean existsByName(String name);
}
