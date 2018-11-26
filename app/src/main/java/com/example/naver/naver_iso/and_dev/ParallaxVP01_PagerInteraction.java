package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.naver.naver_iso.Utils;


/**
 * Created by Naver on 2018. 7. 26..
 */


public class ParallaxVP01_PagerInteraction implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private ViewPager vp;
    private Context mContext = null;
    final int pageWidth;
    boolean scrollBool = false;

    float startXPosition = 0;
    float endXPosition = 0;

    int selected_index = 0;
    int selected_index_before = 0;
    int currentIdx = 0;;
    float titleXpos = 0;
    float CAL_IMG = 1.5f;
    double CAL_PAGING = 1.0/ ParallaxVP01_MainActivity.ITEM_COUNT;

    String vpDirection = "none";

    public ParallaxVP01_PagerInteraction(ViewPager viewPager) {
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
                scrollBool = false;
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
        currentIdx = position;
        float CAL_TITLE = 0.8f;
        final int pageWidth = vp.getWidth();
        float pageScrollX = 0;
        for (int i = 0; i < ParallaxVP01_MainActivity.pvArray.length; i++) {
            if ( i == position && position >= 0 ) { ParallaxVP01_MainActivity.bgImgArray[i].setTranslationX(positionOffsetPixels/CAL_IMG + 0);
                Utils.ModulateAlphaAnim(ParallaxVP01_MainActivity.pvArray[i], positionOffsetPixels, 0.0f, vp.getWidth(), 1.0f , 0.4f );
                Utils.ModulateAlphaAnim(ParallaxVP01_MainActivity.titleArray[i], positionOffsetPixels, 0.0f, pageWidth/2, 1.0f, 0.0f);
                Utils.ModulatetTransXAnim(ParallaxVP01_MainActivity.titleArray[i], positionOffsetPixels, 0.0f, pageWidth, 0.0f, ParallaxVP01_MainActivity.titleArray[i].getWidth()/CAL_TITLE);
            } else {
                Utils.ModulateAlphaAnim(ParallaxVP01_MainActivity.titleArray[i], positionOffsetPixels, pageWidth/2, pageWidth, 0.0f, 1.0f);
                Utils.ModulatetTransXAnim(ParallaxVP01_MainActivity.titleArray[i], positionOffsetPixels, 0.0f, pageWidth, -ParallaxVP01_MainActivity.titleArray[i].getWidth()/CAL_TITLE, 0.0f);
            }
        }
        for (int i = 0; i < ParallaxVP01_MainActivity.pvArray.length; i++) {
            if (i == position+1){ ParallaxVP01_MainActivity.bgImgArray[i].setTranslationX(0); }
        }
    }

    @Override
    public void onPageSelected(final int position) {
        final int pageWidth = vp.getWidth();
        selected_index = position;
        selected_index_before =  position;
        for (int i = 0; i < ParallaxVP01_MainActivity.pvArray.length; i++) {
            if (i == position){
                ParallaxVP01_MainActivity.pvArray[i].setAlpha(1.0f);
                ParallaxVP01_MainActivity.vp01_dotArray[i].pauseAnimation();
                ParallaxVP01_MainActivity.vp01_dotArray[i].setProgress(0);
                ParallaxVP01_MainActivity.vp01_dotArray[i].playAnimation();
            } else {
                if (ParallaxVP01_MainActivity.vp01_dotArray[i].getProgress() == 1.0){
                    ParallaxVP01_MainActivity.vp01_dotArray[i].pauseAnimation();
                    ParallaxVP01_MainActivity.vp01_dotArray[i].reverseAnimation();
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}