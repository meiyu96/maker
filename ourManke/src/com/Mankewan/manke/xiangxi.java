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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.manke.fragment.MineFragment;
import com.manke.info.goodsmessage;
import com.manke.info.islogin;

public class xiangxi extends Activity implements OnClickListener {
	ArrayList<goodsmessage> arr = new ArrayList<goodsmessage>();
	private ListView ls = null;
	sureAdapter adapter = new sureAdapter();
	private ImageButton im;
	private ImageButton buy;
	int i = 0;
	public static String truename;
	String price;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_set_ui);
		ls = (ListView) findViewById(R.id.list_buy);
		im = (ImageButton) findViewById(R.id.back);
		im.setOnClickListener(this);
		buy = (ImageButton) findViewById(R.id.buy_ok);
		buy.setOnClickListener(this);
		ls.setAdapter(adapter);
		Intent i = getIntent();
		truename = i.getStringExtra("name");
		price = i.getStringExtra("price");
		String pic = i.getStringExtra("pic");
		goodsmessage g = new goodsmessage();
		g.setName(truename);
		g.setPrice(price);
		g.setPic(pic);
		arr.add(g);

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

	class sureAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arr.size();
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
			// TODO Auto-generated method stub
			if (arg1 == null) {
				arg1 = getLayoutInflater().inflate(R.layout.message_ui, null);
				shows s = new shows();
				s.ivshow = (ImageView) arg1.findViewById(R.id.image_buy);
				s.tvname = (TextView) arg1.findViewById(R.id.name_buy);
				s.tvprice = (TextView) arg1.findViewById(R.id.money_sure_buy);
				arg1.setTag(s);
			}
			shows s = (shows) arg1.getTag();
			s.tvname.setText(arr.get(arg0).getName());
			s.tvprice.setText(arr.get(arg0).getPrice());
			new MyTaskRcm(s.ivshow).execute(arr.get(arg0).getPic());

			return arg1;
		}

		class shows {
			public ImageView ivshow;
			public TextView tvname;
			public TextView tvprice;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buy_ok:
			
//			Bundle b = new Bundle();
//			b.putString("name", name);
//			b.putString("price", price);
//			new MineFragment().setArguments(b);
			new MyDelete().execute(islogin.id);
			Toast.makeText(xiangxi.this, "购买成功", Toast.LENGTH_SHORT).show();
			finish();
			
			break;
		case R.id.back:
			finish();
			break;
		}
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

}
