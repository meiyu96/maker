package com.Mankewan.manke;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manke.info.ShopImage;
import com.manke.info.islogin;

public class ShopDatailActivity extends Activity {

	private ImageView imshow;
	private TextView tvshopdatailname, tvshopdatailprice, tvshopdatailaddress;
	private ArrayList<ShopImage> list = new ArrayList<ShopImage>();
	private String imageshopshow, image, name, id, s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shopdatail_item);

		imshow = (ImageView) findViewById(R.id.iv_shopdatal_item_image);
		tvshopdatailaddress = (TextView) findViewById(R.id.tv_shopdatal_item_address);
		tvshopdatailname = (TextView) findViewById(R.id.tv_shopdatal_item_name);
		tvshopdatailprice = (TextView) findViewById(R.id.tv_shopdatal_item_price);

		Intent intent = getIntent();

		id = intent.getStringExtra("id");
		image = intent.getStringExtra("image");
		name = intent.getStringExtra("name");

		tvshopdatailname.setText(intent.getStringExtra("name"));
		tvshopdatailprice.setText("¥" + intent.getStringExtra("price"));
		// tvshopdatailaddress.setText(intent.getStringExtra("address"));

		new MyTaskRcm(imshow).execute(image);

	}

	public void putin(View v) {
		if (islogin.islog) {
			new MyTask().execute();
			Intent intent = new Intent(ShopDatailActivity.this,
					MainActivity.class);
			intent.putExtra("shopping", 3 + "");
			startActivityForResult(intent, 3);
			finish();
		} else {
			Toast.makeText(ShopDatailActivity.this, "请先登录", Toast.LENGTH_SHORT)
					.show();
		}
	}

	class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/GoodsInfoService";

			HttpClient client = new DefaultHttpClient();// ������
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> arrname = new ArrayList<NameValuePair>();
			arrname.add(new BasicNameValuePair("g_id", id));
			try {
				// ����װ�õ�list����ת����Entity����󶨵�HttpPost����
				post.setEntity(new UrlEncodedFormEntity(arrname, HTTP.UTF_8));
				// ͨ��HttpClient�ͻ��˶���ִ���ҵ�post���󣬷��ؽ��HttpResponse����
				HttpResponse response = client.execute(post);
				// �жϷ��������ͻ��˵���Ӧ����ȷ��
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					byte[] data = new byte[1024];
					int length = -1;
					while ((length = is.read(data)) != -1) {
						s += new String(data, 0, length);
					}
				}

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(image);
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
