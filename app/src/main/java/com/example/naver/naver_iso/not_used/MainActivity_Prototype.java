package com.example.naver.naver_iso.not_used;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.naver.naver_iso.R;

import java.util.ArrayList;

/**
 * Created by Naver on 2018. 8. 3..
 */

public class MainActivity_Prototype extends AppCompatActivity {

    static ListView lv;
    private Context mContext = null;

    public static final int ITEM_COUNT = 3;
    static RelativeLayout lstProtoTyRlArray[] = new RelativeLayout[ITEM_COUNT];
    static LinearLayout lstProtoTyItemArray[] = new LinearLayout[ITEM_COUNT];
    static Class<?> classProtoTyItemArray[] = new Class<?> [ITEM_COUNT];
    static String urlPotoTyArray[] = new String[ITEM_COUNT];
    static final ArrayList<String[]> valuesProtoTy = new ArrayList<String[]>();
    String porotoTy_listURL;

    private Intent intent;
    private final String packageName = "com.framerjs.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        if(valuesProtoTy.size() == 0){
            valuesProtoTy.add(new String[]{"0", "[Article.] 인터랙션", "https://framer.cloud/aakrT"});
            valuesProtoTy.add(new String[]{"1", "[Taily] 인터랙션", "https://framer.cloud/EYKJX"});
            valuesProtoTy.add(new String[]{"2", "[오디오클립] 인터랙션", "https://framer.cloud/TIZyd"});
        }
        lv = (ListView) findViewById(R.id.list_view);
        ListCustomAdapter_ProtoTy adapter = new ListCustomAdapter_ProtoTy(this, valuesProtoTy);
        lv.setAdapter(adapter);

        intent = getPackageManager().getLaunchIntentForPackage(packageName);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                porotoTy_listURL = urlPotoTyArray[position];
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (launchIntent == null) {
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.framerjs.android"));
                    startActivity(intent2);
                } else {
//                    Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(porotoTy_listURL));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
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



}
