package com.example.musicplay;

import com.aidl.IBook;
import com.example.musicplay.service.BookService;
import com.example.musicplay.service.MusicService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	public final static String TAG = "MainActivity";
	Button start, stop, mServiceStart, mServiceStop;
	private IBook bookQuery;
	private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        	bookQuery = IBook.Stub.asInterface(service);
            Log.i(TAG, "连接Service 成功");
            try {
                String s = bookQuery.queryBook(1);
                Log.i(TAG, "从Service得到的字符串：" + s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "连接Service失败");
        }
    };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		//startAndBindService();
	}

	public void findView() {
		start = (Button) findViewById(R.id.start);
		start.setOnClickListener(this);
		stop = (Button) findViewById(R.id.stop);
		stop.setOnClickListener(this);
		mServiceStart = (Button) findViewById(R.id.service_start);
		mServiceStart.setOnClickListener(this);
		mServiceStop = (Button) findViewById(R.id.service_stop);
		mServiceStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.start:
			startService(new Intent(MainActivity.this, MusicService.class));
			break;
		case R.id.stop:
			stopService(new Intent(MainActivity.this, MusicService.class));
			// 停止服务
			break;
		case R.id.service_start:

			break;
		case R.id.service_stop:

			break;

		default:
			break;
		}
	}
	
	private void startAndBindService() {
        Intent service = new Intent(MainActivity.this, BookService.class);
        //startService(service);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
