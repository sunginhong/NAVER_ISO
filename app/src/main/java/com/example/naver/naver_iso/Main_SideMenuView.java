package com.example.naver.naver_iso;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class Main_SideMenuView extends View {

    Context context;
    static boolean drag = false;
    static float dragStart_point_x = 0;
    static float dragStart_point_y = 0;
    static float dragMove_point_x = 0;
    static float dragMove_point_y = 0;

    static View sidemenu_canvas;

    Paint paint;
    Path path;

    public Main_SideMenuView(Context context) {
        super(context);
        init();
    }

    public Main_SideMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Main_SideMenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
//        sidemenu_canvas = (View)findViewById(R.id.sidemenu_canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        pathDraw(0, 0, dragMove_point_x, dragMove_point_y, 0, MainActivity.screenHeight);
        canvas.drawPath(path, paint);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    private void pathDraw(float x1, float y1, float x2, float y2, float x3, float y3){
        path = new Path();
        int sidemenu_bg_color = getResources().getColor(R.color.sidemenu_bg_color);

        paint.setColor(sidemenu_bg_color);
        paint.setStrokeWidth(3);
        path.moveTo(0, 0);
        path.cubicTo(x1, y1, x2, y2, x3, y3);
        path.close();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e)
//    {
//        switch (e.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                drag = true;
//                if (!MainActivity.sidemenuActive){
//                    dragStart_point_x = e.getX();
//                    dragStart_point_y = e.getY();
//                    invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (drag && !MainActivity.sidemenuActive){
//                    dragMove_point_x = e.getX()*1.0f - dragStart_point_x;
//                    dragMove_point_y = e.getY()*1.0f;
//                    invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                drag = false;
//                if (dragMove_point_x > MainActivity.screenWidth/2 && !MainActivity.sidemenuActive){
//                    MainActivity.sidemenuActive = true;
//                    path_status_check(MainActivity.sidemenuActive);
//                    path_animator(400);
//                } else {
//                    MainActivity.sidemenuActive = false;
//                    path_animator(300);
//                }
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    static void path_animator(int duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(dragMove_point_x, 0);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float path_origin_Xn = (float) animation.getAnimatedValue();
                dragMove_point_x = path_origin_Xn;
                sidemenu_canvas.invalidate();
            }
        });
        animator.start();
    }

    public static void path_status_check(boolean bool){
        boolean status = bool;
        if (status){
            MainActivity.sidemenu.setX(0);
            Utils.TransAnim(MainActivity.sidemenu, -MainActivity.screenWidth, 0.0f, 0.0f, 0.0f, 400);
            Utils.TransAlphaAnim(MainActivity.main_contain, 0f, MainActivity.screenWidth/2, 0.0f, 0.0f, 1, 0.5f,400);
            Utils.delayMin(40, new Utils.DelayCallback() {
                @Override
                public void afterDelay() {
                    MainActivity.sidemenuActive = true;
                    MainActivity.main_contain.setX(MainActivity.screenWidth);
                }
            });
        } else {
            MainActivity.main_contain.setX(0);
            Utils.TransAnim(MainActivity.sidemenu,0.0f, -MainActivity.screenWidth, 0.0f, 0.0f, 400);
            Utils.TransAlphaAnim(MainActivity.main_contain, MainActivity.screenWidth/2, 0f, 0.0f, 0.0f, 0.5f, 1,400);
            Utils.delayMin(40, new Utils.DelayCallback() {
                @Override
                public void afterDelay() {
                    MainActivity.sidemenuActive = false;
                    MainActivity.sidemenu.setX(-MainActivity.screenWidth);
                }
            });
        }
    }

}