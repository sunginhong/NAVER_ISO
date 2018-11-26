package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.naver.naver_iso.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

/**
 * Created by Naver on 2018. 7. 26..
 */

public class ParallaxVP01_MainActivity extends AppCompatActivity {

    ViewPager vp;
    private ParallaxVP01_PagerInteraction pagerInteraction;
    static View pageNav_current;
    public static final int ITEM_COUNT = 5;
    public static final int ITEM_MARGIN = 0;

    static RelativeLayout pvArray[] = new RelativeLayout[ITEM_COUNT];
    static ImageView bgImgArray[] = new ImageView[ITEM_COUNT];
    static LinearLayout titleArray[] = new LinearLayout[ITEM_COUNT];
    static LottieAnimationView vp01_dotArray[] = new LottieAnimationView[ITEM_COUNT];

    public static LottieAnimationView vp01_dot0;
    public static LottieAnimationView vp01_dot1;
    public static LottieAnimationView vp01_dot2;
    public static LottieAnimationView vp01_dot3;
    public static LottieAnimationView vp01_dot4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallaxvp01_activiry_main);

        final ArrayList<String[]> values = new ArrayList<String[]>();
        if(values.size() == 0) {
            values.add(new String[]{"0", "Article Title0", "subText0", Integer.toString(R.drawable.img5)});
            values.add(new String[]{"1", "Article Title1", "subText1", Integer.toString(R.drawable.img6)});
            values.add(new String[]{"2", "Article Title2", "subText2", Integer.toString(R.drawable.img7)});
            values.add(new String[]{"3", "Article Title3", "subText3", Integer.toString(R.drawable.img8)});
            values.add(new String[]{"4", "Article Title4", "subText4", Integer.toString(R.drawable.img10)});
        }

        ParallaxVP01_MyPagerAdapter mAdapter = new ParallaxVP01_MyPagerAdapter(this, values);
        vp = (ViewPager)findViewById(R.id.vp01_pager);
        vp.setAdapter(mAdapter);
        vp.setClipToPadding(false);
        vp.setPageMargin(ITEM_MARGIN);
        vp.setOffscreenPageLimit(ITEM_COUNT);

        vp.setCurrentItem(0);

        pagerInteraction = new ParallaxVP01_PagerInteraction(vp);

        vp01_dot0 = (LottieAnimationView) findViewById(R.id.vp01_dot0);
        vp01_dot1 = (LottieAnimationView) findViewById(R.id.vp01_dot1);
        vp01_dot2 = (LottieAnimationView) findViewById(R.id.vp01_dot2);
        vp01_dot3 = (LottieAnimationView) findViewById(R.id.vp01_dot3);
        vp01_dot4 = (LottieAnimationView) findViewById(R.id.vp01_dot4);
        vp01_dotArray[0] = vp01_dot0;
        vp01_dotArray[1] = vp01_dot1;
        vp01_dotArray[2] = vp01_dot2;
        vp01_dotArray[3] = vp01_dot3;
        vp01_dotArray[4] = vp01_dot4;
        vp01_dot0.setProgress(1);
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}