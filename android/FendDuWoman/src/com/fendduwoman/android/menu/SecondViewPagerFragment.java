/**
 * 工程名: FengDuLibs
 * 文件名: SecondViewPagerFragment.java
 * 包名: com.fengdu.ui.fragment
 * 日期: 2015年10月27日下午4:42:42
 * QQ: 378640336
 *
*/

package com.fendduwoman.android.menu;

import com.fendduwoman.android.R;
import com.fengdu.BaseViewPagerFragment;
import com.fengdu.adapter.ViewPageFragmentAdapter;
import com.fengdu.android.URLs;
import com.fengdu.interf.OnTabReselectListener;
import com.fengdu.ui.fragment.ArticleFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 类名: SecondViewPagerFragment <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月27日 下午4:42:42 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class SecondViewPagerFragment extends BaseViewPagerFragment implements
	OnTabReselectListener{
	
	@Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		 String[] title = getResources().getStringArray(
	                R.array.second_viewpage_arrays);
	        adapter.addTab(title[0], "news", ArticleFragment.class,
	                getBundle("/dashan/"));
	        adapter.addTab(title[1], "news_week", ArticleFragment.class,
	                getBundle("/yuehui/"));
	        adapter.addTab(title[2], "latest_blog", ArticleFragment.class,
	                getBundle("/pua/"));
        
    }

    private Bundle getBundle(String urls) {
        Bundle bundle = new Bundle();
        bundle.putString("key", URLs.HOST+urls);
        return bundle;
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onTabReselect() {
        try {
            int currentIndex = mViewPager.getCurrentItem();
            Fragment currentFragment = getChildFragmentManager().getFragments()
                    .get(currentIndex);
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
            }
        } catch (NullPointerException e) {
        }
    }
}

