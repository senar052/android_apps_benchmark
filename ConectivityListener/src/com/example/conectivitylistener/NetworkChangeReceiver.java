package com.example.conectivitylistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {

		String status = NetworkUtil.getConnectivityStatusString(context);
		Log.d("Change in connectivity found", ", Test Successful");

		Toast.makeText(context, status, Toast.LENGTH_LONG).show();
	}
}
