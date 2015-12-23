/**
 * 工程名: FengDuManLibs
 * 文件名: URLs.java
 * 包名: com.fengdu.android
 * 日期: 2015年10月25日下午12:45:26
 * Copyright (c) 2015, 暴走兄弟 All Rights Reserved.
 * 
 * Mail: leixun33@163.com
 * QQ: 378640336
 *
*/

package com.fengdu.android;
/**
 * 类名: URLs <br/>
 * 功能: 请求地址 接口.  <br/>
 * 日期: 2015年10月25日 下午12:45:26 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class URLs {

	public static final String HOST = "http://www.fengdu100.com/paoniu";
	
	// 缩略图信息地址
	public static final String IMAGE_HOST = "http://7xo27p.com1.z0.glb.clouddn.com/teamimg/native/";
	
//	public static final String MY_HOST = "http://192.168.1.11:8080";
	public static final String MY_HOST = "http://172.26.194.1:8080";
//	public static final String MY_HOST = "http://115.28.224.54:8080";
	
	// 获取版本更新信息
	public static final String URL_GET_CHECK_VERSION =MY_HOST+ "/web/luser/upgradeInfo";
	
	// 获取首页信息
	public static final String URL_GET_WELCOME_INFO = MY_HOST+"/web/luser/welcomeInfo";
	
	// 获取图片信息
	public static final String URL_GET_IMAGE = MY_HOST + "/web/image/getImage";
	
	// 搜索图片
	public static final String URL_SEARCH_IMAGE = MY_HOST + "/web/image/searchImage";
	
	// 发送app信息
	public static final String URL_SEND_APP_INFO = MY_HOST+"/web/luser/updateInfo";
	
	// 收藏取消收藏 图片
	public static final String URL_FAVOR_IMG = MY_HOST+"/web/image/favorImage";
	
	// 是否是收藏图片
	public static final String URL_IS_FAVOR_IMG = MY_HOST+"/web/image/isFavorImage";
	// 获取收藏的图片
	public static final String URL_GET_FAVOR_IMGS = MY_HOST+"/web/image/getFavorImage";
}

