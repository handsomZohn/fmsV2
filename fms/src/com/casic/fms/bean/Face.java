package com.casic.fms.bean;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "fms_user_face")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Face extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012113943111379468L;

	@Column(length=50,nullable=false)
	private String faceType;
	
	private Blob faceContent;
	


	public String getFaceType() {
		return faceType;
	}

	public void setFaceType(String faceType) {
		this.faceType = faceType;
	}

	public Blob getFaceContent() {
		return faceContent;
	}

	public void setFaceContent(Blob faceContent) {
		this.faceContent = faceContent;
	}
	
	public Face() {
		
	}

	public Face(String id, String faceType, Blob faceContent) {
		super();
		this.faceType = faceType;
		this.faceContent = faceContent;
		super.setId(id);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
