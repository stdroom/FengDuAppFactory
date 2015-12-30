/**
 * 工程名: FengDuLibs
 * 文件名: PictureItemActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年10月28日下午4:59:03
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.android.URLs;
import com.fengdu.bean.Favor;
import com.fengdu.bean.ImageBean;
import com.fengdu.service.DownloadService;
import com.fengdu.service.UpgradeService;
import com.fengdu.ui.activity.adapter.ImageAdapter;
import com.fengdu.volley.FastJSONRequest;
import com.fengdu.volley.FastResponse.Listener;
import com.mike.aframe.MKLog;
import com.fengdu.volley.VolleyManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;

/**
 * 类名: PictureItemActivity <br/>
 * 功能: 图片展示. <br/>
 * 日期: 2015年10月28日 下午4:59:03 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class PictureItemActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener
	,View.OnClickListener{
	private ArrayList<ImageBean> mList;
	private DisplayImageOptions mOptions;
	private ViewPager viewPager;
	private ImageAdapter imgAdapter;
	private ImageBean bean;
	TextView titleTv;
	TextView pageNumTv;
	int totalSize;
	RelativeLayout bottom_menu_rl;
	private ImageView mBackImg;
	private ImageView mFavorImg;
	private ImageView mDownloadImg;
	private ImageView mRemoveImg;
	
	RequestQueue mQueue;
	Favor favor = null;
	boolean fave = true;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_item); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    
		        WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		initOptions();
		Intent intent = getIntent();
		bean = (ImageBean)intent.getExtras().getSerializable("key");
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		pageNumTv = (TextView)findViewById(R.id.pageNumTv);
		titleTv = (TextView)findViewById(R.id.titleTv);
		bottom_menu_rl = (RelativeLayout)findViewById(R.id.bottom_menu_rl);
		mBackImg = (ImageView)findViewById(R.id.bottom_back);
		mFavorImg = (ImageView)findViewById(R.id.bottom_favor);
		mRemoveImg = (ImageView)findViewById(R.id.bottom_remove);
		mDownloadImg = (ImageView)findViewById(R.id.bottom_download);
		mDownloadImg.setOnClickListener(this);
		mFavorImg.setOnClickListener(this);
		mBackImg.setOnClickListener(this);
		mRemoveImg.setOnClickListener(this);
		imgAdapter = new ImageAdapter(bean.getPagePaths(),this,new OnPhotoTapListener() {
			
			@Override
			public void onPhotoTap(View view, float x, float y) {
				if(bottom_menu_rl.getVisibility() == View.VISIBLE){
					bottom_menu_rl.startAnimation(AnimationUtils.loadAnimation(PictureItemActivity.this, 
							R.anim.anim_push_bottom_out));
					bottom_menu_rl.setVisibility(View.GONE);
				}else{
					bottom_menu_rl.startAnimation(AnimationUtils.loadAnimation(PictureItemActivity.this, 
							R.anim.anim_push_bottom_in));
					bottom_menu_rl.setVisibility(View.VISIBLE);
				}
			}
		});
		
		viewPager.setAdapter(imgAdapter);
		totalSize = bean.getPagePaths().size();
		int size = totalSize>5? 5:totalSize;
		viewPager.setOffscreenPageLimit(size);
		titleTv.setText(bean.getDesc());
		pageNumTv.setText(1+"/"+totalSize);
		viewPager.setOnPageChangeListener(this);
		
		mQueue = Volley.newRequestQueue(this);
		mQueue.start();
		fechIsFavor();
	}
	
	private void initOptions(){
		mOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.friends_sends_pictures_no)
		.showImageForEmptyUri(R.drawable.friends_sends_pictures_no)
		.showImageOnFail(R.drawable.friends_sends_pictures_no)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int arg0) {
		int num = arg0+1;
		pageNumTv.setText(num+"/"+totalSize);
	}

	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.bottom_favor){
			if(favor != null){
				fave = !favor.getEnable();
			}
			VolleyManager.getInstance().beginSubmitRequest(mQueue, new FastJSONRequest(
					URLs.URL_FAVOR_IMG+"?imgId="+bean.getIid()+"&isFavor="+fave, "", new Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject obj, String executeMethod, String flag, boolean dialogFlag) {
							if(obj!=null){
								if(fave){	//收藏
									mHandler.sendEmptyMessage(0x1003);
								}else{
									mHandler.sendEmptyMessage(0x1002);
								}
								obj.toJSONString();
								MKLog.d("on Favor", obj.toJSONString());
							}
						}
					}, new ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							MKLog.d("on Favor", error.toString());
						}
					}));
		}else if(arg0.getId() == R.id.bottom_back){
			finish();
		}else if(arg0.getId() == R.id.bottom_remove){
			VolleyManager.getInstance().beginSubmitRequest(mQueue, new FastJSONRequest(
					URLs.URL_FAVOR_IMG+"?imgId="+bean.getIid()+"&report="+true, "", new Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject obj, String executeMethod, String flag, boolean dialogFlag) {
							if(obj!=null){
								mHandler.sendEmptyMessage(0x1004);
								obj.toJSONString();
								MKLog.d("on Favor", obj.toJSONString());
							}
						}
					}, new ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							MKLog.d("on Favor", error.toString());
						}
					}));
		}else if(arg0.getId() == R.id.bottom_download){
			arg0.setEnabled(false);
			Toast.makeText(this, "开始下载...", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this,DownloadService.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", bean);
			intent.putExtras(bundle);
			startService(intent);
		}
		
	}
	
	private void fechIsFavor(){
		VolleyManager.getInstance().beginSubmitRequest(mQueue, new FastJSONRequest(
				URLs.URL_IS_FAVOR_IMG+"?imgId="+bean.getIid(), "", new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject obj, String executeMethod, String flag, boolean dialogFlag) {
						if(obj!=null){
							if(obj.containsKey("isFavor")){
								if(obj.get("isFavor")!=null){
									Message msg = new Message();
									msg.what = 0x1001;
									msg.obj = JSON.parseObject(obj.getString("isFavor"),Favor.class);
									mHandler.sendMessage(msg);
								}else{
									mHandler.sendEmptyMessage(0x1002);
								}
							}else{
								mHandler.sendEmptyMessage(0x1002);
							}
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						MKLog.d("on Favor", error.toString());
					}
				}));
	}
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0x1001:
				favor= (Favor)msg.obj;
				if(favor!=null){
					if(favor.getEnable()){	// 已收藏
						mFavorImg.setImageResource(R.drawable.bottom_collectioned_icon);
					}else{	// 未收藏
						mFavorImg.setImageResource(R.drawable.bottom_collection_icon);
					}
				}else{
					mFavorImg.setImageResource(R.drawable.bottom_collection_icon);
				}
				break;
			case 0x1002:
				mFavorImg.setImageResource(R.drawable.bottom_collection_icon);
				break;
			case 0x1003:
				mFavorImg.setImageResource(R.drawable.bottom_collectioned_icon);
				break;
			case 0x1004:
				Toast.makeText(PictureItemActivity.this, "图片举报成功，感谢您的配合", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
}

