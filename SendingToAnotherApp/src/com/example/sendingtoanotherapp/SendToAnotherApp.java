package com.example.sendingtoanotherapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class SendToAnotherApp extends Activity {

    Button buttonSend;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_another_app);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        address = (EditText) findViewById(R.id.editTextAddress);

        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// Make intent
                if (address.getText().equals(null)) {
                    Uri location = Uri.parse("geo:0,0?q=" + address.getText().toString());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

// Verify it resolves
                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                    boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
                    if (isIntentSafe) {
                        startActivity(mapIntent);
                    }

                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_to_another_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
