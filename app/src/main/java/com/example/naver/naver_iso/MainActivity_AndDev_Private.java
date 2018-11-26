package com.example.naver.naver_iso;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.naver.naver_iso.and_dev.ParallaxVP00_MainActivity;
import com.example.naver.naver_iso.and_dev.ParallaxVP01_MainActivity;
import com.example.naver.naver_iso.and_dev.ParallaxVP02_MainActivity;
import com.example.naver.naver_iso.and_dev.Sidemenu00_MainActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

public class MainActivity_AndDev_Private extends Activity implements View.OnClickListener {

    public static final int ITEM_COUNT = 4;
    static final ArrayList<String[]> values_DevPrivateMain = new ArrayList<String[]>();
    static final ArrayList<Class<?>[]> callVal_DevPrivateMain = new ArrayList<Class<?>[]>();
    static Class<?>  class_DevPrivate_ItemArray[] = new Class<?> [ITEM_COUNT];
    static String urlLibArray[] = new String[ITEM_COUNT];

    Toolbar dev_private_toolbar;
    AppBarLayout dev_private_appbar;
    NestedScrollView dev_private_nestedscrollview;
    TextView dev_private_toolbar_title;
    FrameLayout dev_private_toolbar_backbtn;

    boolean dev_private_getSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_devprivate_main);
        this.overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);

        dev_private_toolbar = (Toolbar) findViewById(R.id.dev_private_toolbar);
        dev_private_appbar = (AppBarLayout) findViewById(R.id.dev_private_appbar);
        dev_private_nestedscrollview = (NestedScrollView) findViewById(R.id.dev_private_nestedscrollview);
        dev_private_toolbar_title = (TextView) findViewById(R.id.dev_private_toolbar_title);
        dev_private_toolbar_backbtn = (FrameLayout) findViewById(R.id.dev_private_toolbar_backbtn);

        if(values_DevPrivateMain.size() == 0){
            values_DevPrivateMain.add(new String[]{"0", String.valueOf("#815ECC"), "Parallax Type00.", "Carousel Interaction"});
            callVal_DevPrivateMain.add(new Class<?>[]{ParallaxVP00_MainActivity.class});
            values_DevPrivateMain.add(new String[]{"1", String.valueOf("#815ECC"), "Parallax Type01.", "Carousel Interaction"});
            callVal_DevPrivateMain.add(new Class<?>[]{ParallaxVP01_MainActivity.class});
            values_DevPrivateMain.add(new String[]{"2", String.valueOf("#815ECC"), "Parallax Type02.", "Carousel Interaction"});
            callVal_DevPrivateMain.add(new Class<?>[]{ParallaxVP02_MainActivity.class});
            values_DevPrivateMain.add(new String[]{"3", String.valueOf("#FF6946"), "Article.", "SideMenu Interaction"});
            callVal_DevPrivateMain.add(new Class<?>[]{Sidemenu00_MainActivity.class});
        }

        RecyclerViewAdapter_devPrivate adapter = new RecyclerViewAdapter_devPrivate(this, values_DevPrivateMain, callVal_DevPrivateMain );
        RecyclerView dev_private_View =  (RecyclerView)findViewById(R.id.main_dev_private_recyclerview);

        dev_private_View.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        dev_private_View.setLayoutManager(llm);
        dev_private_View.setNestedScrollingEnabled(false);
        dev_private_View.setHasFixedSize(false);

        dev_private_toolbar_backbtn.setOnClickListener(this);

        Utils.delayMin(1, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                dev_private_getSet = true;
            }
        });


        dev_private_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollY;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                scrollY = -verticalOffset;
            }
        });

        dev_private_nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        ActivityCompat.finishAfterTransition(this);
        this.overridePendingTransition(R.anim.activity_slide_in2, R.anim.activity_slide_out2);
    }
}
