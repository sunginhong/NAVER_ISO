package com.example.naver.naver_iso;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Naver on 2018. 8. 3..
 */

public class MainActivity extends AppCompatActivity {
    public ListView lv;
    public Context context;
    AssetManager assetManager;
    Toolbar toolbar;
    AppBarLayout main_appbar;
    NestedScrollView main_nestedscrollview;
    CoordinatorLayout main_contain;

    boolean getSet = false;

    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://naver.com";
    public static final String WIFE_STATE = "WIFE";
    public static final String MOBILE_STATE = "MOBILE";
    public static final String NONE_STATE = "NONE";
    private boolean newtwork = true;

    static int screenWidth;
    static int screenHeight;

    public static final int ITEM_COUNT = 100;
    public static final int MAIN_CARD_TRANS_DURATION_IN = 500;
    public static final int MAIN_CARD_TRANS_DURATION_OUT = 400;

    static RelativeLayout lstMainRlArray[] = new RelativeLayout[ITEM_COUNT];
    static LinearLayout lstMainItemArray[] = new LinearLayout[ITEM_COUNT];
    static Class<?>  classMainItemArray[] = new Class<?> [ITEM_COUNT];

    static View lstMaincardArray[] = new View[3];
//    static View main_cardArray[] = new View[3];

    static final ArrayList<String[]> valuesMain = new ArrayList<String[]>();
    static final ArrayList<Class<?>[]> callValMain = new ArrayList<Class<?>[]>();

    static ViewPager vp;
    private MainVp_PagerInteraction mainVp_PagerInteraction;
    static View pageNav_current;
    public static final int PAGE_ITEM_COUNT = 5;
    public static final int ITEM_MARGIN = 0;
    public static final float MAIN_PAGE_WIDTH_RATE = 1.0f;

    static final ArrayList<String[]> values_MainActivity = new ArrayList<String[]>();
    static RelativeLayout pvArray[] = new RelativeLayout[PAGE_ITEM_COUNT];
    static ImageView bgImgArray[] = new ImageView[PAGE_ITEM_COUNT];
    static CardView main_vp_cardcotainArray[] = new CardView[PAGE_ITEM_COUNT];
    static LinearLayout main_vp_textllArray[] = new LinearLayout[PAGE_ITEM_COUNT];


    static String urlMainRecentArray[] = new String[PAGE_ITEM_COUNT];
    static String titleMainRecentArray[] = new String[PAGE_ITEM_COUNT];
    static String imgMainRecentArray[] = new String[PAGE_ITEM_COUNT];

    //    static final ArrayList<String> mainJson_Arr0 = new ArrayList<String>();
    public String mainJson_Arr0[] = new String[ITEM_COUNT];
    public String mainJson_Arr1[] = new String[ITEM_COUNT];
    public String mainJson_Arr2[] = new String[ITEM_COUNT];
    public String mainJson_Arr3[] = new String[ITEM_COUNT];
    public String mainJson_Arr4[] = new String[ITEM_COUNT];

    public static String json = null;
    public static String URL_THUMB_IMG = "http://10.113.183.52/naverISO/json/thumbImg/";
    public static String URL_LINK = "http://jjangik.com/";

    public FrameLayout main_appbar_contain;
    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;

    private String scrollDirection = "none";
    private int scrollCardHeight =  0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        // 앱 실행시 체크
        String getNetwork =  getWhatKindOfNetwork(getApplication());
        if(getNetwork.equals("NONE")){ newtwork = false; }

        Utils.updateStatusBarColor_string(this, R.color.statusbar_color_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(MainActivity.MAIN_CARD_TRANS_DURATION_IN);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        main_contain = (CoordinatorLayout) findViewById(R.id.main_contain);

        if (newtwork){
            setSupportActionBar(toolbar);
            main_nestedscrollview = (NestedScrollView) findViewById(R.id.main_nestedscrollview);
            main_appbar_contain = (FrameLayout) findViewById(R.id.main_appbar_contain);

            if(valuesMain.size() == 0){
                valuesMain.add(new String[]{"0", "Interaction.", "Interaction Library", String.valueOf("#0055F8")});
                callValMain.add(new Class<?>[]{MainActivity_Library.class});
                valuesMain.add(new String[]{"1", "Motion.", "MotionGraphic Library", String.valueOf("#0055F8")});
                callValMain.add(new Class<?>[]{MainActivity_Motion.class});
                //            valuesMain.add(new String[]{"2", "AndDev.", "Android Prototype Library", String.valueOf("#ffffff")});
                //            callValMain.add(new Class<?>[]{MainActivity_AndDev_Private.class});
                valuesMain.add(new String[]{"2", "About us.", "Naver Interactive Studio", String.valueOf("#0055F8")});
                callValMain.add(new Class<?>[]{MainActivity_About.class});
            }

            RecyclerViewAdapter_Main adapter = new RecyclerViewAdapter_Main(this, valuesMain, callValMain);
            RecyclerView myView =  (RecyclerView)findViewById(R.id.main_recyclerview);

            myView.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            myView.setLayoutManager(llm);
            myView.setNestedScrollingEnabled(false);
            myView.setHasFixedSize(false);
            main_nestedscrollview.setSmoothScrollingEnabled(true);
            main_nestedscrollview.smoothScrollTo(0,0);

            Utils.delayMin(2, new Utils.DelayCallback() {
                @Override
                public void afterDelay() {
                    getSet = true;
                    HIDE_THRESHOLD = main_appbar_contain.getHeight();
                    for (int i = 0; i < MainActivity.lstMaincardArray.length; i++) {
                        if (MainActivity.lstMaincardArray[i].getY() > 0){
                            scrollCardHeight = MainActivity.lstMaincardArray[i].getHeight();
                            MainActivity.lstMaincardArray[i].setTranslationY(scrollCardHeight);
                        }
                    }
                }
            });

            main_nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    scrolledDistance = scrollY;
//                    Log.v("ssssssss", ""+String.valueOf(main_cardArray));
                    if (scrolledDistance < HIDE_THRESHOLD && appbarVisible) {
                        ScrollHederAnim.HeaderShow(main_appbar_contain, main_appbar_contain.getHeight(), Utils.dpToPx(0), 400);
                        appbarVisible = false;
                        scrolledDistance = 0;
                    } else if (scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
                        ScrollHederAnim.HeaderHide(main_appbar_contain, Utils.dpToPx(0), main_appbar_contain.getHeight(), 400);
                        appbarVisible = true;
                        scrolledDistance = 0;
                    }
                    if((!appbarVisible && scrollY>0) || (appbarVisible && scrollY<0)) {
                        scrolledDistance += scrollY;
                    }

                    for (int i = 0; i < MainActivity.lstMaincardArray.length; i++) {
                        if ( scrolledDistance-MainActivity.lstMaincardArray[i].getY() < 0 && scrolledDistance-MainActivity.lstMaincardArray[i].getY() > -MainActivity.lstMaincardArray[i].getHeight()/0.5 ){
                            if (MainActivity.lstMaincardArray[i].getTranslationY() == scrollCardHeight){
                                MainActivity.lstMaincardArray[i].setTranslationY(0);
                                Utils.TransAnim(MainActivity.lstMaincardArray[i], 0, 0, scrollCardHeight, 0, 400);
                            }
                        }
                        if ( scrolledDistance-MainActivity.lstMaincardArray[i].getY() < 0 && scrolledDistance-MainActivity.lstMaincardArray[i].getY() < -MainActivity.lstMaincardArray[i].getHeight()/1.5 ){
                            if (MainActivity.lstMaincardArray[i].getTranslationY() == 0){
                                Utils.TransAnim(MainActivity.lstMaincardArray[i], 0, 0, 0, scrollCardHeight, 000);
                                MainActivity.lstMaincardArray[i].setTranslationY(scrollCardHeight);
                            }
                        }
                    }
                    if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "DOWN"; }
                    else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "UP"; }

                }
            });

            values_MainActivity.removeAll(values_MainActivity);
            if(values_MainActivity.size() == 0) {
                String url = "http://10.113.183.52/naverISO/json/data_main.json";
                MainActivity.NetworkTask networkTask = new MainActivity.NetworkTask(url, null);
                networkTask.execute();
            }
        }
    }

    @Override
    protected void onResume() {
//        this.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
        if (getSet){
            RecyclerViewAdapter_Main.reset();
        } else {
//            mainHeaderTrans.setAlpha(0);
        }
        super.onResume();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
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

                values_MainActivity.add(new String[]{num, title, subtitle, URL_THUMB_IMG+thumbImg, url});
                mainJson_Arr0[i] = num;
                mainJson_Arr1[i] = title;
                mainJson_Arr2[i] = subtitle;
                mainJson_Arr3[i] = URL_THUMB_IMG+thumbImg;
                mainJson_Arr4[i] = URL_LINK+url;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < PAGE_ITEM_COUNT; i++) {
            final ArrayList<String[]> values = new ArrayList<String[]>();
            if(values.size() == 0) {
                values.add(new String[]{mainJson_Arr0[0], mainJson_Arr1[0], mainJson_Arr2[0], mainJson_Arr3[0], mainJson_Arr4[0]});
                values.add(new String[]{mainJson_Arr0[1], mainJson_Arr1[1], mainJson_Arr2[1], mainJson_Arr3[1], mainJson_Arr4[1]});
                values.add(new String[]{mainJson_Arr0[2], mainJson_Arr1[2], mainJson_Arr2[2], mainJson_Arr3[2], mainJson_Arr4[2]});
                values.add(new String[]{mainJson_Arr0[3], mainJson_Arr1[3], mainJson_Arr2[3], mainJson_Arr3[3], mainJson_Arr4[3]});
                values.add(new String[]{mainJson_Arr0[4], mainJson_Arr1[4], mainJson_Arr2[4], mainJson_Arr3[4], mainJson_Arr4[4]});
            }
            vp = (ViewPager)findViewById(R.id.mainvp_pager);
            MainVp_MyPagerAdapter mAdapter = new MainVp_MyPagerAdapter(this, values);;

            vp.setAdapter(mAdapter);
            vp.setClipToPadding(false);
            vp.setOffscreenPageLimit(PAGE_ITEM_COUNT);
            vp.setPadding(vp.getWidth()/15, 0, vp.getWidth()/15, vp.getWidth()/20);
            vp.setPageMargin(-vp.getWidth()/12);
            vp.setCurrentItem(0);
            pageNav_current = (View)findViewById(R.id.mainvp_pageNav_current);
            mainVp_PagerInteraction = new MainVp_PagerInteraction(vp);

        }
    }

    public static String getWhatKindOfNetwork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return WIFE_STATE;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return MOBILE_STATE;
            }
        } else {
            Toast toast = Toast.makeText(context, "사내 네트워크만 접근이 가능합니다. Connect_wlan 연결을 확인 해주세요.", Toast.LENGTH_LONG);
            toast.show();
        }
        return NONE_STATE;
    }

}