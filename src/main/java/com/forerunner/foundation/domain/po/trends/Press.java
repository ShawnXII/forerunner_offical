package com.forerunner.foundation.domain.po.trends;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;
/**
 * 新闻动态
 * @author Administrator
 */
@Entity
@Table(name="media_press")
public class Press extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1690227463856965798L;
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
	//作者
	private String author;
	//标题
	private String title;
	//副标题
	private String subtitle;
	//简介
	private String synopsis;
	//新闻类型
	private String type;
	//过期时间
	private Date prescription;
	//权重
	private Integer weight;
	//内容 编辑器编辑
	@Column(length=6000)
	private String info;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getPrescription() {
		return prescription;
	}
	public void setPrescription(Date prescription) {
		this.prescription = prescription;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
