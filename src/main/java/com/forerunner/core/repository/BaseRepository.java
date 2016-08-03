package com.forerunner.core.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.forerunner.core.search.Searchable;
import com.forerunner.foundation.domain.po.AbstractEntity;

/**
 * 抽象DAO层基类 提供一些简便方法<br>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加 factory-class=
 * "com.sinosoft.arch.core.common.repository.support.SimpleBaseRepositoryFactoryBean"
 * 
 * @author Administrator
 * @param <M>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<M extends AbstractEntity<ID>, ID extends Serializable>
		extends CrudRepository<M, ID>, Serializable {
	/**
	 * 根据主键删除
	 *
	 * @param ids
	 */
	public void delete(ID[] ids);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<M> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll(
	 * org.springframework.data.domain.Sort)
	 */
	List<M> findAll(Sort sort);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction
	 * provided in the {@code Pageable} object.
	 *
	 * @param pageable
	 * @return a page of entities
	 */
	Page<M> findAll(Pageable pageable);

	/**
	 * 根据条件查询所有 条件 + 分页 + 排序
	 *
	 * @param searchable
	 * @return
	 */
	public Page<M> findAll(Searchable searchable);

	/**
	 * 自定义sql 根据条件查询所有
	 * 
	 * @param searchable
	 * @return
	 */
	public List<M> findAll(String definedSql);

	/**
	 * 根据条件统计所有记录数
	 *
	 * @param searchable
	 * @return
	 */
	public long count(Searchable searchable);

	public List<M> query(String query, Map<String,Object> params, int begin, int max);

	public M getBy(Class<M> clazz, final String propertyName, final Object value);

	public M getBy(String propertyName, String value);

	public List<M> find(String condition, Map<String,Object> params, int begin, int max);

	public M getBy(String propertyName, Object value);

	public List<M> findByProperty(Map<String, String> params);

	public List<M> findByPropertyAndCondition(Map<String, String> params, String sqlCondition);

	public void flush();

}
