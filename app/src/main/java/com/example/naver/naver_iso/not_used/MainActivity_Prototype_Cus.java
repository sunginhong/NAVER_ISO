package com.example.naver.naver_iso.not_used;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;

import com.example.naver.naver_iso.R;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;


/**
 * Created by Naver on 2018. 8. 6..
 */

public class MainActivity_Prototype_Cus extends Activity {
    static final ArrayList<String[]> porotoTy_listURL_Array = new ArrayList<String[]>();
    private ExpandableListView lv;
    private final String packageName = "com.framerjs.android";
    public static String porotoTy_listURL;
    int lastClickedPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_prototype_main);

        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<Prototype_myGroup> DataList = new ArrayList<Prototype_myGroup>();
        lv = (ExpandableListView)findViewById(R.id.main_protoList);

        Prototype_myGroup temp = new Prototype_myGroup("[Taily] 인터랙션");
        temp.childTitle.add("'좋아요' 게이지 내 파티클 모션 / 게이지 위 하트 모션");
        temp.childUrl.add("https://framer.cloud/EYKJX");
        temp.childTitle.add("'좋아요' 버튼 효과 / 게이지 퍼센트에 따른 파티클 모션");
        temp.childUrl.add("https://framer.cloud/dkhfk/");
        temp.childTitle.add("'좋아요' 500개 달성시 축하 팝업 모션");
        temp.childUrl.add("https://framer.cloud/lKcJH/");
        temp.childTitle.add("로딩 인디케이터 애니메이션");
        temp.childUrl.add("https://framer.cloud/DeacY/");
        temp.childTitle.add("MY ID카드 펼침 애니메이션");
        temp.childUrl.add("https://framer.cloud/oWhCg/");

        DataList.add(temp);

        temp = new Prototype_myGroup("[오디오클립] 인터랙션");
        temp.childTitle.add("배너 Carousel 인터랙션");
        temp.childUrl.add("https://framer.cloud/TIZyd/");

        DataList.add(temp);

        ListCustomAdapter_ProtoTy_Cus adapter = new ListCustomAdapter_ProtoTy_Cus(getApplicationContext(),R.layout.activity_prototype_row_main, R.layout.activity_prototype_row_child, DataList);
        lv.setIndicatorBounds(width-50, width);
        lv.setAdapter(adapter);

        // 그룹 클릭 했을 경우 이벤트
        lv.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Listener 에서 Adapter 사용법 (getExpandableListAdapter 사용해야함.)
                // BaseExpandableAdpater에 오버라이드 된 함수들을 사용할 수 있다.
                int groupCount = (int) parent.getExpandableListAdapter().getGroupCount();
                int childCount = (int) parent.getExpandableListAdapter().getChildrenCount(groupPosition);

//                return false;
                Boolean isExpand = (!lv.isGroupExpanded(groupPosition));

                // 이 전에 열려있던 group 닫기
                lv.collapseGroup(lastClickedPosition);

                if(isExpand){ lv.expandGroup(groupPosition); }
                lastClickedPosition = groupPosition;

                return true;
            }
        });

        // 차일드 클릭 했을 경우 이벤트
        lv.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (launchIntent == null) {
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.framerjs.android"));
                    startActivity(intent2);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(porotoTy_listURL));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                return false;
            }
        });

        // 그룹이 닫힐 경우 이벤트
        lv.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

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

