package com.forerunner.core.search.filter;

import java.util.List;

import com.google.common.collect.Lists;

public class OrCondition implements SearchFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034950517546332972L;

	private List<SearchFilter> orFilters = Lists.newArrayList();

	OrCondition() {
		super();
	}

	/**
	 * 添加or查询条件
	 * 
	 * @param filter
	 * @return
	 */
	public OrCondition add(SearchFilter filter) {
		orFilters.add(filter);
		return this;
	}
	/**
	 * 获取or查询条件
	 * @return
	 */
	public List<SearchFilter> getOrFilters() {
		return orFilters;
	}

	@Override
	public String toString() {
		return "OrCondition [orFilters=" + orFilters + "]";
	}
	
}
