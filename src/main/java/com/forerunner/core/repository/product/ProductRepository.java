package com.forerunner.core.repository.product;

import java.util.List;

import com.forerunner.core.repository.BaseRepository;
import com.forerunner.foundation.domain.po.product.Product;

public interface ProductRepository extends BaseRepository<Product,Long>{
	/**
	 * 搜索产品列表
	 * @param product
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Product> searchProduct(Product product,Integer pageIndex,Integer pageSize);
	/**
	 * 搜索产品列表总数量
	 * @param product
	 * @return
	 */
	public Long searchCount(Product product);
}	
