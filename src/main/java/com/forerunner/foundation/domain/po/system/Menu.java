package com.forerunner.foundation.domain.po.system;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;

@Entity
@Table(name="sys_menu")
public class Menu extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8898681653797580674L;
	
	private String url;
	
	private String target;
	
	private String title;
	
	private String info;
	
	private String type;
	
	private String icon;
	
	private Menu parent;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	
	
}
