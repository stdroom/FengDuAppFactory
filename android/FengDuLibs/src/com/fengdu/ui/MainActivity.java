package com.fengdu.ui;


import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.android.AppConfig;

import android.os.Bundle;

public class MainActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.AppBaseTheme_Light);
		setContentView(R.layout.activity_main);
		
	}

}
