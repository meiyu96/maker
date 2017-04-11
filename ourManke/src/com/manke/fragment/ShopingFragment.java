package com.manke.fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Mankewan.manke.R;
import com.Mankewan.manke.xiangxi;
import com.manke.info.GetInfo;
import com.manke.info.goodsmessage;
import com.manke.info.islogin;

public class ShopingFragment extends Fragment implements
		OnItemLongClickListener, GetInfo, OnClickListener {
	private shopingAdapter adapter = new shopingAdapter();
	private ListView ls;
	public static int money = 0;
	private static TextView tvmoney;
	private CheckBox cbox;
	private String imageshopshow;
	private ImageButton jump;

	private ArrayList<goodsmessage> datalist = new ArrayList<goodsmessage>();
	private ArrayList<goodsmessage> array = new ArrayList<goodsmessage>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_shopping, null);
		ls = (ListView) view.findViewById(R.id.listView1);
		tvmoney = (TextView) view.findViewById(R.id.showMoney);
		cbox = (CheckBox) view.findViewById(R.id.fallmoney);
		jump = (ImageButton) view.findViewById(R.id.buy);
		if (!islogin.islog) {
			Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
		} else {
			ls.setOnItemLongClickListener(this);
			adapter.notifyDataSetChanged();
			
			cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					
					adapter.notifyDataSetChanged();

				}
			});

			
			jump.setOnClickListener(this);
			MyData my = new MyData();
			my.execute();
			adapter.test(this);
			ls.setAdapter(adapter);
			registerForContextMenu(ls);
		}
		return view;
	}



	class MyDelete extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/DelectCartService";

			HttpClient client = new DefaultHttpClient();// ������
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
			arr.add(new BasicNameValuePair("id", params[0]));
			try {
				post.setEntity(new UrlEncodedFormEntity(arr, HTTP.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					byte[] buffer = new byte[1024];
					int l = -1;
					String s = "";
					while ((l = is.read(buffer)) != -1) {
						s += new String(buffer, 0, l);
					}
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}

	class MyData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			// ���ӷ�����
			String url = "http://192.168.1.195:8080/TaoService/CartSleService";

			HttpClient client = new DefaultHttpClient();// ������
			HttpPost post = new HttpPost(url);
			try {
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					byte[] buffer = new byte[1024];
					int l = -1;
					String s = "";
					while ((l = is.read(buffer)) != -1) {
						s += new String(buffer, 0, l);
					}

					JSONObject jo = new JSONObject(s);
					JSONArray ja = jo.getJSONArray("cartlist");
					for (int i = 0; i < ja.length(); i++) {
						JSONObject temp = ja.getJSONObject(i);
						goodsmessage bit = new goodsmessage();
						// ��������
						bit.setGid(temp.getInt("g_id"));
						bit.setPic(temp.getString("goodsImage"));
						bit.setName(temp.getString("goodsName"));
						bit.setPrice(temp.getString("goodsPrice"));
						datalist.add(bit);// ��ӵ�����
					}

					// ShopActivity.this.h.sendEmptyMessage(1);
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
			adapter.notifyDataSetChanged();
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
				System.out.println("����");
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

	class shopingAdapter extends BaseAdapter implements OnCheckedChangeListener {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datalist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			final String price = datalist.get(arg0).getPrice();
			// TODO Auto-generated method stub
			if (arg1 == null) {
				arg1 = getLayoutInflater(getArguments()).inflate(
						R.layout.shopping_list, null);
				show s = new show();
				s.tvname = (TextView) arg1.findViewById(R.id.textView_names);
				s.iview = (ImageView) arg1.findViewById(R.id.imageView_shows);
				s.tvprice = (TextView) arg1.findViewById(R.id.num);
				s.chbox = (CheckBox) arg1.findViewById(R.id.checkBox1);
				s.chbox.setTag(arg0);
				s.chbox.setOnCheckedChangeListener(this);
				arg1.setTag(s);
			}
			final show s = (show) arg1.getTag();
			s.tvname.setText(datalist.get(arg0).getName());
			s.tvprice.setText(datalist.get(arg0).getPrice());
			imageshopshow = datalist.get(arg0).getPic();

			if (cbox.isChecked() == true) {
				s.chbox.setChecked(true);
			}

			new MyTaskRcm(s.iview).execute(imageshopshow);

			tvmoney.setText(String.valueOf(money));
			return arg1;

		}

		class show {
			public ImageView iview;
			public TextView tvname;
			public TextView tvprice;
			public CheckBox chbox;
		}

		public GetInfo g;

		public void test(GetInfo getInfo) {// sh fr
			g = getInfo;

		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			g.test(Integer.parseInt(buttonView.getTag() + ""), isChecked);
		}

	}


	@Override
	public void test(int p, boolean b) {
		// TODO Auto-generated method stub
		// System.out.println("rcm p : " + p);
		goodsmessage g = new goodsmessage();
		islogin.id = String.valueOf(datalist.get(p).getGid());
		g.setGid(datalist.get(p).getGid());
		g.setName(datalist.get(p).getName());
		g.setPrice(datalist.get(p).getPrice());
		g.setPic(datalist.get(p).getPic());

		if (b) {
			money += Integer.parseInt(datalist.get(p).getPrice());
			tvmoney.setText(String.valueOf(money));
			array.add(g);

		} else {
			money -= Integer.parseInt(datalist.get(p).getPrice());
			tvmoney.setText(String.valueOf(money));
			array.remove(g);
			if (money <= 0) {
				money = 0;
			}
			cbox.setChecked(false);
		}
		if (!cbox.isChecked()) {
			b = false;
			adapter.notifyDataSetChanged();
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), xiangxi.class);
		intent.putExtra("name", array.get(0).getName());
		intent.putExtra("price", array.get(0).getPrice());
		intent.putExtra("pic", array.get(0).getPic());

		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,  final int arg2,
			long arg3) {
		
		// TODO Auto-generated method stub
		new AlertDialog.Builder(getActivity())
				.setTitle("系统提示")
				// 设置对话框标题
				.setMessage("是否要删除这个商品")
				// 设置显示的内容
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 添加确定按钮
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 确定按钮的响应事件
								// TODO Auto-generated method stub
								new MyDelete().execute(String.valueOf(datalist.get(arg2).getGid()));
							    
							}
						})
				.setNegativeButton("返回", new DialogInterface.OnClickListener() {// 添加返回按钮
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 响应事件
								// TODO Auto-generated method stub
								
							}
						}).show();// 在按键响应事件中显示此对话框
		
		return false;
	}

}
