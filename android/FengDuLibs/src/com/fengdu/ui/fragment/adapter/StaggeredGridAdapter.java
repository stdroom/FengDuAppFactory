package com.fengdu.ui.fragment.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fengdu.R;
import com.fengdu.bean.ImageBean;
import com.fengdu.widgets.stragedview.util.DynamicHeightImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StaggeredGridAdapter extends BaseAdapter{

	private List<ImageBean> mList;
	protected LayoutInflater mInflater;
	private DisplayImageOptions mOption;
	private Context mContext;
	ViewHolder viewHolder;
	public StaggeredGridAdapter(Context context, List<ImageBean> list,DisplayImageOptions option) {
		super();
		this.mList = list;
		mInflater = LayoutInflater.from(context);
		mOption = option;
		mContext = context;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ImageBean bean = mList.get(position);
		viewHolder = new ViewHolder();
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.staggered_grid_child_item, parent,false);

			viewHolder.mImageView = (DynamicHeightImageView) convertView.findViewById(R.id.child_image);
			viewHolder.mTitleTv = (TextView)convertView.findViewById(R.id.news_title);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.mImageView.setImageResource(R.drawable.friends_sends_pictures_no);
		}
		viewHolder.mImageView.setHeightRatio(bean.getImage_height()/bean.getImage_width());
		viewHolder.mTitleTv.setText(bean.getDesc());
		ImageLoader.getInstance().displayImage(bean.getImage_url(), viewHolder.mImageView, mOption);
		
		return convertView;
	}

	public static class ViewHolder{
		public DynamicHeightImageView mImageView;
		private TextView mTitleTv;
	}
	
}
