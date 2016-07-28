package com.forerunner.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.forerunner.foundation.domain.po.AbstractEntity;
/**
 * 抽象DAO层基类 提供一些简便方法<br>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加
 * factory-class="com.sinosoft.arch.core.common.repository.support.SimpleBaseRepositoryFactoryBean"
 * @author Administrator
 * @param <M>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<M extends AbstractEntity<ID>, ID extends Serializable> extends CrudRepository<M, ID>,JpaSpecificationExecutor<M>,Serializable{
	/**
	 * 根据主键删除
	 * @param ids
	 */
	public int delete(ID... ids);
	/**
	 * 查询条数
	 * @return
	 */
	public long count();
	

}
