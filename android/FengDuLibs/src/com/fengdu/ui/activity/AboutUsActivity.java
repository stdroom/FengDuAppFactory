/**
 * 工程名: FengDuLibs
 * 文件名: AboutUsActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年12月26日上午10:57:05
 * Copyright (c) 2015, 暴走兄弟 All Rights Reserved.
 * 
 * Mail: leixun33@163.com
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.widgets.CustomDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 类名: AboutUsActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月26日 上午10:57:05 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class AboutUsActivity extends BaseFragmentActivity implements View.OnClickListener{
	private ImageView backImage;
	private TextView mTitleTv;
	
	private RelativeLayout mMianzeShengmingRl;
	private CustomDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		backImage = (ImageView)findViewById(R.id.img_title_back);
		backImage.setOnClickListener(this);
		mTitleTv = (TextView)findViewById(R.id.tv_title);
		mTitleTv.setText("关于我们");
		mMianzeShengmingRl = (RelativeLayout)findViewById(R.id.mianze_pub_rl);
		mMianzeShengmingRl.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.img_title_back){
			finish();
		}else if(v.getId() == R.id.mianze_pub_rl){
			showNoticeDialog();
		}
	}
	
	/**
	 * 显示版本更新通知对话框
	 */
	private void showNoticeDialog(){
		dialog = new CustomDialog(this,R.style.custom_dialog);
		View view = View.inflate(this, R.layout.custom_single_dialog, null);
		dialog.setCanceledOnTouchOutside(false);

		dialog.setView(view);
		((TextView)view.findViewById(R.id.pop_title)).setText("免责声明");
		((TextView)view.findViewById(R.id.tv_pop_win_text)).setText("\"夜色美女套图\"内容来源于网络，我们尊重他人知识产权及合法权益，用户也应该尊重他人知识产权及合法权益，在使用本软件的过程中如果您认为您的著作权、信心网络传播权被侵犯，请通过QQ或者用户反馈和我们取得联系，出具权利通知（保证权利通知并未失实，否则相关法律责任由出具人承担），并详细说明侵权的内容，核实后我们将删除被控内容断开相关链接。");

		TextView commit = (TextView)view.findViewById(R.id.tv_commit);
		commit.setText("确定");
		commit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		dialog.show();
	}

}

