package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 组织结构信息
 * 
 * @author crazylion
 */
@Entity
@Table(name = "fms_orginfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgInfoBean extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7917347893984901081L;
	
	/**
	 * 部门编码，符合XXXX-XXXX-XXXX.....的组织机构模式
	 */
	private String code;
	private String name;
	private String comments;
	private String leadname;

	/**
	 * 上级组织结构名称
	 */
	private String parentId;
	
	/**
	 * 是否虚拟部门
	 */
	private boolean isVirtual;
	
	/**
	 * 是否末级部门呢
	 */
	private boolean isLeaf;

	public OrgInfoBean() {
	}
	
	@Column(length=50,nullable=true)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean isVirtual() {
		return isVirtual;
	}

	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}


	public OrgInfoBean(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=500,nullable=true)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	@Column(length=30,nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getLeadname() {
		return leadname;
	}

	public void setLeadname(String leadname) {
		this.leadname = leadname;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * 列表选项的时候显示内容
	 * @return
	 */
	@Transient
	public String getShowTag(){
		if(code != null && !"".equals(code)){
			return "["+code+"]-"+name;
		}
		return name;
	}
}
