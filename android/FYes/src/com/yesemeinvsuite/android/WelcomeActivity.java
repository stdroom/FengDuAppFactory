package com.yesemeinvsuite.android;

import java.util.HashMap;
import java.util.logging.Logger;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SplashView;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.android.AppConstant;
import com.mike.aframe.MKLog;
import com.umeng.analytics.MobclickAgent;

public class WelcomeActivity extends BaseFragmentActivity{
	
	SplashView splashView;
	View splash;
	RelativeLayout splashLayout;
	String platform = "";
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		AdManager.getInstance(this).init(AppConstant.YOUMI_APPID, AppConstant.YOUMI_APPSECRET, false);
		splashView = new SplashView(this, null);
		splashView.setShowReciprocal(true);
		splashView.hideCloseBtn(true);

		Intent intent = new Intent(this, MainActivity.class);
		splashView.setIntent(intent);
		splashView.setIsJumpTargetWhenFail(true);

		splash = splashView.getSplashView();
		setContentView(R.layout.splash_activity);
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
	
//	View splash;
//	RelativeLayout splashLayout;
//	String platform = "";
//	@Override
//	protected void onCreate(Bundle arg0) {
//		// TODO Auto-generated method stub
//		super.onCreate(arg0);
//		setContentView(R.layout.splash_activity);
//		
//		
//		mHandler.sendEmptyMessageDelayed(0x01, 500);
//		
//	}
	

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
                

	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
			startActivity(intent2);
			finish();
		}
		
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 一个鄙人感觉不错的退出体验*/
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            exitTime = System.currentTimeMillis();
	            Toast.makeText(this, "对不起，悦读圈考验你耐心了", Toast.LENGTH_SHORT).show();
	        } else {
	        	splashView.setIntent(null);
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
		return super.onKeyDown(keyCode, event);
	}

}
