package com.example.naver.naver_iso;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Naver on 2018. 8. 8..
 */

public class SquareRectView extends View {

    public SquareRectView(Context context) {
        super(context);
    }

    public SquareRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        width = Math.min(width, height);
        height = width;

        setMeasuredDimension(width, height);
    }
}
