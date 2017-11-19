package com.casic.fms.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casic.fms.dao.custom.FileLogDaoCustom;


@Component
public class FileLogDaoCustomImp implements FileLogDaoCustom {



	private static Logger logger = LoggerFactory.getLogger(FileLogDaoCustomImp.class);
	private EntityManagerFactory  entityManagerFactory;
	
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public int transferDailyLog(String dateBefore){
		try{
			//EntityManagerFactory emf= Persistence.createEntityManagerFactory("defaultFMSV2PU");
			
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction newTx = em.getTransaction();
			newTx.begin();
			em.createNativeQuery("INSERT INTO FMS_FILELOG_HISTORY([id],[ts],[client_mac],[client_name],[file_fullname],"
					+ "[file_id],[file_length],[identify_code],[ip_address],[operation],[opertime],[secruity_level],"
					+ "[suffix_name],[usbkey],[username],[processmd5],[process_path])"
					+ "SELECT [id],[ts],[client_mac],[client_name],[file_fullname],[file_id],[file_length]"
					+ ",[identify_code],[ip_address],[operation],[opertime],[secruity_level],[suffix_name],"
					+ "[usbkey],[username],[processmd5],[process_path] FROM FMS_FILELOG WHERE ts<='"+dateBefore+"'").executeUpdate();
			em.createNativeQuery("DELETE FMS_FILELOG WHERE ts<='"+dateBefore+"'").executeUpdate();
			newTx.commit();
		}catch(Exception ex){
			logger.error("转移日志出错！", ex);
		}
		return 0;
	}
}
