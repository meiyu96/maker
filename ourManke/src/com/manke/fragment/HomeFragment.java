package com.manke.fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.Mankewan.manke.ContralActivity;
import com.Mankewan.manke.LoginActivity;
import com.Mankewan.manke.MainActivity;
import com.Mankewan.manke.NOActivity;
import com.Mankewan.manke.R;
import com.Mankewan.manke.RegisterActivity;
import com.Mankewan.manke.ShopDatailActivity;
import com.manke.info.ShopImage;
import com.manke.info.islogin;

public class HomeFragment extends Fragment implements OnClickListener {
	private ImageButton b = null;
	private Button test = null;
	private Button test2 = null;
	private Button test3 = null;
	private Button test4 = null;
	private ImageButton anime, game;
	private ViewPager mViewPaper;
	private List<ImageView> images;
	private List<View> dots;
	private int currentItem;
	// 记录上一次点的位置
	private int oldPosition = 0;
	// 存放图片的id
	private int[] imageIds = new int[] { R.drawable.asuna, R.drawable.xiujian,
			R.drawable.dandao, R.drawable.kirito, R.drawable.aishang };
	// 存放图片的标题
	private String[] titles = new String[] { "刀剑神域亚丝娜cos", "刺客信条4爱德华袖箭",
			"魔兽世界伊利丹蛋刀", "刀剑神域桐人SAO服装", "魔兽世界霜之哀伤" };
	private TextView title;
	private ViewPagerAdapter adapter;
	private ScheduledExecutorService scheduledExecutorService;
	private ListView ls = null;
	private ArrayList<ShopImage> data = new ArrayList<ShopImage>();
	private String imageshopshow;
	String url, num;
	listAdapter ad;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.home_layout, container, false);
		View ui = inflater.inflate(R.layout.ui, container, false);
		mViewPaper = (ViewPager) view.findViewById(R.id.vp);

		// 显示的图片
		images = new ArrayList<ImageView>();

		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setBackgroundResource(imageIds[i]);
			images.add(imageView);
		}
		// 显示的小点
		dots = new ArrayList<View>();
		dots.add(view.findViewById(R.id.dot_0));
		dots.add(view.findViewById(R.id.dot_1));
		dots.add(view.findViewById(R.id.dot_2));
		dots.add(view.findViewById(R.id.dot_3));
		dots.add(view.findViewById(R.id.dot_4));

		title = (TextView) view.findViewById(R.id.title);
		title.setText(titles[0]);

		adapter = new ViewPagerAdapter();
		mViewPaper.setAdapter(adapter);

		mViewPaper
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						title.setText(titles[position]);
						dots.get(position).setBackgroundResource(
								R.drawable.dot_focused);
						dots.get(oldPosition).setBackgroundResource(
								R.drawable.dot_normal);

						oldPosition = position;
						currentItem = position;
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {

					}
				});
		b = (ImageButton) view.findViewById(R.id.button1);
		b.setOnClickListener(this);
		test = (Button) ui.findViewById(R.id.button_test);
		test.setOnClickListener(this);
		test2 = (Button) ui.findViewById(R.id.button_test2);
		test2.setOnClickListener(this);
		test3 = (Button) ui.findViewById(R.id.button_test3);
		test3.setOnClickListener(this);
		test4 = (Button) ui.findViewById(R.id.button_test4);
		test4.setOnClickListener(this);
		anime = (ImageButton) view.findViewById(R.id.ImageButton_anime);
		anime.setOnClickListener(this);
		game = (ImageButton) view.findViewById(R.id.imageButton_game);
		game.setOnClickListener(this);
		// dl.set
		ls = (ListView) view.findViewById(R.id.listView_show_tui);
		ad = new listAdapter();
		ls.setAdapter(ad);
		ls.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// intent��ֵ
				Intent intent = new Intent(getActivity(),
						ShopDatailActivity.class);
				intent.putExtra("id", data.get(arg2).getId());
				intent.putExtra("image", data.get(arg2).getPictureID());
				intent.putExtra("name", data.get(arg2).getName());
				intent.putExtra("price", data.get(arg2).getPrice());
				intent.putExtra("address", data.get(arg2).getAddress());
				// ����intent
				startActivity(intent);

			}
		});
		return view;
	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			// view.removeView(view.getChildAt(position));
			// view.removeViewAt(position);
			view.removeView(images.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			// TODO Auto-generated method stub
			view.addView(images.get(position));
			return images.get(position);
		}

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	/**
	 * 利用线程池定时执行动画轮播
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 2,
				2, TimeUnit.SECONDS);
	}

	private class ViewPageTask implements Runnable {

		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageIds.length;
			mHandler.sendEmptyMessage(0);
		}
	}

	/**
	 * 接收子线程传递过来的数据
	 */
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPaper.setCurrentItem(currentItem);
		};
	};

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	// b = (Button)getView().findViewById(R.id.button1);
	// b.setOnClickListener(this);
	// }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			showPopupWindow(v);
			break;
		case R.id.ImageButton_anime:
			num = "0";
			new MyData().execute();
			ad.notifyDataSetChanged();
			break;
		case R.id.imageButton_game:
			num = "1";
			new MyData().execute();
			ad.notifyDataSetChanged();
			break;

		}

	}

	class listAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			ViewHandler vh = null;
			if (convertView == null) {
				convertView = getLayoutInflater(getArguments()).inflate(
						R.layout.actvity_shop_item, null);
				vh = new ViewHandler();
				vh.ivshopitemshow = (ImageView) convertView
						.findViewById(R.id.imageView_shop_itemshow);
				vh.tvshopitemname = (TextView) convertView
						.findViewById(R.id.textView_shop_itemname);
				vh.tvshopitemprice = (TextView) convertView
						.findViewById(R.id.textView_shop_itemprice);
				convertView.setTag(vh);

			} else {
				vh = (ViewHandler) convertView.getTag();
			}

			vh.tvshopitemname.setText(data.get(position).getName());
			vh.tvshopitemprice.setText("¥" + data.get(position).getPrice()) ;
			imageshopshow = data.get(position).getPictureID();
			new MyTaskRcm(vh.ivshopitemshow).execute(data.get(position)
					.getPictureID());

			return convertView;

		}

		class ViewHandler {
			ImageView ivshopitemshow;
			TextView tvshopitemname;
			TextView tvshopitemprice;
		}

	}

	class MyData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			if (data != null) {
				data.clear();
			}
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/HomeClassifyService";

			HttpClient client = new DefaultHttpClient();// �������������
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
			arr.add(new BasicNameValuePair("classify", num));
			try {
				post.setEntity(new UrlEncodedFormEntity(arr, HTTP.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					// ��������ʽ���ܷ���������������
					byte[] buffer = new byte[1024];
					int l = -1;
					String s = "";
					while ((l = is.read(buffer)) != -1) {
						s += new String(buffer, 0, l);
					}

					JSONObject jo = new JSONObject(s);
					JSONArray ja = jo.getJSONArray("goodslist");
					for (int i = 0; i < ja.length(); i++) {
						JSONObject temp = ja.getJSONObject(i);
						ShopImage bit = new ShopImage();
						bit.setId(temp.getString("g_id"));
						bit.setPictureID(temp.getString("goodsImage"));
						bit.setName(temp.getString("goodsName"));
						bit.setPrice(temp.getString("goodsPrice"));
						// bit.setAddress(temp.getString("SourceOfGoods"));
						data.add(bit);
					}

				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// adapter.notifyDataSetChanged();
		}
	}

	class MyTaskRcm extends AsyncTask<String, Void, String> {
		public ImageView mImageView;
		public Bitmap mBitmap;

		public MyTaskRcm(ImageView imageView) {
			// TODO Auto-generated constructor stub
			mImageView = imageView;
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			HttpClient client = new DefaultHttpClient();// ������
			HttpPost post = new HttpPost(params[0]);
			try {
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					byte[] b = new byte[1024];
					String temp = "";
					int res = 0;
					while ((res = is.read(b, 0, 1024)) != -1) {
						byteArrayOutputStream.write(b, 0, res);
					}
					is.close();
					b = byteArrayOutputStream.toByteArray();

					mBitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
				}
			} catch (Exception e) {

			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mImageView.setImageBitmap(mBitmap);
		}
	}

	private void showPopupWindow(View view) {

		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.ui, null);
		// 设置按钮的点击事件
		Button button = (Button) contentView.findViewById(R.id.button_test);
		if (islogin.islog) {
			button.setEnabled(false);
		} else {
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), LoginActivity.class);
					startActivity(i);
				}
			});
		}
		Button button2 = (Button) contentView.findViewById(R.id.button_test2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), RegisterActivity.class);
				startActivity(i);
			}
		});
		Button button3 = (Button) contentView.findViewById(R.id.button_test3);
		if (!islogin.islog) {
			button3.setEnabled(false);
		} else {
			button3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					if ((islogin.c.trim()).equals("suc1")) {
						System.out.println(islogin.c);
						Intent intent = new Intent(getActivity(),
								ContralActivity.class);
						startActivity(intent);
					} else if ((islogin.c.trim()).equals("suc0")) {
						System.out.println(islogin.c);
						Intent i = new Intent(getActivity(), NOActivity.class);
						startActivity(i);
					}
				}
			});
		}
		Button button4 = (Button) contentView.findViewById(R.id.button_test4);
		if (!islogin.islog) {
			button4.setEnabled(false);
		} else {
			button4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					islogin.islog = false;
					islogin.name = "s";
					Intent i = new Intent(getActivity(), MainActivity.class);
					startActivity(i);

				}
			});
		}
		final PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

		popupWindow.setTouchable(true);

		popupWindow.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				Log.i("mengdd", "onTouch : ");

				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});

		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.bg));

		// 设置好参数之后再show
		popupWindow.showAsDropDown(view);

	}
}
