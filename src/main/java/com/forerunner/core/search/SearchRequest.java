package com.forerunner.core.search;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.forerunner.core.search.exception.InvalidSearchPropertyException;
import com.forerunner.core.search.exception.InvalidSearchValueException;
import com.forerunner.core.search.exception.SearchException;
import com.forerunner.core.search.filter.AndCondition;
import com.forerunner.core.search.filter.Condition;
import com.forerunner.core.search.filter.OrCondition;
import com.forerunner.core.search.filter.SearchFilter;
import com.forerunner.core.search.filter.SearchFilterHelper;
import com.forerunner.core.search.util.SearchableConvertUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 查询条件(包括分页和排序)
 * 
 * @author Administrator
 */
public final class SearchRequest extends Searchable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1514512151404574401L;

	private final Map<String, SearchFilter> searchFilterMap = Maps.newHashMap();
	/*
	 * 使用list是保证拼sql时的顺序是按照添加时候的顺序
	 */
	private final List<SearchFilter> searchFilters = Lists.newArrayList();
	// 分页
	private Pageable page;
	// 排序
	private Sort sort;

	private boolean converted;

	/**
	 * @param searchParams
	 * @see SearchRequest#SearchRequest
	 */
	public SearchRequest() {
		this(null, null, null);
	}

	/**
	 * @param searchParams
	 * @see SearchRequest#SearchRequest(java.util.Map
	 *      <java.lang.String,java.lang.Object>)
	 */
	public SearchRequest(final Map<String, Object> searchParams) throws SearchException {
		this(searchParams, null, null);
	}

	/**
	 * @param searchParams
	 * @see SearchRequest#SearchRequest(java.util.Map
	 *      <java.lang.String,java.lang.Object>)
	 */
	public SearchRequest(final Map<String, Object> searchParams, final Pageable page) throws SearchException {
		this(searchParams, page, null);
	}

	/**
	 * @param searchParams
	 * @see SearchRequest#SearchRequest(java.util.Map
	 *      <java.lang.String,java.lang.Object>)
	 */
	public SearchRequest(final Map<String, Object> searchParams, final Sort sort) throws SearchException {
		this(searchParams, null, sort);
	}

	/**
	 * 根据查询参数拼Search<br>
	 * 查询参数格式：property_op=value 或 customerProperty=value<br>
	 * customerProperty查找规则是：1、先查找domain的属性，2、
	 * 如果找不到查找domain上的SearchPropertyMappings映射规则
	 * 属性、操作符之间用_分割，op可省略/或custom，省略后值默认为custom，即程序中自定义<br>
	 * 如果op=custom，property也可以自定义（即可以与domain的不一样）
	 * 
	 * @param searchParams
	 * @param page
	 * @param sort
	 * @throws SearchException
	 */
	public SearchRequest(final Map<String, Object> searchParams, final Pageable page, final Sort sort)
			throws SearchException {
		toSearchFilters(searchParams);
		merge(sort, page);
	}

	private void toSearchFilters(final Map<String, Object> searchParams) throws SearchException {
		if (searchParams == null || searchParams.size() == 0) {
            return;
        }
		for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			addSearchFilter(SearchFilterHelper.newCondition(key, value));
		}
	}

	@Override
	public Searchable addSearchParam(String key, Object value) throws SearchException {
		addSearchFilter(SearchFilterHelper.newCondition(key, value));
		return this;
	}

	@Override
	public Searchable addSearchParams(Map<String, Object> searchParams) throws SearchException {
		toSearchFilters(searchParams);
		return this;
	}

	@Override
	public Searchable addSearchFilter(String searchProperty, String operator, Object value) throws SearchException {
		SearchFilter searchFilter = SearchFilterHelper.newCondition(searchProperty, operator, value);
		return addSearchFilter(searchFilter);
	}

	@Override
	public Searchable addSearchFilter(SearchFilter searchFilter) throws SearchException {
		if (searchFilter == null) {
			return this;
		}
		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			String key = condition.getKey();
			searchFilterMap.put(key, condition);
		}
		int index = searchFilters.indexOf(searchFilter);
		if (index != -1) {
			searchFilters.set(index, searchFilter);
		} else {
			searchFilters.add(searchFilter);
		}
		return this;
	}

	@Override
	public Searchable addSearchFilters(Collection<? extends SearchFilter> searchFilters) {
		if (CollectionUtils.isEmpty(searchFilters)) {
			return this;
		}
		for (SearchFilter searchFilter : searchFilters) {
			addSearchFilter(searchFilter);
		}
		return this;
	}

	@Override
	public Searchable or(SearchFilter first, SearchFilter... others) {
		addSearchFilter(SearchFilterHelper.or(first, others));
		return this;
	}

	@Override
	public Searchable and(SearchFilter first, SearchFilter... others) {
		addSearchFilter(SearchFilterHelper.and(first, others));
		return this;
	}

	@Override
	public Searchable removeSearchFilter(String key) {
		if (key == null) {
			return this;
		}
		SearchFilter searchFilter = searchFilterMap.remove(key);
		if (searchFilter == null) {
			searchFilter = searchFilterMap.remove(getCustomKey(key));
		}
		if (searchFilter == null) {
			return this;
		}
		searchFilters.remove(searchFilter);
		return this;
	}

	private String getCustomKey(String key) {
		return key + Condition.SEPARATOR + SearchOperator.custom;
	}

	@Override
	public Searchable removeSearchFilter(String searchProperty, SearchOperator operator) {
		this.removeSearchFilter(searchProperty + Condition.SEPARATOR + operator);
		return this;
	}

	@Override
	public <T> Searchable convert(Class<T> entityClass)
			throws InvalidSearchValueException, InvalidSearchPropertyException {
		SearchableConvertUtils.convertSearchValueToEntityValue(this, entityClass);
		markConverted();
		return this;
	}

	@Override
	public Searchable markConverted() {
		this.converted = true;
		return this;
	}

	@Override
	public Searchable setPage(Pageable page) {
		merge(sort, page);
		return this;
	}

	@Override
	public Searchable setPage(int pageNumber, int pageSize) {
		merge(sort, new PageRequest(pageNumber, pageSize));
		return this;
	}

	@Override
	public Searchable addSort(Sort sort) {
		merge(sort, page);
		return this;
	}

	@Override
	public Searchable addSort(Direction direction, String property) {
		merge(new Sort(direction, property), page);
		return this;
	}

	@Override
	public Collection<SearchFilter> getSearchFilters() {
		return Collections.unmodifiableCollection(searchFilters);
	}

	@Override
	public boolean isConverted() {
		return converted;
	}

	@Override
	public boolean hasSearchFilter() {
		return !searchFilters.isEmpty();
	}

	@Override
	public boolean hashSort() {
		return this.sort != null && this.sort.iterator().hasNext();
	}

	@Override
	public void removeSort() {
		this.sort = null;
		if (this.page != null) {
			this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), null);
		}
	}

	@Override
	public boolean hasPageable() {
		return this.page != null && this.page.getPageSize() > 0;
	}

	@Override
	public void removePageable() {
		this.page = null;
	}

	@Override
	public Pageable getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}

	@Override
	public Sort getSort() {
		return this.sort;
	}

	@Override
	public boolean containsSearchKey(String key) {
		boolean contains = searchFilterMap.containsKey(key) || searchFilterMap.containsKey(getCustomKey(key));

		if (contains) {
			return true;
		}

		// 否则检查其中的or 和 and
		return containsSearchKey(searchFilters, key);
	}

	@Override
	public Object getValue(String key) {
		SearchFilter searchFilter = searchFilterMap.get(key);
		if (searchFilter == null) {
			searchFilter = searchFilterMap.get(getCustomKey(key));
		}
		if (searchFilter == null) {
			return null;
		}

		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			return condition.getValue();
		}

		return null;
	}

	private void merge(Sort sort, Pageable page) {
		if (sort == null) {
			sort = this.sort;
		}
		if (page == null) {
			page = this.page;
		}

		// 合并排序
		if (sort == null) {
			this.sort = page != null ? page.getSort() : null;
		} else {
			this.sort = (page != null ? sort.and(page.getSort()) : sort);
		}
		// 把排序合并到page中
		if (page != null) {
			this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), this.sort);
		} else {
			this.page = null;
		}
	}

	private boolean containsSearchKey(List<SearchFilter> searchFilters, String key) {
		boolean contains = false;
		for (SearchFilter searchFilter : searchFilters) {
			if (searchFilter instanceof OrCondition) {
				OrCondition orCondition = (OrCondition) searchFilter;
				contains = containsSearchKey(orCondition.getOrFilters(), key);
			}
			if (searchFilter instanceof AndCondition) {
				AndCondition andCondition = (AndCondition) searchFilter;
				contains = containsSearchKey(andCondition.getAndFilters(), key);
			}

			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;
				contains = condition.getKey().equals(key) || condition.getSearchProperty().equals(key);
			}

			if (contains) {
				return true;
			}
		}

		return contains;
	}
}
