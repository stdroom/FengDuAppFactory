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
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.android.URLs;
import com.fengdu.bean.SearchWord;
import com.fengdu.ui.fragment.PhotosViewPagerFragment;
import com.fengdu.volley.FastJSONRequest;
import com.fengdu.volley.VolleyManager;
import com.fengdu.widgets.customgrid.TextViewDisplay;
import com.fengdu.widgets.customgrid.TextViewFactory;
import com.fengdu.volley.FastResponse.Listener;
import com.mike.aframe.utils.SystemTool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
	private RelativeLayout mTvContainer;
	private PhotosViewPagerFragment photsViewPagerFragment;
	
	private RequestQueue mQueue;
	
	private ArrayList<String> mKeyWords;
	private TextViewFactory mViewFactory;
	private TextViewDisplay mViewDisplay;
	private ArrayList<TextView> mTextViewArrays;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		findViewById();
		mQueue = Volley.newRequestQueue(this);
		mQueue.start();
		sendRequest();
	}
	
	private void findViewById(){
		btn_search = (EditText)findViewById(R.id.btn_search);
		backImage = (ImageView)findViewById(R.id.back_img);
		clearImage = (ImageView)findViewById(R.id.clear_search_text);
		searchImage = (ImageView)findViewById(R.id.btn_searching);
		searchImage.setOnClickListener(this);
		clearImage.setOnClickListener(this);
		mTvContainer = (RelativeLayout)findViewById(R.id.tv_container_rl);
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
		    	SystemTool.hideKeyBoard(SearchActivity.this);
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
			SystemTool.hideKeyBoard(SearchActivity.this);
			showResult();
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
	
	private void sendRequest(){
		VolleyManager.getInstance().beginSubmitRequest(
				mQueue,
				new FastJSONRequest(URLs.URL_GET_KEY_SEARCH, "", new Listener<JSONObject>(){

					@Override
					public void onResponse(JSONObject obj, String executeMethod, String flag, boolean dialogFlag) {
						if(obj!=null && obj.containsKey("status") && obj.getInteger("status")==200){
							ArrayList<SearchWord> searchWord = (ArrayList<SearchWord>)JSONArray.parseArray(obj.getString("results"),SearchWord.class);
							mKeyWords = new ArrayList<String>();
							for(SearchWord bean : searchWord){
								mKeyWords.add(bean.getWord());
							}
							mHandler.sendEmptyMessage(0x1001);
						}else{
						}
					}
					
				},new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						
					}
				}));
	}
	
	Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0x1001:
				mViewFactory = new TextViewFactory(R.drawable.step_third_circle_rectangle_green_bg,R.color.text_color_green);
				mViewDisplay = new TextViewDisplay();
				mTextViewArrays = mViewDisplay.displayAtRelativeLayout(mTvContainer, 
						mViewFactory.createFactory(SearchActivity.this, mKeyWords), 
						SearchActivity.this, 
						getScreenWidth()-(int)getResources().getDimension(R.dimen.custom_textview_gaps_x));
				if(mTextViewArrays!=null){
					for(int i = 0 ;i < mTextViewArrays.size() ;i++){
						mTextViewArrays.get(i).setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								btn_search.setText(((TextView)arg0).getText());
								showResult();
							}
						});
					}
				}
				break;
			}
		}
	};
	
	public int getScreenWidth(){
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
	}
	
	
}

