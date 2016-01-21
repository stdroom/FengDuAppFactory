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
import com.mike.aframe.utils.SystemTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;

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
	private OnPhotoTapListener clickListenerCallback;
	private DisplayImageOptions mOptions;
	public ImageAdapter(ArrayList<String> list,Context context,OnPhotoTapListener listener) {
		this.clickListenerCallback = listener;
		inflater = LayoutInflater.from(context);
		this.list = list;
		mOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.considerExifParams(true)
				.build();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return list!=null? list.size():0;
	}

	@Override
	public Object instantiateItem(ViewGroup view, final int position) {
		View imageLayout = inflater.inflate(R.layout.viewpager_item, view, false);
		assert imageLayout != null;
		final PhotoView imageView = (PhotoView) imageLayout.findViewById(R.id.photoView);
		final ImageView defaultImg = (ImageView)imageLayout.findViewById(R.id.default_img);
		final TextView tv = (TextView)imageLayout.findViewById(R.id.ratio);
		imageView.setOnPhotoTapListener(clickListenerCallback);
		defaultImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ImageLoader.getInstance().displayImage(list.get(position), imageView,mOptions,
						new ImageLoadingListener() {
					
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						defaultImg.setImageResource(R.drawable.meizi_default);
						defaultImg.setVisibility(View.VISIBLE);
					}
					
					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						defaultImg.setImageResource(R.drawable.loading_fail);
//						Toast.makeText(mContext, list.get(position), Toast.LENGTH_LONG).show();
						defaultImg.setVisibility(View.VISIBLE);
					}
					
					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						tv.setText(loadedImage.getWidth()+"x"+loadedImage.getHeight());
						defaultImg.setVisibility(View.GONE);
					}
					
					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						
					}
				});
			}
		});
		ImageLoader.getInstance().displayImage(list.get(position), imageView,
				new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				defaultImg.setImageResource(R.drawable.meizi_default);
				defaultImg.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				defaultImg.setImageResource(R.drawable.loading_fail);
				defaultImg.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				tv.setText(loadedImage.getWidth()+"x"+loadedImage.getHeight());
				defaultImg.setVisibility(View.GONE);
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				
			}
		});
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

