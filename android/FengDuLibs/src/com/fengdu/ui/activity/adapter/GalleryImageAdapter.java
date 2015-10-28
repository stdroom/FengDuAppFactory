package com.fengdu.ui.activity.adapter;

import java.util.ArrayList;
import java.util.List;

import com.fengdu.R;
import com.fengdu.bean.ImageBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter{
	
	private ArrayList<ImageBean> mList ;
	private Context mContext;
	private DisplayImageOptions mOptions;
	private int mGalleryItemBackground;
	
	public GalleryImageAdapter(Context context,ArrayList<ImageBean> list,DisplayImageOptions options) {
		super();
		// TODO Auto-generated constructor stub
		mList = list;
		mContext = context;
		mOptions = options;
		
		//ʵ��gallery����
		TypedArray typedArray = mContext.obtainStyledAttributes(R.styleable.Gallery);
		mGalleryItemBackground = typedArray.getResourceId(
		R.styleable.Gallery_android_galleryItemBackground, 0); 
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ImageBean bean = mList.get(position);
		ViewHolder viewHolder = new ViewHolder();
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gallery_child_item, parent,false);
			viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.child_image);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.mImageView.setImageResource(R.drawable.friends_sends_pictures_no);
		}

		viewHolder.mImageView.setBackgroundResource(mGalleryItemBackground);
		ImageLoader.getInstance().displayImage(bean.getThumbnail(), viewHolder.mImageView, mOptions);
		return convertView;
	}

	public static class ViewHolder{
		public ImageView mImageView;
	}
}
