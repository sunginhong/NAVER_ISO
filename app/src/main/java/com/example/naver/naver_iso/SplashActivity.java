package com.example.naver.naver_iso;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class SplashActivity extends AppCompatActivity {

    public static MediaPlayer mMediaPlayer;
    static Context context;
    VideoView splash_videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);

        splash_videoView = (VideoView) findViewById(R.id.splash_videoView);


        MediaController ctrl = new MediaController(this);
        ctrl.setVisibility(View.GONE);
        splash_videoView.setMediaController(ctrl);
//        splash_videoView.setVideoURI(Uri.parse("android.resource://com.example.naver.naver_iso/" + R.raw.videosplash ));
        splash_videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaPlayer = mp;
                mMediaPlayer.start();
            }
        });

        Utils.delay(10, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
            Intent intent=new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            }

        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }



}
