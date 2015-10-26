/**
 * 工程名: FengDuLibs
 * 文件名: MyFragmentTabHost.java
 * 包名: com.fengdu.ui.menu
 * 日期: 2015年10月26日下午5:46:53
 * QQ: 378640336
 *
*/

package com.fengdu.ui.menu;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 * 类名: MyFragmentTabHost <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月26日 下午5:46:53 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class MyFragmentTabHost extends FragmentTabHost{
	private String mCurrentTag;
	
	private String mNoTabChangedTag;
	
	public MyFragmentTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void onTabChanged(String tag) {
		
		if (tag.equals(mNoTabChangedTag)) {
			setCurrentTabByTag(mCurrentTag);
		} else {
			super.onTabChanged(tag);
			mCurrentTag = tag;
		}
	}
	
	public void setNoTabChangedTag(String tag) {
		this.mNoTabChangedTag = tag;
	}
}

