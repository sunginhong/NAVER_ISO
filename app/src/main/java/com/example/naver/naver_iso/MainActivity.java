package com.example.naver.naver_iso;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

/**
 * Created by Naver on 2018. 8. 3..
 */

public class MainActivity extends AppCompatActivity {
    static ListView lv;
    protected Context context;
    AssetManager assetManager;
    Toolbar toolbar;
    AppBarLayout main_appbar;
    NestedScrollView main_nestedscrollview;
    CollapsingToolbarLayout collapsingToolbar;
    RelativeLayout toolbar_rl;
    LinearLayout collapsing_toolbar_titlell;

    float collapsing_toolbar_titlell_TransX;
    boolean getSet = false;
    int toolbarDistance;

    TextView collapsing_toolbar_title;
    TextView collapsing_toolbar_subTitle;
    View collapsing_toolbar_line;

    public static final int ITEM_COUNT = 100;
    static RelativeLayout lstMainRlArray[] = new RelativeLayout[ITEM_COUNT];
    static LinearLayout lstMainItemArray[] = new LinearLayout[ITEM_COUNT];
    static Class<?>  classMainItemArray[] = new Class<?> [ITEM_COUNT];
    static final ArrayList<String[]> valuesMain = new ArrayList<String[]>();
    static final ArrayList<Class<?>[]> callValMain = new ArrayList<Class<?>[]>();

    public static String json = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);

        toolbar_rl = (RelativeLayout) findViewById(R.id.toolbar_rl);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsing_toolbar_line = (View) findViewById(R.id.collapsing_toolbar_line);

        main_appbar = (AppBarLayout) findViewById(R.id.main_appbar);
        main_nestedscrollview = (NestedScrollView) findViewById(R.id.main_nestedscrollview);
        collapsing_toolbar_titlell = (LinearLayout) findViewById(R.id.collapsing_toolbar_titlell);
        collapsing_toolbar_title = (TextView) findViewById(R.id.collapsing_toolbar_title);
        collapsing_toolbar_subTitle = (TextView) findViewById(R.id.collapsing_toolbar_subTitle);

        if(valuesMain.size() == 0){
            valuesMain.add(new String[]{"0", "Interaction.", "Interaction Library", String.valueOf("#ffffff")});
            callValMain.add(new Class<?>[]{MainActivity_Library.class});
            valuesMain.add(new String[]{"1", "Motion.", "MotionGraphic Library", String.valueOf("#ffffff")});
            callValMain.add(new Class<?>[]{MainActivity_Motion.class});
//            valuesMain.add(new String[]{"2", "AndDev.", "Android Prototype Library", String.valueOf("#ffffff")});
//            callValMain.add(new Class<?>[]{MainActivity_AndDev_Private.class});
            valuesMain.add(new String[]{"3", "About us.", "Naver Interactive Studio", String.valueOf("#ffffff")});
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
        main_nestedscrollview.smoothScrollTo(0,0);

        Utils.delayMin(2, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                toolbarDistance = main_appbar.getHeight() - toolbar.getHeight();
                collapsing_toolbar_titlell_TransX = collapsing_toolbar_titlell.getTranslationX() + collapsing_toolbar_line.getWidth();
                getSet = true;
            }
        });

        main_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollY;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                scrollY = -verticalOffset;
                if(scrollY<=toolbarDistance && getSet){
                    Utils.ModulateAlphaAnim(collapsing_toolbar_title, scrollY, 0, toolbarDistance, 1, 0);
//                    Utils.ModulateAlphaAnim(collapsing_toolbar_line, scrollY, 0, toolbarDistance/2, 1, 0);
                }
            }
        });

        main_nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.v("ssssssss", ""+String.valueOf(scrollY));
            }
        });

    }

    @Override
    protected void onResume() {
//        this.overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
//        RecyclerViewAdapter_Main.mainVideoPlay(getSet);
        super.onResume();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}