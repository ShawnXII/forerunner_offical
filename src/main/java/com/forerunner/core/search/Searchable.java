package com.forerunner.core.search;

import java.util.Collection;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.forerunner.core.search.exception.InvalidSearchPropertyException;
import com.forerunner.core.search.exception.InvalidSearchValueException;
import com.forerunner.core.search.exception.SearchException;
import com.forerunner.core.search.filter.SearchFilter;

/**
 * 查询条件接口
 * 
 * @author Administrator
 *
 */
public abstract class Searchable implements Search {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2903518457775838920L;

	/**
	 * 创建一个新的查询
	 * 
	 * @return
	 */
	public static Searchable newSearchable() {
		return new SearchRequest();
	}

	/**
	 * 创建一个新的查询
	 * 
	 * @param searchParams
	 * @return
	 * @throws SearchException
	 */
	public static Searchable newSearchable(final Map<String, Object> searchParams) throws SearchException {
		return new SearchRequest(searchParams);
	}

	/**
	 * 创建一个新的查询
	 * 
	 * @param searchParams
	 * @param page
	 * @return
	 * @throws SearchException
	 */
	public static Searchable newSearchable(final Map<String, Object> searchParams, final Pageable page)
			throws SearchException {
		return new SearchRequest(searchParams, page);
	}

	/**
	 * 创建一个新的查询
	 * 
	 * @param searchParams
	 * @param sort
	 * @return
	 * @throws SearchException
	 */
	public static Searchable newSearchable(final Map<String, Object> searchParams, final Sort sort)
			throws SearchException {
		return new SearchRequest(searchParams, sort);
	}

	/**
	 * 创建一个新的查询
	 * 
	 * @param searchParams
	 * @param page
	 * @param sort
	 * @return
	 * @throws SearchException
	 */
	public static Searchable newSearchable(final Map<String, Object> searchParams, final Pageable page, final Sort sort)
			throws SearchException {
		return new SearchRequest(searchParams, page, sort);
	}

	/**
	 * 添加过滤条件 如key="parent.id_eq" value = 1<br>
	 * 如果添加时不加操作符 默认是custom 即如key=parent 实际key是parent_custom
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws SearchException
	 */
	public abstract Searchable addSearchParam(final String key, final Object value) throws SearchException;

	/**
	 * 添加一组过滤参数
	 * 
	 * @param searchParams
	 * @return
	 * @throws SearchException
	 */
	public abstract Searchable addSearchParams(final Map<String, Object> searchParams) throws SearchException;

	/**
	 * 添加过滤条件
	 * 
	 * @param searchProperty
	 * @param operator
	 * @param value
	 * @return
	 * @throws SearchException
	 */
	public abstract Searchable addSearchFilter(final String searchProperty, final String operator, final Object value)
			throws SearchException;
	/**
	 * 添加过滤条件
	 * @param searchFilter
	 * @return
	 */
	public abstract Searchable addSearchFilter(final SearchFilter searchFilter)throws SearchException;
	/**
	 * 添加多个and连接的过滤条件
	 * 
	 * @param searchFilters
	 * @return
	 */
	public abstract Searchable addSearchFilters(final Collection<? extends SearchFilter> searchFilters);

	/**
	 * 添加多个or连接的过滤条件
	 * 
	 * @param first
	 * @param others
	 * @return
	 */
	public abstract Searchable or(final SearchFilter first, final SearchFilter... others);

	/**
	 * 添加多个and连接的过滤条件
	 * 
	 * @param first
	 * @param others
	 * @return
	 */
	public abstract Searchable and(final SearchFilter first, final SearchFilter... others);

	/**
	 * 移除指定key的过滤条件
	 * 
	 * @param key
	 * @return
	 */
	public abstract Searchable removeSearchFilter(final String key);

	/**
	 * 移除指定属性 和 操作符的过滤条件
	 * 
	 * @param searchProperty
	 * @param operator
	 * @return
	 */
	public abstract Searchable removeSearchFilter(String searchProperty, SearchOperator operator);

	/**
	 * 把字符串类型的值转化为entity属性值
	 * 
	 * @param entityClass
	 * @return
	 * @throws InvalidSearchValueException
	 * @throws InvalidSearchPropertyException
	 */
	public abstract <T> Searchable convert(final Class<T> entityClass)
			throws InvalidSearchValueException, InvalidSearchPropertyException;

	/**
	 * 标识为已经转换过了 避免多次转换
	 * 
	 * @return
	 */
	public abstract Searchable markConverted();

	/**
	 * 添加分页信息
	 * 
	 * @param page
	 * @return
	 */
	public abstract Searchable setPage(final Pageable page);

	/**
	 * 添加分页信息
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public abstract Searchable setPage(final int pageNumber, final int pageSize);

	/**
	 * 添加排序信息
	 * 
	 * @param sort
	 * @return
	 */
	public abstract Searchable addSort(final Sort sort);

	/**
	 * 添加分页信息
	 * 
	 * @param direction
	 * @param property
	 * @return
	 */
	public abstract Searchable addSort(final Sort.Direction direction, String property);

	/**
	 * 获取查询过滤条件
	 * 
	 * @return
	 */
	public abstract Collection<SearchFilter> getSearchFilters();

	/**
	 * 是否已经转换过了 避免多次转换
	 * 
	 * @return
	 */
	public abstract boolean isConverted();

	/**
	 * 是否有查询参数
	 * 
	 * @return
	 */
	public abstract boolean hasSearchFilter();

	/**
	 * 是否有排序
	 * 
	 * @return
	 */
	public abstract boolean hashSort();

	/**
	 * 移除排序
	 */
	public abstract void removeSort();

	/**
	 * 是否有分页
	 * 
	 * @return
	 */
	public abstract boolean hasPageable();

	/**
	 * 移除分页
	 */
	public abstract void removePageable();

	/**
	 * 获取分页信息
	 * 
	 * @return
	 */
	public abstract Pageable getPage();

	/**
	 * 获取排序信息
	 * 
	 * @return
	 */
	public abstract Sort getSort();

	/**
	 * 是否包含查询键 如 name_like 包括 or 和 and
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean containsSearchKey(final String key);

	/**
	 * 获取查询属性对应的值 不能获取or 或 and
	 * 
	 * @param key
	 * @return
	 */
	public abstract Object getValue(final String key);
}
