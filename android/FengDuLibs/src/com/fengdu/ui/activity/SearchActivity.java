/**
 * 工程名: FengDuLibs
 * 文件名: SearchActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年11月26日下午2:56:33
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;

import android.os.Bundle;
import android.support.v7.widget.SearchView;

/**
 * 类名: SearchActivity <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月26日 下午2:56:33 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class SearchActivity extends BaseFragmentActivity{
	
	private SearchView d;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_item);
	}

}

