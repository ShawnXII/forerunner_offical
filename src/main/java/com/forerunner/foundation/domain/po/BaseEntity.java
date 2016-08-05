package com.forerunner.foundation.domain.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 该类默认创建主键 默认自动生成主键 仅适用MySql 主键需要设置为自增<p> 
 * 创建时间 修改时间 备注 是否删除 字段<p>
 * 如不需要这些字段 请直接继承 #{com.forerunner.foundation.domain.po.AbstractEntity}
 * @author Administrator
 *
 * @param <ID>
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends java.io.Serializable> extends AbstractEntity<ID> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1702982221535657353L;

	
	//创建时间 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date createTime=new Date();
	
	//最后一次修改时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time",insertable=false)
	private Date updateTime=new Date();
	//创建人
	@Column(name="create_by")
	private String createBy;
	//修改人
	@Column(name="update_by")
	private String updateBy;
	//备注
	@Column(name="remarks")
	private String remarks;
	//是否删除
	@Column(name="delete_status")
	private Boolean deleteStatus=false;
	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
}
