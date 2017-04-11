package com.Mankewan.manke;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager{

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		//请求 父控件 以及祖宗控件 不拦截事件
//		requestDisallowInterceptTouchEvent(true);
//		return super.dispatchTouchEvent(ev);
//	}

}
