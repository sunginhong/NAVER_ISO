package com.example.naver.naver_iso;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class LineView_LibraryList extends View {
    public Context context;
    static public boolean drag = false;
    static public float dragStart_point_x = 0;
    static public float dragStart_point_y = 0;
    static public float dragMove_point_x = 0;
    static public float dragMove_point_y = 0;
    static public float dragEnd_point_x = 0;
    static public float dragEnd_point_y = 0;

    static public View lib_lineview;
    static public boolean lib_lineAnim_Active = false;

    static Paint paint;
    static Path path;

    public LineView_LibraryList(Context context) {
        super(context);
        init();
    }

    public LineView_LibraryList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineView_LibraryList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        lib_lineview = (View)findViewById(R.id.lib_lineview);
        lib_lineview.bringToFront();
//        dragStart_point_y = Utils.dpToPx(21);
        dragMove_point_y = Utils.dpToPx(21);
        dragEnd_point_y = Utils.dpToPx(21);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        pathDraw(-Utils.dpToPx(0), Utils.dpToPx(21), dragMove_point_x, dragMove_point_y, lib_lineview.getWidth(), Utils.dpToPx(21));
        canvas.drawPath(path, paint);
        invalidate();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public static void clearCanvas(){
        if (path!=null){
            path.reset();
        }
    }

    private void pathDraw(float x1, float y1, float x2, float y2, float x3, float y3){
        path = new Path();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(-Utils.dpToPx(0), Utils.dpToPx(21));
        path.cubicTo(x1, y1, x2, y2, x3, y3);
//        path.close();
    }

    static public void functionRedraw(String scrollDirection, float x, float y) {

        if (scrollDirection=="DOWN"){
//            dragEnd_point_y = dragMove_point_y;
//            dragMove_point_x = x;
//            dragMove_point_y = -(dragEnd_point_x-dragMove_point_y)+y;
        }
        if (scrollDirection=="UP"){

        }

        dragMove_point_x = x;

        if (y < Utils.dpToPx(21*2) && y > Utils.dpToPx(0)){
            dragMove_point_y = Utils.dpToPx(21)-y;
//            Log.v("ssssssss", ""+String.valueOf(Utils.dpToPx(21)-y));
        }

        lib_lineview.invalidate();

    }

    static public void path_animator(int duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(dragMove_point_y, Utils.dpToPx(21));
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float path_origin_Yn = (float) animation.getAnimatedValue();
                dragMove_point_y = path_origin_Yn;
                lib_lineview.invalidate();
            }
        });
        animator.start();
    }
}