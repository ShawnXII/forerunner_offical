package com.forerunner.core.sso;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.forerunner.core.tool.CommUtil;
import com.forerunner.foundation.domain.po.system.Account;

public class LoginUser implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1449415694359395390L;

	private Long id;
	
	private String nickname;
	
	private String image;
	
	private Boolean enablead;
	
	private Date loginTime;
	
	private String enableadMsg;
	
	public LoginUser(String username,Account account){
		this.image="/media/image/avatar1_small.jpg";
		if(account!=null){
			String name=CommUtil.hidename(username);
			if(StringUtils.isNotBlank(account.getNickname())){
				name=account.getNickname();
			}
			this.nickname=name;
			this.id=account.getId();
			String img=account.getHeadPortrait();
			if(!StringUtils.isBlank(img)){
				this.image=img;
			}
			this.loginTime=new Date();
			this.enablead=false;
		}
	}
	
	public  LoginUser(String username,Account account,boolean enablead){
		this(username,account);
		this.enablead=enablead;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getEnablead() {
		return enablead;
	}

	public void setEnablead(Boolean enablead) {
		this.enablead = enablead;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getEnableadMsg() {
		return enableadMsg;
	}

	public void setEnableadMsg(String enableadMsg) {
		this.enableadMsg = enableadMsg;
	}	
}
