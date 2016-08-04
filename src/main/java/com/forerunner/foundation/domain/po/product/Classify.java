package com.forerunner.foundation.domain.po.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.forerunner.foundation.domain.po.BaseEntity;

/**
 * 产品分类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "business_classify")
public class Classify extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912857254908437320L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "url")
	private String url;

	@Column(name = "target")
	private String target;

	@Column(name = "info")
	private String info;

	@Column(name = "title")
	private String title;

	@Column(name = "type")
	private String type;

	@Column(name = "icon")
	private String icon;

	@Column(name = "image")
	private String image;

	@Column(name = "parent_id")
	private Long parentId;
	// SEO 关键字
	@Column(name = "keywords")
	private String keywords;

	// SEO 介绍
	@Column(name = "description")
	private String description;
	@Transient
	private Classify parent;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Classify [id=" + id + ", url=" + url + ", target=" + target + ", info=" + info + ", title=" + title
				+ ", type=" + type + ", icon=" + icon + ", image=" + image + ", parentId=" + parentId + ", keywords="
				+ keywords + ", description=" + description + ", parent=" + parent + "]";
	}

	
}
