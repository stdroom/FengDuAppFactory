/**
 * 工程名: FengDuLibs
 * 文件名: SearchActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年11月26日下午2:56:33
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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

/**
 * 类名: SearchActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月26日 下午2:56:33 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class SearchActivity extends BaseFragmentActivity implements View.OnClickListener{
	
	private EditText btn_search;
	private String mSearchText;
	private ImageView backImage;
	private ImageView clearImage;
	private ImageView searchImage;
	
	private PhotosViewPagerFragment photsViewPagerFragment;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		findViewById();
	}
	
	private void findViewById(){
		btn_search = (EditText)findViewById(R.id.btn_search);
		backImage = (ImageView)findViewById(R.id.back_img);
		clearImage = (ImageView)findViewById(R.id.clear_search_text);
		searchImage = (ImageView)findViewById(R.id.btn_searching);
		searchImage.setOnClickListener(this);
		clearImage.setOnClickListener(this);
		backImage.setOnClickListener(this);
		
		btn_search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				
			}
		});
		
		btn_search.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		    	Toast.makeText(SearchActivity.this, "点击了键盘搜索", Toast.LENGTH_SHORT).show();
		    	showResult();
				return true;
		    }
		});
	}

	private void doSearch(){
		
	}
	
	private void hideSoftKeyBoard(){
//		((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).
//		hideSoftInputFromWindow(input.getWindowToken(), 0);
	}

	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.btn_searching){
			showResult();
			Toast.makeText(SearchActivity.this, "点击了搜索", Toast.LENGTH_SHORT).show();
		}else if(arg0.getId() == R.id.clear_search_text){
			btn_search.setText("");
			mSearchText = "";
		}else if(arg0.getId() == R.id.back_img){
			finish();
		}
	}
	
	private void showResult(){
		try {
			mSearchText = btn_search.getText().toString();
			mSearchText = URLEncoder.encode(mSearchText, "UTF-8");
			mSearchText = URLEncoder.encode(mSearchText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(photsViewPagerFragment == null){
			Bundle bundle = getBundle("?keyname="+mSearchText);
			photsViewPagerFragment = new PhotosViewPagerFragment();
			photsViewPagerFragment.setArguments(bundle);
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.add(R.id.fragment_container, photsViewPagerFragment);
			fragmentTransaction.commit();
		}else{
			photsViewPagerFragment.refreshData(URLs.URL_SEARCH_IMAGE+"?keyname="+mSearchText);
		}
		
	}
	
	private Bundle getBundle(String urls) {
        Bundle bundle = new Bundle();
		bundle.putString("key", URLs.URL_SEARCH_IMAGE+urls);
        return bundle;
    }
}

