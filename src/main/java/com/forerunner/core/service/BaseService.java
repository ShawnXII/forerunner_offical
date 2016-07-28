package com.forerunner.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.forerunner.core.repository.BaseRepository;
import com.forerunner.foundation.domain.po.AbstractEntity;
import com.google.common.collect.Lists;

/**
 * 抽象Service层基类 提供一些数据库的增删改查方法
 * 
 * @author Administrator
 *
 * @param <M>
 * @param <ID>
 */
@Transactional
public abstract class BaseService<M extends AbstractEntity<ID>, ID extends java.io.Serializable> {

	@Autowired
	private BaseRepository<M, ID> baseRepository;

	public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}

	public BaseRepository<M, ID> getBaseRepository() {
		return this.baseRepository;
	}

	/**
	 * 保存单个实体
	 * 
	 * @param entity
	 * @return
	 */
	public M saveOrUpdate(M entity) {
		return this.baseRepository.save(entity);
	}

	/**
	 * 保存多个实体
	 * 
	 * @param entities
	 * @return
	 */
	public Iterable<M> save(Iterable<M> entities) {
		return this.baseRepository.save(entities);
	}

	/**
	 * 删除实体
	 * 
	 * @param m
	 */
	public void delete(M m) {
		baseRepository.delete(m);
	}
	/**
	 * 按照主键查询
	 * @param id
	 * @return
	 */
	public M findOne(ID id) {	
		return baseRepository.findOne(id);
	}
	/**
	 * 实体是否存在
	 * @param id
	 * @return
	 */
	public boolean exists(ID id) {
		return this.baseRepository.exists(id);
	}
	/**
	 * 统计实体数量
	 * @return
	 */
	public long count(){
		return this.baseRepository.count();
	}
	/**
	 * 查询全部
	 * @return
	 */
	public List<M> findAll(){
		Iterable<M> iterable=this.baseRepository.findAll();
		if(iterable!=null){
			return Lists.newArrayList(iterable.iterator());
		}
		return Lists.newArrayList();
	}
	
	public List<M> findAll(Sort sort){
		Iterable<M> iterable=this.baseRepository.findAll(null, sort);
		if(iterable!=null){
			return Lists.newArrayList(iterable.iterator());
		}
		return Lists.newArrayList();
	}
	
	public List<M> findAll(Specification<M> spec){
		Iterable<M> iterable=this.baseRepository.findAll(spec);
		if(iterable!=null){
			return Lists.newArrayList(iterable.iterator());
		}
		return Lists.newArrayList();
	}
	
	public List<M> findAll(Specification<M> spec,Pageable pageable){
		Iterable<M> iterable=this.baseRepository.findAll(spec, pageable);
		if(iterable!=null){
			return Lists.newArrayList(iterable.iterator());
		}
		return Lists.newArrayList();
	}
	
	public long count(Specification<M> spec){
		return this.baseRepository.count(spec);
	}
	
}
