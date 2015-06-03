package com.example.playaudio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener{
	private MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		Button button1=(Button)findViewById(R.id.button_1);
		Button button2=(Button)findViewById(R.id.button_2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	public void onClick(View v) {
		int resId;
		switch (v.getId()) {
		case R.id.button_1:
			resId = R.raw.a;
			break;
		case R.id.button_2:
			resId = R.raw.b;
			break;
		default:
			resId = R.raw.a;
			break;
		}
		// Release any resources from previous MediaPlayer
		if (mp != null) {
			mp.release();
		}
		// Create a new MediaPlayer to play this sound
		mp = MediaPlayer.create(this, resId);
		mp.start();
		Log.d("Playing Audio...", " ");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		if(null!=mp){
			mp.release();
		}
		super.onDestroy();
	}
}
