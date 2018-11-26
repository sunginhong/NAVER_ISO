package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.naver.naver_iso.Utils;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class ParallaxVP02_PagerInteraction implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private ViewPager vp;
    private Context mContext = null;
    final int pageWidth;
    boolean scrollBool = false;
    float startXPosition = 0;
    float endXPosition = 0;
    static float PositionOffsetPixels = 0;
    static int cuttentPage_Index = 0;
    double CAL_PAGING = 1.0/(ParallaxVP02_MainActivity.ITEM_COUNT-2);
    String vpDirection = "none";

    public ParallaxVP02_PagerInteraction(ViewPager viewPager) {
        vp = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);
        pageWidth = vp.getWidth();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scrollBool = true;
                startXPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (ParallaxVP02_MainActivity.scrollEnabled && !scrollBool){
                    if (PositionOffsetPixels < vp.getWidth()/2){
                        vp.setCurrentItem(cuttentPage_Index+1, true);
                    } else {
                        vp.setCurrentItem(cuttentPage_Index+1, true);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                endXPosition = event.getX();
                if(scrollBool) {
                    if ((startXPosition > endXPosition) && (startXPosition - endXPosition) > 10) { vpDirection = "RIGHT"; }
                    else if((startXPosition < endXPosition) && (endXPosition - startXPosition) > 10) { vpDirection = "LEFT"; }
                }
                break;
        }
        return false;
    }

    @Override
    public void transformPage(View page, float position) {
    }

    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
        cuttentPage_Index = position;
        PositionOffsetPixels = positionOffsetPixels;
        scrollBool = false;
    }

    @Override
    public void onPageSelected(final int position) {
        Utils.delay(3, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                if ( position == 0 ) { vp.setCurrentItem(ParallaxVP02_MainActivity.ITEM_COUNT-2, false); cuttentPage_Index = ParallaxVP02_MainActivity.ITEM_COUNT-2; }
                if ( position == ParallaxVP02_MainActivity.ITEM_COUNT-1 ) { vp.setCurrentItem(1, false); cuttentPage_Index = 1;  }
            }
        });
        final int pageWidth = vp.getWidth();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }



}