/**
 * 工程名: FengDuLibs
 * 文件名: CommonConfig.java
 * 包名: com.fengdu.config
 * 日期: 2015年10月26日下午8:59:18
 * Copyright (c) 2015, 暴走兄弟 All Rights Reserved.
 * 
 * Mail: leixun33@163.com
 * QQ: 378640336
 *
*/

package com.fengdu.config;

import com.fengdu.BaseApplication;
import com.fengdu.android.AppConfig;

/**
 * 类名: CommonConfig <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月26日 下午8:59:18 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class CommonConfig {
	/** sdcard根目录 */
	public static final String sdcardRootPath = android.os.Environment
			.getExternalStorageDirectory().getPath();
	/** 机身根目录 */
	public static final String dataRootPath = android.os.Environment
			.getDataDirectory() + "/data/" + BaseApplication.globalContext.getPackageName();
	public static String getDownloadImgPath(){
		if(ExistSDCard()){
			return CommonConfig.sdcardRootPath + AppConfig.officialRoot + AppConfig.imgPath;
		}else{
			return CommonConfig.dataRootPath + AppConfig.officialRoot + AppConfig.imgPath;
		}
	}
	public static String getUploadHtmlPath(){
		if(ExistSDCard()){
			return CommonConfig.sdcardRootPath + AppConfig.officialRoot + AppConfig.htmlPath;
		}else{
			return CommonConfig.dataRootPath + AppConfig.officialRoot + AppConfig.htmlPath;
		}
	}
	
	public static boolean ExistSDCard() {  
		if (android.os.Environment.getExternalStorageState().equals(  
				android.os.Environment.MEDIA_MOUNTED)) {  
			return true;  
		} else {
			return false;    
		} 
	} 
}

