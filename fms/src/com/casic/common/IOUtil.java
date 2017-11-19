package com.casic.common;

import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {

	public static void closeStream(InputStream is){
		try{
			if(is != null){
				is.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void closeStream(OutputStream is){
		try{
			if(is != null){
				is.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
