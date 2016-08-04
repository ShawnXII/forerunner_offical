package com.forerunner.core.repository.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.foundation.domain.po.product.Brand;
import com.forerunner.foundation.domain.po.product.Classify;
import com.forerunner.foundation.domain.po.product.Product;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ProductRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	public Long searchCount(Product product) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select count(*) from business_produce as product left outer join business_brand as brand on (product.brand_id = brand.id) ");
		sb.append("left outer join business_classify as classify on (product.classify_id = classify.id)");
		sb.append(" where product.delete_status=? ");
		List<Object> list = Lists.newArrayList();
		list.add(0);
		if (product != null) {
			if (product.getId() != null && product.getId() > 0) {
				sb.append("product.id=?");
				list.add(product.getId());
			} else {
				if (product.getStartDate() != null && product.getEndDate() != null
						&& product.getEndDate().getTime() < product.getStartDate().getTime()) {
					product.setEndDate(null);
				}
				if (StringUtils.isNotBlank(product.getProductName())) {
					sb.append(" and (product_name like %?% or subhead like %?% or introduce like %?%) ");
					list.add(product.getProductName());
					list.add(product.getProductName());
					list.add(product.getProductName());
				}
				if (product.getBrandId() != null && product.getBrandId() > 0) {
					sb.append(" and brand_id=? ");
					list.add(product.getBrandId());
				}
				if (product.getClassifyId() != null && product.getClassifyId() > 0) {
					sb.append(" and classify_id=? ");
					list.add(product.getClassifyId());
				}
				if (product.getMinPrice() != null && product.getMinPrice().compareTo(BigDecimal.ZERO) == 1) {
					sb.append(" and price >= ?");
					list.add(product.getMinPrice());
				}
				if (product.getMaxPrice() != null && product.getMaxPrice().compareTo(BigDecimal.ZERO) == 1
						&& product.getMaxPrice().compareTo(product.getMinPrice()) == 1) {
					sb.append(" and price <=?");
					list.add(product.getMaxPrice());
				}
				if (product.getStartDate() != null) {
					sb.append(" and putaway >= ? ");
					list.add(product.getStartDate());
				}
				if (product.getEndDate() != null) {
					sb.append(" and putaway<=?");
					list.add(product.getEndDate());
				}
			}
		}
		String sql = sb.toString();
		Query query = em.createNativeQuery(sql);
		int i = 1;
		for (Object obj : list) {
			if (obj instanceof Date) {
				Calendar cal = Calendar.getInstance();
				cal.setTime((Date) obj);
				query.setParameter(i, cal);
			} else {
				query.setParameter(i, obj);
			}
			i++;
		}
		return CommUtil.null2Long(query.getSingleResult());
	}

	public List<Product> searchProduct(Product product, Integer pageIndex, Integer pageSize) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		// <产品名称,产品副标题,产品描述> 产品品牌<等于> 产品价格(区间) 创建时间(大于等于 小于等于) 状态 hashCode 图片搜索
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select product.id,product.product_name,product.subhead,product.photo,product.price,product.store_price,product.volume,product.weight,product.putaway,product.soldout ");
		sb.append(
				",product.classify_id,product.introduce,product.spec,product.images,product.keywords,product.description,product.status,product.audit_logging,product.brand_id ");
		sb.append(
				",classify.url,classify.target,classify.info,classify.title,classify.type,classify.icon,classify.image,classify.id as cid");
		sb.append(",brand.id as bid,brand.name,brand.sequence,brand.images as brandImages");
		sb.append(
				" from business_produce as product left outer join business_brand as brand on (product.brand_id = brand.id) ");
		sb.append(" left outer join business_classify as classify on (product.classify_id = classify.id) ");
		sb.append(" where product.delete_status=? ");
		List<Object> list = Lists.newArrayList();
		list.add(0);
		if (product != null) {
			if (product.getId() != null && product.getId() > 0) {
				sb.append("product.id=?");
				list.add(product.getId());
			} else {
				if (product.getStartDate() != null && product.getEndDate() != null
						&& product.getEndDate().getTime() < product.getStartDate().getTime()) {
					product.setEndDate(null);
				}
				if (StringUtils.isNotBlank(product.getProductName())) {
					sb.append(" and (product_name like %?% or subhead like %?% or introduce like %?%) ");
					list.add(product.getProductName());
					list.add(product.getProductName());
					list.add(product.getProductName());
				}
				if (product.getBrandId() != null && product.getBrandId() > 0) {
					sb.append(" and brand_id=? ");
					list.add(product.getBrandId());
				}
				if (product.getClassifyId() != null && product.getClassifyId() > 0) {
					sb.append(" and classify_id=? ");
					list.add(product.getClassifyId());
				}
				if (product.getMinPrice() != null && product.getMinPrice().compareTo(BigDecimal.ZERO) == 1) {
					sb.append(" and price >= ?");
					list.add(product.getMinPrice());
				}
				if (product.getMaxPrice() != null && product.getMaxPrice().compareTo(BigDecimal.ZERO) == 1
						&& product.getMaxPrice().compareTo(product.getMinPrice()) == 1) {
					sb.append(" and price <=?");
					list.add(product.getMaxPrice());
				}
				if (product.getStartDate() != null) {
					sb.append(" and putaway >= ? ");
					list.add(product.getStartDate());
				}
				if (product.getEndDate() != null) {
					sb.append(" and putaway<=? ");
					list.add(product.getEndDate());
				}
			}
		}
		sb.append(" order by id ");
		int total = pageIndex * pageSize;
		sb.append(" limit ?,?");
		list.add(total);
		list.add(pageSize);
		String sql = sb.toString();
		Query query = em.createNativeQuery(sql);
		int i = 1;
		for (Object obj : list) {
			if (obj instanceof Date) {
				Calendar cal = Calendar.getInstance();
				cal.setTime((Date) obj);
				query.setParameter(i, cal);
			} else {
				query.setParameter(i, obj);
			}
			i++;
		}
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		List<Product> resultList = Lists.newArrayList();
		for (Object obj : result) {
			Product entity = new Product();
			Object[] objs = (Object[]) obj;
			Long id = CommUtil.null2Long(objs[0]);
			String productName = CommUtil.null2String(objs[1]);
			String subhead = CommUtil.null2String(objs[2]);
			String photo = CommUtil.null2String(objs[3]);
			BigDecimal price = new BigDecimal(CommUtil.null2String(objs[4]));
			price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal storePrice = new BigDecimal(CommUtil.null2String(objs[5]));
			storePrice = storePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal volume = new BigDecimal(CommUtil.null2String(objs[6]));
			volume = volume.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal weight = new BigDecimal(CommUtil.null2String(objs[7]));
			weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			String putaway = CommUtil.null2String(objs[8]);// 上架时间
			String soldout = CommUtil.null2String(objs[9]);// 下架时间
			try {
				Date putWayDate=df.parse(putaway);
				Date soldoutDate=df.parse(soldout);
				entity.setPutaway(putWayDate);
				entity.setSoldout(soldoutDate);
			} catch (ParseException e) {
				
			}
			Long classifyId = CommUtil.null2Long(objs[10]);
			String introduce = CommUtil.null2String(objs[11]);// 产品描述
			String spec = CommUtil.null2String(objs[12]);// 产品规格属性
			String images = CommUtil.null2String(objs[13]);// 组图
			String keywords = CommUtil.null2String(objs[14]);
			String description = CommUtil.null2String(objs[15]);
			Integer status = CommUtil.null2Int(objs[16]);
			String audit_logging = CommUtil.null2String(objs[17]);
			Long brandId = CommUtil.null2Long(objs[18]);
			entity.setId(id);
			entity.setProductName(productName);
			entity.setSubhead(subhead);
			entity.setPhoto(photo);
			entity.setPrice(price);
			entity.setStorePrice(storePrice);
			entity.setVolume(volume);
			entity.setWeight(weight);
			entity.setClassifyId(classifyId);
			entity.setKeywords(keywords);
			entity.setDescription(description);
			entity.setStatus(status);
			entity.setAuditLogging(audit_logging);
			entity.setBrandId(brandId);
			entity.setIntroduce(introduce);
			entity.setSpecList(this.analysisSpec(spec));
			entity.setPhotoList(this.analysisImages(images));
			Long cid = CommUtil.null2Long(objs[26]);
			if (cid != null && cid > 0) {
				// 分类
				String url = CommUtil.null2String(objs[19]);
				String target = CommUtil.null2String(objs[20]);
				String info = CommUtil.null2String(objs[21]);
				String title = CommUtil.null2String(objs[22]);
				String type = CommUtil.null2String(objs[23]);
				String icon = CommUtil.null2String(objs[24]);
				String image = CommUtil.null2String(objs[25]);
				Classify classify = new Classify();
				classify.setId(cid);
				classify.setUrl(url);
				classify.setTarget(target);
				classify.setInfo(info);
				classify.setTitle(title);
				classify.setType(type);
				classify.setIcon(icon);
				classify.setImage(image);
				entity.setClassify(classify);
			}
			// 品牌
			Long bid = CommUtil.null2Long(objs[27]);
			if (bid != null && bid > 0) {
				String name = CommUtil.null2String(objs[28]);
				Integer sequence = CommUtil.null2Int(objs[29]);
				String brandImages = CommUtil.null2String(objs[30]);
				Brand brand = new Brand();
				brand.setId(bid);
				brand.setName(name);
				brand.setImages(brandImages);
				brand.setSequence(sequence);
				entity.setBrand(brand);
			}
			resultList.add(entity);
		}
		return resultList;
	}

	/**
	 * 解析规格 格式 [{name:'xx','value':'xx',sequence:'xx'}]
	 */
	@SuppressWarnings("rawtypes")
	private List<Map<String, String>> analysisSpec(String spec) {
		List<Map<String, String>> result = Lists.newArrayList();
		if (StringUtils.isBlank(spec)) {
			return result;
		}
		try {
			List<Map> list = JSONObject.parseArray(spec, Map.class);
			if (list != null && list.size() > 0) {
				for (Map map : list) {
					Map<String, String> m = Maps.newHashMap();
					String name = CommUtil.null2String(map.get("name"));
					String value = CommUtil.null2String(map.get("value"));
					Integer sequence = CommUtil.null2Int(map.get("sequence"));
					m.put("name", name);
					m.put("value", value);
					m.put("sequence", CommUtil.null2String(sequence));
					result.add(m);
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 解析组图
	 * 格式:[{'path':'xx','weight':'xx','height':'xx','info':'xx','sequence':'xx'}
	 * ]
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map<String, Object>> analysisImages(String str) {
		List<Map<String, Object>> result = Lists.newArrayList();
		if (StringUtils.isBlank(str)) {
			return result;
		}
		try {
			List<Map> list = JSONObject.parseArray(str, Map.class);
			if (list != null && list.size() > 0) {
				for (Map map : list) {
					Map<String, Object> m = Maps.newHashMap();
					String path = CommUtil.null2String(map.get("path"));
					String info = CommUtil.null2String(map.get("info"));
					Integer sequence = CommUtil.null2Int(map.get("sequence"));
					BigDecimal weight = new BigDecimal(CommUtil.null2String(map.get("weight")));
					BigDecimal height = new BigDecimal(CommUtil.null2String(map.get("height")));
					weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
					height = height.setScale(2, BigDecimal.ROUND_HALF_UP);
					m.put("weight", weight);
					m.put("height", height);
					m.put("path", path);
					m.put("info", info);
					m.put("sequence", CommUtil.null2String(sequence));
					result.add(m);
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

}
