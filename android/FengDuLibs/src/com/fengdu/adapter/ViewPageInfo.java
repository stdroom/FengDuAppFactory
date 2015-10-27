/**
 * 工程名: FengDuLibs
 * 文件名: ViewPageInfo.java
 * 包名: com.fengdu.adapter
 * 日期: 2015年10月27日上午11:11:35
 * QQ: 378640336
 *
*/

package com.fengdu.adapter;

import android.os.Bundle;

/**
 * 类名: ViewPageInfo <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年10月27日 上午11:11:35 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class ViewPageInfo {
	public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public final String title;

    public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args) {
    	title = _title;
        tag = _tag;
        clss = _class;
        args = _args;
    }
}

