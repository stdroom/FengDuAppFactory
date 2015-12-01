package com.youmi.android.offerdemo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import net.youmi.android.offers.OffersManager;

/**
 * 本类的作用
 * <ol>
 * <li>当微信向应用发送请求的时候，会回调这里</li>
 * <li>应用发送请求到微信后，有结果时会回调这里</li>
 * </ol>
 * <p>
 * 使用有米分享墙的开发者需要在本类（"应用包名.wxapi.WXEntryActivity"）中，重写{@link #onCreate(Bundle)} 和 {@link #onNewIntent(Intent)}
 * 方法，并在super语句之后调用有米的处理方法
 * <p/>
 * <pre>
 * OffersManager.getInstance(this).handleIntent(getIntent());
 * </pre>
 * <p/>
 * </p>
 *
 * @author youmi
 * @since 2015-05-22 10:46
 */
public class WXEntryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 添加有米的处理方法
		OffersManager.getInstance(this).handleIntent(getIntent());

		// 分享有结果之后会打开这个activity，但是因为这个activity在这个demo中没有界面，因此将会是一片空白的，开发者请酌情判断是否需要finish掉
		finish();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		// 添加有米的处理方法
		OffersManager.getInstance(this).handleIntent(getIntent());

		// 分享有结果之后会打开这个activity，但是因为这个activity在这个demo中没有界面，因此将会是一片空白的，开发者请酌情判断是否需要finish掉
		finish();

	}
}
