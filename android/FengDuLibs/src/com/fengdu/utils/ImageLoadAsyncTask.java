package com.fengdu.utils;

import java.util.ArrayList;
import java.util.List;

import com.fengdu.interf.BaseObject;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;


public class ImageLoadAsyncTask extends AsyncTask<Object, Void, List<String>>{

	private Context mContext;
	
	public ImageLoadAsyncTask(Context context) {
		super();
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	protected List<String> doInBackground(Object... params) {
		// TODO Auto-generated method stub
		Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		ContentResolver mContentResolver = mContext.getContentResolver();

		Cursor mCursor = MediaStore.Images.Media.query(mContentResolver,mImageUri,PictureFormatUtil.STORE_IMAGES);

		List<String> pathList = new ArrayList<String>();
		while (mCursor.moveToNext()) {
			//��ȡͼƬ��·��
			String path = mCursor.getString(mCursor
					.getColumnIndex(MediaStore.Images.Media.DATA));

			pathList.add(path);
		}
		
		mCursor.close();
		return pathList;
	}

	@Override
	protected void onPostExecute(List<String> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		((BaseObject) mContext).setImageAdapter(result);
	}
	
	
}
