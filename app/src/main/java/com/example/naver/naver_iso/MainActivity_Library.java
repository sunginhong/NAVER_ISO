package com.example.naver.naver_iso;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity_Library extends AppCompatActivity implements View.OnClickListener {

    public static int ITEM_COUNT = 100;
    static final ArrayList<String[]> values_LibMain = new ArrayList<String[]>();
    static String urlLibArray[] = new String[ITEM_COUNT];
    static String libTitleNames[] = new String[ITEM_COUNT];

    static boolean lib_lineAnim_Active = false;
    private int mState = RecyclerView.SCROLL_STATE_IDLE;
    static int libItemLength = 0;

    Toolbar lib_toolbar;
    AppBarLayout lib_appbar;
    CoordinatorLayout lib_contain;
    CollapsingToolbarLayout lib_Detail_appbar;
    static NestedScrollView lib_nestedscrollview;
    TextView lib_toolbar_Title;
    FrameLayout lib_toolbar_Backbtn;
    CardView lib_contain_card;
    ImageView lib_imageview;
    Context context;
    RecyclerView libView;

    float cardRound_result;
    boolean lib_getSet = false;
    boolean scrollHeader = false;
    static public boolean lib_scrollBool = false;
    int lib_toolbarDistance;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";
    static float lib_distanceY;
    static int Lib_LisItem_Height = 0;

    private RecyclerViewAdapter_Lib adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_library_main);

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);
//        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION_IN);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        lib_contain = (CoordinatorLayout) findViewById(R.id.lib_contain);
        lib_toolbar = (Toolbar) findViewById(R.id.lib_toolbar);
        lib_appbar = (AppBarLayout) findViewById(R.id.lib_appbar);
        lib_Detail_appbar = (CollapsingToolbarLayout)findViewById(R.id.lib_Detail_appbar);
        lib_nestedscrollview = (NestedScrollView) findViewById(R.id.lib_nestedscrollview);
//        lib_nestedscrollview.setSmoothScrollingEnabled(true);
        lib_toolbar_Title = (TextView) findViewById(R.id.lib_toolbar_title);
        lib_toolbar_Backbtn = (FrameLayout) findViewById(R.id.lib_toolbar_backbtn);
        lib_contain_card = (CardView) findViewById(R.id.lib_contain_card);
        lib_imageview = (ImageView) findViewById(R.id.lib_imageview);
//        setSupportActionBar(lib_toolbar);

//        setActivityBackgroundColor(R.color.detailBgColor_dimmed2);

        // URL 설정.
        Utils.delayMin(50, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                values_LibMain.removeAll(values_LibMain);
                if (values_LibMain.size() == 0) {
                    String url = MainActivity.URL_JSON + "data_interaction.json";
                    NetworkTask networkTask = new NetworkTask(url, null);
                    networkTask.execute();
                }
            }
        });

        Picasso.with(context).load(MainActivity.URL_THUMB_IMG+"main_thumb_thumb_00.png").into(lib_imageview);

        lib_toolbar_Backbtn.setOnClickListener(this);
        lib_appbar.bringToFront();

        Utils.delayMin(1, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
//                lib_toolbarDistance = lib_appbar.getHeight() - lib_toolbar.getHeight();
                lib_getSet = true;
                HIDE_THRESHOLD = lib_appbar.getHeight();
            }
        });

        scrollHeader = false;

        lib_nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolledDistance = scrollY;
                if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
                else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }
            }
        });

        lib_nestedscrollview.setOnClickListener(null);

        lib_appbar.bringToFront();
        lib_contain_card.setRadius(Utils.dpToPx(12));
        Utils.delayMin(20, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                lib_contain_card.setRadius(Utils.dpToPx(0));
            }
        });
        headerAnim("IN");
        Utils.TransAnim(lib_appbar, 0, 0, -lib_appbar.getHeight(), 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN);
        Utils.AlphaAnim(lib_contain, 0, 0, 0);
        Utils.AlphaAnim(lib_imageview, 1, 0, MainActivity.MAIN_CARD_TRANS_DURATION_IN/2);
        Utils.delayMin(2, new Utils.DelayCallback() {
            @Override
            public void afterDelay() { Utils.AlphaAnim(lib_contain, 0, 1, MainActivity.MAIN_CARD_TRANS_DURATION_IN); }
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

    void doJSONParser(String s) {
        StringBuffer sb = new StringBuffer();
        String str = s;
        try {
            JSONArray jarray = new JSONArray(str);   // JSONArray 생성
            libItemLength = jarray.length();
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String title = jObject.getString("title");
                String subtitle = jObject.getString("subtitle");
                String thumbImg = jObject.getString("img");
                String url = jObject.getString("url");

                values_LibMain.add(new String[]{title, subtitle, MainActivity.URL_IMG+thumbImg, MainActivity.URL_LINK+url});

                adapter = new RecyclerViewAdapter_Lib(this, values_LibMain);
                libView = (RecyclerView) findViewById(R.id.main_lib_recyclerview);

                libView.setAdapter(adapter);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                libView.setLayoutManager(llm);
                libView.setNestedScrollingEnabled(false);
                libView.setHasFixedSize(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
//        Utils.delay(10, new Utils.DelayCallback() {
//            @Override
//            public void afterDelay() {
//                RecyclerViewAdapter_Lib.detailItemResumeAnim();
//            }
//        });
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        outAnim();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Log.v("ssssssss", ""+String.valueOf("0k"));
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
//                Log.v("ssssssss", ""+String.valueOf("0k"));
                break;
            default:
                break;
        }
        return true;
    }


    private void outAnim(){
        finish();
        this.overridePendingTransition(R.anim.activity_slide_in4, R.anim.activity_slide_out4);
    }

    private void headerAnim(String status) {
        if (status == "OUT") {
            Utils.TransAnim(lib_appbar, 0, 0, 0, -lib_appbar.getHeight(), 200);
            Utils.delayMin(4, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { ActivityCompat.finishAfterTransition(MainActivity_Library.this); }
            });
        }
        if (status == "IN"){
            Utils.delayMin(2, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(lib_appbar, 0, 0, -lib_appbar.getHeight(), -lib_appbar.getHeight(), 0); }
            });
            Utils.delayMin(42, new Utils.DelayCallback() {
                @Override
                public void afterDelay() { Utils.TransAnim(lib_appbar, 0, 0, -lib_appbar.getHeight(), 0, 400); }
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
                lib_contain_card.setRadius(cardRound_result);
            }
        });
        animator.start();
    }

    private void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
