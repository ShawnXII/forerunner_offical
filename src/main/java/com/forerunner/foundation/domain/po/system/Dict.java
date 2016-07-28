package com.forerunner.foundation.domain.po.system;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.AbstractEntity;
/**
 * 系统字典
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_dict")
public class Dict extends AbstractEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5164051391891031684L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String value;	// 数据值
	
	private String label;	// 标签名
	
	private String type;	// 类型
	
	private String description;// 描述
	
	private Integer sort;	// 排序
	
	private Dict parent;//父Id

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Dict getParent() {
		return parent;
	}

	public void setParent(Dict parent) {
		this.parent = parent;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
	}	
}
