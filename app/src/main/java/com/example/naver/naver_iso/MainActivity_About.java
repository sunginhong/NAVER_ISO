package com.example.naver.naver_iso;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity_About extends AppCompatActivity implements View.OnClickListener{
    int width;
    int height;
    float screenScale;
    FrameLayout about_toolbar_backbtn;
    TextView about_toolbar_title;
    CollapsingToolbarLayout collapsing_about_toolbar;
    WebView aboutWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about_main);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        collapsing_about_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_about_toolbar);

        about_toolbar_backbtn = (FrameLayout)findViewById(R.id.about_toolbar_backbtn);
        about_toolbar_backbtn.setOnClickListener(this);

        Intent intent = getIntent();

        aboutWebview = (WebView)findViewById(R.id.aboutWebview);

        aboutWebview.getSettings().setJavaScriptEnabled(true);
        aboutWebview.loadUrl("http://jjangik.com/");
        aboutWebview.setWebViewClient(new MainActivity_About.WebViewClientClass());

        settingWebview(aboutWebview);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        about_toolbar_title = (TextView)findViewById(R.id.about_toolbar_title);
        about_toolbar_title.setText("About us.");

        collapsing_about_toolbar.bringToFront();
        headerAnim("IN");
        Utils.TransAnim(collapsing_about_toolbar, 0, 0, -collapsing_about_toolbar.getHeight(), 0, 400);
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
//        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        outAnim();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onClick(View view) {
        outAnim();
    }

    private void outAnim(){
        headerAnim("OUT");
        Utils.AlphaAnim(aboutWebview, 1, 0, 200);
    }

    private void headerAnim(String status) {
        if (status == "OUT") {
            Utils.TransAnim(collapsing_about_toolbar, 0, 0, 0, -collapsing_about_toolbar.getHeight(), 200);
            Utils.delayMin(10, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { ActivityCompat.finishAfterTransition(MainActivity_About.this); }
            });
        }
        if (status == "IN"){
            Utils.delayMin(2, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(collapsing_about_toolbar, 0, 0, -collapsing_about_toolbar.getHeight(), -collapsing_about_toolbar.getHeight(), 0); }
            });
            Utils.delayMin(42, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(collapsing_about_toolbar, 0, 0, -collapsing_about_toolbar.getHeight(), 0, 400); }
            });
        }
    }
}