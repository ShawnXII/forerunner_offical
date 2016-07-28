package com.forerunner.foundation.domain.po.product;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;
/**
 * 产品分类
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_classify")
public class Classify extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 912857254908437320L;
	
	
	private String url;
	
	private String target;
	
	private String info;
	
	private String title;
	
	private String type;
	
	private String icon;
	
	private String image;
	
	private Classify parent;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Classify getParent() {
		return parent;
	}

	public void setParent(Classify parent) {
		this.parent = parent;
	}
	
}
