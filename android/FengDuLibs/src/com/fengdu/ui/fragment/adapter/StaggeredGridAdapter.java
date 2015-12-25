package com.fengdu.ui.fragment.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fengdu.R;
import com.fengdu.android.AppConstant;
import com.fengdu.bean.ImageBean;
import com.fengdu.widgets.strageview.DynamicHeightImageView;
import com.mike.aframe.utils.PreferenceHelper;
import com.mike.aframe.utils.StringUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StaggeredGridAdapter extends BaseAdapter{

	private List<ImageBean> mList;
	protected LayoutInflater mInflater;
	private DisplayImageOptions mOption;
	private Context mContext;
	private Boolean noText;
	ViewHolder viewHolder;
	public StaggeredGridAdapter(Context context, List<ImageBean> list,DisplayImageOptions option) {
		super();
		this.mList = list;
		mInflater = LayoutInflater.from(context);
		mOption = option;
		mContext = context;
		noText = PreferenceHelper.readBoolean(context, AppConstant.FLAG_TEXT, AppConstant.FLAG_TEXT, false);
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
			viewHolder.mTimeTv = (TextView)convertView.findViewById(R.id.news_time);
			viewHolder.mPageNum = (TextView)convertView.findViewById(R.id.pageNum);
			viewHolder.mTitleLl = (LinearLayout)convertView.findViewById(R.id.title_ll);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.mImageView.setImageResource(R.drawable.friends_sends_pictures_no);
		}
		viewHolder.mTitleTv.setText(bean.getDesc());
		viewHolder.mTimeTv.setText(StringUtils.friendly_time(bean.getUpdatedTime()));
		viewHolder.mPageNum.setText(bean.getTotalNum()+"P");
		if(noText){
			viewHolder.mTitleLl.setVisibility(View.GONE);
		}else{
			viewHolder.mTitleLl.setVisibility(View.VISIBLE);
		}
		if(!StringUtils.isEmpty(bean.getThumbYun())){
			viewHolder.mImageView.setHeightRatio(bean.getThumbnail_height()/bean.getThumbnail_width());
			ImageLoader.getInstance().displayImage("http://file.bmob.cn/"+bean.getThumbYun(), viewHolder.mImageView, mOption);
		}else{
			viewHolder.mImageView.setHeightRatio(bean.getImage_height()/bean.getImage_width());
			ImageLoader.getInstance().displayImage(bean.getImage_url(), viewHolder.mImageView, mOption);
		}
		
		return convertView;
	}

	public static class ViewHolder{
		public DynamicHeightImageView mImageView;
		private TextView mTitleTv;
		private TextView mTimeTv;
		private TextView mPageNum;
		private LinearLayout mTitleLl;
	}
	
}
