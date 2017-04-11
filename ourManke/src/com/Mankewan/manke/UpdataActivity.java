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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdataActivity extends Activity {
	private EditText Update_editName, Update_editPwd, Update_editAdress,
			Update_editPhoneNum;
	private String username;
	private String password;
	private String tel;
	private String address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_user);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		Update_editName = (EditText) findViewById(R.id.Update_editName);
		Update_editPwd = (EditText) findViewById(R.id.Update_editPwd);
		Update_editAdress = (EditText) findViewById(R.id.Update_editAdress);
		Update_editPhoneNum = (EditText) findViewById(R.id.Update_editPhoneNum);
		// 显示在textView中
		Intent upent = getIntent();
		Update_editName.setText(upent.getStringExtra("name"));
		Update_editPwd.setText((upent.getStringExtra("pass")));
		Update_editAdress.setText(upent.getStringExtra("add"));
		Update_editPhoneNum.setText(upent.getStringExtra("tel"));
		System.out.println("77777:");
	}

	public void Update_ButtonSubmit(View v) {
		username = Update_editName.getText().toString();
		password = Update_editPwd.getText().toString();
		tel = Update_editPhoneNum.getText().toString();
		address = Update_editAdress.getText().toString();
		UpdateTask ut = new UpdateTask();
		ut.execute("" + username, password, tel, address);
		Intent intent = new Intent(UpdataActivity.this,LoginActivity.class);
		startActivity(intent);
		finish();
	}

	class UpdateTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String s = "";
			String url = "http://192.168.1.95:8080/TaoService/UpdateService";
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
			paramList.add(new BasicNameValuePair("username", "" + params[0]));
			paramList.add(new BasicNameValuePair("password", "" + params[1]));
			paramList.add(new BasicNameValuePair("tel", "" + params[2]));
			paramList.add(new BasicNameValuePair("address", "" + params[3]));
			try {
				post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();

					byte[] buffer = new byte[1024];
					int l = -1;

					while ((l = is.read(buffer)) != -1) {
						s += new String(buffer, 0, l);
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
			return s;
		}

		@Override
		protected void onPostExecute(String result) {
			if ("0\r\n".equals(result)) {
				Toast.makeText(UpdataActivity.this, "修改失败", Toast.LENGTH_LONG)
						.show();
			} else {
				islogin.name = username;
			}
		}

	}
}
