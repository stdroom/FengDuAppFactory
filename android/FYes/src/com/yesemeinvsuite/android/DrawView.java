/**
 * 工程名: FYes
 * 文件名: DrawView.java
 * 包名: com.yesemeinvsuite.android
 * 日期: 2015年12月3日下午2:02:08
 * QQ: 378640336
 *
*/

package com.yesemeinvsuite.android;

import com.fengdu.BaseApplication;
import com.fengdu.ui.activity.FeedbackActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import net.youmi.android.listener.Interface_ActivityListener;
import net.youmi.android.offers.OffersManager;

/**
 * 类名: DrawView <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月3日 下午2:02:08 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class DrawView implements View.OnClickListener{

	private final Activity activity;
	SlidingMenu localSlidingMenu;
	public DrawView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT);//设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//设置要使菜单滑动，触碰屏幕的范围
//		localSlidingMenu.setTouchModeBehind(SlidingMenu.RIGHT);
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//使SlidingMenu附加在Activity右边
		localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.fragment_navi);//设置menu的布局文件
//		localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
		localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {
						
					}
				});
		
		initView();
		return localSlidingMenu;
	}

	private void initView() {
		localSlidingMenu.findViewById(R.id.tv_navi_feedback).setOnClickListener(this);
		localSlidingMenu.findViewById(R.id.tv_navi_intro).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.tv_navi_feedback){
			activity.startActivity(new Intent(activity,FeedbackActivity.class));
			activity.overridePendingTransition(R.anim.anim_activity_right_in, R.anim.anim_activity_left_out);
		}else if(id == R.id.tv_navi_intro){
			OffersManager.getInstance(activity).showOffersWall(new Interface_ActivityListener() {
				@Override
				public void onActivityDestroy(Context context) {
					
				}
			});
		}
	}
	
	private void switchTheme() {
        if (BaseApplication.globalContext.getNightModeSwitch()) {
        	BaseApplication.globalContext.setNightModeSwitch(false);
        } else {
        	BaseApplication.globalContext.setNightModeSwitch(true);
        }

        if (BaseApplication.globalContext.getNightModeSwitch()) {
            activity.setTheme(R.style.AppBaseTheme_Night);
        } else {
            activity.setTheme(R.style.AppBaseTheme_Light);
        }

       activity.recreate();
    }

}
