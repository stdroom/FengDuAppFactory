package com.fengdu.ui;


import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.android.URLs;
import com.fengdu.ui.menu.MainTabs;
import com.fengdu.ui.menu.MyFragmentTabHost;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

/**
 * 类名: MainActivity <br/>
 * 主页面Activity. <br/>
 * 日期: 2015年10月26日 下午8:36:50 <br/>
 *
 * @author leixun
 * @version 
 */
public class MainActivity extends BaseFragmentActivity implements OnTouchListener {			
	long exitTime;

	MyFragmentTabHost mTabHost = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.AppBaseTheme_Light);
		setContentView(R.layout.activity_main);
		findViewById();
		initTabs();
	}
	
	private void findViewById(){
		mTabHost = (MyFragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);   
	}

	private void initTabs(){
		MainTabs[] tabs = MainTabs.values();
		final int size = tabs.length;
		
		for(int i = 0 ; i < size ; i ++){
			MainTabs mainTab = tabs[i];
			TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                    null);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            Bundle bundle = new Bundle();
            bundle.putString("key", URLs.HOST+"news/2/");
            mTabHost.addTab(tab, mainTab.getClz(), bundle);

//            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/** 一个鄙人感觉不错的退出体验*/
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            exitTime = System.currentTimeMillis();   
	            Toast.makeText(MainActivity.this, "再按一次退出"+getResources().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
		return super.onKeyDown(keyCode, event);
	}
}
