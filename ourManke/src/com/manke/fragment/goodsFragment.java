package com.manke.fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Mankewan.manke.R;
import com.Mankewan.manke.ShopDatailActivity;
import com.manke.info.ShopImage;

public class goodsFragment extends Fragment {

	private ShopAdapter adapter;
	private ListView listview;
	private ArrayList<ShopImage> dataList = new ArrayList<ShopImage>();
	private Bitmap pic;
	private String imageshopshow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.goods_ui, null);
		new MyData().execute();
		listview = (ListView) view.findViewById(R.id.listView_shopshow);
		adapter = new ShopAdapter();
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// intent��ֵ
				Intent intent = new Intent(getActivity(),
						ShopDatailActivity.class);
				intent.putExtra("id", dataList.get(arg2).getId());
				intent.putExtra("image", dataList.get(arg2).getPictureID());
				intent.putExtra("name", dataList.get(arg2).getName());
				intent.putExtra("price", dataList.get(arg2).getPrice());
				intent.putExtra("address", dataList.get(arg2).getAddress());
				// ����intent
				startActivity(intent);

			}
		});
		
		

		return view;
	}

	class ShopAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

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

			vh.tvshopitemname.setText(dataList.get(position).getName());
			vh.tvshopitemprice.setText("¥"
					+ dataList.get(position).getPrice());
			imageshopshow = dataList.get(position).getPictureID();
			new MyTaskRcm(vh.ivshopitemshow).execute(dataList.get(position)
					.getPictureID());
            adapter.notifyDataSetChanged();
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
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/GoodslistService";

			HttpClient client = new DefaultHttpClient();// �������������
			HttpPost post = new HttpPost(url);
			try {
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
					JSONArray ja = jo.getJSONArray("goodlist");
					for (int i = 0; i < ja.length(); i++) {
						JSONObject temp = ja.getJSONObject(i);
						ShopImage bit = new ShopImage();
						bit.setId(temp.getString("g_id"));
						bit.setPictureID(temp.getString("goodsImage"));
						bit.setName(temp.getString("goodsName"));
						bit.setPrice(temp.getString("goodsPrice"));
						// bit.setAddress(temp.getString("SourceOfGoods"));
						dataList.add(bit);
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

}
