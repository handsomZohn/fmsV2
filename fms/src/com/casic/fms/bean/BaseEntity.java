package com.casic.fms.bean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.casic.common.SimpleUtils;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author crazylion
 */
//JPA 基类的标识
@MappedSuperclass
public abstract class BaseEntity implements Serializable {


	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
		updateTs();
	}
	protected String id;
	
	/**
	 * 时间戳
	 */
	private String  ts;


	@Id
	@GeneratedValue(generator="systemid")  
	@GenericGenerator(name="systemid", strategy="uuid")  
	@Column(length=50,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(length=30,nullable=false)
	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}
	
	
	
	public void updateTs(){
		setTs(SimpleUtils.getCreateTime());
	}
	/**
	 * 降序排列
	 * @return
	 */
	public static Sort getTsSort(){
		return new Sort(Direction.DESC,"ts");
	}
	/**
	 * 升序排列
	 * @return
	 */
	public static Sort getTsSortASC(){
		return new Sort(Direction.ASC,"ts");
	}
	/**
	 * 按照某个属性降序
	 * @param property
	 * @return
	 */
	public static Sort getSortASC(String property){
		return new Sort(Direction.ASC,property);
	}
	
	/**
	 * 按照某个属性升序
	 * @param property
	 * @return
	 */
	public static Sort getSortDESC(String property){
		return new Sort(Direction.DESC,property);
	}

}
