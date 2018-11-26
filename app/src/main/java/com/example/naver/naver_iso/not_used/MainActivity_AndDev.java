package com.example.naver.naver_iso.not_used;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.naver.naver_iso.R;
import com.example.naver.naver_iso.and_dev.ParallaxVP00_MainActivity;
import com.example.naver.naver_iso.and_dev.ParallaxVP01_MainActivity;
import com.example.naver.naver_iso.and_dev.ParallaxVP02_MainActivity;
import com.example.naver.naver_iso.and_dev.Sidemenu00_MainActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

/**
 * Created by Naver on 2018. 7. 26..
 */

public class MainActivity_AndDev extends AppCompatActivity {
    static ListView lv;

    public static final int ITEM_COUNT = 4;
    static RelativeLayout lstAndRlArray[] = new RelativeLayout[ITEM_COUNT];
    static LinearLayout lstAndItemArray[] = new LinearLayout[ITEM_COUNT];
    static Class<?> classAndItemArray[] = new Class<?> [ITEM_COUNT];
    static final ArrayList<String[]> valuesAnd = new ArrayList<String[]>();
    static final ArrayList<Class<?>[]> callValAnd = new ArrayList<Class<?>[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_listview);

        if(valuesAnd.size() == 0){
            valuesAnd.add(new String[]{"0", "ViewPager 01-HORIZONTAL"});
            callValAnd.add(new Class<?>[]{ParallaxVP00_MainActivity.class});
            valuesAnd.add(new String[]{"1", "ViewPager 02-HORIZONTAL"});
            callValAnd.add(new Class<?>[]{ParallaxVP01_MainActivity.class});
            valuesAnd.add(new String[]{"2", "ViewPager 03-VERTICAL"});
            callValAnd.add(new Class<?>[]{ParallaxVP02_MainActivity.class});

            valuesAnd.add(new String[]{"3", "SideMenu 01-Article."});
            callValAnd.add(new Class<?>[]{Sidemenu00_MainActivity.class});
        }
        lv = (ListView) findViewById(R.id.list_view);
        ListCustomAdapter_AndDev adapter = new ListCustomAdapter_AndDev(this, valuesAnd, callValAnd);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity_AndDev.this, classAndItemArray[position]);
                startActivity(intent);
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
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}