package com.forerunner.foundation.domain.po.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.AbstractEntity;
/**
 * 产品规格
 * 规格不需要记录多于的信息 用于节约数据库空间
 * @author Administrator
 */
@Entity
@Table(name="business_specification")
public class Specification extends AbstractEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390513575953925237L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//规格名称
	@Column(name="spec_name")
	private String specName;
	//排序号
	@Column(name="sort")
	private Integer sort;
	
	@Column(name="info")
	private String info;
	
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id=id;
		
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
