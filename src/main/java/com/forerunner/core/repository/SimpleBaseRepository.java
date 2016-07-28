package com.forerunner.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.forerunner.foundation.domain.po.AbstractEntity;
/**
 * 抽象基础 Repository 实现
 * @author Administrator
 *
 * @param <M>
 * @param <ID>
 */
public class SimpleBaseRepository<M extends AbstractEntity<ID>, ID extends java.io.Serializable>
		extends SimpleJpaRepository<M, ID>implements BaseRepository<M, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 522032918676710603L;
	
	 private final  EntityManager em;
	 
	 private final JpaEntityInformation<M, ID> entityInformation;
	 
	 private String entityName;
	 
	 private Class<M> entityClass;
	 
	 private String idName;
	
	public SimpleBaseRepository(JpaEntityInformation<M, ID>  domainClass, EntityManager em) {
		super(domainClass, em);
		this.em=em;
		this.entityInformation=domainClass;
		this.entityName=domainClass.getEntityName();
		this.entityClass=domainClass.getJavaType();
		this.idName=domainClass.getIdAttributeNames().iterator().next();
	}
	/*
	 * 物理删除
	 * (non-Javadoc)
	 * @see com.forerunner.core.repository.BaseRepository#delete(java.io.Serializable[])
	 */
	@Override
	public int delete(ID... ids) {
		if(ArrayUtils.isEmpty(ids)){
			return 0;
		}
		String sql="delete from "+this.entityName+" where id in (?:ids)";
		Query query=this.em.createQuery(sql);
		query.setParameter("ids", ids);
		return query.executeUpdate();
	}

}
