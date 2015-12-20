/**
 * 工程名: FengDuLibs
 * 文件名: ImageBean.java
 * 包名: com.fengdu.bean
 * 日期: 2015年10月27日下午3:43:55
 * QQ: 378640336
 *
*/

package com.fengdu.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 类名: ImageBean <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月27日 下午3:43:55 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class ImageBean implements Serializable{

	Integer iid = 0;
	String desc = "";
	
	String image_url = "";
	float image_width = 0;
	float image_height= 0;
	
	String thumbnail = "";
	float thumbnail_width = 0;
	float thumbnail_height = 0;
	int totalNum = 0;
	String updatedTime= "";
	
	Integer cata_id = 0;
	String thumbYun = "";
	
	ArrayList<String> pagePaths;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public float getImage_width() {
		return image_width;
	}
	public void setImage_width(float image_width) {
		this.image_width = image_width;
	}
	public float getImage_height() {
		return image_height;
	}
	public void setImage_height(float image_height) {
		this.image_height = image_height;
	}
	public float getThumbnail_width() {
		return thumbnail_width;
	}
	public void setThumbnail_width(float thumbnail_width) {
		this.thumbnail_width = thumbnail_width;
	}
	public float getThumbnail_height() {
		return thumbnail_height;
	}
	public void setThumbnail_height(float thumbnail_height) {
		this.thumbnail_height = thumbnail_height;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public ArrayList<String> getPagePaths() {
		return pagePaths;
	}
	public void setPagePaths(ArrayList<String> pagePaths) {
		this.pagePaths = pagePaths;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Integer getIid() {
		return iid;
	}
	public void setId(Integer iid) {
		this.iid = iid;
	}
	public Integer getCata_id() {
		return cata_id;
	}
	public void setCata_id(Integer cata_id) {
		this.cata_id = cata_id;
	}
	public String getThumbYun() {
		return thumbYun;
	}
	public void setThumbYun(String thumbYun) {
		this.thumbYun = thumbYun;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
}

