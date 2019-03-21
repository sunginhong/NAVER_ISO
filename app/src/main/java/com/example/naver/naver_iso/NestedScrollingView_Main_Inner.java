package com.example.naver.naver_iso;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NestedScrollingView_Main_Inner extends NestedScrollView {
    private int mState = RecyclerView.SCROLL_STATE_IDLE;

    public interface NestedScrollViewScrollStateListener {
        void onNestedScrollViewStateChanged(int state);
    }

    public void setScrollListener(NestedScrollingView_Library.NestedScrollViewScrollStateListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private NestedScrollingView_Library.NestedScrollViewScrollStateListener mScrollListener;

    public NestedScrollingView_Main_Inner(Context context) {
        super(context);
    }

    public NestedScrollingView_Main_Inner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollingView_Main_Inner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void stopNestedScroll() {

        super.stopNestedScroll();
        dispatchScrollState(RecyclerView.SCROLL_STATE_IDLE);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public boolean startNestedScroll(int axes) {
        boolean superScroll = super.startNestedScroll(axes);
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return superScroll;
    }

    private void dispatchScrollState(int state) {
        if (state == 1){
            /// scrollStart

        }
        if (state == 0){
            /// scrollEnd

        }
        if (mScrollListener != null && mState != state) {
            mScrollListener.onNestedScrollViewStateChanged(state);
            mState = state;
        }
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {

        switch (e.getAction())
        {
//            case MotionEvent.ACTION_DOWN:
//                Main_SideMenuView.drag = true;
//                if (!MainActivity.sidemenuActive){
//                    Main_SideMenuView.dragStart_point_x = e.getX();
//                    Main_SideMenuView.dragStart_point_y = e.getY();
//                    Main_SideMenuView.sidemenu_canvas.invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (Main_SideMenuView.drag && !MainActivity.sidemenuActive){
////                    ((NestedScrollingView_Main)findViewById(R.id.main_nestedscrollview)).setY(NestedScrollingView_Main.ScrollY);
////                    MainActivity.main_nestedscrollview.scrollTo(0, NestedScrollingView_Main.ScrollY);
//                    Main_SideMenuView.dragMove_point_x = e.getX()*1.0f - Main_SideMenuView.dragStart_point_x;
//                    Main_SideMenuView.dragMove_point_y = e.getY()*1.0f - NestedScrollingView_Main.ScrollY;
//                    Main_SideMenuView.sidemenu_canvas.invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                Main_SideMenuView.drag = false;
//                if (Main_SideMenuView.dragMove_point_x > MainActivity.screenWidth/2 && !MainActivity.sidemenuActive){
//                    MainActivity.sidemenuActive = true;
//                    Main_SideMenuView.path_status_check(MainActivity.sidemenuActive);
//                    Main_SideMenuView.path_animator(400);
//                } else {
//                    MainActivity.sidemenuActive = false;
//                    Main_SideMenuView.path_animator(300);
//                }
//                break;
//            default:
//                break;
        }
        return super.onTouchEvent(e);
    }
}