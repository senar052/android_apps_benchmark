package com.upm.tfg.condition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Main extends AppCompatActivity {
        boolean condicion = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activitymain);
            Button button = (Button) findViewById(R.id.buttonMain);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (condicion) {
                        Log.d("Condition:", " passed");
                        startActivity(new Intent(Main.this, secondActivity.class));
                    }
                }
            });
 }




}



