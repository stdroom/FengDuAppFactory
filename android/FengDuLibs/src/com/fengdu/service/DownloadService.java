/**
 * 工程名: FengDuLibs
 * 文件名: DownloadService.java
 * 包名: com.fengdu.service
 * 日期: 2015年12月30日上午10:59:36
 * QQ: 378640336
 *
*/

package com.fengdu.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fengdu.bean.ImageBean;
import com.fengdu.utils.ExcutorServiceUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

/**
 * 类名: DownloadService <br/>
 * 功能: 下载服务. <br/>
 * 日期: 2015年12月30日 上午10:59:36 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class DownloadService extends Service{

	@Override  
	public void onCreate() {
		super.onCreate();
	}  

	@Override  
	public void onDestroy() {
		super.onDestroy();
	} 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
//		String downloadUrl = ToolsPreferences.getPreferences(ToolsPreferences.DOWNLOAD_URL);
		//启动线程开始执行下载任务
		ImageBean bean = (ImageBean)intent.getExtras().getSerializable("key");
		if(bean!=null){
			ExcutorServiceUtils.getInstance().getThreadPool().submit(new MyDown(bean));
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	public class MyDown implements Runnable{
		
		private ImageBean bean;
		
		public MyDown(ImageBean bean){
			this.bean  = bean;
		}

		@Override
		public void run() {
			try {    
				ArrayList<String> urls = bean.getPagePaths();
				HttpClient client = new DefaultHttpClient();  
				String path = "";
				for(int i = 0 ;i < urls.size();i++){
					String url = urls.get(i);
					HttpGet get = new HttpGet(url);       
					HttpResponse response = client.execute(get);    
					HttpEntity entity = response.getEntity();    
					InputStream is = entity.getContent();
					File tempFile = null;
					if (is != null) {
						File rootFile=new File(Environment.getExternalStorageDirectory(), "/DCIM/yese/"+bean.getDesc());
						if(!rootFile.exists())
							rootFile.mkdirs();
						
						tempFile = new File(Environment.getExternalStorageDirectory(),"/DCIM/yese/"+bean.getDesc()+"/"+
						url.substring(url.lastIndexOf("/")+1));
						path = tempFile.getAbsolutePath();
						if(tempFile.exists())
							tempFile.delete();
						tempFile.createNewFile();
						
						//已读出流作为参数创建一个带有缓冲的输出流
						BufferedInputStream bis = new BufferedInputStream(is);
						
						//创建一个新的写入流，讲读取到的图像数据写入到文件中
						FileOutputStream fos = new FileOutputStream(tempFile);
						//已写入流作为参数创建一个带有缓冲的写入流
						BufferedOutputStream bos = new BufferedOutputStream(fos); 
						
						int read;
						byte[] buffer=new byte[1024];
						while( (read = bis.read(buffer)) != -1){ 
							bos.write(buffer,0,read);
						}  
						bos.flush();
						bos.close();
						fos.flush();
						fos.close();
						is.close();
						bis.close();
					}
				} 
				Message msg = new Message();
				msg.what = 0x3001;
				msg.obj = bean.getDesc()+"\n保存于："+path;
				myHandler.sendMessage(msg);
			}catch(Exception e){
				e.printStackTrace();
				Message msg = new Message();
				msg.what = 0x3002;
				msg.obj = e.getMessage();
				myHandler.sendMessage(msg);
			}
		}
		
	}
	
	public Handler myHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x3001){
				Toast.makeText(DownloadService.this, msg.obj+"", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(DownloadService.this, msg.obj+"", Toast.LENGTH_SHORT).show();
			}
		}
		
	};

}

