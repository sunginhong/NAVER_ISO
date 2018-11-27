package com.example.naver.naver_iso;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity_Library extends Activity implements View.OnClickListener {

    public static int ITEM_COUNT = 100;
    static final ArrayList<String[]> values_LibMain = new ArrayList<String[]>();
    static String urlLibArray[] = new String[ITEM_COUNT];
    static String libTitleNames[] = new String[ITEM_COUNT];

    Toolbar lib_toolbar;
    AppBarLayout lib_appbar;
    NestedScrollView lib_nestedscrollview;
    TextView lib_toolbar_Title;
    FrameLayout lib_toolbar_Backbtn;

    boolean lib_getSet = false;
    int lib_toolbarDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_library_main);
//        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        lib_toolbar = (Toolbar) findViewById(R.id.lib_toolbar);
        lib_appbar = (AppBarLayout) findViewById(R.id.lib_appbar);
        lib_nestedscrollview = (NestedScrollView) findViewById(R.id.lib_nestedscrollview);
        lib_toolbar_Title = (TextView) findViewById(R.id.lib_toolbar_title);
        lib_toolbar_Backbtn = (FrameLayout) findViewById(R.id.lib_toolbar_backbtn);

        // URL 설정.
        values_LibMain.removeAll(values_LibMain);
        if (values_LibMain.size() == 0) {
            String url = "http://10.113.183.52/naverISO/json/data_library.json";
            NetworkTask networkTask = new NetworkTask(url, null);
            networkTask.execute();
        }

//        if(values_LibMain.size() == 0){
//            values_LibMain.add(new String[]{"0", "2018 러시아 월드컵 특집", "2018 러시아 월드컵 특집", Integer.toString(R.drawable.lib_thumb__worldcup), "http://jjangik.com/?p=849"});
//            values_LibMain.add(new String[]{"1", "6.13 지방선거", "제7회 전국동시지방선거 특집페이지 3차 투·개표 오픈", Integer.toString(R.drawable.lib_thumb__vote), "http://jjangik.com/?p=851"});
//            values_LibMain.add(new String[]{"2", "모바일 이미지검색 뷰어 개편", "편리한 이미지 뷰어로 개편", Integer.toString(R.drawable.lib_thumb__mo_search), "http://jjangik.com/?p=853"});
//            values_LibMain.add(new String[]{"3", "VLIVE APP 개편", "VLIVE의 인터랙션을 개편한 작업", Integer.toString(R.drawable.lib_thumb__vlive), "http://jjangik.com/?p=859"});
//            values_LibMain.add(new String[]{"4", "Focusbot", "스마트폰의 방해에서 벗어날 수 있도록 도와주는 방해 금지 앱", Integer.toString(R.drawable.lib_thumb__focusbot), "http://jjangik.com/?p=857"});
//        }
//
//        RecyclerViewAdapter_Lib adapter = new RecyclerViewAdapter_Lib(this, values_LibMain);
//        RecyclerView libView =  (RecyclerView)findViewById(R.id.main_lib_recyclerview);
//
//        libView.setHasFixedSize(true);
//        libView.setAdapter(adapter);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        libView.setLayoutManager(llm);
//        libView.setNestedScrollingEnabled(false);
//        libView.setHasFixedSize(false);

        lib_toolbar_Backbtn.setOnClickListener(this);

        Utils.delayMin(1, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
//                lib_toolbarDistance = lib_appbar.getHeight() - lib_toolbar.getHeight();
                lib_getSet = true;
            }
        });


        lib_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollY;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                scrollY = -verticalOffset;
            }
        });

        lib_nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });

        headerAnim("IN");
        Utils.TransAnim(lib_appbar, 0, 0, -lib_appbar.getHeight(), 0, 400);
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
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String num = jObject.getString("num");
                String title = jObject.getString("title");
                String subtitle = jObject.getString("subtitle");
                String thumbImg = jObject.getString("img");
                String url = jObject.getString("url");

                values_LibMain.add(new String[]{num, title, subtitle, thumbImg, url});

                RecyclerViewAdapter_Lib adapter = new RecyclerViewAdapter_Lib(this, values_LibMain);
                RecyclerView libView = (RecyclerView) findViewById(R.id.main_lib_recyclerview);

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
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        outAnim();
//        ActivityCompat.finishAfterTransition(this);
//        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        outAnim();
    }

    private void outAnim(){
        headerAnim("OUT");
    }

    private void headerAnim(String status) {
        if (status == "OUT") {
            Utils.TransAnim(lib_appbar, 0, 0, 0, -lib_appbar.getHeight(), 200);
            Utils.delayMin(10, new Utils.DelayCallback() {
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

}
