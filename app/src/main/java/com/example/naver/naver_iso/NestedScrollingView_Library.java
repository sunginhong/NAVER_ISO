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
        LineView_LibraryList.path_animator(300, true);
        super.stopNestedScroll();
        dispatchScrollState(RecyclerView.SCROLL_STATE_IDLE);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        LineView_LibraryList.path_animator(300, false);
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }


    @Override
    public boolean startNestedScroll(int axes) {
        LineView_LibraryList.path_animator(300, false);
        MainActivity_Library.lib_scrollBool = true;
        float oldScrollY = MainActivity_Library.lib_nestedscrollview.getScrollY();
        LineView_LibraryList.dragStart_point_y = oldScrollY;
        boolean superScroll = super.startNestedScroll(axes);
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return superScroll;
    }


    private void dispatchScrollState(int state) {
        if (state == 1){
            MainActivity_Library.lib_scrollBool = true;
            float oldScrollY = MainActivity_Library.lib_nestedscrollview.getScrollY();
            LineView_LibraryList.dragStart_point_y = oldScrollY;
        }
        if (mScrollListener != null && mState != state) {
            mScrollListener.onNestedScrollViewStateChanged(state);
            mState = state;
        }
    }

}