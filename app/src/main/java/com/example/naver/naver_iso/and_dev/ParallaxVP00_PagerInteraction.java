package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.naver.naver_iso.Utils;

/**
 * Created by Naver on 2018. 7. 26..
 */

public class ParallaxVP00_PagerInteraction implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private ViewPager vp;
    private Context mContext = null;
    final int pageWidth;
    boolean scrollBool = false;

    float startXPosition = 0;
    float endXPosition = 0;

    int selected_index = 0;
    int selected_index_before = 0;
    int currentIdx = 0;;
    float CAL_IMG = 1.5f;
    double CAL_PAGING = 1.0/ ParallaxVP00_MainActivity.ITEM_COUNT;
    float PAPING_ALPHA_LOW = 0.4f;
    String vpDirection = "none";

    public ParallaxVP00_PagerInteraction(ViewPager viewPager) {
        vp = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);
        pageWidth = vp.getWidth();
        Utils.SclaeAnim(ParallaxVP00_MainActivity.pageNav_current, (float) CAL_PAGING, (float) CAL_PAGING, 1.0f, 1.0f, 0.0f, 0.5f, 0);
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
        float pageScrollX = 0;

        for (int i = 0; i < ParallaxVP00_MainActivity.pvArray.length; i++) {
            pageScrollX = -( ParallaxVP00_MainActivity.pvArray[i].getTranslationX() - positionOffsetPixels );
            if (i == currentIdx && i == selected_index_before ){
                ParallaxVP00_MainActivity.pvArray[i].setAlpha( 1 );
            }
            if (i == currentIdx && i != selected_index) {
                ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 + positionOffsetPixels/CAL_IMG  );

            } else { if (i != currentIdx && i == selected_index){
                ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( -vp.getWidth()/CAL_IMG + positionOffsetPixels/CAL_IMG  );
                }
            }
            if (i == currentIdx && i == selected_index_before) {
                ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 + positionOffsetPixels/CAL_IMG );
            }
            if (i == currentIdx+1 && i != selected_index+1 && i != selected_index_before+1) {
                if (pageScrollX > 0 && pageScrollX < pageWidth && selected_index == i){
                    ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( positionOffsetPixels/CAL_IMG - pageWidth/CAL_IMG );
                }
            }
        }

        if (scrollBool){
            for (int i = 0; i < ParallaxVP00_MainActivity.pvArray.length; i++) {
                if (i == currentIdx && i != selected_index) {

                } else { if (i != currentIdx && i == selected_index){
                    Utils.ModulateAlphaAnim(ParallaxVP00_MainActivity.pvArray[i], positionOffsetPixels, 0.0f, vp.getWidth(), PAPING_ALPHA_LOW, 1.0f );
                    }
                }
                if (i == currentIdx && i == selected_index_before) {
                    Utils.ModulateAlphaAnim(ParallaxVP00_MainActivity.pvArray[i], positionOffsetPixels, vp.getWidth(), 0.0f, PAPING_ALPHA_LOW, 1.0f );
                } else if (i == currentIdx){
                    ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 );
                    ParallaxVP00_MainActivity.pvArray[i].setAlpha( 1 );
                }
                if (selected_index+1 == i){
                    ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 );
                    ParallaxVP00_MainActivity.pvArray[i].setAlpha( 1 );
                }
            }
//            for (int i = 0; i < pvArray.length; i++) {
//                pageScrollX = -( pvArray[i].getTranslationX() - positionOffsetPixels );
//                if (i == currentIdx && i != selected_index) {
//                    bgImgArray[i].setTranslationX( 0 + positionOffsetPixels/CAL_IMG  );
//                } else { if (i != currentIdx && i == selected_index){
//                    bgImgArray[i].setTranslationX( -vp.getWidth()/CAL_IMG + positionOffsetPixels/CAL_IMG  );
//                    Utils.ModulateAlphaAnim(pvArray[i], positionOffsetPixels, 0.0f, vp.getWidth(), PAPING_ALPHA_LOW, 1.0f );
//                }
//                }
//                if (i == currentIdx && i == selected_index_before) {
//                    bgImgArray[i].setTranslationX( 0 + positionOffsetPixels/CAL_IMG );
//                    Utils.ModulateAlphaAnim(pvArray[i], positionOffsetPixels, vp.getWidth(), 0.0f, PAPING_ALPHA_LOW, 1.0f );
//                } else if (i == currentIdx){
//                    bgImgArray[i].setTranslationX( 0 );
//                    pvArray[i].setAlpha( 1 );
//                }
//                if (i == currentIdx+1 && i != selected_index+1 && i != selected_index_before+1) {
//                    if (pageScrollX > 0 && pageScrollX < pageWidth && selected_index == i){
//                        bgImgArray[i].setTranslationX( positionOffsetPixels/CAL_IMG - pageWidth/CAL_IMG );
//                    }
//                }
//                if (selected_index+1 == i){
//                    bgImgArray[i].setTranslationX( 0 );
//                    pvArray[i].setAlpha( 1 );
//                }
//            }
        } else {
            for (int i = 0; i < ParallaxVP00_MainActivity.pvArray.length; i++) {
                if (i == currentIdx && i == selected_index){ ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 ); ParallaxVP00_MainActivity.pvArray[i].setAlpha( 1.0f ); }
                if (i == currentIdx && selected_index+1 == i){  ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 );  }
                if (i == currentIdx+1 && i == selected_index_before){ ParallaxVP00_MainActivity.bgImgArray[i].setTranslationX( 0 );  }
            }
        }
    }

    @Override
    public void onPageSelected(final int position) {
        final int pageWidth = vp.getWidth();
        selected_index = position;
        selected_index_before =  position;
        if (vpDirection == "RIGHT") { Utils.SclaeAnim(ParallaxVP00_MainActivity.pageNav_current, (selected_index_before+0)*(float) CAL_PAGING, (selected_index+1)*(float) CAL_PAGING, 1.0f, 1.0f, 0.0f, 0.5f, 300); }
        if (vpDirection == "LEFT") { Utils.SclaeAnim(ParallaxVP00_MainActivity.pageNav_current, (selected_index_before+2)*(float) CAL_PAGING, (selected_index+1)*(float) CAL_PAGING, 1.0f, 1.0f, 0.0f, 0.5f, 300); }

        Utils.delay(4, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                for (int i = 0; i < ParallaxVP00_MainActivity.pvArray.length; i++) { if (i == position+1 && i == position-1){ Utils.TransAnim(ParallaxVP00_MainActivity.bgImgArray[i], pageWidth/CAL_IMG, 0, 0, 0, 600); } }
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}