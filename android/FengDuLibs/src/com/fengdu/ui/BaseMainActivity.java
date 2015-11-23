/**
 * 工程名: FengDuLibs
 * 文件名: BaseMainActivity.java
 * 包名: com.fengdu.ui
 * 日期: 2015年10月28日下午4:00:02
 * QQ: 378640336
 *
*/

package com.fengdu.ui;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.ui.menu.MyFragmentTabHost;
import com.fengdu.ui.slide.DrawerView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import android.widget.ImageView;
import net.youmi.android.spot.SpotManager;

/**
 * 类名: BaseMainActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月28日 下午4:00:02 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public abstract class BaseMainActivity extends BaseFragmentActivity implements OnTouchListener {			
	long exitTime;

	protected MyFragmentTabHost mTabHost = null;
	SlidingMenu side_drawer;
	ImageView mTopHead;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		AdManager.getInstance(this).init(AppConstant.YOUMI_APPID, AppConstant.YOUMI_APPSECRET, false);
		SpotManager.getInstance(this).loadSpotAds();
		SpotManager.getInstance(this).setAnimationType(
				SpotManager.ANIM_ADVANCE);
		SpotManager.getInstance(this).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		// 友盟发送策略
		MobclickAgent.updateOnlineConfig(this);
		findViewById();
		initSlidingMenu();
		initTabs();
	}
	
	private void findViewById(){
		mTabHost = (MyFragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);   
		mTopHead = (ImageView)findViewById(R.id.top_head);
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
	}

	protected abstract void initTabs();

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
	
	private void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 一个鄙人感觉不错的退出体验*/
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
			if(side_drawer.isMenuShowing() ||side_drawer.isSecondaryMenuShowing()){
				side_drawer.showContent();
			}else if((System.currentTimeMillis()-exitTime) > 2000){  
	            exitTime = System.currentTimeMillis();   
	            Toast.makeText(this, "再按一次退出"+getResources().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
		return super.onKeyDown(keyCode, event);
	}
}

