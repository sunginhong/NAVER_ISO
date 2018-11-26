package com.example.naver.naver_iso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.naver.naver_iso.MainActivity.classMainItemArray;
import static com.example.naver.naver_iso.MainActivity.lstMainItemArray;
import static com.example.naver.naver_iso.MainActivity.lstMainRlArray;
import static java.lang.Integer.parseInt;

/**
 * Created by Naver on 2018. 8. 3..
 */

public class ListCustomAdapter_Main extends BaseAdapter {
    ArrayList<String[]> arrayList;
    ArrayList<Class<?>[]> arrayList2;
    Context c;
    boolean set = false;

    public ListCustomAdapter_Main(Context c, ArrayList<String[]> list, ArrayList<Class<?>[]> className) {
        arrayList = list;
        arrayList2 = className;
        this.c = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View row = null;
        LayoutInflater inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.main_row_layout, parent, false);
        } else {
            row = convertView;
        }
        String[] detail = arrayList.get(position);
        Class<?>[] className = arrayList2.get(position);

        RelativeLayout lst_rl= (RelativeLayout)row.findViewById(R.id.lst_rl);

        TextView listTitle = (TextView) row.findViewById(R.id.lst_title);
        listTitle.setText(detail[1]);

        lstMainRlArray[parseInt(detail[0])] = lst_rl;
        LinearLayout lst_item = (LinearLayout) row.findViewById(R.id.lst_item);
        lstMainItemArray[parseInt(detail[0])] = lst_item;

        Class<?> classNameF = className[0];
        classMainItemArray[parseInt(detail[0])] = classNameF;

        return row;
    }

}
