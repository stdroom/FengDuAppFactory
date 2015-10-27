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
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.Volley;
import com.fengdu.BaseFragment;
import com.fengdu.R;
import com.fengdu.android.URLs;
import com.fengdu.bean.ArticleItemBean;
import com.fengdu.bean.ImageBean;
import com.fengdu.bean.PagesInfo;
import com.fengdu.bean.UpdateBean;
import com.fengdu.parse.ArticleItemListParse;
import com.fengdu.parse.ArticleItemPagesParse;
import com.fengdu.ui.fragment.adapter.ArticleListAdapter;
import com.fengdu.ui.fragment.adapter.StaggeredGridAdapter;
import com.fengdu.utils.ExcutorServiceUtils;
import com.fengdu.utils.ImageLoadAsyncTask;
import com.fengdu.volley.FastJSONRequest;
import com.fengdu.volley.VolleyManager;
import com.fengdu.volley.FastResponse.Listener;
import com.fengdu.widgets.stragedview.StaggeredGridView;
import com.mike.aframe.MKLog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 类名: PhotosViewPagerFragment <br/>
 * 功能: 浏览图片的framgnet. <br/>
 * 日期: 2015年10月26日 下午2:38:19 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class PhotosViewPagerFragment extends BaseFragment{

	private StaggeredGridView mGridView;
	private ArrayList<ImageBean> list;
	private StaggeredGridAdapter mAdapter;
	private DisplayImageOptions mOptions;
	private TextView mActionText;
    
	private String urls = "";
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
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.staggered_grid, container,false);
		mGridView = (StaggeredGridView) view.findViewById(R.id.image_grid);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),PictureItemActivity.class);
//				intent.putExtra("path_position", position);
//				intent.putStringArrayListExtra("all_path", (ArrayList<String>)list);
//				startActivity(intent);
			}
		});
		initData();
		return view;
	}

	public void setImageAdapter(List<ImageBean> list) {
		// TODO Auto-generated method stub
		mAdapter = new StaggeredGridAdapter(getActivity(), list,mOptions);
		mGridView.setAdapter(mAdapter);
	}
	
	public void initData(){
		RequestQueue mQueue = Volley.newRequestQueue(getActivity());
		mQueue.start();
		VolleyManager.getInstance().beginSubmitRequest(
				mQueue, 
				new FastJSONRequest(urls, "", new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject obj,
							String executeMethod, String flag,
							boolean dialogFlag) {
						if ("0".equals(flag)){	// 取出results
							if (obj!=null){
								
								JSONArray arrays = obj.getJSONArray("data");
								int size = arrays!=null ? arrays.size():0;
								list = new ArrayList<ImageBean>();
								for(int i = 0 ; i < size ;i++){
									ImageBean bean = new ImageBean();
									JSONObject json = (JSONObject)arrays.get(i);
									bean.setDesc(json.getString("desc"));
									bean.setImage_height(json.getIntValue("image_height"));
									bean.setImage_width(json.getIntValue("image_width"));
									bean.setImage_url(json.getString("image_url"));
									bean.setThumbnail(json.getString("thumbnail_url"));
									bean.setThumbnail_height(json.getIntValue("thumbnail_height"));
									bean.setThumbnail_width(json.getIntValue("thumbnail_width"));
									list.add(bean);
								}
								myHandler.sendEmptyMessage(0x110);
							} else {
								MKLog.e(getClass().getName(), "can't get data"); 
							}
						} else {
							MKLog.e(getClass().getName(), "The Response flag is not right");
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						MKLog.d("", error.toString());
					}
				}));
	}
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x110){
				if(list != null){
					//图片路径的list
					mAdapter = new StaggeredGridAdapter(getActivity(), list, mOptions);
					mGridView.setAdapter(mAdapter);
				}else{
//					new ImageLoadAsyncTask(getActivity()).execute();
				}
			}else{
			}
		}
		
	};
}

