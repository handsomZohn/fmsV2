package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 版本信息
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_versioninfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VersionInfo extends BaseEntity {
	
	/**
	 * 版本号
	 */
	public String code;
	
	/**
	 * 版本说明
	 */
	public String comment;
	
	/**
	 * 下载地址
	 */
	public String url;

	@Column(length=30)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length=300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(length=300)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
