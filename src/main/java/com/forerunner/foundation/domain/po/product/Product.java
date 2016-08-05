package com.forerunner.foundation.domain.po.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.forerunner.foundation.domain.po.BaseEntity;
import com.google.common.collect.Lists;

/**
 * 产品列表
 * 
 * @author Administrator
 */
@Entity
@Table(name = "business_produce")
public class Product extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6567362577113039980L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	// 产品名称
	@Column(name = "product_name")
	private String productName;
	// 副标题()
	@Column(name = "subhead")
	private String subhead;
	// 产品图片(主图)
	@Column(name = "photo")
	private String photo;
	//主图的hashCode值
	@Column(name = "hash_code_photo")
	private String hashCodePhoto;
	// 推荐价格
	@Column(precision = 12, scale = 2,name="price")
	private BigDecimal price;
	// 市场价
	@Column(precision = 12, scale = 2,name="store_price")
	private BigDecimal storePrice;

	// 体积 平方
	@Column(precision = 12, scale = 2,name="volume")
	private BigDecimal volume;

	// 重量 kg
	@Column(precision = 12, scale = 2,name="weight")
	private BigDecimal weight;

	// 上架时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="putaway")
	private Date putaway;

	// 下架时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="soldout")
	private Date soldout;

	// 分类ID
	@Column(name = "classify_id")
	private Long classifyId;

	// 产品介绍
	@Lob
	@Column(columnDefinition = "LongText",name="introduce")
	private String introduce;

	// 产品规格 JSON
	@Lob
	@Column(columnDefinition = "LongText",name="spec")
	private String spec;

	// 组图 JSON
	@Lob
	@Column(columnDefinition = "LongText",name="images")
	private String images;

	// SEO 关键字
	@Column(name="keywords")
	private String keywords;

	// SEO 介绍
	@Column(name="description")
	private String description;

	// 产品状态 [0:在售商品 1:等待审核 2:下架商品 3:审核失败 4:违规下架 ]
	@Column(name="status")
	private Integer status;

	// 审核记录
	@Column(name = "audit_logging")
	private String auditLogging;

	// 产品品牌
	@Column(name = "brand_id")
	private Long brandId;
	/**
	 * 商品状态翻译
	 */
	@Transient
	private String statusStr;
	/**
	 * 封装产品规格
	 */
	@Transient
	private List<Map<String, String>> specList = Lists.newArrayList();
	/**
	 * 分类名称
	 */
	@Transient
	private Classify classify;
	/**
	 * 商品品牌
	 */
	@Transient
	private Brand brand;
	/**
	 * 组图
	 */
	@Transient
	private List<Map<String, Object>> photoList = Lists.newArrayList();
	//查询价格参数 最大价格
	@Transient
	private BigDecimal maxPrice;
	//查询价格参数 最小价格
	@Transient
	private BigDecimal minPrice;
	//查询时间参数 开始时间
	@Transient
	private Date startDate;
	//结束时间
	@Transient
	private Date endDate;
	
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
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

	public BigDecimal getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(BigDecimal storePrice) {
		this.storePrice = storePrice;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Date getPutaway() {
		return putaway;
	}

	public void setPutaway(Date putaway) {
		this.putaway = putaway;
	}

	public Date getSoldout() {
		return soldout;
	}

	public void setSoldout(Date soldout) {
		this.soldout = soldout;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditLogging() {
		return auditLogging;
	}

	public void setAuditLogging(String auditLogging) {
		this.auditLogging = auditLogging;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public List<Map<String, String>> getSpecList() {
		return specList;
	}

	public void setSpecList(List<Map<String, String>> specList) {
		this.specList = specList;
	}

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Map<String, Object>> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Map<String, Object>> photoList) {
		this.photoList = photoList;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getHashCodePhoto() {
		return hashCodePhoto;
	}

	public void setHashCodePhoto(String hashCodePhoto) {
		this.hashCodePhoto = hashCodePhoto;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", subhead=" + subhead + ", photo=" + photo + ", hashCodePhoto="
				+ hashCodePhoto + ", price=" + price + ", storePrice=" + storePrice + ", volume=" + volume + ", weight="
				+ weight + ", putaway=" + putaway + ", soldout=" + soldout + ", classifyId=" + classifyId
				+ ", introduce=" + introduce + ", spec=" + spec + ", images=" + images + ", keywords=" + keywords
				+ ", description=" + description + ", status=" + status + ", auditLogging=" + auditLogging
				+ ", brandId=" + brandId + ", statusStr=" + statusStr + ", specList=" + specList + ", classify="
				+ classify + ", brand=" + brand + ", photoList=" + photoList + ", maxPrice=" + maxPrice + ", minPrice="
				+ minPrice + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
	}
	
	

}
