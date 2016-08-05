package com.forerunner.core.service.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.forerunner.core.search.SearchOperator;
import com.forerunner.core.search.Searchable;
import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.product.Property;

@Service
public class PropertyService extends BaseService<Property, Long>{
	
/*	@Autowired
	private SpecificationService specificationService;*/
	
	/**
	 * 获取分类列表
	 * @return
	 */
	public Page<Property> searchProperty(Property property,Integer pageIndex,Integer pageSize){
		Searchable searchable=Searchable.newSearchable();
		if(property!=null){
			if(property.getId()!=null){
				searchable.addSearchFilter("id", SearchOperator.eq, property.getId());
			}else{
				if(StringUtils.isNotBlank(property.getPropertyName())){
					searchable.addSearchFilter("propertyName", SearchOperator.like, property.getPropertyName());
				}
			}
		}
		searchable.setPage(pageIndex, pageSize);
		return this.findAll(searchable);
	}
}
