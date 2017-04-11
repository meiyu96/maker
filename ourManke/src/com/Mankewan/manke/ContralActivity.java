package com.Mankewan.manke;

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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.manke.info.ShopImage;

public class ContralActivity extends Activity implements OnClickListener {
	
	private ListView ls =null;
	private Button bt = null;
	private EditText input = null;
	private ImageButton ibt = null;
	private ArrayList<ShopImage> data =new ArrayList<ShopImage>();
	private String imageshopshow;
	private String name;
	private TextView t;
	ListAdapter adapter;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.contral);
    	ls = (ListView)findViewById(R.id.find_goods_show);
    	bt = (Button)findViewById(R.id.true_click);
    	input = (EditText)findViewById(R.id.findGoods);
    	ibt = (ImageButton)findViewById(R.id.button_backtoHome);
    	bt.setOnClickListener(this);
    	ibt.setOnClickListener(this);
    	t = (TextView)findViewById(R.id.textView_put);
    	t.setOnClickListener(this);
    	ls.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// intent��ֵ
				Intent intent = new Intent(ContralActivity.this,
						changActivity.class);
				intent.putExtra("id", data.get(arg2).getId());
				intent.putExtra("name", data.get(arg2).getName());
				intent.putExtra("price", data.get(arg2).getPrice());
				intent.putExtra("address", data.get(arg2).getAddress());
				// ����intent
				startActivity(intent);

			}
		});
		ls.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				new delete().execute(data.get(arg2).getId());
				adapter.notifyAll();
				return false;
			}
		});
    	adapter = new listAdapter();
    	ls.setAdapter(adapter);
    	
    	
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.true_click:
			name = input.getText().toString();
			new MyData().execute();
			
			break;
			
		case R.id.button_backtoHome:
			finish();
			break;
		case R.id.textView_put:
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri uri = Uri
					.parse("http://192.168.1.195:8080/TaoService/");
			intent.setData(uri);
			startActivity(intent);
			break;
		}
	}
	class delete extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/DeleteGoodsService";

			HttpClient client = new DefaultHttpClient();// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
			arr.add(new BasicNameValuePair("id", params[0]));
			try {
				post.setEntity(new UrlEncodedFormEntity(arr, HTTP.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					// 锟斤拷锟斤拷锟斤拷锟斤拷式锟斤拷锟杰凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
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

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// adapter.notifyDataSetChanged();
		}
	}
	class MyData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			if(data!=null){
				data.clear();
			}
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/FuzzySleService";

			HttpClient client = new DefaultHttpClient();// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
			arr.add(new BasicNameValuePair("jp", name));
			try {
				post.setEntity(new UrlEncodedFormEntity(arr, HTTP.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					// 锟斤拷锟斤拷锟斤拷锟斤拷式锟斤拷锟杰凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
					byte[] buffer = new byte[1024];
					int l = -1;
					String s = "";
					while ((l = is.read(buffer)) != -1) {
						s += new String(buffer, 0, l);
					}

					JSONObject jo = new JSONObject(s);
					JSONArray ja = jo.getJSONArray("goodhh");
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

			HttpClient client = new DefaultHttpClient();// 锟斤拷锟斤拷锟斤拷
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
	class listAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
			ViewHandler vh = null;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(
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
			vh.tvshopitemprice.setText("¥" + data.get(position).getPrice());
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
}
