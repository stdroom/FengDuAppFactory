/**
 * 工程名: FengDuLibs
 * 文件名: ThemeSwitchUtils.java
 * 包名: com.fengdu.utils
 * 日期: 2015年11月14日上午11:53:27
 * QQ: 378640336
 *
*/

package com.fengdu.utils;

import com.fengdu.BaseApplication;
import com.fengdu.R;

/**
 * 类名: ThemeSwitchUtils <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月14日 上午11:53:27 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class ThemeSwitchUtils {

	public static int getTitleReadedColor() {
        if (BaseApplication.globalContext.getNightModeSwitch()) {
            return R.color.night_has_read_text_color;
        } else {
            return R.color.day_has_read_text_color;
        }
    }

    public static int getTitleUnReadedColor() {
        if (BaseApplication.globalContext.getNightModeSwitch()) {
            return R.color.night_not_read_text_color;
        } else {
            return R.color.day_not_read_text_color;
        }
    }
}

