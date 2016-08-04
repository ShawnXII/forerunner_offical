package com.forerunner.core.service.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.forerunner.core.search.SearchOperator;
import com.forerunner.core.search.Searchable;
import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.product.Classify;
/**
 * 产品分类
 * @author Administrator
 *
 */
@Service
public class ClassifyService  extends BaseService<Classify, Long>{
	/**
	 * 获取分类列表
	 * @return
	 */
	public Page<Classify> searchClassify(Classify classify,Integer pageIndex,Integer pageSize){
		Searchable searchable=Searchable.newSearchable();
		searchable.addSearchFilter("deleteStatus", SearchOperator.eq, Boolean.FALSE);
		if(classify!=null){
			if(classify.getId()!=null){
				searchable.addSearchFilter("id", SearchOperator.eq, classify.getId());
			}else{
				if(StringUtils.isNotBlank(classify.getTitle())){
					searchable.addSearchFilter("title", SearchOperator.like, classify.getTitle());
				}
			}
		}
		searchable.setPage(pageIndex, pageSize);
		return this.findAll(searchable);
	}
}
