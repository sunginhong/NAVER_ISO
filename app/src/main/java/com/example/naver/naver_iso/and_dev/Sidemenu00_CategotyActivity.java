package com.example.naver.naver_iso.and_dev;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.naver.naver_iso.R;
import com.example.naver.naver_iso.Utils;

/**
 * Created by Naver on 2018. 7. 30..
 */

public class Sidemenu00_CategotyActivity extends AppCompatActivity {

    private boolean sidemenuListSel = false;
    private View category_bg;
    private RelativeLayout category;
    private RelativeLayout category_rl;
    private View sidemenu_icon_rect;
    private FrameLayout sidemenu_icon_fr;
    private LottieAnimationView sidemenu_icon;

    public ImageView category_logo;

    private ImageView sidenavi_title_Array[] = new ImageView[5];
    private int sidenavi_title_widthArr[] = new int[5];
    private ImageView sidenavi_title_0;
    private ImageView sidenavi_title_1;
    private ImageView sidenavi_title_2;
    private ImageView sidenavi_title_3;
    private ImageView sidenavi_title_4;

    private View category_current_Array[] = new View[4];
    private View category_current0;
    private View category_current1;
    private View category_current2;
    private View category_current3;

    private Object category_bgColorArr[] = new Object[4];

    int deviceWidth;
    int deviceHeight;
    static int Current;
    static int Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenu00_activity_category);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;

        category = (RelativeLayout) findViewById(R.id.sidemenu00_category);
        category_bg = (View) findViewById(R.id.sidemenu00_category_bg);
        category_rl = (RelativeLayout) findViewById(R.id.sidemenu00_category_rl);
        category_logo = (ImageView) findViewById(R.id.sidemenu00_category_logo);

        sidemenu_icon_rect = (View) findViewById(R.id.sidemenu00_icon_rect);
        sidemenu_icon_fr = (FrameLayout) findViewById(R.id.sidemenu00_icon_fr);
        sidemenu_icon = (LottieAnimationView) findViewById(R.id.sidemenu00_icon);
        sidemenu_icon_rect.setOnClickListener(onBackIcnPressed);

        sidenavi_title_0 = (ImageView) findViewById(R.id.BTN_ARTICLE_MAIN);
        sidenavi_title_1 = (ImageView) findViewById(R.id.BTN_ARTICLE_TRAVEL);
        sidenavi_title_2 = (ImageView) findViewById(R.id.BTN_ARTICLE_LIFESTYLE);
        sidenavi_title_3 = (ImageView) findViewById(R.id.BTN_ARTICLE_FOOD);
        sidenavi_title_4 = (ImageView) findViewById(R.id.NONE);

        sidenavi_title_Array[0] = sidenavi_title_0;
        sidenavi_title_Array[1] = sidenavi_title_1;
        sidenavi_title_Array[2] = sidenavi_title_2;
        sidenavi_title_Array[3] = sidenavi_title_3;
        sidenavi_title_Array[4] = sidenavi_title_4;

        category_bgColorArr[0] = 0xFFffffff;
        category_bgColorArr[1] = 0xFF3fb4ff;
        category_bgColorArr[2] = 0xFF8851ff;
        category_bgColorArr[3] = 0xFFff9d00;

        category_current0 = (View) findViewById(R.id.sidemenu00_category_current0);
        category_current1 = (View) findViewById(R.id.sidemenu00_category_current1);
        category_current2 = (View) findViewById(R.id.sidemenu00_category_current2);
        category_current3 = (View) findViewById(R.id.sidemenu00_category_current3);

        category_current_Array[0] = category_current0;
        category_current_Array[1] = category_current1;
        category_current_Array[2] = category_current2;
        category_current_Array[3] = category_current3;

        Index = 0;
        Current = 0;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Utils.AlphaAnim(category_bg, 0.0f, 1.0f, 400);
        Utils.TransAnim(category_rl, -deviceWidth, -deviceWidth, 0.0f, 0.0f, 0);
        Utils.TransAnim(sidemenu_icon_fr, 0, deviceWidth-sidemenu_icon_rect.getWidth(), 0.0f, 0.0f, 500);
        Utils.AlphaAnim(category_logo, 0.0f, 0.0f, 0);

        sidenavi_title_widthArr[0] = sidenavi_title_0.getWidth();
        sidenavi_title_widthArr[1] = sidenavi_title_1.getWidth();
        sidenavi_title_widthArr[2] = sidenavi_title_2.getWidth();
        sidenavi_title_widthArr[3] = sidenavi_title_3.getWidth();
        sidenavi_title_widthArr[4] = sidenavi_title_4.getWidth();

        for (int i = 0; i < sidenavi_title_Array.length; i++) {
            Utils.TransAlphaAnim(sidenavi_title_Array[i], -sidenavi_title_widthArr[i] / 2, -sidenavi_title_widthArr[i] / 2, 0.0f, 0.0f, 0.0f, 0.0f, 0);
        }
        for (int i = 0; i < category_current_Array.length; i++) {
            Utils.SclaeAnim(category_current_Array[i], 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.5f, 0);
        }
        if (Sidemenu00_MainActivity.currentIdx == 0 && Sidemenu00_MainActivity.beforeIdx == 0){
            Utils.bgColorAnim(category, category_bgColorArr[0], category_bgColorArr[0], 400);
        }

        for (int i = 0; i < sidenavi_title_Array.length-1; i++) {
            sidenavi_title_Array[i].setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View view) {
                            if (!sidemenuListSel){
                                sidemenuListSel = true;
                                Object obj =  view.getTag();
                                selectItem( obj.toString() );
                            }
                        }
                    }
            );
        }
    }

    @Override
    protected void onResume() {
        sidemenu_icon.pauseAnimation();
        sidemenu_icon.pauseAnimation();
        sidemenu_icon.setProgress(0);
        sidemenu_icon.playAnimation();

        Intent intent = getIntent();
        Integer param1 = intent.getIntExtra("beforeIdx", Sidemenu00_MainActivity.beforeIdx);
        Integer param2 = intent.getIntExtra("currentIdx", Sidemenu00_MainActivity.currentIdx);

        intent.putExtra("beforeIdx", Sidemenu00_MainActivity.beforeIdx);
        intent.putExtra("currentIdx", Sidemenu00_MainActivity.currentIdx);

        Utils.bgColorAnim(category, category_bgColorArr[param1], category_bgColorArr[param2], 300);
        for (int i = 0; i < category_bgColorArr.length; i++) {
            if (param1 == 0){  iconColor("BLACK"); } else { iconColor("WHITE"); }
        }

        Utils.delay(3, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { intoAnim_Steop0(); }
        });

        super.onResume();
    }

    private void intoAnim_Steop0() {
        Utils.TransAnim(category_rl, -deviceWidth, 0.0f, 0.0f, 0.0f, 400);
        sidenavi_title_Anim(0);
        Utils.delay(3, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { intoAnim_Steop1(); }
        });
    }

    private void intoAnim_Steop1() {
        Utils.SclaeAnim(category_current_Array[Sidemenu00_MainActivity.currentIdx], 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.5f, 200);
    }

    private void backAnim_Step0() {
        Utils.TransAnim(category_rl, 0.0f, -deviceWidth, 0.0f, 0.0f, 400);
        Utils.TransAnim(sidemenu_icon_fr, deviceWidth-sidemenu_icon_rect.getWidth(), 0.0f, 0.0f, 0.0f, 500);

        if (sidemenu_icon.getProgress() == 1.0){
            sidemenu_icon.pauseAnimation();
            sidemenu_icon.reverseAnimation();
        }
        for (int i = 0; i < sidenavi_title_Array.length; i++) {
            Utils.AlphaAnim(sidenavi_title_Array[i], 1.0f, 0.0f, 400);
        }
        if (Sidemenu00_MainActivity.currentIdx == 0){ iconColor("WHITE"); }
        Utils.delay(2, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { backAnim_Step1(); }
        });
    }
    private void backAnim_Step1() {
        ActivityCompat.finishAfterTransition(this);
        Utils.delay(2, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { backAnim_Step2(); }
        });
    }
    private void backAnim_Step2() {
        Utils.AlphaAnim(category_bg, 1.0f, 0.0f, 300);
        Utils.AlphaAnim(category_logo, 0.0f, 1.0f, 300);
    }

    private void iconColor(String color) {
        switch (color){
            case "WHITE":{
                sidemenu_icon.addColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP));
                break; }
            case "BLACK":{
                sidemenu_icon.addColorFilter(new PorterDuffColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP));
                break; }
        }
    }

    private void sidenavi_title_Anim(int index) {
        final int interactions = sidenavi_title_Array.length;
        Index = index;
        Current = index+=1;
        if (Current <= interactions) {
            Utils.delayMin(3, new Utils.DelayCallback() {
                @Override
                public void afterDelay() {
                    Utils.TransAnim(sidenavi_title_Array[Index], -sidenavi_title_widthArr[Index]/2, 0.0f, 0.0f, 0.0f, 400);
                    sidenavi_title_Anim(Current);
                }
            });
        }
    }

    private void selectItem(String item) {
        switch (item){
            case "BTN_ARTICLE_MAIN":{
                Sidemenu00_MainActivity.currentIdx = 0; iconColor("BLACK");
                break; }
            case "BTN_ARTICLE_TRAVEL":{
                Sidemenu00_MainActivity.currentIdx = 1; iconColor("WHITE");
                break; }
            case "BTN_ARTICLE_LIFESTYLE":{
                Sidemenu00_MainActivity.currentIdx = 2; iconColor("WHITE");
                break; }
            case "BTN_ARTICLE_FOOD":{
                Sidemenu00_MainActivity.currentIdx = 3; iconColor("WHITE");
                break; }
        }
        if (Sidemenu00_MainActivity.currentIdx != Sidemenu00_MainActivity.beforeIdx){
            Utils.SclaeAnim(category_current_Array[Sidemenu00_MainActivity.currentIdx], 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.5f, 200);
            Utils.SclaeAnim(category_current_Array[Sidemenu00_MainActivity.beforeIdx], 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.5f, 200);
            Utils.delayMin(15, new Utils.DelayCallback() {
                @Override
                public void afterDelay() {
                    category_bgColor(Sidemenu00_MainActivity.currentIdx, Sidemenu00_MainActivity.beforeIdx);
                    Sidemenu00_MainActivity.beforeIdx = Sidemenu00_MainActivity.currentIdx;
                }
            });
        }
        if (Sidemenu00_MainActivity.currentIdx == Sidemenu00_MainActivity.beforeIdx){
            category_bgColor(Sidemenu00_MainActivity.currentIdx, Sidemenu00_MainActivity.beforeIdx);
            Sidemenu00_MainActivity.beforeIdx = Sidemenu00_MainActivity.currentIdx;
        }
    }

    private void category_bgColor(int index, int before){
        for (int i = 0; i < category_bgColorArr.length; i++) {
            if (i == index && i != 0){
                Utils.bgColorAnim(category, category_bgColorArr[before], category_bgColorArr[i], 400);
                Utils.bgColorAnim(Sidemenu00_MainActivity.sidemenu00_bg, category_bgColorArr[before], category_bgColorArr[i], 400);
            }
            if (i == 0){
                Utils.bgColorAnim(category, category_bgColorArr[before], 0xFF000000, 400);
                Utils.bgColorAnim(Sidemenu00_MainActivity.sidemenu00_bg, category_bgColorArr[before], 0xFF000000, 400);
            }
        }
        backAnim_Step0();
    }

    private void sideObj_Function(int index){
        for (int i = 0; i < sidenavi_title_Array.length-1; i++) {
            if(i == index){
                Object obj = sidenavi_title_Array[i].getTag();
                selectItem(obj.toString());
            }
        }
    }

    // 뒤로 가기 이벤트
    @Override
    public void onBackPressed() {
        if (!sidemenuListSel) {
            sidemenuListSel = true;
            sideObj_Function(Sidemenu00_MainActivity.currentIdx);
        }
    }

    Button.OnClickListener onBackIcnPressed = new View.OnClickListener() {
        public void onClick( View v ) {
            if (!sidemenuListSel) {
                sidemenuListSel = true;
                sideObj_Function(Sidemenu00_MainActivity.currentIdx);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}