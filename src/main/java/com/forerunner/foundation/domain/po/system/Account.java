package com.forerunner.foundation.domain.po.system;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.forerunner.foundation.domain.po.BaseEntity;
/**
 * 用户帐号
 * 存储用户帐号的基本信息
 * 用户的详细信息存放#{com.forerunner.foundation.domain.po.system.PersonalInfo}
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_account")
public class Account extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1268496906426527828L;
	//呢称
	private String nickname;
	//用户名
	private String username;
	//手机号码
	@Column(length=20)
	private String mobile;
	//电子邮箱
	private String email;
	//状态 0:创建  1:
	@Column(columnDefinition = "int default 0",length=2)
	private Integer status;
	
	@OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy="account")
	private PersonalInfo personal;
	//是否锁定
	private Boolean enablead=false;
	//手机号码是否通过验证
	@Column(name="validate_mobile")
	private Boolean validateMobile=false;
	//电子邮箱是否通过验证
	@Column(name="validate_email")
	private Boolean validateEmail=false;
	//身份证
	@Column(name="id_card")
	private String idCard;
	//个人头像
	@Column(name="head_portrait")
	private String headPortrait;
	//支付密码
	@Column(name="pay_password")
	private String payPassword;
	//盐值
	private String salt;
	//登录密码
	private String password;

	public Boolean getEnablead() {
		return enablead;
	}

	public void setEnablead(Boolean enablead) {
		this.enablead = enablead;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getValidateMobile() {
		return validateMobile;
	}

	public void setValidateMobile(Boolean validateMobile) {
		this.validateMobile = validateMobile;
	}

	public Boolean getValidateEmail() {
		return validateEmail;
	}

	public void setValidateEmail(Boolean validateEmail) {
		this.validateEmail = validateEmail;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonalInfo getPersonal() {
		return personal;
	}

	public void setPersonal(PersonalInfo personal) {
		this.personal = personal;
	}
	
}
