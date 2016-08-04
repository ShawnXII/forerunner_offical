package com.forerunner.foundation.domain.po.trends;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;
/**
 * 帮助列表
 * @author Administrator
 *
 */
@Entity
@Table(name="media_help")
public class Help extends BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3925928800614130164L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String title;
	
	private Help parent;
	
	private String url;
	
	private String target;
	
	private String info;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Help getParent() {
		return parent;
	}

	public void setParent(Help parent) {
		this.parent = parent;
	}

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
	
	
}
