/**
 * 工程名: FengDuLibs
 * 文件名: FavoriteActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年12月17日上午11:02:40
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.android.URLs;
import com.fengdu.ui.fragment.PhotosViewPagerFragment;
import com.mike.aframe.utils.SystemTool;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * 类名: FavoriteActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月17日 上午11:02:40 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class FavoriteActivity extends BaseFragmentActivity implements View.OnClickListener{
	private ImageView backImage;
	private TextView mTitleTv; 
	
	private PhotosViewPagerFragment photsViewPagerFragment;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		
		findViewById();
		showResult();
	}
	
	private void findViewById(){
		backImage = (ImageView)findViewById(R.id.img_title_back);
		backImage.setOnClickListener(this);
		mTitleTv = (TextView)findViewById(R.id.tv_title);
		mTitleTv.setText("我的收藏");
	}


	@Override
	public void onClick(View arg0) {
		 if(arg0.getId() == R.id.img_title_back){
			finish();
		}
	}
	
	private void showResult(){
		if(photsViewPagerFragment == null){
			Bundle bundle = getBundle();
			photsViewPagerFragment = new PhotosViewPagerFragment();
			photsViewPagerFragment.setArguments(bundle);
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.add(R.id.fragment_container, photsViewPagerFragment);
			fragmentTransaction.commit();
		}else{
			photsViewPagerFragment.refreshData(URLs.URL_GET_FAVOR_IMGS);
		}
		
	}
	
	private Bundle getBundle() {
        Bundle bundle = new Bundle();
		bundle.putString("key", URLs.URL_GET_FAVOR_IMGS);
        return bundle;
    }
}

