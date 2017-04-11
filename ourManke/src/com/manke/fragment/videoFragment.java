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

public class videoFragment extends Fragment {
	private ListView videolist;
	private int[] videoImageId = new int[] { R.drawable.djsy,
			R.drawable.hjhzblr, R.drawable.hyrz, R.drawable.hzw,
			R.drawable.jjdjr, R.drawable.qlz, R.drawable.qsmy, R.drawable.zhj,
			R.drawable.timg };
	videolistAdapter videoAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View view = inflater.inflate(R.layout.video_fragment, null);


		videolist = (ListView) view.findViewById(R.id.VideolistView);

		videolist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根

				switch (position) {
				case 0:
					// 刀剑
					Intent videoint = new Intent();
					videoint.setAction("android.intent.action.VIEW");
					Uri uri = Uri
							.parse("http://www.iqiyi.com/lib/m_200022514.html?src=search");
					videoint.setData(uri);
					startActivity(videoint);
					break;
				case 1:
					// 画江湖
					Intent videoint1 = new Intent();
					videoint1.setAction("android.intent.action.VIEW");
					Uri uri1 = Uri
							.parse("http://www.iqiyi.com/lib/m_205198114.html?src=search");
					videoint1.setData(uri1);
					startActivity(videoint1);
					break;
				case 2:
					// 火影
					Intent videoint2 = new Intent();
					videoint2.setAction("android.intent.action.VIEW");
					Uri uri2 = Uri
							.parse("http://www.iqiyi.com/lib/m_200042314.html");
					videoint2.setData(uri2);
					startActivity(videoint2);
					break;
				case 3:
					// 海贼王
					Intent videoint3 = new Intent();
					videoint3.setAction("android.intent.action.VIEW");
					Uri uri3 = Uri
							.parse("http://www.iqiyi.com/a_19rrhb3xvl.html#vfrm=2-3-0-1");
					videoint3.setData(uri3);
					startActivity(videoint3);
					break;
				case 4:
					// 进击的巨人
					Intent videoint4 = new Intent();
					videoint4.setAction("android.intent.action.VIEW");
					Uri uri4 = Uri
							.parse("http://www.iqiyi.com/a_19rrh9nhol.html#vfrm=2-3-0-1");
					videoint4.setData(uri4);
					startActivity(videoint4);
					break;
				case 5:
					// 七龙珠
					Intent videoint5 = new Intent();
					videoint5.setAction("android.intent.action.VIEW");
					Uri uri5 = Uri
							.parse("http://www.iqiyi.com/lib/m_200025414.html?src=search");
					videoint5.setData(uri5);
					startActivity(videoint5);
					break;
				case 6:
					// 清时明月
					Intent videoint6 = new Intent();
					videoint6.setAction("android.intent.action.VIEW");
					Uri uri6 = Uri
							.parse("http://www.iqiyi.com/lib/m_206857814.html?src=search");
					videoint6.setData(uri6);
					startActivity(videoint6);
					break;
				case 7:
					// 镇魂街
					Intent videoint7 = new Intent();
					videoint7.setAction("android.intent.action.VIEW");
					Uri uri7 = Uri
							.parse("http://www.iqiyi.com/lib/m_210318114.html?src=search");
					videoint7.setData(uri7);
					startActivity(videoint7);
					break;
				case 8:
					// 柯南
					Intent videoint8 = new Intent();
					videoint8.setAction("android.intent.action.VIEW");
					Uri uri8 = Uri
							.parse("http://www.iqiyi.com/lib/m_204938014.html?src=search");
					videoint8.setData(uri8);
					startActivity(videoint8);
					break;

				default:
					break;
				}

			}

		});
		videoAdapter = new videolistAdapter();
		videolist.setAdapter(videoAdapter);

		return view;
	}

	class videolistAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return videoImageId.length;
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
						R.layout.video_ui, null);
				sh = new show();
				sh.videoimage = (ImageView) convertView
						.findViewById(R.id.imageViewph);
				sh.namevv = (TextView) convertView
						.findViewById(R.id.textVideoName);
				sh.jianvv = (TextView) convertView
						.findViewById(R.id.jianjietext2);
				convertView.setTag(sh);
			} else {
				sh = (show) convertView.getTag();
			}
			sh.videoimage.setImageResource(videoImageId[position]);
			switch (position) {
			case 0:
				sh.namevv.setText("刀剑神域");
				sh.jianvv.setText("2022年，人类实现了现实世界和假想空间的融..");
				break;
			case 1:
				sh.namevv.setText("画江湖之不良人第一季");
				sh.jianvv.setText("《画江湖之不良人》是北京若森数字科技有限公司继动画..");
				break;
			case 2:
				sh.namevv.setText("火影忍者");
				sh.jianvv.setText("这是一个忍者的世界。因为身上封印着邪恶的九尾妖..");
				break;
			case 3:
				sh.namevv.setText("海贼王");
				sh.jianvv.setText("生长在东海风车村的路飞受到海贼香克斯的精神指引..");
				break;
			case 4:
				sh.namevv.setText("进击的巨人");
				sh.jianvv.setText("【硬汉阿雷】进击的巨人 欢迎大家到 硬汉阿雷.. ");
				break;
			case 5:
				sh.namevv.setText("火影忍者");
				sh.jianvv.setText("《龙珠》原是鸟山明在《少年JUMP》周刊连载的..");
				break;
			case 6:
				sh.namevv.setText("秦时明月之君临天下");
				sh.jianvv.setText("《秦时明月之君临天下》是国产大型3D动画《秦时明月》的第..");
				break;
			case 7:
				sh.namevv.setText("镇魂街");
				sh.jianvv.setText("普通的应届大学毕业生夏铃在求职之际，收到了一条奇..");
				break;
			case 8:
				sh.namevv.setText("名侦探柯南 ");
				sh.jianvv.setText("阳光明媚的一天，东都体育场正进行一场东京....");
				break;
			default:
				break;
			}
			return convertView;
		}

		class show {
			private ImageView videoimage;
			private TextView namevv, jianvv;
		}
	}

}
