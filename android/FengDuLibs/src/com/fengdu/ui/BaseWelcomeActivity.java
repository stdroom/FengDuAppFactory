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
import com.fengdu.utils.Utils;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * 类名: BaseWelcomeActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月16日 下午5:29:23 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class BaseWelcomeActivity extends BaseFragmentActivity implements AMapLocationListener{
	private AMapLocationClient locationClient = null;
	private AMapLocationClientOption locationOption = null;
	
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
				Toast.makeText(BaseWelcomeActivity.this, "正在定位...", Toast.LENGTH_SHORT).show();
				break;
			//定位完成
			case Utils.MSG_LOCATION_FINISH:
				AMapLocation loc = (AMapLocation)msg.obj;
				MyLocation result = Utils.getLocationStr(loc);
				BaseApplication.globalContext.saveObject(result, AppConstant.addressFile);
//				tvReult.setText(result);
				Toast.makeText(BaseWelcomeActivity.this, result.getAddress(), Toast.LENGTH_SHORT).show();
				break;
			case Utils.MSG_LOCATION_STOP:
				Toast.makeText(BaseWelcomeActivity.this, "定位停止", Toast.LENGTH_SHORT).show();
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
	
}

