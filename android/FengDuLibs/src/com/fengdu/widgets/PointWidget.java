package com.fengdu.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import com.fengdu.R;

public class PointWidget extends LinearLayout {

	ArrayList<View> pointList;
	ImageView point;
	LayoutParams lp;
	Context context;
	
	public PointWidget(Context context) {
		super(context);
		this.context=context;
		init();
		setOrientation(LinearLayout.HORIZONTAL);
	}  
	public PointWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		init();
		setOrientation(LinearLayout.HORIZONTAL);
	}
	private void init(){
		lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		pointList=new ArrayList<View>();
	}
	public void setPointCount(int PointCount){
		for(int i=0;i<PointCount;i++){
			point=new ImageView(context);
			point.setImageResource(R.drawable.point_selector);
			point.setPadding(10,0,10,0);
			point.setEnabled(false);
			point.setLayoutParams(lp);
			if(pointList.size()==0){
				point.setEnabled(true);
			}else{
				pointList.get(0).setEnabled(true);
			}
			pointList.add(point);
			addView(point);
		}
	}
	public void setPoint(int i){
		for(int a=0;a<pointList.size();a++){
			if(a==i){
				pointList.get(i).setEnabled(true);
				continue;
			}
			pointList.get(a).setEnabled(false);
		}
		
	}
}
