package com.forerunner.core.service.product;

import org.springframework.stereotype.Service;

import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.product.Product;

@Service
public class ProductService extends BaseService<Product, Long> {

	public Product findOne(Long id) {
		return this.getBaseRepository().findOne(id);
	}
	
}
