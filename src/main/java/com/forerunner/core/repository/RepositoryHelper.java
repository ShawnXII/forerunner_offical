package com.forerunner.core.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.util.Assert;

import com.forerunner.core.search.Searchable;
import com.forerunner.core.search.annotation.EnableQueryCache;
import com.forerunner.core.search.callback.SearchCallback;

/**
 * 仓库辅助类
 * 
 * @author Administrator
 *
 */
public class RepositoryHelper {

	private static EntityManager entityManager;
	private Class<?> entityClass;
	private boolean enableQueryCache = true;
	
	
	
	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public boolean isEnableQueryCache() {
		return enableQueryCache;
	}

	public void setEnableQueryCache(boolean enableQueryCache) {
		this.enableQueryCache = enableQueryCache;
	}

	/**
	 * 是否开启了缓存查询
	 * 
	 * @param entityClass
	 */
	public RepositoryHelper(Class<?> entityClass) {
		this.entityClass = entityClass;
		EnableQueryCache enableQueryCacheAnnotation = AnnotationUtils.findAnnotation(entityClass,
				EnableQueryCache.class);
		boolean enableQueryCache = true;
		if (enableQueryCacheAnnotation != null) {
			enableQueryCache = enableQueryCacheAnnotation.value();
		}
		this.enableQueryCache = enableQueryCache;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		entityManager = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory,
				entityManagerFactory.getProperties());
	}

	public static EntityManager getEntityManager() {
		Assert.notNull(entityManager, "entityManager must null, please see "
				+ "[com.mhl.arch.core.common.repository.RepositoryHelper#setEntityManagerFactory]");
		return entityManager;
	}

	public static void flush() {
		getEntityManager().flush();
	}

	public static void clear() {
		flush();
		getEntityManager().clear();
	}

	/**
	 * ql条件查询 searchCallback默认实现请参考
	 * {@see com.sinosoft.arch.core.common.repository.callback.DefaultSearchCallback}
	 * 
	 * @param ql
	 * @param searchable
	 * @param searchCallback
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <M> List<M> findAll(final String ql, final Searchable searchable, final SearchCallback searchCallback) {
		assertConverted(searchable);
		StringBuilder s = new StringBuilder(ql);
		searchCallback.prepareQL(s, searchable);
		searchCallback.prepareOrder(s, searchable);
		Query query = getEntityManager().createQuery(s.toString());
		applyEnableQueryCache(query);
		searchCallback.setValues(query, searchable);
		searchCallback.setPageable(query, searchable);

		return query.getResultList();
	}

	public long count(final String ql, final Searchable searchable, final SearchCallback searchCallback) {

		assertConverted(searchable);
		StringBuilder s = new StringBuilder(ql);
		searchCallback.prepareQL(s, searchable);
		Query query = getEntityManager().createQuery(s.toString());
		applyEnableQueryCache(query);
		searchCallback.setValues(query, searchable);
		Object res = query.getSingleResult();
		if (res instanceof Integer) {
			return ((Integer) res);
		}
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public <M> M findOne(final String ql, final Searchable searchable, final SearchCallback searchCallback) {

		assertConverted(searchable);

		StringBuilder s = new StringBuilder(ql);
		searchCallback.prepareQL(s, searchable);
		searchCallback.prepareOrder(s, searchable);
		Query query = getEntityManager().createQuery(s.toString());
		applyEnableQueryCache(query);
		searchCallback.setValues(query, searchable);
		searchCallback.setPageable(query, searchable);
		query.setMaxResults(1);
		List<M> result = query.getResultList();

		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public <M> List<M> findAll(final String ql, final Object... params) {
		// 此处必须 (Pageable) null 否则默认有调用自己了 可变参列表
		return findAll(ql, (Pageable) null, params);
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> findAll(final String ql, final Pageable pageable, final Object... params) {
		Query query = getEntityManager().createQuery(ql + prepareOrder(pageable != null ? pageable.getSort() : null));
		applyEnableQueryCache(query);
		setParameters(query, params);
		if (pageable != null) {
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <M> List<M> findAll(final String ql, final Sort sort, final Object... params) {
		Query query = getEntityManager().createQuery(ql + prepareOrder(sort));
		applyEnableQueryCache(query);
		setParameters(query, params);
		return query.getResultList();
	}

	public <M> M findOne(final String ql, final Object... params) {
		List<M> list = findAll(ql, new PageRequest(0, 1), params);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public long count(final String ql, final Object... params) {
		Query query = entityManager.createQuery(ql);
		applyEnableQueryCache(query);
		setParameters(query, params);
		return (Long) query.getSingleResult();
	}

	public int batchUpdate(final String ql, final Object... params) {
		Query query = getEntityManager().createQuery(ql);
		setParameters(query, params);
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	public int batchUpdate(final String ql, final Set params) {
		Query query = getEntityManager().createQuery(ql);
		query.setParameter(1, params);
		return query.executeUpdate();
	}

	public void setParameters(Query query, Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
	}

	public String prepareOrder(Sort sort) {
		if (sort == null || !sort.iterator().hasNext()) {
			return "";
		}
		StringBuilder orderBy = new StringBuilder("");
		orderBy.append(" order by ");
		orderBy.append(sort.toString().replace(":", " "));
		return orderBy.toString();
	}

	public <T> JpaEntityInformation<T, ?> getMetadata(Class<T> entityClass) {
		return JpaEntityInformationSupport.getMetadata(entityClass, entityManager);
	}

	public String getEntityName(Class<?> entityClass) {
		return getMetadata(entityClass).getEntityName();
	}

	private void assertConverted(Searchable searchable) {
		if (!searchable.isConverted()) {
			searchable.convert(this.entityClass);
		}
	}

	public void applyEnableQueryCache(Object obj) {
		//缓存处理
	}

}
