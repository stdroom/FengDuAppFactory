/**
 * 工程名: FengDuLibs
 * 文件名: SettingActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年12月18日上午10:41:18
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.android.AppConstant;
import com.fengdu.service.UpgradeService;
import com.fengdu.utils.UpdateManager;
import com.fengdu.widgets.CustomDialog;
import com.fengdu.widgets.SwitchButton;
import com.fengdu.widgets.SwitchButton.OnChangeListener;
import com.mike.aframe.utils.FileUtils;
import com.mike.aframe.utils.PreferenceHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 类名: SettingActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月18日 上午10:41:18 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class SettingActivity extends BaseFragmentActivity
implements View.OnClickListener{
	private ImageView backImage;
	private TextView mTitleTv;
	private TextView mClearCacheTv;
	private RelativeLayout mClearCacheRl;
	private RelativeLayout mCheckVersionRl;
	private RelativeLayout mAboutUsRl;
	
	private CustomDialog dialog;
	
	// 不显示文字
	private SwitchButton noTextButton;
	private Boolean mSwitchNoTextFlag;
	private SwitchButton onlyWifiButton;
	private Boolean mSwitchOnlyWifiFlag;
	private SwitchButton mBootCheck;
	private Boolean mBootCheckFlag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		mSwitchNoTextFlag = PreferenceHelper.readBoolean(this, AppConstant.FLAG_TEXT, AppConstant.FLAG_TEXT, false);
		mSwitchOnlyWifiFlag = PreferenceHelper.readBoolean(this, AppConstant.FLAG_ONLY_WIFI, AppConstant.FLAG_ONLY_WIFI, false);
		mBootCheckFlag = PreferenceHelper.readBoolean(this, AppConstant.FLAG_BOOTED_CHECK, AppConstant.FLAG_BOOTED_CHECK, true);
		findViewById();
	}

	private void findViewById(){
		backImage = (ImageView)findViewById(R.id.img_title_back);
		backImage.setOnClickListener(this);
		mTitleTv = (TextView)findViewById(R.id.tv_title);
		mTitleTv.setText("我的收藏");
		
		mClearCacheRl = (RelativeLayout)findViewById(R.id.mine_clear_cache_rl);
		mClearCacheRl.setOnClickListener(this);
		
		mClearCacheTv = (TextView)findViewById(R.id.clear_cache_tv);
		mClearCacheTv.setText(FileUtils.FormetFileSize(FileUtils.getFolderSize(
				ImageLoader.getInstance().getDiskCache().getDirectory()))+"");
		
		mCheckVersionRl = (RelativeLayout)findViewById(R.id.setting_check_version_rl);
		mCheckVersionRl.setOnClickListener(this);
		mAboutUsRl = (RelativeLayout)findViewById(R.id.mine_about_us_rl);
		mAboutUsRl.setOnClickListener(this);
		
		noTextButton = (SwitchButton)findViewById(R.id.switch_no_text);
		noTextButton.setBoolean(mSwitchNoTextFlag);
		noTextButton.setOnChangeListener(new OnChangeListener() {
			
			@Override
			public void onChange(SwitchButton sb, boolean state) {
				mSwitchNoTextFlag = state;
			}
		});
		onlyWifiButton = (SwitchButton)findViewById(R.id.switch_no_wifi);
		onlyWifiButton.setBoolean(mSwitchOnlyWifiFlag);
		onlyWifiButton.setOnChangeListener(new OnChangeListener() {
			
			@Override
			public void onChange(SwitchButton sb, boolean state) {
				mSwitchOnlyWifiFlag = state;
			}
		});
		mBootCheck = (SwitchButton)findViewById(R.id.switch_boot_check);
		mBootCheck.setBoolean(mBootCheckFlag);
		mBootCheck.setOnChangeListener(new OnChangeListener() {
			
			@Override
			public void onChange(SwitchButton sb, boolean state) {
				mBootCheckFlag = state;
			}
		});
	}


	@Override
	public void onClick(View arg0) {
		 if(arg0.getId() == R.id.img_title_back){
			goBack();
			finish();
		}else if(arg0.getId() == R.id.mine_clear_cache_rl){
			showNoticeDialog();
		}else if(arg0.getId() == R.id.setting_check_version_rl){
			UpdateManager.getUpdateManager().checkAppUpdate(this, true,true);
		}else if(arg0.getId() == R.id.mine_about_us_rl){
			startActivity(new Intent(this,AboutUsActivity.class));
		}
	}
	
	/**
	 * 显示版本更新通知对话框
	 */
	private void showNoticeDialog(){
		dialog = new CustomDialog(this,R.style.custom_dialog);
		View view = View.inflate(this, R.layout.custom_dialog, null);
		dialog.setCanceledOnTouchOutside(false);

		dialog.setView(view);
		((TextView)view.findViewById(R.id.pop_title)).setText("温馨提示");
		((TextView)view.findViewById(R.id.tv_pop_win_text)).setText("清除缓存");


		TextView cancel = (TextView)view.findViewById(R.id.tv_cancel);
		cancel.setText("取消");
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(dialog != null && dialog.isShowing()){
					dialog.cancel();
				}
			}
		});

		TextView commit = (TextView)view.findViewById(R.id.tv_commit);
		commit.setText("确定");
		commit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				ImageLoader.getInstance().clearDiskCache();
				mHandler.sendEmptyMessage(0x1101);
			}
		});
		dialog.show();
	}
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x1101){
				mClearCacheTv.setText(FileUtils.FormetFileSize(FileUtils.getFolderSize(
						ImageLoader.getInstance().getDiskCache().getDirectory()))+"");
			}
		}
		
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){ 
			goBack();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void goBack(){
		PreferenceHelper.write(this, AppConstant.FLAG_TEXT, AppConstant.FLAG_TEXT, mSwitchNoTextFlag);
		PreferenceHelper.write(this, AppConstant.FLAG_ONLY_WIFI, AppConstant.FLAG_ONLY_WIFI, mSwitchOnlyWifiFlag);
		PreferenceHelper.write(this, AppConstant.FLAG_BOOTED_CHECK, AppConstant.FLAG_BOOTED_CHECK, mBootCheckFlag);
		finish();
	}
}

