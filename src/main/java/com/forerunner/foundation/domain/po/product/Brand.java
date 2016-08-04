package com.forerunner.foundation.domain.po.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;

/**
 * 品牌管理
 * @author Administrator
 *
 */
@Entity
@Table(name = "business_brand")
public class Brand extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7907195557517358733L;
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
	//品牌名称
	@Column(name = "name")
	private String name;
	//排序
	@Column(name = "sequence")
	private Integer sequence;
	//品牌图像
	@Column(name = "images")
	private String images;
	//品牌类别
	@Column(name = "classify_id")
	private Long classifyId;
	//名称首字母
	@Column(name = "first_word")
	private String firstWord;
	//品牌状态 1:正常 2:下架 
	@Column(name = "status")
	private Integer status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Long getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
	public String getFirstWord() {
		return firstWord;
	}
	public void setFirstWord(String firstWord) {
		this.firstWord = firstWord;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
