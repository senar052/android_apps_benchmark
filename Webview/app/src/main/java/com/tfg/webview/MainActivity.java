package com.tfg.webview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView myWebView = (WebView) findViewById(R.id.webview1);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.setInitialScale(100);
        myWebView.loadUrl("https://software.imdea.org/es/");



    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d("Webview","touched");
        return super.dispatchTouchEvent(ev);
    }

}
