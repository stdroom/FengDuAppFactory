/**
 * 工程名: FengDuLibs
 * 文件名: DrawerView.java
 * 包名: com.fengdu.ui.slide
 * 日期: 2015年11月9日下午4:37:42
 * QQ: 378640336
 *
*/

package com.fengdu.ui.slide;

import com.fengdu.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
/**
 * 类名: DrawerView <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月9日 下午4:37:42 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class DrawerView implements View.OnClickListener{

	private final Activity activity;
	SlidingMenu localSlidingMenu;
//	private SwitchButton night_mode_btn;
	private TextView night_mode_text;
	private RelativeLayout setting_btn;
	public DrawerView(Activity activity) {
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
//		localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//设置menu的布局文件
//		localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
		localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {
						
					}
				});
		
		initView();
		return localSlidingMenu;
	}

	private void initView() {
//		night_mode_btn = (SwitchButton)localSlidingMenu.findViewById(R.id.night_mode_btn);
//		night_mode_text = (TextView)localSlidingMenu.findViewById(R.id.night_mode_text);
//		night_mode_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
//				if(isChecked){
//					night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
//				}else{
//					night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
//				}
//			}
//		});
//		night_mode_btn.setChecked(false);
//		if(night_mode_btn.isChecked()){
//			night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
//		}else{
//			night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
//		}
		
		setting_btn =(RelativeLayout)localSlidingMenu.findViewById(R.id.setting_btn);
		setting_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.setting_btn:
////			activity.startActivity(new Intent(activity,SettingsActivity.class));
////			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//			break;
//
//		default:
//			break;
//		}
	}

}

