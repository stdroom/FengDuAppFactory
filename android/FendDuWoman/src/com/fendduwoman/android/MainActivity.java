/**
 * 工程名: FengDuMan
 * 文件名: MainActivity.java
 * 包名: com.fengduman.android
 * 日期: 2015年10月28日下午4:01:50
 * QQ: 378640336
 *
*/

package com.fendduwoman.android;

import com.fendduwoman.android.menu.MainTabs;
import com.fengdu.android.URLs;
import com.fengdu.ui.BaseMainActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

/**
 * 类名: MainActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月28日 下午4:01:50 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class MainActivity extends BaseMainActivity{

	@Override
	protected void initTabs() {
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

//          mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
		}
	}

}

