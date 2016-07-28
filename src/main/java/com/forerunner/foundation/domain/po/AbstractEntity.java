package com.forerunner.foundation.domain.po;
/**
 * 所有的数据库实体类都需要继承该类 并实现主键设计
 * @author Administrator
 * @param <ID>
 */
public abstract class AbstractEntity<ID extends java.io.Serializable> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5526256384705921481L;
	/**
	 * 获取主键<p>
	 * 数据库设计应该有主键
	 * @return
	 */
	public abstract ID getId();
	/**
	 * 设置主键
	 * @param id
	 */
	public abstract void setId(ID id);

	public Boolean isNew() {
		return getId() == null;
	}
	
}
