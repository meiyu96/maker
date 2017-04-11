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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	final String URL = "http://192.168.1.195:8080/TaoService/RegisterService";
	private EditText nameEdit, pwdEdit, AgainpwdEdit, phoneNumEdit, SexEdit,
			addressEdit;
	private Button submitButton;
	String name, pwd, againPwd, phoneNum, sex, address;

	// // 判断密码的长度
	// public void pwdLength() {
	// if (!pwdEdit.getText().toString().equals(AgainpwdEdit)) {
	// System.out.println("两次密码不一致!");
	// }
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		nameEdit = (EditText) findViewById(R.id.register_editName);
		pwdEdit = (EditText) findViewById(R.id.register_editpsd);
		AgainpwdEdit = (EditText) findViewById(R.id.register_editPsdAgain);
		phoneNumEdit = (EditText) findViewById(R.id.register_editPhonrNum);
		SexEdit = (EditText) findViewById(R.id.register_editSex);
		addressEdit = (EditText) findViewById(R.id.register_editAdress);
		submitButton = (Button) findViewById(R.id.register_submit);
	}

	public void registerClick(View v) {
		name = nameEdit.getText().toString();
		pwd = pwdEdit.getText().toString();
		againPwd = AgainpwdEdit.getText().toString();
		phoneNum = phoneNumEdit.getText().toString();
		sex = SexEdit.getText().toString();
		address = addressEdit.getText().toString();
		RegisterThread rt = new RegisterThread();
		rt.start();
		Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
		startActivity(intent);
		finish();

	}

	Handler h = new Handler() {
		public void handleMessage(Message msg) {
			String s = (String) msg.obj;
			Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
		}
	};

	class RegisterThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 获取客户端对象
			HttpClient client = new DefaultHttpClient();
			// 获取客户端提交方式对象
			HttpPost post = new HttpPost(URL);
			// 封装向服务器发送数据
			ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("username", name));
			list.add(new BasicNameValuePair("password", pwd));
			list.add(new BasicNameValuePair("Tel", phoneNum));
			list.add(new BasicNameValuePair("sex", sex));
			list.add(new BasicNameValuePair("Address", address));
			System.out.println(address);
			try {
				// 将封装好的list数据转换成Entity对象绑定到HttpPost对象。
				post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
				// 通过HttpClient客户端对象执行我的post请求，返回结果HttpResponse对象
				HttpResponse response = client.execute(post);
				// 判断服务器给客户端的响应是正确的
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					byte[] data = new byte[1024];
					int length = -1;
					String s = "";
					while ((length = is.read(data)) != -1) {
						s += new String(data, 0, length);
					}
					// 向主线程的handler对象h发送服务器返回的值
					Message m = Message.obtain();
					m.obj = s;
					RegisterActivity.this.h.sendMessage(m);
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
	}
}
