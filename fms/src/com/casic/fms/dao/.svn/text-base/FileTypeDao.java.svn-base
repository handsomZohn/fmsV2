package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.FileType;

/**
 * 文档类型DAO定义
 *
 * @author crazylion
 */

public interface FileTypeDao extends JpaRepository<FileType, String> {

	/**
	 * 根据文件后缀名，获取文件类型列表
	 * @param suffixName
	 * @return
	 */
	List<FileType>	findAllBySuffixName(String suffixName);
	
	/**
	 * 根据文件后缀名，获取文件类型
	 * @param suffixName
	 * @return
	 */
	FileType		findOneBySuffixName(String suffixName);

	
	/**
	 * 根据文件识别码和文件后缀名称，获取文件类型的信息
	 * @param code
	 * @param suffixName
	 * @return
	 */
	FileType		findOneByCodeAndSuffixName(String code,String suffixName);

	FileType findOneByCode(String code);
}
