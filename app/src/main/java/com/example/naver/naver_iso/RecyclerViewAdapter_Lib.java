package com.example.naver.naver_iso;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.naver.naver_iso.MainActivity_Library.urlLibArray;
import static java.lang.Integer.parseInt;

public class RecyclerViewAdapter_Lib extends RecyclerView.Adapter<RecyclerViewAdapter_Lib.MyViewHolder> implements View.OnClickListener  {
    ArrayList<String[]> arrayList;
    Context c;

    public RecyclerViewAdapter_Lib (Context c, ArrayList<String[]> list) {
        arrayList = list;
        this.c = c;
    }

    @Override
    public RecyclerViewAdapter_Lib.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_library_fragment, parent, false);
        return new RecyclerViewAdapter_Lib.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Lib.MyViewHolder holder, final int position) {

        String[] detail = arrayList.get(position);

        holder.lib_titleview.setText(detail[1]);
        holder.lib_subtitleview.setText(detail[2]);

        Uri myUri = Uri.parse(detail[3]);
        Picasso.with(c).load(myUri).into(holder.lib_imgview);

        holder.lib_lst_rl.setId(parseInt(detail[0]));
        holder.lib_lst_rl.setOnClickListener(this);

        urlLibArray[parseInt(detail[0])] = detail[4];
        MainActivity_Library.libTitleNames[parseInt(detail[0])] = detail[1];
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Context c;

        private RelativeLayout lib_lst_rl;
        private TextView lib_titleview;
        private TextView lib_subtitleview;
        private ImageView lib_imgview;

        public MyViewHolder(View itemView) {
            super(itemView);
            lib_lst_rl = (RelativeLayout)itemView.findViewById(R.id.lib_lst_rl);
            lib_titleview = (TextView)itemView.findViewById(R.id.lib_titleview);
            lib_subtitleview = (TextView)itemView.findViewById(R.id.lib_subtitleview);
            lib_imgview = (ImageView)itemView.findViewById(R.id.lib_imgview);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailActivity_Library.class);
        intent.putExtra("libraryeUrl" ,urlLibArray[view.getId()]);
        intent.putExtra("libraryeTitle" ,MainActivity_Library.libTitleNames[view.getId()]);
        view.getContext().startActivity(intent);
    }

}
