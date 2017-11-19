package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 文件分类指定，指定的文件分类需要设置最低的保密级别
 * 
 * @author crazylion
 */
@Entity
@Table(name = "fms_file_classify")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileClassify extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8746894923145447276L;
	//分类编码
	private String code;
	//分类名称
	private String name;
	//最低密级限制
	private String miniSecurity;
	//说明
	private String comments;
	
	public FileClassify() {
		super();
	}
	@Column(length=50,nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=50,nullable=false)
	public String getMiniSecurity() {
		return miniSecurity;
	}
	public void setMiniSecurity(String miniSecurity) {
		this.miniSecurity = miniSecurity;
	}
	@Column(length=500)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
