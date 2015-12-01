/**
 * 工程名: FengDuLibs
 * 文件名: PhotosViewPagerFragment.java
 * 包名: com.fengdu.ui.fragment
 * 日期: 2015年10月26日下午2:38:19
 * QQ: 378640336
 *
*/

package com.fengdu.ui.fragment;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.Volley;
import com.fengdu.BaseFragment;
import com.fengdu.R;
import com.fengdu.android.URLs;
import com.fengdu.bean.ImageBean;
import com.fengdu.ui.activity.PictureItemActivity;
import com.fengdu.ui.fragment.adapter.StaggeredGridAdapter;
import com.fengdu.volley.FastJSONRequest;
import com.fengdu.volley.VolleyManager;
import com.fengdu.volley.FastResponse.Listener;
import com.fengdu.widgets.pullview.PullToRefreshBase;
import com.fengdu.widgets.pullview.PullToRefreshBase.OnRefreshListener;
import com.fengdu.widgets.pullview.PullToRefreshStaggeredGridView;
import com.fengdu.widgets.strageview.StaggeredGridView;
import com.fengdu.widgets.strageview.StaggeredGridView.OnItemClickListener;
import com.fengdu.widgets.strageview.StaggeredGridView.OnLoadmoreListener;
import com.mike.aframe.MKLog;
import com.mike.aframe.utils.SystemTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

/**
 * 类名: PhotosViewPagerFragment <br/>
 * 功能: 浏览图片的framgnet. <br/>
 * 日期: 2015年10月26日 下午2:38:19 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class PhotosViewPagerFragment extends BaseFragment{

	private PullToRefreshStaggeredGridView mGridView;
	private ArrayList<ImageBean> list;
	private StaggeredGridAdapter mAdapter;
	private DisplayImageOptions mOptions;
	private TextView mActionText;
    
	private String urls = "";
	RequestQueue mQueue= null;
	int pageSize = 0;
	
	private boolean isRequest = false;
	
	private View view = null;
	private FrameLayout mAdContainer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.friends_sends_pictures_no)
		.showImageForEmptyUri(R.drawable.friends_sends_pictures_no)
		.showImageOnFail(R.drawable.friends_sends_pictures_no)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();
		
		Bundle args = getArguments();
        if (null !=  args) {
            if (args.containsKey("key")) {
                this.urls = args.getString("key");
            }
        }
        mQueue = Volley.newRequestQueue(getActivity());
		mQueue.start();
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view == null){
			view = inflater.inflate(R.layout.staggered_grid, container,false);
		}
		mAdContainer = (FrameLayout)view.findViewById(R.id.frame_ad);
		mLoadingLayout = (RelativeLayout)view.findViewById(R.id.layout_loading_bar);
		mNoNetLayout = (RelativeLayout)view.findViewById(R.id.layout_refresh_onclick);
		mNoNetLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pageSize = 1;
				initData(urls+"&page="+pageSize+"&pageSize=20");
			}
		});
		mGridView = (PullToRefreshStaggeredGridView) view.findViewById(R.id.pull_grid_view);
		mGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        View footerView;
        footerView = inflater.inflate(R.layout.layout_loading_footer, null);
        mGridView.getRefreshableView().setFooterView(footerView);
        mGridView.setAdapter(mAdapter);
		((StaggeredGridView)mGridView.getRefreshableView()).setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(),PictureItemActivity.class);
				Bundle bundle = new Bundle();
				ImageBean bean = list.get(position);
				bundle.putSerializable("key", bean);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		mGridView.setOnLoadmoreListener(new OnLoadmoreListener() {
			@Override
			public void onLoadmore() {
				if(!isRequest){
					pageSize = pageSize+1;
					String url = urls+"&page="+pageSize+"&pageSize=20";
					initData(url);
				}else{
				}
			}
		});
		mGridView.setOnRefreshListener(new OnRefreshListener<StaggeredGridView>() {

			@Override
			public void onRefresh(PullToRefreshBase<StaggeredGridView> refreshView) {
				initData(urls+"&page="+1+"&pageSize=20");
			}
		});
		MKLog.d("PhotosViewPagerFragment", "onCreateView");
		showBanner();
		return view;
	}

	@Override
	public void onResume(){
		lazyLoad();
		super.onResume();
	}
	
	public void lazyLoad(){
		if(mAdapter!=null && mAdapter.getCount()>0 && list!=null && list.size()>0){
			return;
		}else{
			list = new ArrayList<ImageBean>();
			pageSize = 1;
			initData(urls+"&page="+pageSize+"&pageSize=20");
		}
	}
	
	public void initData(final String urls){
		if(!isRequest){
			isRequest = true;
			if(list==null || list.size()==0){
				setLoadingVisible(true);
				setNoNetVisible(false);
				mGridView.setVisibility(View.GONE);
			}
			VolleyManager.getInstance().beginSubmitRequest(
					mQueue, 
					new FastJSONRequest(urls, "", new Listener<JSONObject>() {
						
						@Override
						public void onResponse(JSONObject obj,
								String executeMethod, String flag,
								boolean dialogFlag) {
							if (obj!=null){
								
								JSONArray arrays = obj.getJSONArray("data");
								int size = arrays!=null ? arrays.size():0;
								
								MKLog.d(PhotosViewPagerFragment.class.getSimpleName(), urls+"");
								MKLog.d(PhotosViewPagerFragment.class.getSimpleName(), obj+"");
								for(int i = 0 ; i < size ;i++){
									ImageBean bean = new ImageBean();
									JSONObject json = (JSONObject)arrays.get(i);
									bean.setDesc(json.getString("title"));
									bean.setImage_height(json.getIntValue("height"));
									bean.setImage_width(json.getIntValue("width"));
									bean.setImage_url(json.getString("thumbNail"));
									bean.setTotalNum(json.getIntValue("pageNum"));
									String imgPaths = json.getString("imgPaths");
									
									bean.setUpdatedTime(SystemTool.getTimFromStamps(json.getLong("updateed_at"))+"");
									if(!"".equals(imgPaths)){
										String[] img = imgPaths.split(";");
										int length = img.length;
										ArrayList<String> paths = new ArrayList<String>();
										for(int j=0 ;j < length;j++){
											String path = URLs.IMAGE_HOST+json.getString("cata_id")+"/"+img[j];
											paths.add(path);
										}	
										bean.setPagePaths(paths);
									}else{
										
										imgPaths = json.getString("srcImgpaths");
										if(!"".equals(imgPaths)){
											String[] img = imgPaths.split(";");
											int length = img.length;
											ArrayList<String> paths = new ArrayList<String>();
											for(int j=0 ;j < length;j++){
												String path = img[j];
												paths.add(path);
											}
											bean.setPagePaths(paths);
										}
									}
									list.add(bean);
								}
								myHandler.sendEmptyMessage(0x110);
							} else {
								MKLog.e(getClass().getName(), "can't get data"); 
								myHandler.sendEmptyMessage(0x111);
							}
						}
					}, new ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							MKLog.d("", error.toString());
							myHandler.sendEmptyMessage(0x112);
						}
					}));
		}else{	// 直接结束
			
		}
	}
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			isRequest = false;
			if(msg.what == 0x110){
				if(list != null){
					if(mAdapter==null){
						mAdapter = new StaggeredGridAdapter(getActivity(), list, mOptions);
						mGridView.setAdapter(mAdapter);
					}
					mAdapter.notifyDataSetChanged();
					mGridView.getRefreshableView().showFooterView();
					mGridView.onRefreshComplete();
					mGridView.setVisibility(View.VISIBLE);
					setNoNetVisible(false);
					setLoadingVisible(false);
					//图片路径的list
				}else{
//					new ImageLoadAsyncTask(getActivity()).execute();
				}
			}else if(msg.what==0x111){// 请求正常无数据	
				
			}else if(msg.what==0x112){// 请求失败
				if(list== null || list.size()==0){
					setNoNetVisible(true);
					mGridView.setVisibility(View.GONE);
					setLoadingVisible(false);
				}
				mGridView.getRefreshableView().showFooterView();
				mGridView.onRefreshComplete();
			}
		}
		
	};
	
	public void refreshData(String urls){
		// 1 初始化数据
		this.urls = urls;
		list = new ArrayList<ImageBean>();
		pageSize = 1;
		initData(urls+"&page="+pageSize+"&pageSize=20");
	}
	
	private void showBanner() {

		// 实例化LayoutParams(重要)
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		// 设置广告条的悬浮位置
		layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
		// 实例化广告条
		AdView adView = new AdView(getActivity(), AdSize.FIT_SCREEN);
		// 调用Activity的addContentView函数

		// 监听广告条接口
		adView.setAdListener(new AdViewListener() {

			@Override
			public void onSwitchedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "广告条切换");
			}

			@Override
			public void onReceivedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "请求广告成功");
			}

			@Override
			public void onFailedToReceivedAd(AdView arg0) {
				Log.i("YoumiAdDemo", "请求广告失败");
			}
		});
		mAdContainer.addView(adView, layoutParams);
	}
}

