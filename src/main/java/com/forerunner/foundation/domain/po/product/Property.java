package com.forerunner.foundation.domain.po.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.AbstractEntity;
/**
 * 属性
 * @author Administrator
 *
 */
@Entity
@Table(name="business_property")
public class Property extends AbstractEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5124634695604576318L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="property_name")
	private String propertyName;
	
	@Column(name="sort")
	private Integer sort;
	//属性说明
	@Column(name="info")
	private String info;
	//属性类型 1:文字 2:图片 3:icon
	@Column(name="type")
	private String type;
	
	@Column(name="icon")
	private String icon;
	
	@Column(name="image")
	private String image;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
	
}
