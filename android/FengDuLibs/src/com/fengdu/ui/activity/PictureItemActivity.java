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

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.bean.ImageBean;
import com.fengdu.ui.activity.adapter.ImageAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
		mBackImg.setOnClickListener(this);
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
			
		}else if(arg0.getId() == R.id.bottom_back){
			finish();
		}
		
	}
}

