/**
 * ������: SpecialFocus
 * �ļ���: BaseApplication.java
 * ����: com.sepcialfocus.android
 * ����: 2015-9-1����8:47:25
 * Copyright (c) 2015, ����С���ӽ���Ƽ����޹�˾ All Rights Reserved.
 * http://www.xiaoma.com/
 * Mail: leixun@xiaoma.cn
 * QQ: 378640336
 *
*/

package com.fengdu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import com.fengdu.android.AppConfig;
import com.fengdu.android.AppConstant;
import com.fengdu.android.URLs;
import com.fengdu.bean.MyLocation;
import com.fengdu.config.CommonConfig;
import com.mike.aframe.utils.PreferenceHelper;
import com.mike.aframe.utils.SystemTool;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ����: BaseApplication <br/>
 * ����: TODO ��ӹ�������. <br/>
 * ����: 2015-9-1 ����8:47:25 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class BaseApplication extends Application{
	/** 全局上下文 */
	public static BaseApplication globalContext;
	
	private HashMap<String,String> headers;
	private MyLocation location;
	@Override
	public void onCreate() {
		super.onCreate();
		this.globalContext = this;
		initImageLoader(this);
	}
	
	public HashMap<String,String> getHeaders(){
		if(headers == null){
			headers = new HashMap<String,String>();
			headers.put("appid", AppConstant.APPID+"");
			headers.put("osversion", android.os.Build.VERSION.RELEASE+"");
			headers.put("appversion", SystemTool.getAppVersion(this));
			headers.put("client_type", "android");
			headers.put("imei",SystemTool.getPhoneIMEI(this)); //手机序列号
			headers.put("lang",SystemTool.getLang(this)); //手机语言
			headers.put("phone_type", android.os.Build.MODEL);	// 手机型号
			headers.put("timestamp", SystemTool.getTimeStamp()+""); //时间戳　可用于判断是否是同一次启动
			headers.put("channel", encodingUtf8(SystemTool.getAppMetaData(this, "UMENG_CHANNEL")));
		}
		if(location == null){
			location = (MyLocation) readObject(AppConstant.addressFile);
			if(location!=null){
				headers.put("address", encodingUtf8(location.getAddress()));
				headers.put("city",  encodingUtf8(location.getCity()));
				headers.put("country",  encodingUtf8(location.getCountry()));
				headers.put("district",  encodingUtf8(location.getDistrict()));
				headers.put("province",  encodingUtf8(location.getProvince()));
				headers.put("latitude", location.getLatitude());
				headers.put("longitude", location.getLongitude());
			}else{
				location = null;
			}
		}
		return headers;
	}
	
	public void cleanHeaders(){
		headers = null;
	}

	/**
	 * 保存对象
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = openFileOutput(file, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				oos.close();
			} catch (Exception e) {}
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 读取对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file){
		if(!isExistDataCache(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable)ois.readObject();
		}catch(FileNotFoundException e){
		}catch(Exception e){
			e.printStackTrace();
			//反序列化失败 - 删除缓存文件
			if(e instanceof InvalidClassException){
				File data = getFileStreamPath(file);
				data.delete();
			}
		}finally{
			try {
				ois.close();
			} catch (Exception e) {}
			try {
				fis.close();
			} catch (Exception e) {}
		}
		return null;
	}
	
	/**
	 * 判断缓存是否存在
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile)
	{
		boolean exist = false;
		File data = getFileStreamPath(cachefile);
		if(data.exists())
			exist = true;
		return exist;
	}
	
	public boolean getNightModeSwitch(){
		return PreferenceHelper.readBoolean(this, AppConfig.sharedFile,AppConstant.KEY_NIGHT_MODE_SWITCH, false);
	}

	public void setNightModeSwitch(boolean flag){
		PreferenceHelper.write(this, AppConfig.sharedFile, AppConstant.KEY_NIGHT_MODE_SWITCH, flag);
	}
	
	public boolean isNetworkConnected(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}
	
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSize(8 * 1024 * 1024)
				.diskCacheSize(100 * 1024 * 1024) // 50 Mb
				.diskCacheFileNameGenerator(new FileNameGenerator() {
					@Override
					public String generate(String imageUri) {
						StringBuffer buffer = new StringBuffer();
						buffer.append(String.valueOf(imageUri.hashCode()));
						buffer.append(".");
						buffer.append(imageUri.substring(imageUri.lastIndexOf("."), imageUri.length()));
						return buffer.toString();
					}
				})
				.discCache(new UnlimitedDiskCache(new File(CommonConfig.getDownloadImgPath())))
				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	
	private String encodingUtf8(String str){
		try {
			str = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}

