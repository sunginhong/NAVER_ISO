package com.example.naver.naver_iso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.naver.naver_iso.MainActivity_AndDev_Private.class_DevPrivate_ItemArray;
import static java.lang.Integer.parseInt;

public class RecyclerViewAdapter_devPrivate extends RecyclerView.Adapter<RecyclerViewAdapter_devPrivate.MyViewHolder> implements View.OnClickListener  {
    ArrayList<String[]> arrayList1;
    ArrayList<Class<?>[]> arrayList2;
    Context c;

    public RecyclerViewAdapter_devPrivate (Context c, ArrayList<String[]> list, ArrayList<Class<?>[]> className) {
        arrayList1 = list;
        arrayList2 = className;
        this.c = c;
    }

    @Override
    public RecyclerViewAdapter_devPrivate.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_devprivate_fragment, parent, false);
        return new RecyclerViewAdapter_devPrivate.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_devPrivate.MyViewHolder holder, final int position) {

        String[] detail = arrayList1.get(position);
        Class<?>[] className = arrayList2.get(position);

        holder.dev_private_colorView.setBackgroundColor(Color.parseColor(detail[1]));
        holder.dev_private_titleview.setText(detail[2]);
        holder.dev_private_subtitleview.setText(detail[3]);

        holder.dev_private_lst_rl.setId(parseInt(detail[0]));
        holder.dev_private_lst_rl.setOnClickListener(this);

        Class<?> classNameF = className[0];
        class_DevPrivate_ItemArray[parseInt(detail[0])] = classNameF;
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Context c;

        private RelativeLayout dev_private_lst_rl;
        private View dev_private_colorView;
        private TextView dev_private_titleview;
        private TextView dev_private_subtitleview;

        public MyViewHolder(View itemView) {
            super(itemView);
            dev_private_lst_rl = (RelativeLayout)itemView.findViewById(R.id.dev_private_lst_rl);
            dev_private_colorView = (View)itemView.findViewById(R.id.dev_private_colorView);
            dev_private_titleview = (TextView)itemView.findViewById(R.id.dev_private_titleview);
            dev_private_subtitleview = (TextView)itemView.findViewById(R.id.dev_private_subtitleview);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), class_DevPrivate_ItemArray[view.getId()]);
        view.getContext().startActivity(intent);
    }
}
