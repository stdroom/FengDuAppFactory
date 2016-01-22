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
import com.fengdu.widgets.PointWidget;
import com.mike.aframe.MKLog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

import java.util.ArrayList;

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
    protected ArrayList<View> ViewPagerList;
    protected PointWidget mGuidePoint;
    int singleFlag = -1;
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


    protected ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {
            mGuidePoint.setPoint(arg0);
            if (arg0 == ViewPagerList.size() - 1) {
                return;
            }
        }

        //从第四页 向 第五页 滑动，则直接finish掉activity
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position == ViewPagerList.size()-2 && positionOffset > 0 && singleFlag++ < 0){
                redirectTo();
                MKLog.d("baseWelcome","onPageScrolled");
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    protected PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return ViewPagerList!=null? ViewPagerList.size():0;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(ViewPagerList.get(position));

            return ViewPagerList.get(position);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(ViewPagerList.get(position));
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    };
}

