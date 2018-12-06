package com.example.naver.naver_iso;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity_Motion extends Activity implements View.OnClickListener {

    public static final int ITEM_COUNT = 100;
    static final ArrayList<String[]> values_MotionMain = new ArrayList<String[]>();
    static final ArrayList<Class<?>[]> callVal_MotionMain = new ArrayList<Class<?>[]>();
    static Class<?> class_Motion_ItemArray[] = new Class<?> [ITEM_COUNT];
    static String urlMotionArray[] = new String[ITEM_COUNT];
    static String motionTitleNames[] = new String[ITEM_COUNT];

    Toolbar motion_toolbar;
    AppBarLayout motion_appbar;
    CoordinatorLayout motion_contain;
    NestedScrollView motion_nestedscrollview;
    TextView motion_toolbar_title;
    FrameLayout motion_toolbar_Backbtn;
    CardView motion_contain_card;
    ImageView motion_imageview;
    Context context;

    float cardRound_result;
    boolean motion_getSet = false;
    int motion_toolbarDistance;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_motion_main);
//        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION_IN);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        motion_contain = (CoordinatorLayout) findViewById(R.id.motion_contain);
        motion_toolbar = (Toolbar) findViewById(R.id.motion_toolbar);
        motion_appbar = (AppBarLayout) findViewById(R.id.motion_appbar);
        motion_nestedscrollview = (NestedScrollView) findViewById(R.id.motion_nestedscrollview);
        motion_nestedscrollview.setSmoothScrollingEnabled(true);
        motion_toolbar_title = (TextView) findViewById(R.id.motion_toolbar_title);
        motion_toolbar_Backbtn = (FrameLayout) findViewById(R.id.motion_toolbar_backbtn);

        motion_toolbar_Backbtn.setOnClickListener(this);

        motion_contain_card = (CardView) findViewById(R.id.motion_contain_card);
        motion_imageview = (ImageView) findViewById(R.id.motion_imageview);

//        setActivityBackgroundColor(R.color.detailBgColor_dimmed2);

        // URL 설정.
        Utils.delayMin(50, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                values_MotionMain.removeAll(values_MotionMain);
                if(values_MotionMain.size() == 0) {
                    String url = "http://10.113.183.52/naverISO/json/data_motion.json";
                    NetworkTask networkTask = new NetworkTask(url, null);
                    networkTask.execute();
                }
            }
        });

        Picasso.with(context).load(MainActivity.URL_THUMB_IMG+"main_thumb_thumb_01.png").into(motion_imageview);

        Utils.delayMin(1, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                motion_toolbarDistance = motion_appbar.getHeight() - motion_toolbar.getHeight();
                motion_getSet = true;
                HIDE_THRESHOLD = motion_appbar.getHeight();
            }
        });

        motion_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollY;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                scrollY = -verticalOffset;
            }
        });

        motion_nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolledDistance = scrollY;
                if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
                else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }

                if (scrollDirection == "DOWN" && scrolledDistance < HIDE_THRESHOLD && appbarVisible) {
                    ScrollHederAnim.HeaderHide(motion_appbar, -motion_appbar.getHeight(), Utils.dpToPx(0), 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                } else if (scrollDirection == "UP" && scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
                    ScrollHederAnim.HeaderShow(motion_appbar, Utils.dpToPx(0), -motion_appbar.getHeight(), 300);
                    appbarVisible = true;
                    scrolledDistance = 0;
                }
                if (scrollDirection == "DOWN" && scrolledDistance > HIDE_THRESHOLD && appbarVisible){
                    ScrollHederAnim.HeaderHide(motion_appbar, -motion_appbar.getHeight(), Utils.dpToPx(0), 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                }
            }
        });

        motion_appbar.bringToFront();
        motion_contain_card.setRadius(Utils.dpToPx(12));
        Utils.delayMin(20, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                motion_contain_card.setRadius(Utils.dpToPx(0));
            }
        });
        headerAnim("IN");
        Utils.TransAnim(motion_appbar, 0, 0, -motion_appbar.getHeight(), 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN);

        Utils.AlphaAnim(motion_contain, 0, 0, 0);
        Utils.AlphaAnim(motion_imageview, 1, 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN/2);
        Utils.delayMin(2, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { Utils.AlphaAnim(motion_contain, 0, 1, MainActivity.MAIN_CARD_TRANS_DURATION_IN); }
        });
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            doJSONParser(s);
        }
    }

    void doJSONParser(String s){
        StringBuffer sb = new StringBuffer();
        String str = s;
        try {
            JSONArray jarray = new JSONArray(str);   // JSONArray 생성
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String num = jObject.getString("num");
                String title = jObject.getString("title");
                String subtitle = jObject.getString("subtitle");
                String thumbImg = jObject.getString("img");
                String url = jObject.getString("url");

                values_MotionMain.add(new String[]{num, title, subtitle, MainActivity.URL_THUMB_IMG+thumbImg, MainActivity.URL_LINK+url});

                RecyclerViewAdapter_Motion adapter = new RecyclerViewAdapter_Motion(this, values_MotionMain);
                RecyclerView motion_View =  (RecyclerView)findViewById(R.id.main_motion_recyclerview);

                motion_View.setAdapter(adapter);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                motion_View.setLayoutManager(llm);
                motion_View.setNestedScrollingEnabled(false);
                motion_View.setHasFixedSize(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        outAnim();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        if (!appbarVisible){
            outAnim();
        }
    }

    private void outAnim(){
        finish();
        this.overridePendingTransition(R.anim.activity_slide_in4, R.anim.activity_slide_out4);
    }

    private void headerAnim(String status) {
        if (status == "OUT") {
            Utils.TransAnim(motion_appbar, 0, 0, 0, -motion_appbar.getHeight(), 200);
            Utils.delayMin(4, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { ActivityCompat.finishAfterTransition(MainActivity_Motion.this); }
            });
        }
        if (status == "IN"){
            Utils.delayMin(2, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(motion_appbar, 0, 0, -motion_appbar.getHeight(), -motion_appbar.getHeight(), 0); }
            });
            Utils.delayMin(42, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(motion_appbar, 0, 0, -motion_appbar.getHeight(), 0, 400); }
            });
        }
    }

    private void card_round_animator(int duration, float radiusStart, float radiusEnd) {
        ValueAnimator animator = ValueAnimator.ofFloat(radiusStart, radiusEnd);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                cardRound_result = (float) animation.getAnimatedValue();
                motion_contain_card.setRadius(cardRound_result);
            }
        });
        animator.start();
    }

    private void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

}