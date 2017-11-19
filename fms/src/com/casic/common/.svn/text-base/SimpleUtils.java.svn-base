package com.casic.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.util.SystemOutLogger;

public class SimpleUtils {
	
	/**
	 * 比较两个日前之间的天数 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetweenToday(String smdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        long todaytime = cal.getTimeInMillis();         

        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        long between_days=(todaytime-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    } 
	/**
	 * 比较两个日前之间的天数 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  

	/**
	 * 格式化小数位
	 * @param dl
	 * @param digit
	 * @return
	 */
	public static double formatDigit(double dl, int digit){
		DecimalFormat df1 = new DecimalFormat("#0.0");
		return Double.parseDouble(df1.format(dl));
	}

	/**
	 * 比较第二个时间戳是否大于第一个时间戳
	 * @param firstTs
	 * @param secTs
	 * @return
	 */
	public static boolean compareTwoTs(String firstTs,String secTs){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatter_short = new SimpleDateFormat("yyyy-MM-dd");
		try{
			
			Date ts1 = firstTs.length()<=10?formatter_short.parse(firstTs):formatter.parse(firstTs);
			Date ts2 = secTs.length()<=10?formatter_short.parse(secTs):formatter.parse(secTs);
			return ts2.compareTo(ts1)>0;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 取得所属时区时间戳
	 * @param signHour	exam:"-8" 为北京的时间
	 * @return
	 */
	public static Timestamp getLocalTs(String signHour){
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT" + signHour);
		TimeZone.setDefault(tz);
		Date today = new Date();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = Timestamp.valueOf(formatter.format(today));
		
		return ts;
	}
	/**
	 * 按照传入的日期格式，取得当前日期所对应的字符串
	 * @param format	exam:"yyyy-MM-dd"
	 * @return
	 */
	public static String getTodayStr(String format){
		Format formatter = new SimpleDateFormat(format);
		Date date = new Date();
		String dateStr = formatter.format(date);
		
		return dateStr;
	}
	/**
	 * 将字符串转化为日期
	 * @param format	exam:"yyyy-MM-dd"	
	 * @param str	exam:"2010-12-15" (与format对应)
	 * @return
	 */
	public static Date str2Date(String format, String str){
		Format formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = ((DateFormat) formatter).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(formatter.format(date));
		return date;
	}
	/**
	 * 判断字符串是否是email格式
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	/**
	 * 将float类型的数据大小，转换为常见的大小，如：MB（即兆）。
	 * @param size  float类型
	 * @return 返回的字符串是以B、KB、MB、GB、TB为单位的大小表示
	 */
	public static String formatSize(float size){
		long kb = 1024L;
		long mb = kb*1024L;
		long gb = mb*1024L;
		long tb = gb*1024L;
		
		String result = "0 B";
		//不去转换负数，遇到时将提示出错
		if(size < 0){
			return "size error";
		}
		//对于超出float表示范围，则提示数据太大
		if(size > Float.MAX_VALUE){
			return "size too large";
		}
		//对数据大小从B到TB分区间处理，优先以最小的单位表示
		if(size < kb){
			result = String.format("%d B", (int)size);
		}else if(size < mb){
			result = String.format("%.2f KB", size/kb);
		}else if(size < gb){
			result = String.format("%.2f MB", size/mb);
		}else if(size < tb){
			result = String.format("%.2f GB", size/gb);
		}else{
			//对TB级的数据大小，精确到小数点后第三位（即GB）
			result = String.format("%.3f TB", size/tb);
		}
		return result;
	}
	
	/**
	 * 当使用ajax get方式提交后，再把文本回显为原字符串
	 * @param str	由数字编码组成的字符串，且以空格分隔
	 * @return	以unicode编码的字符串
	 */
	public static String unicodeToString(String str){
		if(str==null || "".equals(str)) return "";
		
		StringBuffer buf = new StringBuffer("");
		String[] ss = str.split(" ");
		for (int i = 0; i < ss.length; i++) {
			char c = (char)Integer.parseInt(ss[i].trim());
			buf.append(c);
		}
		String newStr = buf.toString();
		return newStr;
	}
	
	
	/**
	 * 取得所属时区时间戳
	 * @param signHour	exam:"-8" 为北京的时间
	 * @return
	 */
	public static Timestamp getLocalTs(){
		Date today = new Date();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp ts = Timestamp.valueOf(formatter.format(today));
		return ts;
	}
	/**
	 * 按照传入的日期格式，取得当前日期所对应的字符串
	 * @param format	exam:"yyyy-MM-dd"
	 * @return
	 */
	public static String getCreateTime(){
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateStr = formatter.format(date);
		return dateStr;
	}	
	
	public static SimpleDateFormat getSimpleDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 根据文件名获取文件的后缀
	 * @param fileName
	 * @return fileName's suffix
	 */
	public static String getFileSuffix(String fileName){
		String suffix = "";
		if(fileName == null){
			return suffix;
		}
		//以"." 作为识别后缀的标识
		int suffixIndex = fileName.lastIndexOf(".");
		if(suffixIndex == -1){
			return suffix;
		}
		//取"." 后面的字符串作为后缀
		suffix = fileName.substring(suffixIndex + 1);
		return suffix;
	}	
	
	public static void main(String[] args) {
		//System.out.println(SimpleUtils.getLocalTs("-8"));
		//System.out.println(SimpleUtils.getTodayStr("yy-mm-dd"));
//		boolean result = SimpleUtils.compareTwoTs("2013-09-09 00:00:00","2013-10-10 00:00:00");
		//System.out.println(result);
//		String result = String.format("%.2f KB", (float)1666/1024);
//		System.out.println(result);
//		System.out.println(SimpleUtils.formatSize((float)1024*1024*1024*10247*1024));
//		System.out.println(SimpleUtils.formatSize((float)-5990));		
//		for(int i=0; i<10000; i++){
//			System.out.println("Merry Christmas");
//		}
		try{
			System.out.println(SimpleUtils.daysBetweenToday("2015-12-30"));
			System.out.println(SimpleUtils.daysBetweenToday("2016-1-25"));
			System.out.println(SimpleUtils.daysBetweenToday("2016-1-29"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
