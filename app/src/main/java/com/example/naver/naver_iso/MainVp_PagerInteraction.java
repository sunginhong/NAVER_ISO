package com.example.naver.naver_iso;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

public class MainVp_PagerInteraction implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private ViewPager vp;
    private Context mContext = null;
    final int pageWidth;
    static boolean scrollBool = false;
    private CardAdapter mAdapter;

    float startXPosition = 0;
    float endXPosition = 0;
    int positionNEXT;
    int selected_index = 0;
    int selected_index_before = 0;
    int currentIdx = 0;;
    float CAL_IMG = 1.5f;
    double CAL_PAGING = 1.0/ MainActivity.PAGE_ITEM_COUNT;
    float PAPING_ALPHA_LOW = 0.4f;
    String vpDirection;
    public double SIZE_MIN = 1.0;
    public double SIZE_DEF = 1.0;
    public static int currentItemIdx = 0;
    public static int currentItem_scroll_Idx = 0;
    public static int positionMAX = MainActivity.PAGE_ITEM_COUNT-1;
    public static int itemLength = 2;
    private boolean defaultSet = false;
    private float mLastOffset;

    public MainVp_PagerInteraction(ViewPager viewPager) {
        vp = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);

        pageWidth = vp.getWidth();

        vp.setCurrentItem(0);
        Utils.SclaeAnim(MainActivity.pageNav_current, (float) CAL_PAGING, (float) CAL_PAGING, 1.0f, 1.0f, 0.0f, 0.5f, 0);
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

                if ((startXPosition > endXPosition) && (startXPosition - endXPosition) > 10) { vpDirection = "RIGHT"; }
                else if((startXPosition < endXPosition) && (endXPosition - startXPosition) > 10) { vpDirection = "LEFT"; }

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

        positionNEXT = 0;
        if (position < positionMAX){
            positionNEXT = position+1;
        }
        currentItemIdx = position;

        if(positionOffset >= 0.0 && positionOffset < 0.5){
            if (currentItem_scroll_Idx > position){
                currentItem_scroll_Idx = currentItem_scroll_Idx-1;
            }
        }
        if(positionOffset > 0.5 && positionOffset < 1.0){
            if (currentItem_scroll_Idx < itemLength){
                currentItem_scroll_Idx = position+1;
            }
        }
        mLastOffset = positionOffset;

        for (int i = 0; i < MainActivity.main_vp_cardcotainArray.length; i++) {
            if (i == position) {
                MainActivity.main_vp_cardcotainArray[i].bringToFront();
                Utils.ModulatetScaleAnim(MainActivity.bgImgArray[i], positionOffset, 0, 1, 1, 1.4f);
//                Utils.ModulatetScaleAnim(MainActivity.main_vp_cardcotainArray[i], positionOffset, 0, 1, (float) SIZE_DEF, (float) SIZE_MIN);
//                Utils.ModulatetTransYAnim(MainActivity.main_vp_cardcotainArray[i], positionOffset, 0, 1, Utils.dpToPx(10), Utils.dpToPx(25));
                Utils.ModulateAlphaAnim(MainActivity.main_vp_textllArray[i], positionOffset, 0, 0.5f, 1, 0);
            }
            if (i == positionNEXT) {
                Utils.ModulatetScaleAnim(MainActivity.bgImgArray[i], positionOffset, 0, 1, 1.4f, 1);
//                Utils.ModulatetScaleAnim(MainActivity.main_vp_cardcotainArray[i], positionOffset, 0, 1, (float) SIZE_MIN, (float) SIZE_DEF);
//                Utils.ModulatetTransYAnim(MainActivity.main_vp_cardcotainArray[i], positionOffset, 0, 1, Utils.dpToPx(25), Utils.dpToPx(10));
                Utils.ModulateAlphaAnim(MainActivity.main_vp_textllArray[i], positionOffset, 0.5f, 1, 0, 1);
            }

        }
    }

    @Override
    public void onPageSelected(final int position) {
        final int pageWidth = vp.getWidth();
        selected_index = position;
        selected_index_before =  position;

        if (positionNEXT == selected_index) { Utils.SclaeAnim(MainActivity.pageNav_current, (selected_index_before+0)*(float) CAL_PAGING, (selected_index+1)*(float) CAL_PAGING, 1.0f, 1.0f, 0.0f, 0.5f, 300); }
        if (positionNEXT != selected_index) { Utils.SclaeAnim(MainActivity.pageNav_current, (selected_index_before+2)*(float) CAL_PAGING, (selected_index+1)*(float) CAL_PAGING, 1.0f, 1.0f, 0.0f, 0.5f, 300); }

        Utils.delay(4, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                for (int i = 0; i < MainActivity.pvArray.length; i++) {
                    if (MainActivity.pvArray[i] != null) {
                        if (i == position + 1 && i == position - 1) {
                            Utils.TransAnim(MainActivity.bgImgArray[i], pageWidth / CAL_IMG, 0, 0, 0, 600);
                        }
                    }
                }
            }
        });
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}