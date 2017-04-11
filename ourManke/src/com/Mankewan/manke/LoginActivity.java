package com.Mankewan.manke;

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

import com.manke.info.islogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	private EditText etuser;
	private EditText etpass;
	private Button button;
	private ImageButton back;
	private String username,password;
	private String un;
	private TextView tv;
	public static String value;
	private Handler hand = new Handler(){
		public void handleMessage(Message msg) {
			String s = (String) msg.obj;
			System.out.println("$$$$: " + s);
			islogin.c = s;
			if(s.trim().equals("�û�������")){
				Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
			}else if(s.trim().equals("�������")){
				Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
			}else if(s.trim().equals("suc1")){
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				islogin.islog = true;
				islogin.name = un;
				startActivity(intent);
				finish();
			}else if(s.trim().equals("suc0")){
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				islogin.islog = true;
				islogin.name = un;
				startActivity(intent);
				finish();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etuser = (EditText)findViewById(R.id.editText_username);
		etpass = (EditText)findViewById(R.id.editText_password);
		
	
		button = (Button)findViewById(R.id.button_login);
		button.setOnClickListener(this);
		back = (ImageButton)findViewById(R.id.imageButton1);
		back.setOnClickListener(this);
		tv = (TextView)findViewById(R.id.textViewsucc);
		tv.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_login:
		username = etuser.getText().toString().trim();
		System.out.println("$$$$$$$1: " + username);
		password = etpass.getText().toString().trim();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String url = "http://192.168.1.195:8080/TaoService/LoginService";
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				ArrayList<NameValuePair> pass = new ArrayList<NameValuePair>();
				pass.add(new BasicNameValuePair("username", username));
				pass.add(new BasicNameValuePair("password", password));
				System.out.println("$$$$$$$: " + username);
				try {
					post.setEntity(new UrlEncodedFormEntity(pass,HTTP.UTF_8));
					HttpResponse res = client.execute(post);
					if(res.getStatusLine().getStatusCode()==200){
						HttpEntity entity = res.getEntity();
						InputStream is = entity.getContent();
						byte[] data = new byte[1024];
						int l = -1;
						String s = "";
						while((l= is.read(data))!=-1){
							s += new String(data, 0, l);
							System.out.println("#" + s);
						}
						un = etuser.getText().toString();
						Message m = Message.obtain();
						m.obj = s;
						hand.sendMessage(m);
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
			}
		}).start();
		break;
		case R.id.imageButton1:
			finish();
		    break;
		case R.id.textViewsucc:
			Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(i);
			finish();
	
		}
	}
	
}
