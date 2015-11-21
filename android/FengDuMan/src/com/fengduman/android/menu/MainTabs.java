/**
 * 工程名: FengDuLibs
 * 文件名: MainTabs.java
 * 包名: com.fengdu.ui.menu
 * 日期: 2015年10月26日下午2:37:12
 * QQ: 378640336
 *
*/

package com.fengduman.android.menu;

import com.fengdu.ui.fragment.PhotosViewPagerFragment;
import com.fengduman.android.R;

/**
 * 类名: MainTabs <br/>
 * 功能: 主菜单. <br/>
 * 日期: 2015年10月26日 下午2:37:12 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public enum MainTabs {
	ONE(0, R.string.main_tab_name_one, R.drawable.tab_icon_one,
			SecondViewPagerFragment.class),
	TWO(0, R.string.main_tab_name_two, R.drawable.tab_icon_two,
			SecondViewPagerFragment.class),
	THREE(0, R.string.main_tab_name_three, R.drawable.tab_icon_three,
			FirstViewPagerFragment.class),
	FOUR(0, R.string.main_tab_name_four, R.drawable.tab_icon_four,
			FirstViewPagerFragment.class);

	private int idx;
	private int resName;
	private int resIcon;
	private Class<?> clz;

	private MainTabs(int idx, int resName, int resIcon, Class<?> clz) {
		this.idx = idx;
		this.resName = resName;
		this.resIcon = resIcon;
		this.clz = clz;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getResName() {
		return resName;
	}

	public void setResName(int resName) {
		this.resName = resName;
	}

	public int getResIcon() {
		return resIcon;
	}

	public void setResIcon(int resIcon) {
		this.resIcon = resIcon;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
}

