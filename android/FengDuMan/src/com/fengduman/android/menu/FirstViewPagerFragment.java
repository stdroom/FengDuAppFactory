/**
 * 工程名: FengDuLibs
 * 文件名: FirstViewPagerFragment.java
 * 包名: com.fengdu.ui.fragment
 * 日期: 2015年10月27日上午11:28:23
 * QQ: 378640336
 *
*/

package com.fengduman.android.menu;


import com.fengdu.BaseViewPagerFragment;
import com.fengdu.adapter.ViewPageFragmentAdapter;
import com.fengdu.android.URLs;
import com.fengdu.interf.OnTabReselectListener;
import com.fengdu.ui.fragment.PhotosViewPagerFragment;
import com.fengduman.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

/**
 * 类名: FirstViewPagerFragment <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月27日 上午11:28:23 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class FirstViewPagerFragment extends BaseViewPagerFragment implements
	OnTabReselectListener{
	@Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		String[] title = getResources().getStringArray(
                R.array.first_viewpage_arrays);
        adapter.addTab(title[0], "news1", PhotosViewPagerFragment.class,
                getBundle("?cataid=1103"));
        adapter.addTab(title[1], "news_week1", PhotosViewPagerFragment.class,
                getBundle("?cataid=1104"));
        adapter.addTab(title[2], "news3", PhotosViewPagerFragment.class,
                getBundle("?cataid=1105"));
        adapter.addTab(title[3], "news_week4", PhotosViewPagerFragment.class,
                getBundle("?cataid=1107"));
    }

    private Bundle getBundle(String urls) {
        Bundle bundle = new Bundle();
		bundle.putString("key", URLs.URL_GET_IMAGE+urls);
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

