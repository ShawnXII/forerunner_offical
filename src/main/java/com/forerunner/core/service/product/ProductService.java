package com.forerunner.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forerunner.core.repository.product.ProductRepository;
import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.product.Product;

@Service
public class ProductService extends BaseService<Product, Long> {
	
	@Autowired
	private ProductRepository productRepository;



	public List<Product> searchProduct(Product product, Integer pageIndex, Integer pageSize) {
		pageIndex = (pageIndex == null || pageIndex < 0) ? 0 : pageIndex;
		pageSize = (pageSize == null || pageSize < 0) ? 20 : pageSize;
		List<Product> resultList = productRepository.searchProduct(product, pageIndex, pageSize);
		return resultList;
	}
	
	public Long searchCount(Product product){
		return productRepository.searchCount(product);
	}

}
