/**
 * 工程名: Zblog
 * 文件名: SearchWord.java
 * 包名: com.zblog.core.dal.entity
 * 日期: 2015年12月26日下午12:31:29
 * Copyright (c) 2015, 暴走兄弟 All Rights Reserved.
 * 
 * Mail: leixun33@163.com
 * QQ: 378640336
 *
*/

package com.fengdu.bean;

import java.util.Date;

/**
 * 类名: SearchWord <br/>
 * 功能: 关键词查询. <br/>
 * 日期: 2015年12月26日 下午12:31:29 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class SearchWord {

	private String imei;
	
	private String word;
	
	private Integer appid;
	
	private Boolean enable;
	
	private Date searchTime;
	
	// 搜索到结果数
	private Integer resultCount;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
}

