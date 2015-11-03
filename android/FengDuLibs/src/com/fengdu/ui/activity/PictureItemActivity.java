/**
 * 工程名: FengDuLibs
 * 文件名: PictureItemActivity.java
 * 包名: com.fengdu.ui.activity
 * 日期: 2015年10月28日下午4:59:03
 * QQ: 378640336
 *
*/

package com.fengdu.ui.activity;

import java.util.ArrayList;

import com.fengdu.BaseFragmentActivity;
import com.fengdu.R;
import com.fengdu.bean.ImageBean;
import com.fengdu.ui.activity.adapter.GalleryImageAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import uk.co.senab.photoview.PhotoView;

/**
 * 类名: PictureItemActivity <br/>
 * 功能: 图片展示. <br/>
 * 日期: 2015年10月28日 下午4:59:03 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class PictureItemActivity extends BaseFragmentActivity{
	private ArrayList<ImageBean> mList;
	private DisplayImageOptions mOptions;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_item); 
		initOptions();
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		final PhotoView photoView = (PhotoView) findViewById(R.id.photoView); 
		
		Intent intent = getIntent();
		mList = (ArrayList<ImageBean>)intent.getExtras().getSerializable("key");
		
		gallery.setAdapter(new GalleryImageAdapter(this,mList,mOptions)); 
		gallery.setSelection(0);

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ImageBean bean = mList.get(position);
				ImageLoader.getInstance().displayImage(bean.getImage_url(), photoView, mOptions);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	
	private void initOptions(){
		mOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.friends_sends_pictures_no)
		.showImageForEmptyUri(R.drawable.friends_sends_pictures_no)
		.showImageOnFail(R.drawable.friends_sends_pictures_no)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.build();
	}
}

