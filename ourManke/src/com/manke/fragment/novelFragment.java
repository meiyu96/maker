package com.manke.fragment;

import android.content.Intent;
import android.net.Uri;
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

public class novelFragment extends Fragment {

	private ListView novellist;
	private int[] novelimageId = new int[] { R.drawable.dalaoshi,
			R.drawable.dxc, R.drawable.gdu, R.drawable.lgcr, R.drawable.ngnl,
			R.drawable.re0, R.drawable.sao, R.drawable.yh, R.drawable.yly, };
	novellistAdapter noveladapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View view = inflater.inflate(R.layout.novel_fragment, null);
		novellist = (ListView) view.findViewById(R.id.nodellistView);
		novellist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				switch (position) {
				case 0:
					Intent videoint = new Intent();
					videoint.setAction("android.intent.action.VIEW");
					Uri uri = Uri
							.parse("http://xs.dmzj.com/1224/index.shtml");
					videoint.setData(uri);
					startActivity(videoint);
					break;
				case 1:
					Intent videoint1 = new Intent();
					videoint1.setAction("android.intent.action.VIEW");
					Uri uri1 = Uri
							.parse("http://xs.dmzj.com/1522/index.shtml");
					videoint1.setData(uri1);
					startActivity(videoint1);
					break;
				case 2:
					//火影
					Intent videoint2 = new Intent();
					videoint2.setAction("android.intent.action.VIEW");
					Uri uri2 = Uri
							.parse("http://xs.dmzj.com/1826/index.shtml");
					videoint2.setData(uri2);
					startActivity(videoint2);
					break;
				case 3:
					//海贼王
					Intent videoint3 = new Intent();
					videoint3.setAction("android.intent.action.VIEW");
					Uri uri3 = Uri
							.parse("http://xs.dmzj.com/271/index.shtml");
					videoint3.setData(uri3);
					startActivity(videoint3);
					break;
				case 4:
					//进击的巨人
					Intent videoint4 = new Intent();
					videoint4.setAction("android.intent.action.VIEW");
					Uri uri4 = Uri
							.parse("http://xs.dmzj.com/1977/index.shtml");
					videoint4.setData(uri4);
					startActivity(videoint4);
					break;
				case 5:
					//七龙珠
					Intent videoint5 = new Intent();
					videoint5.setAction("android.intent.action.VIEW");
					Uri uri5 = Uri
							.parse("http://xs.dmzj.com/1873/index.shtml");
					videoint5.setData(uri5);
					startActivity(videoint5);
					break;
				case 6:
					//清时明月
					Intent videoint6= new Intent();
					videoint6.setAction("android.intent.action.VIEW");
					Uri uri6 = Uri
							.parse("http://xs.dmzj.com/1279/index.shtml");
					videoint6.setData(uri6);
					startActivity(videoint6);
					break;
				case 7:
					//镇魂街
					Intent videoint7 = new Intent();
					videoint7.setAction("android.intent.action.VIEW");
					Uri uri7 = Uri
							.parse("http://www.wuyanxia.net/read/157.html");
					videoint7.setData(uri7);
					startActivity(videoint7);
					break;
				case 8:
					//柯南
					Intent videoint8= new Intent();
					videoint8.setAction("android.intent.action.VIEW");
					Uri uri8 = Uri
							.parse("http://xs.dmzj.com/1132/index.shtml");
					videoint8.setData(uri8);
					startActivity(videoint8);
					break;
					
				default:
					break;
				}
			}
		});
		noveladapter = new novellistAdapter();
		novellist.setAdapter(noveladapter);
		return view;
	}

	class novellistAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return novelimageId.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			show sh = null;
			if (convertView == null) {
				convertView = getLayoutInflater(getArguments()).inflate(
						R.layout.novel_ui, null);
				sh = new show();
				sh.novelimage = (ImageView) convertView
						.findViewById(R.id.novelimageView);
				sh.editorvv=(TextView)convertView.findViewById(R.id.editorText);
				sh.jianjievv=(TextView)convertView.findViewById(R.id.textViewnoveljianjie);
				convertView.setTag(sh);
			} else {
				sh = (show) convertView.getTag();
			}
			sh.novelimage.setImageResource(novelimageId[position]);
			switch (position) {
			case 0:
				sh.editorvv.setText("渡航");
				sh.jianjievv.setText("别扭的，没有朋友，没有女朋友.");
				break;
			case 1:
				sh.editorvv.setText("大森藤野 ");
				sh.jianjievv.setText("迷宫都市欧拉丽——这是一座..");
				break;
			case 2:
				sh.editorvv.setText("川原砾");
				sh.jianjievv.setText("人类初次接触的地球..");
				break;
			case 3:
				sh.editorvv.setText("谷川流");
				sh.jianjievv.setText("西宫市北高中学一年五班.");
				break;
			case 4:
				sh.editorvv.setText("赤松中学");
				sh.jianjievv.setText("我绝对要……成为亚莉 ..");
				break;
			case 5:
				sh.editorvv.setText("长月达平");
				sh.jianjievv.setText("走出便利商店要回家的高中生?..");
				break;
			case 6:
				sh.editorvv.setText("榎宫佑");
				sh.jianjievv.setText("尼特族又闭门不出，但是在网..");
				break;
			case 7:
				sh.editorvv.setText("川原砾");
				sh.jianjievv.setText("无法完成攻略就无法离开游..");
				break;
			case 8:
				sh.editorvv.setText("橘公司 ");
				sh.jianjievv.setText("主人公五河士道只是一名普通的..");
				break;
			default:
				break;
			}
			return convertView;
		}

		class show {
			private ImageView novelimage;
			private TextView editorvv,jianjievv;
		}
	}
}
