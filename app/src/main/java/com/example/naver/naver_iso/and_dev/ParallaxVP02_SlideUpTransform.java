package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.naver.naver_iso.Utils;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class ParallaxVP02_SlideUpTransform extends ViewPager {

    public ParallaxVP02_SlideUpTransform(Context context) {
        this(context, null);
    }
    public ParallaxVP02_SlideUpTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true, new VerticalPageTransformer());
        init();
    }
    @Override
    public boolean canScrollHorizontally(int direction) {
//        return super.canScrollHorizontally(direction);
        return false;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return super.canScrollHorizontally(direction);
    }

    private void init() {
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean toIntercept = super.onInterceptTouchEvent(flipXY(ev));
        flipXY(ev);
        return toIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final boolean toHandle = super.onTouchEvent(flipXY(ev));
        flipXY(ev);
        return toHandle;
    }

    private MotionEvent flipXY(MotionEvent ev) {
        final float width = getWidth();
        final float height = getHeight();
        final float x = (ev.getY() / height) * width;
        final float y = (ev.getX() / width) * height;
        ev.setLocation(x, y);
        return ev;
    }

    private static final class VerticalPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            final int pageWidth = view.getWidth();
            final int pageHeight = view.getHeight();
            float yPosition = position * pageHeight;

            view.setTranslationX((int)(view.getWidth() * -position));
            view.setTranslationY((int)yPosition);

            Utils.ModulatetAlphaAnim(ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index], ParallaxVP02_PagerInteraction.PositionOffsetPixels, 0.0f, ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index].getWidth(), 1.0f, 0.0f );
            Utils.ModulatetScaleAnim(ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index], ParallaxVP02_PagerInteraction.PositionOffsetPixels, 0.0f, ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index].getWidth(), 1.0f, 0.75f );
            Utils.ModulatetTransYAnim(ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index], ParallaxVP02_PagerInteraction.PositionOffsetPixels, 0.0f, ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index].getWidth(), 0, ParallaxVP02_MainActivity.vp02RL_Array[ParallaxVP02_PagerInteraction.cuttentPage_Index].getHeight() );

            for (int i = 0; i < ParallaxVP02_MainActivity.vp02RL_Array.length; i++) {
                if (ParallaxVP02_PagerInteraction.cuttentPage_Index == i){
                    ParallaxVP02_MainActivity.scrollEnabled = true;
                } else {
                    ParallaxVP02_MainActivity.vp02RL_Array[i].setAlpha(1.0f); ParallaxVP02_MainActivity.vp02RL_Array[i].setScaleY(1.0f); ParallaxVP02_MainActivity.vp02RL_Array[i].setScaleX(1.0f); ParallaxVP02_MainActivity.vp02RL_Array[i].setTranslationY(0);
                }
            }

        }
    }

}