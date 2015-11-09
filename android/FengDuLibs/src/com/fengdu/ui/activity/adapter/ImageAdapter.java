/**
 * 工程名: FengDuLibs
 * 文件名: ImageAdapter.java
 * 包名: com.fengdu.ui.fragment.adapter
 * 日期: 2015年11月7日下午5:48:31
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity.adapter;

import java.util.ArrayList;
import java.util.List;

import com.fengdu.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import uk.co.senab.photoview.PhotoView;

/**
 * 类名: ImageAdapter <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年11月7日 下午5:48:31 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class ImageAdapter extends PagerAdapter{

	private LayoutInflater inflater;
	private ArrayList<String> list;
	private Context mContext;
	
	public ImageAdapter(ArrayList<String> list,Context context) {
		inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = inflater.inflate(R.layout.viewpager_item, view, false);
		assert imageLayout != null;
		PhotoView imageView = (PhotoView) imageLayout.findViewById(R.id.photoView);

		ImageLoader.getInstance().displayImage(list.get(position), imageView);
		view.addView(imageLayout, 0);
		return imageLayout;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

}
