/**
 * 工程名: FengDuLibs
 * 文件名: WelcomeBean.java
 * 包名: com.fengdu.bean
 * 日期: 2015年12月20日上午9:06:15
 * Copyright (c) 2015, 暴走兄弟 All Rights Reserved.
 * 
 * Mail: leixun33@163.com
 * QQ: 378640336
 *
*/

package com.fengdu.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 类名: WelcomeBean <br/>
 * 功能: 欢迎页逻辑控制. <br/>
 * 日期: 2015年12月20日 上午9:06:15 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class WelcomeBean implements Serializable{

	private Integer appid;
	// 显示欢迎页的图片地址
	private String welcomeImgUrl;
	// 显示欢迎页的日期
	private Long welcomeShowDate;
	// 显示欢迎页的秒数
	private int welcomeSeconds;
	// 默认欢迎页
	private String defaultImgUrl;
	// 默认欢迎页秒数
	private int defaultSeconds;
	// 是否展现广告
	private Boolean showAds;
	// 显示广告的概率
	private float showAdRate;
	// 欢迎页是否全屏
	private Boolean isWelcomeFullScreen;
	// 默认欢迎页是否全屏
	private Boolean isDefaultFullScreen;
	
	
	public String getWelcomeImgUrl() {
		return welcomeImgUrl;
	}
	public void setWelcomeImgUrl(String welcomeImgUrl) {
		this.welcomeImgUrl = welcomeImgUrl;
	}
	public Long getWelcomeShowDate() {
		return welcomeShowDate;
	}
	public void setWelcomeShowDate(Long welcomeShowDate) {
		this.welcomeShowDate = welcomeShowDate;
	}
	public int getWelcomeSeconds() {
		return welcomeSeconds;
	}
	public void setWelcomeSeconds(int welcomeSeconds) {
		this.welcomeSeconds = welcomeSeconds;
	}
	public Boolean getShowAds() {
		return showAds;
	}
	public void setShowAds(Boolean showAds) {
		this.showAds = showAds;
	}
	public float getShowAdRate() {
		return showAdRate;
	}
	public void setShowAdRate(float showAdRate) {
		this.showAdRate = showAdRate;
	}
	public String getDefaultImgUrl() {
		return defaultImgUrl;
	}
	public void setDefaultImgUrl(String defaultImgUrl) {
		this.defaultImgUrl = defaultImgUrl;
	}
	public int getDefaultSeconds() {
		return defaultSeconds;
	}
	public void setDefaultSeconds(int defaultSeconds) {
		this.defaultSeconds = defaultSeconds;
	}
	public Boolean getIsWelcomeFullScreen() {
		return isWelcomeFullScreen;
	}
	public void setIsWelcomeFullScreen(Boolean isWelcomeFullScreen) {
		this.isWelcomeFullScreen = isWelcomeFullScreen;
	}
	public Boolean getIsDefaultFullScreen() {
		return isDefaultFullScreen;
	}
	public void setIsDefaultFullScreen(Boolean isDefaultFullScreen) {
		this.isDefaultFullScreen = isDefaultFullScreen;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
}

