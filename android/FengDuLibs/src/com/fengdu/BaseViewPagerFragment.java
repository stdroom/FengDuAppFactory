/**
 * 工程名: FengDuLibs
 * 文件名: BaseViewPagerFragment.java
 * 包名: com.fengdu
 * 日期: 2015年10月27日上午10:41:51
 * QQ: 378640336
 *
*/

package com.fengdu;

import com.fengdu.adapter.ViewPageFragmentAdapter;
import com.fengdu.ui.empty.EmptyLayout;
import com.fengdu.widgets.PagerSlidingTabStrip;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类名: BaseViewPagerFragment <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月27日 上午10:41:51 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public abstract class BaseViewPagerFragment extends BaseFragment{

	protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mTabsAdapter;
    protected EmptyLayout mErrorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpage_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTabStrip = (PagerSlidingTabStrip) view
                .findViewById(R.id.pager_tabstrip);

        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);

        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
        // if (savedInstanceState != null) {
        // int pos = savedInstanceState.getInt("position");
        // mViewPager.setCurrentItem(pos, true);
        // }
    }
    
    protected void setScreenPageLimit() {
    }

    // @Override
    // public void onSaveInstanceState(Bundle outState) {
    // //No call for super(). Bug on API Level > 11.
    // if (outState != null && mViewPager != null) {
    // outState.putInt("position", mViewPager.getCurrentItem());
    // }
    // //super.onSaveInstanceState(outState);
    // }

    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
}

