package com.Mankewan.manke;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MeActivity extends Activity {

	public static String value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
	
		
		
		Intent mei = getIntent();
		value = mei.getStringExtra("key");
		System.out.println("MMMMM:" + value);
	}

}
