package com.Mankewan.manke;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NOActivity extends Activity implements OnClickListener {
	
	private Button b;
	
      @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.no);
    	b = (Button)findViewById(R.id.button_warning);
    	b.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}
}
