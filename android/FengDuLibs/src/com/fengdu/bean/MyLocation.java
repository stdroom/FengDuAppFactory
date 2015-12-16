/**
 * 工程名: FengDuLibs
 * 文件名: MyLocation.java
 * 包名: com.fengdu.bean
 * 日期: 2015年12月16日下午4:52:27
 * QQ: 378640336
 *
*/

package com.fengdu.bean;

import java.io.Serializable;

/**
 * 类名: MyLocation <br/>
 * 功能: 地理位置信息. <br/>
 * 日期: 2015年12月16日 下午4:52:27 <br/>
 *
 * @author   leixun
 * @version  	
 */
public class MyLocation implements Serializable{
	// 经度
	String longitude = "";
	// 纬度
	String latitude = "";
	//国家
	String country = "";
	// 省
	String province = "";
	// 市
	String city = "";
	// 区
	String district = "";
	// 地址
	String address = "";
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

