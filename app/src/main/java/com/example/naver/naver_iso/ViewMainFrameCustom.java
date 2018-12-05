package com.example.naver.naver_iso;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class ViewMainFrameCustom extends FrameLayout {
    private boolean enabled;

    public ViewMainFrameCustom(Context context, AttributeSet attrs){
        super(context, attrs);
        this.enabled = true;
//        Log.v("ssssssss", ""+String.valueOf("9sss"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("ssssssss", ""+String.valueOf(event));

        return false;
//        return this.enabled ? super.onTouchEvent(event) : false;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        return this.enabled ? super.onInterceptTouchEvent(event) : false;
//    }


//    public void setSwipeEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }



}
