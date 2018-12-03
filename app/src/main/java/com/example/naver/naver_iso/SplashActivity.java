package com.example.naver.naver_iso;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class SplashActivity extends AppCompatActivity {

    public static MediaPlayer mMediaPlayer;
    static Context context;
    VideoView splash_videoView;
    View splash_border;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_splash);
        splash_border = (View) findViewById(R.id.splash_border);

//        splash_videoView = (VideoView) findViewById(R.id.splash_videoView);

//        MediaController ctrl = new MediaController(this);
//        ctrl.setVisibility(View.GONE);
        Utils.SclaeAnim(splash_border, 0, 0, 1, 1, 0, 0.5f, 0);
        Utils.delayMin(30, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                Utils.SclaeAnim(splash_border, 0, 1, 1, 1, 0, 0.5f, 400);
                Utils.delayMin(30, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        Utils.SclaeAnim(splash_border, 1, 0, 1, 1, 1, 0.5f, 500);
                        Utils.delayMin(50, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                splashEnd();
                            }
                        });
                    }
                });
            }
        });
//        splashEnd();




//        splash_videoView.setMediaController(ctrl);
//        splash_videoView.setVideoURI(Uri.parse("android.resource://com.example.naver.naver_iso/" + R.raw.videosplash ));
//        splash_videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mMediaPlayer = mp;
////                mMediaPlayer.isLooping();
//
//                Utils.delayMin(10, new Utils.DelayCallback() {
//                    @Override
//                    public void afterDelay() {
//                        mMediaPlayer.start();
//                    }
//                });
//            }
//        });
//
//        splash_videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//
//
//                Utils.delayMin(20, new Utils.DelayCallback() {
//                    @Override
//                    public void afterDelay() {
//
//                    }
//                });
//            }
//        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    private void splashEnd() {
        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
