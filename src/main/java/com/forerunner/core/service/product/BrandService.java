package com.forerunner.core.service.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.forerunner.core.search.SearchOperator;
import com.forerunner.core.search.Searchable;
import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.product.Brand;

@Service
public class BrandService extends BaseService<Brand,Long>{
	/**
	 * 查找品牌
	 * @param classify
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<Brand> searchBrand(Brand brand,Integer pageIndex,Integer pageSize){
		Searchable searchable=Searchable.newSearchable();
		searchable.addSearchFilter("deleteStatus", SearchOperator.eq, Boolean.FALSE);
		if(brand!=null){
			if(brand.getId()!=null){
				searchable.addSearchFilter("id", SearchOperator.eq, brand.getId());
			}else{
				if(StringUtils.isNotBlank(brand.getName())){
					searchable.addSearchFilter("name", SearchOperator.like, brand.getName());
				}
			}
		}
		searchable.setPage(pageIndex, pageSize);
		searchable.addSort(Direction.DESC, "sequence");
		searchable.addSort(Direction.DESC, "id");
		return this.findAll(searchable);
	}
}
