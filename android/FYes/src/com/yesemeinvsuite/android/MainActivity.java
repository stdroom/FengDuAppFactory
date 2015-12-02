/**
 * 工程名: FengDuMan
 * 文件名: MainActivity.java
 * 包名: com.fengduman.android
 * 日期: 2015年10月28日下午4:01:50
 * QQ: 378640336
 *
*/

package com.yesemeinvsuite.android;

import com.fengdu.android.URLs;
import com.fengdu.ui.BaseSecondMainActivity;
import com.fengdu.ui.fragment.PhotosViewPagerFragment;
import com.yesemeinvsuite.android.menu.MainTabs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class MainActivity extends BaseSecondMainActivity{

	@Override
	protected void initFragment() {
		mFragmentList.clear();
		int length = mTitleList.size();
		for(int i = 1 ; i <= length ; i++){
			Bundle bundle = null;
			switch(i){
			case 1:
				bundle = getBundle("?cataid=1");
				break;
			case 2:
				bundle = getBundle("?cataid=2");
				break;
			case 3:
				bundle = getBundle("?cataid=3");
				break;
			case 4:
				bundle = getBundle("?cataid=4");
				break;
			case 5:
				bundle = getBundle("?cataid=5");
				break;
			case 6:
				bundle = getBundle("?cataid=6");
				break;
				default:
					bundle = getBundle("?cataid=1103");
			}
			
			Fragment fragment = new PhotosViewPagerFragment();
			fragment.setArguments(bundle);
			mFragmentList.add(fragment);
		}
		mFragmentPagerAdapter.setFragments(mFragmentList);
	}
	
	private Bundle getBundle(String urls) {
        Bundle bundle = new Bundle();
		bundle.putString("key", URLs.URL_GET_IMAGE+urls);
        return bundle;
    }
	

}

