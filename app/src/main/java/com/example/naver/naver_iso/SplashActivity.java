package com.example.naver.naver_iso;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class SplashActivity extends AppCompatActivity {

//    LottieAnimationView splash_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);

//        splash_anim = (LottieAnimationView) findViewById(R.id.splash_anim);
//        splash_anim.loop(true);
//        splash_anim.pauseAnimation();
//        splash_anim.setProgress(0);
//        splash_anim.playAnimation();

        Utils.delay(4, new Utils.DelayCallback() {
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
