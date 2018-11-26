package com.example.naver.naver_iso.and_dev;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.naver.naver_iso.R;

/**
 * Created by Naver on 2018. 7. 30..
 */

public class Sidemenu00_MainActivity extends AppCompatActivity {

    FrameLayout sidemenu00_fl;
    static View sidemenu00_bg;
    static ImageView sidemenu00_logo;
    public static int currentIdx;
    public static int beforeIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenu00_activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sidemenu00_fl = (FrameLayout) findViewById(R.id.sidemenu00);
        sidemenu00_bg = (View) findViewById(R.id.sidemenu00_bg);
        sidemenu00_logo = (ImageView) findViewById(R.id.sidemenu00_logo);

        sidemenu00_fl.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick( View view ) {
                        Intent intent = new Intent(getApplicationContext(), Sidemenu00_CategotyActivity.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Sidemenu00_MainActivity.this, view, "side_menu00_trasit");
                        startActivity(intent, options.toBundle());
                        intent.putExtra("beforeIdx", beforeIdx);
                        intent.putExtra("currentIdx", currentIdx);
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        currentIdx = 0;
        beforeIdx = 0;
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }


}