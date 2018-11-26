package com.example.naver.naver_iso.not_used;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.naver.naver_iso.R;

import java.util.ArrayList;

import static com.example.naver.naver_iso.not_used.MainActivity_Prototype_Cus.porotoTy_listURL;

/**
 * Created by Naver on 2018. 8. 6..
 */

public class ListCustomAdapter_ProtoTy_Cus extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<Prototype_myGroup> DataList;

    private LayoutInflater myinf = null;
    private String title;
    private TextView protoType_rowTitle;
    public static TextView protoType_rowChild;
    private final String packageName = "com.framerjs.android";

    public static ArrayList<TextView> childArray = new ArrayList<TextView>();

    public static int childIndex;
    public static int childCurrent;
    public static boolean childArraySet = false;

    public ListCustomAdapter_ProtoTy_Cus(Context context,int groupLay,int chlidLay,ArrayList<Prototype_myGroup> DataList){
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;

        this.context = context;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        protoType_rowTitle = (TextView)convertView.findViewById(R.id.protoType_rowTitle);
        protoType_rowTitle.setText(DataList.get(groupPosition).groupName);
        childArraySet = false;
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        protoType_rowChild = (TextView)convertView.findViewById(R.id.protoType_rowChild);
        protoType_rowChild.setText(DataList.get(groupPosition).childTitle.get(childPosition));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.my_animation);
        protoType_rowChild.startAnimation(animation);

        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        getUrl(DataList.get(groupPosition).childUrl.get(childPosition));
        return true;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).childTitle.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).childTitle.size();
    }

    @Override
    public Prototype_myGroup getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return DataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    static public void getUrl(String  url){
        String urlResult = url;
        porotoTy_listURL = urlResult;
    }

}

