package com.forerunner.foundation.domain.po.product;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;
/**
 * 产品列表 关联对象都用JSON字段表示
 * @author Administrator
 */
@Entity
@Table(name="business_produce")
public class Product extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6567362577113039980L;
	//产品名称
	@Column(name="product_name")
	private String productName;
	//产品图片(主图)
	private String photo;
	//推荐价格
	private BigDecimal price;
	//分类ID
	@Column(name="classify_id")
	private Long classifyId;
	
	//产品介绍 JSON
	@Column(length=1024)
	private String introduce;
	//产品规格 JSON
	@Column(length=512)
	private String spec;
	//组图 JSON
	@Column(length=512)
	private String images;
	//产品标题
	private String title;
	//SEO 关键字
	private String keywords;
	//SEO 介绍
	private String description;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Long getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", photo=" + photo + ", price=" + price + ", classifyId="
				+ classifyId + ", introduce=" + introduce + ", spec=" + spec + ", images=" + images + ", title=" + title
				+ ", keywords=" + keywords + ", description=" + description + "]";
	}
	
	
}
