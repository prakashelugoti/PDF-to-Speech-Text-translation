package com.example.prakashreddy45.project;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FBLike extends Activity {
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblike);
        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.loadUrl("https://www.google.com/");
    }
}
