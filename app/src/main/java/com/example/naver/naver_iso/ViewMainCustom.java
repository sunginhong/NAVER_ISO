package com.example.naver.naver_iso;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ViewMainCustom extends View {
    private boolean enabled;
    private float dragStart_point_x = 0;
    private float dragStart_point_y = 0;
    private float dragMove_point_x = 0;
    private float dragMove_point_y = 0;
    private float dragEnd_point_x = 0;
    private float dragEnd_point_y = 0;


    public ViewMainCustom(Context context, AttributeSet attrs){
        super(context, attrs);
        this.enabled = true;
//        Log.v("ssssssss", ""+String.valueOf("9sss"));

        setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dragStart_point_x = event.getX();
                        dragStart_point_y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        dragMove_point_x = event.getX()*1.0f - dragStart_point_x;
                        dragMove_point_y = event.getY()*1.0f - dragStart_point_y;
//                        Log.v("ssssssss", ""+String.valueOf(dragMove_point_y));
                        break;
                }
                return false;
            }
        });
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        return this.enabled ? super.onInterceptTouchEvent(event) : false;
//    }


//    public void setSwipeEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }



}
