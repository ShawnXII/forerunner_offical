package com.forerunner.core.search.filter;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * And 条件
 * @author Administrator
 *
 */
public class AndCondition implements SearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8408229837209682927L;
	
	private List<SearchFilter> andFilters=Lists.newArrayList();
	
	AndCondition(){
		super();
	}
	/**
	 * 添加and查询
	 * @param filter
	 * @return
	 */
	public AndCondition add(SearchFilter filter){
		andFilters.add(filter);
		return this;
	}
	/**
	 * 获取and查询条件
	 * @return
	 */
	public List<SearchFilter> getAndFilters() {
		return andFilters;
	}
	
	@Override
	public String toString() {
		return "AndCondition [andFilters=" + andFilters + "]";
	}
	
}
