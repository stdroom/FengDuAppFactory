package com.fengdu.utils;

import android.provider.MediaStore;

public class PictureFormatUtil {

	public static final String[] picture_format = { "image/jpeg", "image/png" };
	
	public static final String[] STORE_IMAGES = {
//			MediaStore.Images.Media.DISPLAY_NAME, 
			MediaStore.Images.Media.DATA,
//			MediaStore.Images.Media.LONGITUDE,
//			MediaStore.Images.Media._ID, 
//			MediaStore.Images.Media.BUCKET_ID, 
//			MediaStore.Images.Media.BUCKET_DISPLAY_NAME 
			MediaStore.Images.Media.DATE_MODIFIED
		};
}
