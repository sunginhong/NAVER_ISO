package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.naver.naver_iso.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

public class ParallaxVP00_MainActivity extends AppCompatActivity {

    ViewPager vp;
    private ParallaxVP00_PagerInteraction pagerInteraction;
    static View pageNav_current;
    public static final int ITEM_COUNT = 5;
    public static final int ITEM_MARGIN = 0;

    static RelativeLayout pvArray[] = new RelativeLayout[ITEM_COUNT];
    static ImageView bgImgArray[] = new ImageView[ITEM_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallaxvp00_activiry_main);

        final ArrayList<String[]> values = new ArrayList<String[]>();
        if(values.size() == 0) {
            values.add(new String[]{"0", "Article Title0", "subText0", Integer.toString(R.drawable.img5)});
            values.add(new String[]{"1", "Article Title1", "subText1", Integer.toString(R.drawable.img6)});
            values.add(new String[]{"2", "Article Title2", "subText2", Integer.toString(R.drawable.img7)});
            values.add(new String[]{"3", "Article Title3", "subText3", Integer.toString(R.drawable.img8)});
            values.add(new String[]{"4", "Article Title4", "subText4", Integer.toString(R.drawable.img10)});
        }

        ParallaxVP00_MyPagerAdapter mAdapter = new ParallaxVP00_MyPagerAdapter(this, values);
        vp = (ViewPager)findViewById(R.id.vp00_pager);
        vp.setAdapter(mAdapter);
        vp.setClipToPadding(false);
        vp.setPageMargin(ITEM_MARGIN);
        vp.setOffscreenPageLimit(ITEM_COUNT);

        vp.setCurrentItem(0);
        pageNav_current = (View)findViewById(R.id.vp00_pageNav_current);

        pagerInteraction = new ParallaxVP00_PagerInteraction(vp);
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
