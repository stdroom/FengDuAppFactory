/**
 * 工程名: FengDuLibs
 * 文件名: BaseWelcomeActivity.java
 * 包名: com.fengdu.ui
 * 日期: 2015年12月16日下午5:29:23
 * QQ: 378640336
 *
*/

package com.fengdu.ui;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.fengdu.BaseApplication;
import com.fengdu.BaseFragmentActivity;
import com.fengdu.android.AppConstant;
import com.fengdu.bean.MyLocation;
import com.fengdu.bean.WelcomeBean;
import com.fengdu.utils.Utils;
import com.mike.aframe.MKLog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

/**
 * 类名: BaseWelcomeActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月16日 下午5:29:23 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public abstract class BaseWelcomeActivity extends BaseFragmentActivity implements AMapLocationListener{
	private AMapLocationClient locationClient = null;
	private AMapLocationClientOption locationOption = null;
	protected WelcomeBean welcomeBean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Object obj = BaseApplication.globalContext.readObject(AppConstant.welcomeFile);
		if(obj!=null){
			welcomeBean = (WelcomeBean)obj;
		}
	}

	@Override
	public void onLocationChanged(AMapLocation loc) {
		if (null != loc) {
			Message msg = mHandler.obtainMessage();
			msg.obj = loc;
			msg.what = Utils.MSG_LOCATION_FINISH;
			mHandler.sendMessage(msg);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationClient = new AMapLocationClient(this.getApplicationContext());
		locationOption = new AMapLocationClientOption();
		locationOption.setOnceLocation(true);
		// 设置定位模式为低功耗模式
		locationOption.setLocationMode(AMapLocationMode.Battery_Saving);
		// 设置定位监听
		locationClient.setLocationListener(this);
		
		locationOption.setNeedAddress(true);
		// 设置定位参数
		locationClient.setLocationOption(locationOption);
		// 启动定位
		locationClient.startLocation();
		mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
	}
	
	Handler mHandler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case Utils.MSG_LOCATION_START:
//				tvReult.setText("正在定位...");
				MKLog.d("welcome", "正在定位...");
				break;
			//定位完成
			case Utils.MSG_LOCATION_FINISH:
				AMapLocation loc = (AMapLocation)msg.obj;
				MyLocation result = Utils.getLocationStr(loc);
				BaseApplication.globalContext.saveObject(result, AppConstant.addressFile);
//				tvReult.setText(result);
				MKLog.d("welcome", result.getAddress()+"");
				break;
			case Utils.MSG_LOCATION_STOP:
				MKLog.d("welcome", "定位停止");
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != locationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			locationClient.onDestroy();
			locationClient = null;
			locationOption = null;
		}
	}
	
	protected void alphaJump(View view , int time){
		 // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(time * 100);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });
	}
	
	protected abstract void redirectTo();
	
}

