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
import android.widget.TextView;

/**
 * 类名: PictureItemActivity <br/>
 * 功能: 图片展示. <br/>
 * 日期: 2015年10月28日 下午4:59:03 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class PictureItemActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener{
	private ArrayList<ImageBean> mList;
	private DisplayImageOptions mOptions;
	private ViewPager viewPager;
	private ImageAdapter imgAdapter;
	private ImageBean bean;
	TextView titleTv;
	TextView pageNumTv;
	int totalSize;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_item); 
		initOptions();
		Intent intent = getIntent();
		bean = (ImageBean)intent.getExtras().getSerializable("key");
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		pageNumTv = (TextView)findViewById(R.id.pageNumTv);
		titleTv = (TextView)findViewById(R.id.titleTv);
		imgAdapter = new ImageAdapter(bean.getPagePaths(),this);
		viewPager.setAdapter(imgAdapter);
		totalSize = bean.getPagePaths().size();
		viewPager.setOffscreenPageLimit(totalSize);
		titleTv.setText(bean.getDesc());
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
		pageNumTv.setText(arg0+"/"+totalSize);
	}
}

