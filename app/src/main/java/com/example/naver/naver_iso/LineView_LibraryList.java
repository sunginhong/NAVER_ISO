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
    static Paint paintLine;
    static Path pathLine;
    static private int libLineMargin = 21;

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
        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.FILL);

//        lib_lineview = (View)findViewById(R.id.lib_lineview);
//        lib_lineview.bringToFront();
        dragMove_point_y = Utils.dpToPx(libLineMargin);
        dragEnd_point_y = Utils.dpToPx(libLineMargin);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        pathDraw(-Utils.dpToPx(0), Utils.dpToPx(libLineMargin), dragMove_point_x, dragMove_point_y, lib_lineview.getWidth(), Utils.dpToPx(libLineMargin));
        canvas.drawPath(path, paint);
        canvas.drawPath(pathLine, paintLine);
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
            pathLine.reset();
        }
    }

    private void pathDraw(float x1, float y1, float x2, float y2, float x3, float y3){
        path = new Path();
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(-Utils.dpToPx(0), Utils.dpToPx(libLineMargin));
        path.cubicTo(x1, y1, x2, y2, x3, y3);
//        path.close();
        pathLine = new Path();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(3);
        paintLine.setAntiAlias(true);
        paintLine.setStyle(Paint.Style.FILL);
        pathLine.moveTo(-Utils.dpToPx(0), Utils.dpToPx(libLineMargin));
        pathLine.cubicTo(x1, y1, x2, y2, x3, y3);

    }

    static public void functionRedraw(float x, float y) {

        dragMove_point_x = x;

        if (y < (Utils.dpToPx(libLineMargin)+MainActivity_Library.Lib_LisItem_Height) && y > -(Utils.dpToPx(libLineMargin)+MainActivity_Library.Lib_LisItem_Height)){
            dragMove_point_y = Utils.dpToPx(libLineMargin)-y/2;
        }
        lib_lineview.invalidate();
        LineView_LibraryList.path_animator(300, false);
    }

    static public void path_animator(int duration, boolean state) {
        ValueAnimator animator = ValueAnimator.ofFloat(dragMove_point_y, Utils.dpToPx(libLineMargin));
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float path_origin_Yn = (float) animation.getAnimatedValue();
//                dragMove_point_y = path_origin_Yn;
//                lib_lineview.invalidate();
            }
        });
        if (state == true){
            animator.start();
        }else {
            animator.cancel();
        }

    }
}