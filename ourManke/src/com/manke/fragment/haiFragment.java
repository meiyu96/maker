package com.manke.fragment;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.Mankewan.manke.R;

public class haiFragment extends Fragment {

	Resources resources;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private TextView novelTextView, videoTextView;
	private ImageView ivBottomLine;
	Fragment home1;
	Fragment home2;
	private int currIndex = 0;
	private int position_one;
	private int bottomLineWidth;
	public final static int num = 2;
	private int offset = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.hai_ui, null);
		resources = getResources();
		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);
		TranslateAnimation animation = new TranslateAnimation(position_one,
				offset, 0, 0);
		novelTextView.setTextColor(resources.getColor(R.color.lightwhite));
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);
		return view;
	}

	private void InitTextView(View parentView) {
		novelTextView = (TextView) parentView.findViewById(R.id.tv_tab_1);
		videoTextView = (TextView) parentView.findViewById(R.id.tv_tab_2);

		novelTextView.setOnClickListener(new MyOnClickListener(0));
		videoTextView.setOnClickListener(new MyOnClickListener(1));
	}

	private void InitViewPager(View parentView) {
		mPager = (ViewPager) parentView.findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();

		home1 = new videoFragment();
		home2 = new novelFragment();

		fragmentsList.add(home1);
		fragmentsList.add(home2);

		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),
				fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);
	}

	private void InitWidth(View parentView) {
		ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
		bottomLineWidth = ivBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / num - bottomLineWidth) / 2);
		int avg = (int) (screenW / num);
		position_one = avg + offset;
	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, offset, 0,
							0);
					videoTextView.setTextColor(resources
							.getColor(R.color.lightwhite));
				}
				novelTextView.setTextColor(resources.getColor(R.color.white));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					videoTextView.setTextColor(resources
							.getColor(R.color.lightwhite));
				}
				novelTextView.setTextColor(resources.getColor(R.color.white));
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			ivBottomLine.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
