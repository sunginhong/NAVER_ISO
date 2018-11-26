package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.naver.naver_iso.R;
import com.example.naver.naver_iso.Utils;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class ParallaxVP02_MainActivity extends AppCompatActivity {
    public static ViewPager vp;
    private ParallaxVP02_PagerInteraction pagerInteraction;
    static View pageNav_current;

    public static final int ITEM_COUNT = 7;
    public static final int ITEM_MARGIN = 0;
    public static boolean scrollEnabled = false;

    static RelativeLayout vp02RL_Array[] = new RelativeLayout[ITEM_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallaxvp02_activiry_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final ArrayList<String[]> values = new ArrayList<String[]>();
        if(values.size() == 0) {
            values.add(new String[]{"0", "Article Title4", "subText4", Integer.toString(R.drawable.img11)});
            values.add(new String[]{"1", "Article Title0", "subText0", Integer.toString(R.drawable.img5)});
            values.add(new String[]{"2", "Article Title1", "subText1", Integer.toString(R.drawable.img6)});
            values.add(new String[]{"3", "Article Title2", "subText2", Integer.toString(R.drawable.img7)});
            values.add(new String[]{"4", "Article Title3", "subText3", Integer.toString(R.drawable.img10)});
            values.add(new String[]{"5", "Article Title4", "subText4", Integer.toString(R.drawable.img11)});
            values.add(new String[]{"6", "Article Title0", "subText0", Integer.toString(R.drawable.img5)});
        }


        ParallaxVP02_MyPagerAdapter mAdapter = new ParallaxVP02_MyPagerAdapter(this, values);
        vp = (ViewPager) findViewById(R.id.vp02_pager);
        vp.setAdapter(mAdapter);
        vp.setClipToPadding(false);
        vp.setPageMargin(ITEM_MARGIN);
        vp.setOffscreenPageLimit(ITEM_COUNT);

        pagerInteraction = new ParallaxVP02_PagerInteraction(vp);

        Utils.delayMin(3, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                vp.setCurrentItem(1, false);
            }
        });
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