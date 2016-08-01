package com.forerunner.foundation.domain.po.system;

import com.forerunner.foundation.domain.po.BaseEntity;

/**
 * 黑名单
 * @author wx
 *
 */
public class Blacklist extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4467034881534256114L;
	
	private String ip;
	
	private String info;
	
	private String type;
	
	private Integer closeDay;
	
	private Boolean enablead;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public Integer getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(Integer closeDay) {
		this.closeDay = closeDay;
	}

	public Boolean getEnablead() {
		return enablead;
	}

	public void setEnablead(Boolean enablead) {
		this.enablead = enablead;
	}
	
	
}
