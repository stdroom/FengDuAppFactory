package com.yesemeinvsuite.android;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SplashView;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;

import com.fengdu.R;
import com.fengdu.android.AppConstant;
import com.fengdu.ui.BaseWelcomeActivity;
import com.mike.aframe.utils.SystemTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WelcomeActivity extends BaseWelcomeActivity{
	
	SplashView splashView;
	View splash;
	RelativeLayout splashLayout,bottomRl;
	String platform = "";
	private long exitTime = 0;
	private DisplayImageOptions mOptions;
	private ImageView halfImageView,fullImageView;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		AdManager.getInstance(this).init(AppConstant.YOUMI_APPID, AppConstant.YOUMI_APPSECRET, false);
		setContentView(R.layout.splash_activity);
		halfImageView = (ImageView)findViewById(R.id.halfull_imgview);
		fullImageView = (ImageView)findViewById(R.id.full_imgview);
		bottomRl = (RelativeLayout)findViewById(R.id.bottom_rl);
		mOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.considerExifParams(true)
				.build();
		if(welcomeBean!=null && !welcomeBean.getShowAds() ){
			
			if(Math.random() < welcomeBean.getShowAdRate()){
				halfImageView.setVisibility(View.GONE);
				fullImageView.setVisibility(View.GONE);
				showAds();
			}else{
				Date showDate = new Date(welcomeBean.getWelcomeShowDate());
				Date nowDate = new Date();
				if(SystemTool.isSameDate(showDate, nowDate)){	// 同一天 展示当天广告
					if(welcomeBean.getIsWelcomeFullScreen()){	// 全屏
						halfImageView.setVisibility(View.GONE);
						fullImageView.setVisibility(View.VISIBLE);
						bottomRl.setVisibility(View.GONE);
						ImageLoader.getInstance().displayImage(welcomeBean.getWelcomeImgUrl(),fullImageView,mOptions);
						alphaJump(fullImageView, welcomeBean.getWelcomeSeconds());
					}else{
						halfImageView.setVisibility(View.VISIBLE);
						fullImageView.setVisibility(View.GONE);
						ImageLoader.getInstance().displayImage(welcomeBean.getWelcomeImgUrl(),halfImageView,mOptions);
						alphaJump(halfImageView, welcomeBean.getWelcomeSeconds());
					}
				}else{ // 展示默认启动页
					if(welcomeBean.getIsDefaultFullScreen()){
						halfImageView.setVisibility(View.GONE);
						fullImageView.setVisibility(View.VISIBLE);
						bottomRl.setVisibility(View.GONE);
						ImageLoader.getInstance().displayImage(welcomeBean.getDefaultImgUrl(),fullImageView,mOptions);
						alphaJump(fullImageView, welcomeBean.getDefaultSeconds());
					}else{
						halfImageView.setVisibility(View.VISIBLE);
						fullImageView.setVisibility(View.GONE);
						ImageLoader.getInstance().displayImage(welcomeBean.getDefaultImgUrl(),halfImageView,mOptions);
						alphaJump(halfImageView, welcomeBean.getDefaultSeconds());
					}
				}
			}
		}else{
			halfImageView.setVisibility(View.GONE);
			fullImageView.setVisibility(View.GONE);
			showAds();
		}
	}
	
	private void showAds(){
		splashView = new SplashView(this, null);
		splashView.setShowReciprocal(true);
		splashView.hideCloseBtn(true);
		
		Intent intent = new Intent(this, MainActivity.class);
		splashView.setIntent(intent);
		splashView.setIsJumpTargetWhenFail(true);

		splash = splashView.getSplashView();
		splashLayout = ((RelativeLayout) findViewById(R.id.splashview));
		splashLayout.setVisibility(View.GONE);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
		
		splashLayout.addView(splash, params);
//		MKLog.d("shit",getDeviceInfo(this));
		
		SpotManager.getInstance(this).showSplashSpotAds(this, splashView,
				new SpotDialogListener() {
			
			@Override
			public void onShowSuccess() {
				splashLayout.setVisibility(View.VISIBLE);
				splashLayout.startAnimation(AnimationUtils.loadAnimation(WelcomeActivity.this, 
						R.anim.pic_enter_anim_alpha));
			}
			
			@Override
			public void onShowFailed() {
			}
			
			@Override
			public void onSpotClosed() {
			}
			
			@Override
			public void onSpotClick(boolean arg0) {
				
			}
		});
	}
	

public static String getDeviceInfo(Context context) {
    try{
      org.json.JSONObject json = new org.json.JSONObject();
      android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
          .getSystemService(Context.TELEPHONY_SERVICE);

      String device_id = tm.getDeviceId();

      android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

      String mac = wifi.getConnectionInfo().getMacAddress();
      json.put("mac", mac);

      if( !"".equals(device_id) ){
        device_id = mac;
      }

      if( !"".equals(device_id) ){
        device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
      }

      json.put("device_id", device_id);

      return json.toString();
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 一个鄙人感觉不错的退出体验*/
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            exitTime = System.currentTimeMillis();
	            Toast.makeText(this, "对不起，考验你耐心了", Toast.LENGTH_SHORT).show();
	        } else {
	        	if(splashView!=null){
	        		splashView.setIntent(null);
	        	}
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void redirectTo() {
		Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
		startActivity(intent2);
		finish();
	}

	
}
