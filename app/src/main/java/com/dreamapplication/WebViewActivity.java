package com.dreamapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webView);
        url = getIntent().getStringExtra("url");


        if(webView.getParent()!=null)
            ((ViewGroup)webView.getParent()).removeView(webView); // <- fix
        webView.loadUrl(url);
        setContentView(webView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
