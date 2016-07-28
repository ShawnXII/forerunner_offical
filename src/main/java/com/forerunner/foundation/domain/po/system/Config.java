package com.forerunner.foundation.domain.po.system;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.AbstractEntity;
/**
 * 系统配置表 主要存放网站的可配置信息
 * 如网站关键字 作者 版本号 短信 邮箱配置
 * 所有的配置 都不能删除
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_config")
public class Config extends AbstractEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7740959361217576456L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//配置分类 
	private String classify;
	//配置类型(在字段里面配置)
	private String type;
	//值
	private String value;
	//是否禁用
	private Boolean enablead;
	//配置说明
	private String info;
	//预留字段1
	private String param1;
	//预留字段2
	private String param2;
	//预留字段3
	private String param3;
	//预留字段4
	private String param4;
	//预留字段5
	private String param5;
	//排序
	private Integer sort;
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getEnablead() {
		return enablead;
	}
	public void setEnablead(Boolean enablead) {
		this.enablead = enablead;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {
		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	public String getParam5() {
		return param5;
	}
	public void setParam5(String param5) {
		this.param5 = param5;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id=id;
	}
	
	
}
