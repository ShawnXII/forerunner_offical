package com.forerunner.core.service.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.forerunner.core.search.SearchOperator;
import com.forerunner.core.search.Searchable;
import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.product.Product;

@Service
public class ProductService extends BaseService<Product, Long> {
	
	public Page<Product> findProduct(Long classifyId,String name,Integer pageIndex,Integer pageSize){
		Searchable searchable=Searchable.newSearchable();
		if(classifyId!=null&&classifyId>0){
			searchable.addSearchFilter("classifyId", SearchOperator.eq, classifyId);
		}
		if(StringUtils.isNotBlank(name)){
			searchable.addSearchFilter("productName", SearchOperator.like, name);
		}
		pageIndex=(pageIndex==null||pageIndex<0)?0:pageIndex;
		pageSize=(pageSize==null||pageSize<0)?20:pageSize;
		searchable.setPage(pageIndex, pageSize);
		Page<Product> page=this.findAll(searchable);
		return page;
	}
	
	public Page<Product> findProduct(){
		return this.findProduct(0, 20);
	}
	
	public Page<Product> findProduct(Integer pageIndex,Integer pageSize){
		return this.findProduct(null,null,pageIndex, pageSize);
	}
	
	
}
