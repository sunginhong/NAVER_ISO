package com.example.naver.naver_iso.not_used;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.naver.naver_iso.R;

import java.util.ArrayList;

import static com.example.naver.naver_iso.not_used.MainActivity_Prototype.lstProtoTyItemArray;
import static com.example.naver.naver_iso.not_used.MainActivity_Prototype.lstProtoTyRlArray;
import static com.example.naver.naver_iso.not_used.MainActivity_Prototype.urlPotoTyArray;
import static java.lang.Integer.parseInt;

/**
 * Created by Naver on 2018. 8. 3..
 */

public class ListCustomAdapter_ProtoTy extends BaseAdapter {
    ArrayList<String[]> arrayList;
    Context c;
    boolean set = false;

    public ListCustomAdapter_ProtoTy(Context c, ArrayList<String[]> list) {
        arrayList = list;
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

        RelativeLayout lst_rl= (RelativeLayout)row.findViewById(R.id.lst_rl);

        TextView listTitle = (TextView) row.findViewById(R.id.lst_title);
        listTitle.setText(detail[1]);

        lstProtoTyRlArray[parseInt(detail[0])] = lst_rl;
        LinearLayout lst_item = (LinearLayout) row.findViewById(R.id.lst_item);
        lstProtoTyItemArray[parseInt(detail[0])] = lst_item;

        urlPotoTyArray[parseInt(detail[0])] = detail[2];

        return row;
    }

}