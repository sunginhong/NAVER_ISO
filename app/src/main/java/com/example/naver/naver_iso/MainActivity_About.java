package com.example.naver.naver_iso;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.ChangeBounds;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity_About extends AppCompatActivity implements View.OnClickListener{
    int width;
    int height;
    float screenScale;
    CoordinatorLayout about_contain;
    FrameLayout about_toolbar_backbtn;
    TextView about_toolbar_title;
    CollapsingToolbarLayout collapsing_about_toolbar;
    WebView aboutWebview;
    CardView about_contain_card;
    ImageView about_imageview;
    float cardRound_result;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about_main);
        about_imageview = (ImageView) findViewById(R.id.about_imageview);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION_IN);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        about_contain = (CoordinatorLayout) findViewById(R.id.about_contain);
        collapsing_about_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_about_toolbar);
        about_toolbar_backbtn = (FrameLayout)findViewById(R.id.about_toolbar_backbtn);
        about_toolbar_backbtn.setOnClickListener(this);

        Picasso.with(context).load(MainActivity.URL_THUMB_IMG+"main_thumb_thumb_02.png").into(about_imageview);
        Intent intent = getIntent();
        aboutWebview = (WebView)findViewById(R.id.aboutWebview);
        aboutWebview.getSettings().setJavaScriptEnabled(true);

//        setActivityBackgroundColor(R.color.detailBgColor_dimmed2);

        aboutWebview.setWebViewClient(new MainActivity_About.WebViewClientClass());
        Utils.delayMin(50, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                aboutWebview.loadUrl("http://n-interaction.com/");
            }
        });

        settingWebview(aboutWebview);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        about_toolbar_title = (TextView)findViewById(R.id.about_toolbar_title);
        about_toolbar_title.setText("About us.");

        collapsing_about_toolbar.bringToFront();

        about_contain_card = (CardView) findViewById(R.id.about_contain_card);
        about_contain_card.setRadius(Utils.dpToPx(12));
        Utils.delayMin(20, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                about_contain_card.setRadius(Utils.dpToPx(0));
            }
        });

        headerAnim("IN");
        Utils.TransAnim(collapsing_about_toolbar, 0, 0, -collapsing_about_toolbar.getHeight(), 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN);

        Utils.AlphaAnim(about_contain, 0, 0, 0);
        Utils.AlphaAnim(about_imageview, 1, 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN/2);
        Utils.delayMin(2, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { Utils.AlphaAnim(about_contain, 0, 1, MainActivity.MAIN_CARD_TRANS_DURATION_IN); }
        });
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
//        finish();
    }

    @Override
    public void onClick(View view) {
        outAnim();
    }

    private void outAnim(){
        finish();
        this.overridePendingTransition(R.anim.activity_slide_in4, R.anim.activity_slide_out4);

//        ActivityCompat.finishAfterTransition(this);
//        aboutWebview.stopLoading();
//        headerAnim("OUT");
////        Utils.AlphaAnim(aboutWebview, 1, 0, 200);
//        Utils.AlphaAnim(about_imageview, 0, 1, MainActivity.MAIN_CARD_TRANS_DURATION_IN);
//        Utils.AlphaAnim(about_contain, 1, 0, 400);
//        card_round_animator(500, 0f, Utils.dpToPx(12));
//
//        ChangeBounds bounds = new ChangeBounds();
//        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION_OUT);
//        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
//        getWindow().setSharedElementEnterTransition(bounds);
    }

    private void headerAnim(String status) {
        if (status == "OUT") {
            Utils.TransAnim(collapsing_about_toolbar, 0, 0, 0, -collapsing_about_toolbar.getHeight(), 200);
            Utils.delayMin(4, new Utils.DelayCallback() {
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

    private void card_round_animator(int duration, float radiusStart, float radiusEnd) {
        ValueAnimator animator = ValueAnimator.ofFloat(radiusStart, radiusEnd);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                cardRound_result = (float) animation.getAnimatedValue();
                about_contain_card.setRadius(cardRound_result);
            }
        });
        animator.start();
    }

    private void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

}