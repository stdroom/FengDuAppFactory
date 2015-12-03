/**
 * 工程名: FengDuLibs
 * 文件名: BaseSecondMainActivity.java
 * 包名: com.fengdu.ui
 * 日期: 2015年11月12日下午2:36:38
 * QQ: 378640336
 *
*/

package com.fengdu.ui;

import java.util.ArrayList;

import com.fengdu.BaseApplication;
import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.ui.activity.SearchActivity;
import com.fengdu.ui.adapter.MyFragmentAdapter;
import com.fengdu.ui.slide.DrawerView;
import com.fengdu.utils.UpdateManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.spot.SpotManager;

/**
 * 类名: BaseSecondMainActivity <br/>
 * 功能: . <br/>
 * 日期: 2015年11月12日 下午2:36:38 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public abstract class BaseSecondMainActivity extends BaseFragmentActivity implements 
		OnPageChangeListener,View.OnClickListener{
	private HorizontalScrollView mHorizontalScrollView ;
	private LinearLayout mLinearLayout;
	private int mScreenWidth;
	
	private int currentFragmentIndex;
	private boolean isEnd;
	
	protected ArrayList<String> mTitleList = new ArrayList<String>();
	private ViewPager mFragmentViewPager = null;
	
	protected MyFragmentAdapter mFragmentPagerAdapter;
	protected ArrayList<Fragment> mFragmentList;
	
	private long exitTime = 0;
	
	protected SlidingMenu side_drawer;
	ImageView mTopHead;
	ImageView mTopMore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_main);
//		AdManager.getInstance(this).init(AppConstant.YOUMI_APPID, AppConstant.YOUMI_APPSECRET, false);
		SpotManager.getInstance(this).loadSpotAds();
		SpotManager.getInstance(this).setAnimationType(
				SpotManager.ANIM_ADVANCE);
		SpotManager.getInstance(this).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		// 友盟发送策略
		MobclickAgent.updateOnlineConfig(this);
		mFragmentList = new ArrayList<Fragment>();
		initSlidingMenu();
		initView();
		initMenu();
		initFragment();
		UpdateManager.getUpdateManager().checkAppUpdate(this, false);
	}


	@SuppressWarnings("deprecation")
	protected void initView(){
		mFragmentViewPager = (ViewPager)findViewById(R.id.fragment_viewpager);
		mFragmentPagerAdapter = new MyFragmentAdapter(
				getSupportFragmentManager());
		mFragmentViewPager.setOffscreenPageLimit(4);
		mFragmentViewPager.setAdapter(mFragmentPagerAdapter);
		mFragmentViewPager.setOnPageChangeListener(this);
		mTopHead = (ImageView)findViewById(R.id.top_head);
		mTopMore = (ImageView)findViewById(R.id.top_more);
		//左滑
		mTopHead.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(side_drawer.isMenuShowing()){
						side_drawer.showContent();
					}else{
						side_drawer.showMenu();
					}
				}
			});
		mTopMore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(BaseSecondMainActivity.this,SearchActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initMenu(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv_view);
		mLinearLayout = (LinearLayout) findViewById(R.id.hsv_content);
		mLinearLayout.removeAllViews();
		String[] menuName = getResources().getStringArray(R.array.title_arrays);
		for(int i = 0;i<menuName.length;i++){
			mTitleList.add(menuName[i]);
		}
		int length = mTitleList.size();
		for (int i = 0 ; i < length ; i++) {
			RelativeLayout layout = new RelativeLayout(this);
			ImageView mImageView = new ImageView(this);
			TextView view = new TextView(this);
			view.setText(mTitleList.get(i));
			view.setTextSize(getResources().getDimension(R.dimen.title_size));
			view.setGravity(Gravity.CENTER);
			if(i==0){
				view.setTextColor(getResources().getColor(R.color.text_color_green));
			}else{
				view.setTextColor(getResources().getColor(R.color.title_color));
			}
			int itemWidth = (int) (view.getPaint().measureText(mTitleList.get(i))+getResources().getDimension(R.dimen.title_add_width));
			RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			layout.addView(view,0, params);
			mImageView.setBackgroundColor(getResources().getColor(R.color.text_color_green));
			RelativeLayout.LayoutParams params2 =  new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)getResources().getDimension(R.dimen.title_img_height));
			params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			layout.addView(mImageView,1,params2);
			if(i==0){
				mImageView.setVisibility(View.VISIBLE);
			}else{
				mImageView.setVisibility(View.INVISIBLE);
			}
			mLinearLayout.addView(layout, itemWidth, (int)getResources().getDimension(R.dimen.title_height));
			layout.setOnClickListener(this);
			layout.setTag(i);
		}
	}

	protected abstract void initFragment();
	
	@Override
	public void onClick(View v) {
		mFragmentViewPager.setCurrentItem((Integer)v.getTag());
	}


	@Override
	public void onPageScrollStateChanged(int state) {
		if (state == ViewPager.SCROLL_STATE_DRAGGING) {
			isEnd = false;
		} else if (state == ViewPager.SCROLL_STATE_SETTLING) {
			isEnd = true;
			if (mFragmentViewPager.getCurrentItem() == currentFragmentIndex) {
				mHorizontalScrollView.invalidate();
			}
		}
	}


	@Override
	public void onPageScrolled(int position, float positionOffset, int arg2) {
		if(!isEnd){
//			if(currentFragmentIndex == position){
//				endPosition = item_width * currentFragmentIndex + 
//						(int)(item_width * positionOffset);
//			}
//			if(currentFragmentIndex == position+1){
//				endPosition = item_width * currentFragmentIndex - 
//						(int)(item_width * (1-positionOffset));
//			}
			
//			Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
//			mAnimation.setFillAfter(true);
//			mAnimation.setDuration(0);
//			mImageView.startAnimation(mAnimation);
			mHorizontalScrollView.invalidate();
//			beginPosition = endPosition;
		}
	}


	@Override
	public void onPageSelected(int position) {
		RelativeLayout rel = (RelativeLayout)mLinearLayout.getChildAt(currentFragmentIndex);
		rel.getChildAt(1).setVisibility(View.INVISIBLE);
		((TextView)rel.getChildAt(0)).setTextColor(getResources().getColor(R.color.title_color));
		currentFragmentIndex = position;
		RelativeLayout rel2 = (RelativeLayout)mLinearLayout.getChildAt(currentFragmentIndex);
		rel2.getChildAt(1).setVisibility(View.VISIBLE);
		((TextView)rel2.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_green));
//		if (animation != null) {
//			animation.setFillAfter(true);
//			animation.setDuration(0);
//			mImageView.startAnimation(animation);
		int scrollX = 0;
		for(int i = 0 ;i < currentFragmentIndex;i++){
			scrollX += mLinearLayout.getChildAt(i).getWidth();
		}
			mHorizontalScrollView.smoothScrollTo(scrollX, 0);
//		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 一个鄙人感觉不错的退出体验*/
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            exitTime = System.currentTimeMillis();   
	            Toast.makeText(BaseSecondMainActivity.this, "再按一次退出"+getString(R.string.app_name), Toast.LENGTH_SHORT).show();
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
		return super.onKeyDown(keyCode, event);
	}
	
	protected abstract void initSlidingMenu();
}

