/**
 * 工程名: Zblog
 * 文件名: Favor.java
 * 包名: com.zblog.core.dal.entity
 * 日期: 2015年12月15日上午11:39:10
 * QQ: 378640336
 *
*/

package com.fengdu.bean;

import java.util.Date;

/**
 * 类名: Favor <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月15日 上午11:39:10 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class Favor {
	/* 收藏的手机型号  */
	String imei;
	/** 用户id */
	String userId;
	/** appid */
	Integer appid;
	/** 收藏的 id */
	Integer favorId;
	/*创建时间*/
	Date createTime;
	/*更新时间*/
	Date updateTime;
	/*是否有效*/
	Boolean enable;
	/**是否阅读 */
	Boolean readed;
	/** 是否举报*/
	Boolean reported;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public Integer getFavorId() {
		return favorId;
	}
	public void setFavorId(Integer favorId) {
		this.favorId = favorId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Boolean getReaded() {
		return readed;
	}
	public void setReaded(Boolean readed) {
		this.readed = readed;
	}
	public Boolean getReported() {
		return reported;
	}
	public void setReported(Boolean reported) {
		this.reported = reported;
	}
}

