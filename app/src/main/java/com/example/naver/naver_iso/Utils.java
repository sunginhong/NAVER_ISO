package com.example.naver.naver_iso;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Property;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Naver on 2018. 7. 26..
 */

public class Utils {
    protected Context context;

    public Utils(){
    }

    public interface DelayCallback{
        void afterDelay();
    }

//    Utils.delay(4, new Utils.DelayCallback() {
//        @Override
//        public void afterDelay() {
//
//        }
//    });

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 100); // afterDelay will be executed after (secs*1000) milliseconds.
    }

    public static void delayMin(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 10); // afterDelay will be executed after (secs*1000) milliseconds.
    }

    public static void TransAnim(View view, float startX, float endX, float startY, float endY, int duration) {
        TranslateAnimation anim = new TranslateAnimation(
                startX, endX,
                startY, endY );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void AlphaAnim(View view, float startAlpha, float endAlpha, int duration) {
        Animation anim = new AlphaAnimation( startAlpha, endAlpha );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void SclaeAnim(View view, float startScaleX, float endScaleX, float startScaleY,float endScaleY, float originX, float originY, int duration) {
        ScaleAnimation anim = new ScaleAnimation( startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, originX, Animation.RELATIVE_TO_SELF, originY  );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void SclaeAlphaAnim(View view, float startScaleX, float endScaleX, float startScaleY,float endScaleY, float originX, float originY, float startAlpha, float endAlpha, int duration) {
        ScaleAnimation anim1 = new ScaleAnimation( startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, originX, Animation.RELATIVE_TO_SELF, originY  );
        Animation anim2 = new AlphaAnimation( startAlpha, endAlpha );

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.setFillAfter(true);
        setAnim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        setAnim.setDuration(duration);
        setAnim.addAnimation(anim1);
        setAnim.addAnimation(anim2);
        view.setAnimation(setAnim);
    }

    public static void TransScaleAnim(View view, float startX, float endX, float startY, float endY, float startScaleX, float endScaleX, float startScaleY,float endScaleY, int duration) {
        ScaleAnimation anim1 = new ScaleAnimation( startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f  );
        TranslateAnimation anim2 = new TranslateAnimation(
                startX, endX,
                startY, endY );

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.setFillAfter(true);
        setAnim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        setAnim.setDuration(duration);
        setAnim.addAnimation(anim1);
        setAnim.addAnimation(anim2);
        view.setAnimation(setAnim);
    }

    public static void TransAlphaAnim(View view, float startX, float endX, float startY, float endY, float startAlpha, float endAlpha, int duration) {
        Animation anim1 = new AlphaAnimation( startAlpha, endAlpha );
        TranslateAnimation anim2 = new TranslateAnimation(
                startX, endX,
                startY, endY );

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.setFillAfter(true);
        setAnim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        setAnim.setDuration(duration);
        setAnim.addAnimation(anim1);
        setAnim.addAnimation(anim2);
        view.setAnimation(setAnim);
    }


    public static void ModulateAlphaAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
//        resultF = Double.parseDouble(String.format("%.2f", result));
        view.setAlpha(result);
    }

    public static void ModulatetTransXAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setTranslationX(result);
    }

    public static void ModulatetTransYAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setTranslationY(result);
    }

    public static void ModulatetScaleAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setScaleX(result);
        view.setScaleY(result);
    }

    public static void ModulatetAlphaAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setAlpha(result);
    }

    public static void AnimateHeightTo(@NonNull View view, int height, int duration) {
        final int currentHeight = view.getHeight();
        ObjectAnimator anim = ObjectAnimator.ofInt(view, new HeightProperty(), currentHeight, height);
        anim.setDuration(duration);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.start();
    }

    static class HeightProperty extends Property<View, Integer> {

        public HeightProperty() {
            super(Integer.class, "height");
        }

        @Override public Integer get(View view) {
            return view.getHeight();
        }

        @Override public void set(View view, Integer value) {
            view.getLayoutParams().height = value;
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    public static void bgColorAnim(View view, Object startColor, Object endColor, int duration ){
        final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), startColor, endColor);
        backgroundColorAnimator.setDuration(duration);
        backgroundColorAnimator.start();
    }



}