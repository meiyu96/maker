package com.Mankewan.manke;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class changActivity extends Activity implements OnClickListener{
	private EditText inname,inmoney,inaddress;
	private Button bt;
	private ImageButton ibt;
	private String id,name,money,add,rename,remoney,readd;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.contral_change);
    	inname = (EditText)findViewById(R.id.editText_inputname);
    	inmoney = (EditText)findViewById(R.id.editText_inputmoney);
    	inaddress = (EditText)findViewById(R.id.editText_inputadd);
    	bt = (Button)findViewById(R.id.button_shangchuan);
    	ibt = (ImageButton)findViewById(R.id.imagebutton_backtochang);
    	Intent i = getIntent();
    	id = i.getStringExtra("id");
    	name = i.getStringExtra("name");
    	money = i.getStringExtra("price");
    	add = i.getStringExtra("address");
    	inname.setText(name);
    	inmoney.setText(money);
    	inaddress.setText(add);
    	bt.setOnClickListener(this);
    	ibt.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_shangchuan:
			rename = inname.getText().toString();
			remoney = inmoney.getText().toString();
			readd = inaddress.getText().toString();
			new MyData().execute();
			break;
		case R.id.imagebutton_backtochang:
			finish();
			break;
		}
	}
	class MyData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.1.195:8080/TaoService/UpdateMerService";

			HttpClient client = new DefaultHttpClient();// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
			arr.add(new BasicNameValuePair("id", id));
			arr.add(new BasicNameValuePair("name", rename));
			arr.add(new BasicNameValuePair("money",remoney));
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
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// adapter.notifyDataSetChanged();
		}
	}

}
