package com.fengdu.utils;
/**
 * 工程名: SpringMVC3Demo
 * 文件名: StringCompress.java
 * 包名: 
 * 日期: 2015年11月21日上午8:25:01
 * Copyright (c) 2015, 暴走兄弟 All Rights Reserved.
 * 
 * Mail: leixun33@163.com
 * QQ: 378640336
 *
*/

/**
 * 类名: StringCompress <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月21日 上午8:25:01 <br/>
 *
 * @author   leixun
 * @version  	 
 */
import java.io.ByteArrayInputStream; 
import java.io.ByteArrayOutputStream; 
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry; 
import java.util.zip.ZipInputStream; 
import java.util.zip.ZipOutputStream; 
   
public class StringCompress { 
	public static String compress(String str){
		  if (str == null || str.length() == 0) {
			  return str;
		  }
		  try{
			  ByteArrayOutputStream out = new ByteArrayOutputStream();
			  Deflater deflater = new Deflater(2);
			  DeflaterOutputStream gzip = new DeflaterOutputStream(out,deflater);
			  gzip.write(str.getBytes());
			  gzip.close();
			  return out.toString("ISO-8859-1");
		  }catch(IOException e){
			  return "压缩异常"+e.toString();
		  }
	  }

	  // 解压缩
	  public static String uncompress(String str){
	    if (str == null || str.length() == 0) {
	      return str;
	    }
	    try{
	    	 ByteArrayOutputStream out = new ByteArrayOutputStream();
	 	    ByteArrayInputStream in = new ByteArrayInputStream(str
	 	        .getBytes("ISO-8859-1"));
	 	   InflaterInputStream gunzip = new InflaterInputStream(in);
	 	    byte[] buffer = new byte[256];
	 	    int n;
	 	    while ((n = gunzip.read(buffer)) >= 0) {
	 	      out.write(buffer, 0, n);
	 	    }
	 	    // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
	 	    return out.toString();
	    }catch(IOException e){
	    	return "解压异常："+e.toString();
	    }
	   
	  }
}

