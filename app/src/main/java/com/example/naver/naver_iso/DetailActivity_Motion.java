package com.example.naver.naver_iso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;


public class DetailActivity_Motion extends Activity implements View.OnClickListener{
    int width;
    int height;
    float screenScale;
    static Context context;
    FrameLayout motion_detail_toolbar_backbtn;
    TextView motion_detail_toolbar_title;
    WebView motion_detailWebview;
    int deviceRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_motion_detail);

        deviceRotate = getResources().getConfiguration().orientation;

        motion_detail_toolbar_backbtn = (FrameLayout)findViewById(R.id.motion_detail_toolbar_backbtn);
        motion_detail_toolbar_backbtn.setOnClickListener(this);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        Intent intent = getIntent();
        String motionUrl = intent.getStringExtra("motionUrl");
        String motionTitle = intent.getStringExtra("motionTitle");

        motion_detailWebview = (WebView)findViewById(R.id.motion_detailWebview);
        motion_detailWebview.loadUrl(motionUrl);
        motion_detailWebview.setWebViewClient(new DetailActivity_Motion.WebViewClientClass());

        motion_detailWebview.getSettings().setJavaScriptEnabled(true);
        motion_detailWebview.getSettings().setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.36 (KHTML, like Gecko) Chrome/13.0.766.0 Safari/534.36");
//        motion_detailWebview.setWebChromeClient(new FullscreenableChromeClient(this));

        motion_detailWebview.setWebChromeClient(new FullscreenableChromeClient(this));

//        settingWebview(motion_detailWebview);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        motion_detail_toolbar_title = (TextView)findViewById(R.id.motion_detail_toolbar_title);
        motion_detail_toolbar_title.setText(motionTitle);
        Log.v("ssssssss", ""+String.valueOf("C"));
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
        motion_detailWebview.stopLoading();
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
        motion_detailWebview.stopLoading();
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//    }


}

//public class DetailActivity_Motion extends Activity {
//
//    VideoView videoView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_motion_detail);
//    }
//        Intent intent = getIntent();
//        String motionReelUrl = intent.getStringExtra("motionReelUrl");
//
//        videoView = (VideoView)findViewById(R.id.motion_reel_detailVideoView);
//        MediaController controller = new MediaController(DetailActivity_Motion.this);
//        videoView.setMediaController(controller);
//        videoView.requestFocus();
//        videoView.setVideoURI(Uri.parse(motionReelUrl));
//        videoView.setOnPreparedListener(new OnPreparedListener() {
//
//            // 동영상 재생준비가 완료된후 호출되는 메서드
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                playVideo();
//            }
//        });
//        // 동영상 재생이 완료된걸 알수있는 리스너
//        videoView.setOnCompletionListener(new OnCompletionListener() {
//            // 동영상 재생이 완료된후 호출되는 메서드
//            public void onCompletion(MediaPlayer player) {
//
//            }
//        });
//    }
//    private void playVideo() {
//        videoView.seekTo(0);
//        videoView.start();
//    }
//
//    private void stopVideo() {
//        videoView.pause();
//        videoView.stopPlayback();
//    }
