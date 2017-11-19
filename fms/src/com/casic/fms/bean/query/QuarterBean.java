package com.casic.fms.bean.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 季度信息
 * @author sean
 *
 */
public class QuarterBean  {

	private String year;
	private String quarter;
	private List<String> months;
	
	public QuarterBean(String year, String quarter,  String monthStr) {
		super();
		this.year = year;
		this.quarter = quarter;
		if(monthStr != null){
			String[] strs = monthStr.split(",");
			List<String>  monthlist = new ArrayList<String>();
			for(int i=0; i<strs.length; i++){
				monthlist.add(strs[i]);
			}
			this.months = monthlist;
		}
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public List<String> getMonths() {
		return months;
	}
	public void setMonths(List<String> months) {
		this.months = months;
	}
	
	
	public static List<QuarterBean>	getAllQuarter(){
		List<QuarterBean>  quarters = new ArrayList<QuarterBean>();
		quarters.add(new QuarterBean(null,"1","01,02,03"));
		quarters.add(new QuarterBean(null,"2","04,05,06"));
		quarters.add(new QuarterBean(null,"3","07,08,09"));
		quarters.add(new QuarterBean(null,"4","10,11,12"));
		return quarters;
	}
	
	public static List<String>	getMonths(String quarter){
		if(quarter.equals("1")){
			return new QuarterBean(null,"1","01,02,03").getMonths();
		}else	if(quarter.equals("2")){
			return new QuarterBean(null,"2","04,05,06").getMonths();
		}else 	if(quarter.equals("3")){
			return new QuarterBean(null,"3","07,08,09").getMonths();
		}else{
			return new QuarterBean(null,"4","10,11,12").getMonths();
		}
	}

}
