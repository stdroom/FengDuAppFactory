/**
 * 工程名: FengDuLibs
 * 文件名: MyFragmentAdapter.java
 * 包名: com.fengdu.ui.adapter
 * 日期: 2015年11月12日下午3:54:14
 * QQ: 378640336
 *
*/

package com.fengdu.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

/**
 * 类名: MyFragmentAdapter <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月12日 下午3:54:14 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class MyFragmentAdapter extends FragmentPagerAdapter{

	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();;
	private final FragmentManager fm;
	public MyFragmentAdapter(FragmentManager fm) {
		super(fm);
        this.fm = fm;
	}

    public MyFragmentAdapter(FragmentManager fm,
            ArrayList<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    public void appendList(ArrayList<Fragment> fragment) {
        fragments.clear();
        if (!fragments.containsAll(fragment) && fragment.size() > 0) {
            fragments.addAll(fragment);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            List<Fragment> fs = fm.getFragments();
            if(fs!=null){
            	for (Fragment f : fs) {
            		ft.remove(f);
            		f = null;
            	}
            	ft.commit();
            	ft = null;
            	fs = null;
            	fm.executePendingTransactions();
            }
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 这里Destroy的是Fragment的视图层次，并不是Destroy Fragment对象
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (fragments.size() <= position) {
            position = position % fragments.size();
        }
        Object obj = super.instantiateItem(container, position);
        return obj;
    }

}

