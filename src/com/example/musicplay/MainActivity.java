package com.example.musicplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button start, stop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		start.setOnClickListener(startlis);
		stop.setOnClickListener(stoplis);
	}

	private OnClickListener startlis = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startService(new Intent(MainActivity.this, Music.class));
			// 启动服务
		}
	};
	private OnClickListener stoplis = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopService(new Intent(MainActivity.this, Music.class));
			// 停止服务
		}
	};

	public void findView() {
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
	}
}
