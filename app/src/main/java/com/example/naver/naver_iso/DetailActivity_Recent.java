package com.example.naver.naver_iso;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.ChangeBounds;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tsengvn.typekit.TypekitContextWrapper;

public class DetailActivity_Recent extends AppCompatActivity implements View.OnClickListener {
    int width;
    int height;
    float screenScale;
    static Context context;

    CardView recent_contain_card;
    FrameLayout recent_toolbar_backbtn;
    TextView recent_toolbar_title;
    WebView recent_Webview;
    ImageView recent_imageview;
    int deviceRotate;
    float cardRound_result;

    private AppBarLayout recent_appbar;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_recent_detail);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION_IN);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        recent_contain_card = (CardView)findViewById(R.id.recent_contain_card);
        recent_toolbar_backbtn = (FrameLayout)findViewById(R.id.recent_toolbar_backbtn);
        recent_toolbar_backbtn.setOnClickListener(this);

        recent_appbar = (AppBarLayout)findViewById(R.id.recent_appbar);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        Intent intent = getIntent();
        String recentUrl = intent.getStringExtra("recentUrl");
        final String recentTitle = intent.getStringExtra("recentTitle");
        String recentImg = intent.getStringExtra("recentImg");

        recent_Webview = (WebView)findViewById(R.id.recent_detailWebview);

        recent_Webview.getSettings().setJavaScriptEnabled(true);
        recent_Webview.loadUrl(recentUrl);
        recent_Webview.setWebViewClient(new DetailActivity_Recent.WebViewClientClass());
        recent_Webview.setWebChromeClient(new FullscreenableChromeClient(this));

//        setActivityBackgroundColor(R.color.detailBgColor_dimmed2);

        recent_Webview.getSettings().setJavaScriptEnabled(true);
        recent_Webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        recent_Webview.getSettings().setLoadsImagesAutomatically(true);
        recent_Webview.getSettings().setUseWideViewPort(true);
        recent_Webview.getSettings().setSupportZoom(false);
        recent_Webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        recent_Webview.getSettings().setAppCacheEnabled(false);
        recent_Webview.getSettings().setDomStorageEnabled(true);
        recent_Webview.getSettings().setAllowFileAccess(true);

        recent_Webview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(view.getContext(), DetailActivity_Library_LinkPage.class);
                intent.putExtra("libraryeUrl" , url);
                intent.putExtra("libraryeTitle" , recentTitle);
                view.getContext().startActivity(intent);
                MainActivity.webviewDetailView = true;
                return true;
            }
        });

        recent_imageview = (ImageView)findViewById(R.id.recent_imageview);
        Picasso.with(context).load(recentImg).into(recent_imageview);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        recent_toolbar_title = (TextView)findViewById(R.id.recent_toolbar_title);
        recent_toolbar_title.setText(recentTitle);

        recent_Webview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolledDistance = scrollY;
                if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
                else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }

                if (scrollDirection == "DOWN" && scrolledDistance < HIDE_THRESHOLD && appbarVisible) {
                    ScrollHederAnim.HeaderHide(recent_appbar, -recent_appbar.getHeight(), Utils.dpToPx(0), 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                } else if (scrollDirection == "UP" && scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
                    ScrollHederAnim.HeaderShow(recent_appbar, Utils.dpToPx(0), -recent_appbar.getHeight(), 300);
                    appbarVisible = true;
                    scrolledDistance = 0;
                }
                if (scrollDirection == "DOWN" && scrolledDistance > HIDE_THRESHOLD && appbarVisible){
                    ScrollHederAnim.HeaderHide(recent_appbar, -recent_appbar.getHeight(), Utils.dpToPx(0), 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                }
            }
        });

        recent_appbar.bringToFront();
        recent_contain_card.setRadius(Utils.dpToPx(12));
        Utils.delayMin(20, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                recent_contain_card.setRadius(Utils.dpToPx(0));
            }
        });

        headerAnim("IN");
        Utils.TransAnim(recent_appbar, 0, 0, -recent_appbar.getHeight(), 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN);

//        Utils.AlphaAnim(recent_contain_card, 0, 0, 0);
        Utils.AlphaAnim(recent_imageview, 1, 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN/2);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onResume() {
        if (MainActivity.webviewDetailView == false){

        } else {
            MainActivity.webviewDetailView = false;
            this.overridePendingTransition(R.anim.activity_slide_in4, R.anim.activity_slide_out4);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        outAnim();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View view) {
        if (!appbarVisible){
            outAnim();
        }
    }

    private void outAnim(){
        finish();
        this.overridePendingTransition(R.anim.activity_slide_in4, R.anim.activity_slide_out4);
    }

    private void headerAnim(String status) {
        if (status == "OUT") {
            Utils.delayMin(4, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(recent_appbar, 0, 0, 0, -recent_appbar.getHeight(), 000); }
            });
        }
        if (status == "IN"){
            Utils.delayMin(2, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(recent_appbar, 0, 0, -recent_appbar.getHeight(), -recent_appbar.getHeight(), 0); }
            });
            Utils.delayMin(42, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(recent_appbar, 0, 0, -recent_appbar.getHeight(), 0, 400); }
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
                recent_contain_card.setRadius(cardRound_result);
            }
        });
        animator.start();
    }

    private void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

}