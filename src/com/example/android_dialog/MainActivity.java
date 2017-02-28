package com.example.android_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.example.dialog.DialogFactory;

public class MainActivity extends Activity {

	private Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main, null);
		setContentView(view);

		mButton = (Button)findViewById(R.id.button);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {    //创建淡入淡出的dialog，R.layout.dialog_operate2为dialog的显示布局
				
				Dialog dialog = DialogFactory.createAnimDialog(MainActivity.this, R.layout.dialog_operate2, null, android.R.anim.fade_in, android.R.anim.fade_out);
				dialog.show();
			}
		});
	}
}
