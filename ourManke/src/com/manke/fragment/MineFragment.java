package com.manke.fragment;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.Mankewan.manke.LoginActivity;
import com.Mankewan.manke.R;
import com.Mankewan.manke.UpdataActivity;
import com.Mankewan.manke.xiangxi;
import com.manke.info.islogin;
import com.manke.info.over;
import com.manke.info.use;

public class MineFragment extends Fragment implements OnClickListener {

	private TextView MeNameText;
	final String url = "http://192.168.1.195:8080/TaoService/QueryByNameService";
	private ArrayList<use> dataList = new ArrayList<use>();
	private ArrayList<over> da = new ArrayList<over>();
	private ImageButton bt;
	private String name, price;
	private shopingAdapter adapter;
	private ListView ls;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.me_fragment, null);
		new getNameValue().execute();
		MeNameText = (TextView) view.findViewById(R.id.me_name);
		bt = (ImageButton) view.findViewById(R.id.MeUpdate_imageButton);
		bt.setOnClickListener(this);
		ls = (ListView) view.findViewById(R.id.me_listView);
		ls.setAdapter(adapter);
		MeNameText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
		});
		if (islogin.name.trim().equals("s")) {
			MeNameText.setText("请先登录");
		} else {
			MeNameText.setText(islogin.name);
		}

		return view;
	}

	class getNameValue extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO 自动生成的方法存根
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			ArrayList<NameValuePair> alnv = new ArrayList<NameValuePair>();
			alnv.add(new BasicNameValuePair("username", islogin.name));
			try {
				post.setEntity(new UrlEncodedFormEntity(alnv, HTTP.UTF_8));
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					byte[] data = new byte[1024];
					int length = -1;
					String s = "";
					while ((length = is.read(data)) != -1) {
						s += new String(data, 0, length);
					}
					JSONObject jo = new JSONObject(s);
					JSONArray ja = jo.getJSONArray("user");
					for (int i = 0; i < ja.length(); i++) {
						JSONObject temp = ja.getJSONObject(i);
						use ubit = new use();
						// 解析数据
						ubit.setUsername(temp.getString("username"));
						ubit.setPassword(temp.getString("password"));
						ubit.setAddress(temp.getString("address"));
						ubit.setTel(temp.getString("tel"));
						dataList.add(ubit);
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return null;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (dataList.size() == 0) {
			System.out.println("1111111111111111111111");
		} else {
			Intent i = new Intent(getActivity(), UpdataActivity.class);
			i.putExtra("name", dataList.get(0).getUsername());
			i.putExtra("pass", dataList.get(0).getPassword());
			i.putExtra("add", dataList.get(0).getAddress());
			i.putExtra("tel", dataList.get(0).getTel());
			startActivity(i);
		}
	}

	class shopingAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return da.size();
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
				arg1 = getLayoutInflater(getArguments()).inflate(
						R.layout.me_list_ui, null);
				show s = new show();
				s.tvname = (TextView) arg1.findViewById(R.id.goodslistname);
				s.tvsucc = (TextView) arg1.findViewById(R.id.textViewsucc);
				arg1.setTag(s);
			}
			final show s = (show) arg1.getTag();
			s.tvname.setText(xiangxi.truename);
			return arg1;
		}

		class show {
			public TextView tvname, tvsucc;
		}
	}
}
