package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.FileClassify;

public interface FileClassifyDao extends JpaRepository<FileClassify, String> {

	FileClassify findByName(String name);


}
