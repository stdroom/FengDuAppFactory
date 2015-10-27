/**
 * 工程名: FengDuLibs
 * 文件名: BaseFragmentInterface.java
 * 包名: com.fengdu.interf
 * 日期: 2015年10月27日上午11:34:37
 * QQ: 378640336
 *
*/

package com.fengdu.interf;

import android.view.View;

/**
 * 类名: BaseFragmentInterface <br/>
 * 功能: 基类fragment实现接口. <br/>
 * 日期: 2015年10月27日 上午11:34:37 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public interface BaseFragmentInterface {
	public void initView(View view);
	
	public void initData();
}

