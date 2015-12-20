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
import com.fengdu.service.UpgradeService;
import com.fengdu.widgets.CustomDialog;
import com.mike.aframe.utils.FileUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	
	private CustomDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
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
	}


	@Override
	public void onClick(View arg0) {
		 if(arg0.getId() == R.id.img_title_back){
			finish();
		}else if(arg0.getId() == R.id.mine_clear_cache_rl){
			showNoticeDialog();
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
}

