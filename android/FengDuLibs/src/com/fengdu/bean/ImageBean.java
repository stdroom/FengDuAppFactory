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

/**
 * 类名: ImageBean <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月27日 下午3:43:55 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class ImageBean implements Serializable{

	String desc = "";
	
	String image_url = "";
	int image_width = 0;
	int image_height= 0;
	
	String thumbnail = "";
	int thumbnail_width = 0;
	int thumbnail_height = 0;
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
	public int getImage_width() {
		return image_width;
	}
	public void setImage_width(int image_width) {
		this.image_width = image_width;
	}
	public int getImage_height() {
		return image_height;
	}
	public void setImage_height(int image_height) {
		this.image_height = image_height;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getThumbnail_width() {
		return thumbnail_width;
	}
	public void setThumbnail_width(int thumbnail_width) {
		this.thumbnail_width = thumbnail_width;
	}
	public int getThumbnail_height() {
		return thumbnail_height;
	}
	public void setThumbnail_height(int thumbnail_height) {
		this.thumbnail_height = thumbnail_height;
	}
}

