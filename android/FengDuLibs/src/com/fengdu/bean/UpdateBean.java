/**
 * 工程名: MainActivity
 * 文件名: UpdateBean.java
 * 包名: com.sepcialfocus.android.bean
 * 日期: 2015-9-19上午9:51:24
 * Copyright (c) 2015, 北京小马过河教育科技有限公司 All Rights Reserved.
 * http://www.xiaoma.com/
 * Mail: leixun@xiaoma.cn
 * QQ: 378640336
 *
*/

package com.fengdu.bean;
/**
 * 类名: UpdateBean <br/>
 * 功能: 版本更新bean. <br/>
 * 日期: 2015-9-19 上午9:51:24 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class UpdateBean {
	private String appid;
	private int versionCode;
	private String versionName;
	private String downloadUrl;
	
	// 提示信息
	private String updateLog;
	
	// 一句话 提示日志
	private String simpleLog;
	// 更新等级	1：强制更新 2：点击更新 3：提示更新
	private Integer updateLevel;

	public UpdateBean(){
	}
	
	public int getVersionCode() {
		return versionCode;
	}
	
	public void setVersionCode(String versionCode) {
		if (!"".equals(versionCode)){
			this.versionCode = Integer.parseInt(versionCode);
		} else{
			this.versionCode = 0;
		}
	}
	
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getUpdateLog() {
		return updateLog;
	}
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getSimpleLog() {
		return simpleLog;
	}

	public void setSimpleLog(String simpleLog) {
		this.simpleLog = simpleLog;
	}

	public Integer getUpdateLevel() {
		return updateLevel;
	}

	public void setUpdateLevel(Integer updateLevel) {
		this.updateLevel = updateLevel;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
}

