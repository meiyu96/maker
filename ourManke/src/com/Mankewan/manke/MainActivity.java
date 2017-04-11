package com.Mankewan.manke;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.manke.fragment.HomeFragment;
import com.manke.fragment.MineFragment;
import com.manke.fragment.ShopingFragment;
import com.manke.fragment.goodsFragment;
import com.manke.fragment.haiFragment;

public class MainActivity extends FragmentActivity implements OnClickListener{
	private ImageButton image1, image2, image3, image4, image5;
	private LinearLayout ll1, ll2, ll3, ll4, ll5;
	private ViewPager viewpager;
	private String shopping;
	private ArrayList<Fragment> datalsit = new ArrayList<Fragment>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		image1 = (ImageButton) findViewById(R.id.button_home);
		image2 = (ImageButton) findViewById(R.id.button_goods);
		image3 = (ImageButton) findViewById(R.id.button_shoping);
		image4 = (ImageButton) findViewById(R.id.button_mine);
		image5 = (ImageButton) findViewById(R.id.button_hai);
		ll1 = (LinearLayout) findViewById(R.id.main);
		ll2 = (LinearLayout) findViewById(R.id.goods);
		ll3 = (LinearLayout) findViewById(R.id.shoping);
		ll4 = (LinearLayout) findViewById(R.id.mine);
		ll5 = (LinearLayout) findViewById(R.id.hai);
		image1.setOnClickListener(this);
		image2.setOnClickListener(this);
		image3.setOnClickListener(this);
		image4.setOnClickListener(this);
		image5.setOnClickListener(this);
		
		ll1.setOnClickListener(this);
		ll2.setOnClickListener(this);
		ll3.setOnClickListener(this);
		ll4.setOnClickListener(this);
		ll5.setOnClickListener(this);

		viewpager = (ViewPager) findViewById(R.id.ViewPager);
		datalsit.add(new HomeFragment());
		datalsit.add(new goodsFragment());
		datalsit.add(new haiFragment());
		datalsit.add(new ShopingFragment());
		datalsit.add(new MineFragment());
		
		viewpager
				.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					image1.setImageResource(R.drawable.icon_home_selected);
					image2.setImageResource(R.drawable.icon_goods_normal);
					image5.setImageResource(R.drawable.hai_normel);
					image3.setImageResource(R.drawable.icon_shopping_normal);
					image4.setImageResource(R.drawable.icon_my_normal);
					
					break;
				case 1:
					image1.setImageResource(R.drawable.icon_home_normal);
					image2.setImageResource(R.drawable.icon_goods_selected);
					image5.setImageResource(R.drawable.hai_normel);
					image3.setImageResource(R.drawable.icon_shopping_normal);
					image4.setImageResource(R.drawable.icon_my_normal);
					
					break;
				case 2:
					image1.setImageResource(R.drawable.icon_home_normal);
					image2.setImageResource(R.drawable.icon_goods_normal);
					image5.setImageResource(R.drawable.hai_select);
					image3.setImageResource(R.drawable.icon_shopping_normal);
					image4.setImageResource(R.drawable.icon_my_normal);
					
					break;
				case 3:
					image1.setImageResource(R.drawable.icon_home_normal);
					image2.setImageResource(R.drawable.icon_goods_normal);
					image5.setImageResource(R.drawable.hai_normel);
					image3.setImageResource(R.drawable.icon_shopping_selected);
					image4.setImageResource(R.drawable.icon_my_normal);
					
					break;
				case 4:
					image1.setImageResource(R.drawable.icon_home_normal);
					image2.setImageResource(R.drawable.icon_goods_normal);
					image5.setImageResource(R.drawable.hai_normel);
					image3.setImageResource(R.drawable.icon_shopping_normal);
					image4.setImageResource(R.drawable.icon_my_selected);
				
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_home:
			viewpager.setCurrentItem(0);
			image1.setImageResource(R.drawable.icon_home_selected);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.button_goods:
			viewpager.setCurrentItem(1);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_selected);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.button_hai:
			viewpager.setCurrentItem(2);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_select);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.button_shoping:
			viewpager.setCurrentItem(3);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_selected);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.button_mine:
			viewpager.setCurrentItem(4);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_selected);
			
			break;
		
		case R.id.main:
			viewpager.setCurrentItem(0);
			image1.setImageResource(R.drawable.icon_home_selected);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.goods:
			viewpager.setCurrentItem(1);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_selected);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.hai:
			viewpager.setCurrentItem(2);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_select);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.shoping:
			viewpager.setCurrentItem(3);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_selected);
			image4.setImageResource(R.drawable.icon_my_normal);
			
			break;
		case R.id.mine:
			viewpager.setCurrentItem(4);
			image1.setImageResource(R.drawable.icon_home_normal);
			image2.setImageResource(R.drawable.icon_goods_normal);
			image5.setImageResource(R.drawable.hai_normel);
			image3.setImageResource(R.drawable.icon_shopping_normal);
			image4.setImageResource(R.drawable.icon_my_selected);
			
			break;
		
		}

	}

	class MyFragmentAdapter extends FragmentPagerAdapter {

		public MyFragmentAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return datalsit.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datalsit.size();
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		Intent intent = getIntent();
		shopping = intent.getStringExtra("shopping");
		if("3".equals(shopping)){
			viewpager.setCurrentItem(3);
		}
	}

}
