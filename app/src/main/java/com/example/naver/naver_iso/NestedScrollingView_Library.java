package com.example.naver.naver_iso;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class NestedScrollingView_Library extends NestedScrollView {
    private int mState = RecyclerView.SCROLL_STATE_IDLE;

    public interface NestedScrollViewScrollStateListener {
        void onNestedScrollViewStateChanged(int state);
    }

    public void setScrollListener(NestedScrollViewScrollStateListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private NestedScrollViewScrollStateListener mScrollListener;

    public NestedScrollingView_Library(Context context) {
        super(context);
    }

    public NestedScrollingView_Library(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollingView_Library(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void stopNestedScroll() {
        MainActivity_Library.lib_scrollBool = true;
        LineView_LibraryList.path_animator(300, true);
        super.stopNestedScroll();
        dispatchScrollState(RecyclerView.SCROLL_STATE_IDLE);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        MainActivity_Library.lib_scrollBool = false;
        float oldScrollY = MainActivity_Library.lib_nestedscrollview.getScrollY();
        LineView_LibraryList.dragStart_point_y = oldScrollY;
        
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
            MainActivity_Library.lib_scrollBool = false;
            LineView_LibraryList.path_animator(200, true);
        }
        if (state == 0){
            /// scrollEnd
            MainActivity_Library.lib_scrollBool = true;
            LineView_LibraryList.path_animator(300, true);
        }
        if (mScrollListener != null && mState != state) {
            mScrollListener.onNestedScrollViewStateChanged(state);
            mState = state;
        }
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
//        float lib_distanceY = -(LineView_LibraryList.dragStart_point_y - scrollY);
//        LineView_LibraryList.functionRedraw(MainActivity.screenWidth/2-(MainActivity.screenWidth-LineView_LibraryList.lib_lineview.getWidth()), lib_distanceY/2);
    }

}