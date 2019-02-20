package com.example.naver.naver_iso;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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

public class DetailActivity_Library_LinkPage extends AppCompatActivity implements View.OnClickListener {

    int width;
    int height;
    float screenScale;
    FrameLayout detail_linkpage_toolbar_backbtn;
    TextView detail_linkpage_LIBRARY_toolbar_title;
    WebView detail_linkpage_LIBRARY_detailWebview;

    private AppBarLayout detail_linkpage_appbar;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_detail_linkpage);

        detail_linkpage_toolbar_backbtn = (FrameLayout)findViewById(R.id.detail_linkpage_toolbar_backbtn);
        detail_linkpage_toolbar_backbtn.setOnClickListener(this);

        detail_linkpage_appbar = (AppBarLayout)findViewById(R.id.detail_linkpage_appbar);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        Intent intent = getIntent();
        String detail_linkpage_Url = intent.getStringExtra("libraryeUrl");
        String detail_linkpage_Title = intent.getStringExtra("libraryeTitle");

        detail_linkpage_LIBRARY_detailWebview = (WebView)findViewById(R.id.detail_linkpage_detailWebview);

        detail_linkpage_LIBRARY_detailWebview.getSettings().setJavaScriptEnabled(true);
        detail_linkpage_LIBRARY_detailWebview.getSettings().setLoadWithOverviewMode(true);
        detail_linkpage_LIBRARY_detailWebview.getSettings().setUseWideViewPort(true);

        detail_linkpage_LIBRARY_detailWebview.getSettings().setSupportZoom(true);
        detail_linkpage_LIBRARY_detailWebview.getSettings().setBuiltInZoomControls(true);
        detail_linkpage_LIBRARY_detailWebview.getSettings().setDisplayZoomControls(false);

        detail_linkpage_LIBRARY_detailWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        detail_linkpage_LIBRARY_detailWebview.setScrollbarFadingEnabled(false);

        detail_linkpage_LIBRARY_detailWebview.loadUrl(detail_linkpage_Url);
        detail_linkpage_LIBRARY_detailWebview.setWebViewClient(new DetailActivity_Library_LinkPage.WebViewClientClass());
        detail_linkpage_LIBRARY_detailWebview.setWebChromeClient(new FullscreenableChromeClient(this));

        settingWebview(detail_linkpage_LIBRARY_detailWebview);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        detail_linkpage_LIBRARY_toolbar_title = (TextView)findViewById(R.id.detail_linkpage_toolbar_title);
        detail_linkpage_LIBRARY_toolbar_title.setText(detail_linkpage_Title);
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
        this.overridePendingTransition(R.anim.activity_slide_in3, R.anim.activity_slide_out3);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        detail_linkpage_LIBRARY_detailWebview.stopLoading();
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        if (!appbarVisible){
            detail_linkpage_LIBRARY_detailWebview.stopLoading();
            ActivityCompat.finishAfterTransition(this);
            this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
        }
    }
}
