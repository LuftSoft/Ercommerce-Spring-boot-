package com.luffschloss.shop.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luffschloss.shop.model.ProductItem;
import com.luffschloss.shop.respository.ProductItemRepository;
import com.luffschloss.shop.service.ProductItemService;

@Service
public class ProductItemServiceImp implements ProductItemService {
	@Autowired
	ProductItemRepository productItemRepos;

	@Override
	public ProductItem findById(String id) {
		Optional<ProductItem> op = productItemRepos.findById(id);
		if(op.isEmpty()) {
			return null;
		}else {			
			return op.get();
		}
	}

	@Override
	public boolean existByName(String name) {
		return productItemRepos.existsByName(name);
	}

	@Override
	public boolean saveOrUpdate(ProductItem p) {
		// TODO Auto-generated method stub
		try {
			productItemRepos.save(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
