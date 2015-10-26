package com.fengdu.ui;


import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.ui.menu.MainTabs;
import com.fengdu.ui.menu.MyFragmentTabHost;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends BaseFragmentActivity {

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
		}
	}
}
