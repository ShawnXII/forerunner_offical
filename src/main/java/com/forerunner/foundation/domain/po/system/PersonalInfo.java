package com.forerunner.foundation.domain.po.system;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.forerunner.foundation.domain.po.BaseEntity;
/**
 * 用户的基本信息<p>
 * 是用户帐号信息#{com.forerunner.foundation.domain.po.system.Account}的补充
 * @author Administrator
 *
 */
@Entity
@Table(name="sys_personal_info")
public class PersonalInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4865038251438915190L;
	//真实姓名
	private String truename;
	//地址
	private String address;
	//籍贯
	private String origo;
	//性别  0:女 , 1:男   ,2:保密
	@Column(columnDefinition = "int default 2",length=1)
	private Integer sex;
	//年龄 
	@Column(columnDefinition = "int default 0",length=4)
	private Integer age;
	//生日(阳历)
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	//最高学历
	private String education;
	//专业
	private String major;
	//婚姻状况 0:已婚 1:未婚 2:离异 3:恋爱中 4:保密
	@Column(columnDefinition = "int default 4",length=1)
	private Integer marriage;
	//紧急联系人
	@Column(name="emergency_contact")
	private String emergencyContact;
	//紧急联系人电话
	@Column(name="urgent_phone")
	private String urgentPhone;
	//工作职位
	private String job;
	//薪资水平
	private String salary;
	/*
	 * 用户帐号
	 */
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "accountid",referencedColumnName="id", unique = true)
	private Account account;
    
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrigo() {
		return origo;
	}
	public void setOrigo(String origo) {
		this.origo = origo;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getMarriage() {
		return marriage;
	}
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getUrgentPhone() {
		return urgentPhone;
	}
	public void setUrgentPhone(String urgentPhone) {
		this.urgentPhone = urgentPhone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
}
