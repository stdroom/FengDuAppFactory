package com.youmi.android.addemo;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class YoumiNormalAdsDemo extends Activity {

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad);
		context = this;
		setSpotAd();

		showBanner();
	}

	private void setSpotAd() {
		// 插播接口调用

		// 加载插播资源
		SpotManager.getInstance(context).loadSpotAds();
		// 插屏出现动画效果，0:ANIM_NONE为无动画，1:ANIM_SIMPLE为简单动画效果，2:ANIM_ADVANCE为高级动画效果
		SpotManager.getInstance(context).setAnimationType(
				SpotManager.ANIM_ADVANCE);
		// 设置插屏动画的横竖屏展示方式，如果设置了横屏，则在有广告资源的情况下会是优先使用横屏图。
		SpotManager.getInstance(context).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		Button spotBtn = (Button) findViewById(R.id.showSpot);
		spotBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// 展示插播广告，可以不调用loadSpot独立使用
				SpotManager.getInstance(context).showSpotAds(context,
						new SpotDialogListener() {
							@Override
							public void onShowSuccess() {
								Log.i("YoumiAdDemo", "展示成功");
							}

							@Override
							public void onShowFailed() {
								Log.i("YoumiAdDemo", "展示失败");
							}

							@Override
							public void onSpotClosed() {
								Log.i("YoumiAdDemo", "展示关闭");
							}

							@Override
							public void onSpotClick(boolean isWebPath) {
								Log.i("YoumiAdDemo", "插屏点击");
							}

						});

			}
		});
	}

	private void showBanner() {

		// 实例化LayoutParams(重要)
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		// 设置广告条的悬浮位置
		layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
		// 实例化广告条
		AdView adView = new AdView(context, AdSize.FIT_SCREEN);
		// 调用Activity的addContentView函数

		// 监听广告条接口
		adView.setAdListener(new AdViewListener() {

			@Override
			public void onSwitchedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "广告条切换");
			}

			@Override
			public void onReceivedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "请求广告成功");
			}

			@Override
			public void onFailedToReceivedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "请求广告失败");
			}
		});
		((Activity) context).addContentView(adView, layoutParams);
	}

	@Override
	public void onBackPressed() {
		// 如果有需要，可以点击后退关闭插播广告。
		if (!SpotManager.getInstance(context).disMiss()) {
			// 弹出退出窗口，可以使用自定义退屏弹出和回退动画,参照demo,若不使用动画，传入-1
			super.onBackPressed();
		}
	}

	@Override
	protected void onStop() {
		// 如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
		SpotManager.getInstance(context).onStop();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		SpotManager.getInstance(context).onDestroy();
		super.onDestroy();
	}

}
