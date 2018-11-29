package com.example.naver.naver_iso;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DetailActivity_Library extends AppCompatActivity implements View.OnClickListener {
    int width;
    int height;
    float screenScale;
    FrameLayout lib_detail_toolbar_backbtn;
    TextView lib_detail_toolbar_title;
    WebView library_detailWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_library_detail);

        lib_detail_toolbar_backbtn = (FrameLayout)findViewById(R.id.lib_detail_toolbar_backbtn);
        lib_detail_toolbar_backbtn.setOnClickListener(this);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        Intent intent = getIntent();
        String libraryeUrl = intent.getStringExtra("libraryeUrl");
        String libraryeTitle = intent.getStringExtra("libraryeTitle");

        library_detailWebview = (WebView)findViewById(R.id.library_detailWebview);

        library_detailWebview.getSettings().setJavaScriptEnabled(true);
        library_detailWebview.loadUrl(libraryeUrl);
        library_detailWebview.setWebViewClient(new DetailActivity_Library.WebViewClientClass());

        settingWebview(library_detailWebview);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        lib_detail_toolbar_title = (TextView)findViewById(R.id.lib_detail_toolbar_title);
        lib_detail_toolbar_title.setText(libraryeTitle);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void settingWebview(WebView webView){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("app");
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        library_detailWebview.stopLoading();
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onClick(View view) {
        library_detailWebview.stopLoading();
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }
}
