package com.forerunner.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.forerunner.core.repository.BaseRepository;
import com.forerunner.core.search.Searchable;
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

	protected BaseRepository<M, ID> baseRepository;
	
	protected boolean hasInit = false;
	protected boolean isFullCached = false;
	
	@Autowired
	public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}

	public BaseRepository<M, ID> getBaseRepository() {
		return this.baseRepository;
	}
	
	public void init() {
		this.hasInit = true;
	}
	
	 /**
     * 保存单个实体
     *
     * @param m 实体
     * @return 返回保存的实体
     */
    public boolean save(M m) {
    	M m1 =  baseRepository.save(m);
    	if(m1 != null){
    		return true;
    	}
        return false;
    }
    
    public Iterable<M> save(Iterable<M> entities) {
    	Iterable<M> m1 =  baseRepository.save(entities);
    	return m1;
    }
    
    public M saveObj(M m) {
    	m =  baseRepository.save(m);
    	if(m != null){
    		return m;
    	}
    	return null;
    }

    public M saveAndFlush(M m) {
        m = baseRepository.save(m);
       // baseRepository.flush();
        return m;
    }

    /**
     * 更新单个实体
     *
     * @param m 实体
     * @return 返回更新的实体
     */
    public boolean update(M m) {
    	M m1 = baseRepository.save(m);
    	if(m1.equals(m)){
    		return false;
    	}
    	return true;
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    @Transactional
    public boolean delete(ID id) {
        baseRepository.delete(id);
        return true;
    }

    /**
     * 删除实体
     *
     * @param m 实体
     */
    public void delete(M m) {
        baseRepository.delete(m);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param ids 实体
     */
    public void delete(ID[] ids) {
        baseRepository.delete(ids);
    }


    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public M findOne(ID id) {
        M m = baseRepository.findOne(id);
        return m;
    }

    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    public boolean exists(ID id) {
        return baseRepository.exists(id);
    }

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public long count() {
        return baseRepository.count();
    }

    /**
     * 查询所有实体
     *
     * @return
     */
    public List<M> findAll() {
        return baseRepository.findAll();
    }

    /**
     * 按照顺序查询所有实体
     *
     * @param sort
     * @return
     */
    public List<M> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    /**
     * 分页及排序查询实体
     *
     * @param pageable 分页及排序数据
     * @return
     */
    public Page<M> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * 按条件分页并排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public Page<M> findAll(Searchable searchable) {
        return baseRepository.findAll(searchable);
    }

    /**
     * 组装自定义sql
     * @return
     */
    public List<M> findAll(String definedSql) {
        return baseRepository.findAll(definedSql);
    }
    
    /**
     * 按条件不分页不排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public List<M> findAllWithNoPageNoSort(Searchable searchable) {
        searchable.removePageable();
        searchable.removeSort();
        return Lists.newArrayList(baseRepository.findAll(searchable).getContent());
    }

    /**
     * 按条件排序查询实体(不分页)
     *
     * @param searchable 条件
     * @return
     */
    public List<M> findAllWithSort(Searchable searchable) {
        searchable.removePageable();
        return Lists.newArrayList(baseRepository.findAll(searchable).getContent());
    }


    /**
     * 按条件分页并排序统计实体数量
     *
     * @param searchable 条件
     * @return
     */
    public Long count(Searchable searchable) {
        return baseRepository.count(searchable);
    }

    public List<M> findByPropertyAndCondition(Map<String,String> params,String sqlCondition){
    	return baseRepository.findByPropertyAndCondition(params,sqlCondition);
    }
    
    public List<M> findByProperty(Map<String,String> params){
    	return baseRepository.findByProperty(params);
    }

	public List<M> query(String query, Map<String,Object> params, int begin, int max) {
		return this.baseRepository.query(query, params, begin, max);
	}


	public M getObjByProperty(Class<M> clazz, String propertyName, String value) {
		return this.baseRepository.getBy(clazz, propertyName, value);
	}
	
	public M getObjByProperty(String propertyName, String value) {
		return this.baseRepository.getBy(propertyName, value);
	}
	
	public M getObjByProperty(String propertyName, Object value) {
		return this.baseRepository.getBy(propertyName, value);
	}
	
	public M getObjById(ID id){
		return this.baseRepository.findOne(id);
	}
	
	public M getBy(String propertyName, Object value) {
		return this.baseRepository.getBy(propertyName, value);
	}

	public boolean isHasInit() {
		return hasInit;
	}

	public void setHasInit(boolean hasInit) {
		this.hasInit = hasInit;
	}
	public void setHasInit() {
		this.hasInit = true;
	}
	
	
}
