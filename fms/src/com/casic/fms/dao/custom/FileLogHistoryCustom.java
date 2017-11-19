package com.casic.fms.dao.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.FileLogHistory;
import com.casic.fms.bean.query.FileLogQueryBean;



/**
 * FileLogHistory自定义查询描述
 * @author sean
 *
 */
public class FileLogHistoryCustom{
	public static Specification<FileLogHistory>	customQuery(final FileLogQueryBean  queryBean){
		return new Specification<FileLogHistory>() {
			public Predicate toPredicate(Root<FileLogHistory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>(); 
				if(queryBean == null)	return null;
				if(queryBean.getIdentifyCode() != null && !"".equals(queryBean.getIdentifyCode())){
					list.add(cb.equal(root.get("identifyCode").as(String.class), queryBean.getIdentifyCode()));
				}
				if(queryBean.getClientMac() != null && !"".equals(queryBean.getClientMac())){
					list.add(cb.equal(root.get("clientMac").as(String.class), queryBean.getClientMac()));
				}
				if(queryBean.getFileFullname() != null && !"".equals(queryBean.getFileFullname())){
					list.add(cb.like(root.get("fileFullname").as(String.class), "%"+queryBean.getFileFullname()+"%"));
				}
				if(queryBean.getFileId() != null && !"".equals(queryBean.getFileId())){
					list.add(cb.equal(root.get("fileId").as(String.class), queryBean.getFileId()));
				}
				if(queryBean.getOperation() != null && !"".equals(queryBean.getOperation())){
					list.add(cb.equal(root.get("operation").as(String.class), queryBean.getOperation()));
				}
				if(queryBean.getProcessMD5() != null && !"".equals(queryBean.getProcessMD5())){
					list.add(cb.equal(root.get("processMD5").as(String.class), queryBean.getProcessMD5()));
				}
				if(queryBean.getSecruityLevel() != null && !"".equals(queryBean.getSecruityLevel())){
					list.add(cb.equal(root.get("secruityLevel").as(String.class), queryBean.getSecruityLevel()));
				}
				if(queryBean.getUsbkey() != null && !"".equals(queryBean.getUsbkey())){
					list.add(cb.like(root.get("usbkey").as(String.class), "%"+queryBean.getUsbkey()+"%"));
				}
				if(queryBean.getOpertime() != null && !"".equals(queryBean.getOpertime())){
					list.add(cb.like(root.get("opertime").as(String.class), queryBean.getOpertime()+"%"));
				}
				if(queryBean.getOperationStarttime() != null && !"".equals(queryBean.getOperationStarttime())){
					list.add(cb.greaterThanOrEqualTo(root.get("opertime").as(String.class), queryBean.getOperationStarttime()));
				}
				if(queryBean.getOperationEndtime() != null && !"".equals(queryBean.getOperationEndtime())){
					list.add(cb.lessThanOrEqualTo(root.get("opertime").as(String.class), queryBean.getOperationEndtime()));
				}
				if(queryBean.getUsbkeys() != null && queryBean.getUsbkeys().size()>0){
					list.add(root.get("usbkey").in(queryBean.getUsbkeys()));
				}
				
				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));
			}
		};
	}
}
