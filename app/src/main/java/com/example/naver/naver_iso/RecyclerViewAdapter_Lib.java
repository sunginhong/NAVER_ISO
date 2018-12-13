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

public class RecyclerViewAdapter_Lib extends RecyclerView.Adapter<RecyclerViewAdapter_Lib.MyViewHolder> implements View.OnClickListener  {
    ArrayList<String[]> arrayList;
    Context c;
    static View libItemArray[] = new View[MainActivity_Library.libItemLength];
    private  MyViewHolder view;
    private float LibListHeight = 0;

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
        view = holder;
        String[] detail = arrayList.get(position);

        holder.lib_titleview.setText(detail[0]);
        holder.lib_subtitleview.setText(detail[1]);

        Uri libThumb = Uri.parse(detail[2]);
        Picasso.with(c).load(libThumb).into(holder.lib_imgview);

        holder.lib_lst_rl.setId(position);
        holder.lib_lst_rl.setOnClickListener(this);

        urlLibArray[position] = detail[3];
        MainActivity_Library.libTitleNames[position] = detail[0];

        libItemArray[position] = holder.lib_lst_rl;

        Utils.delayMin(5, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                LibListHeight = view.lib_lst_rl.getHeight()/1;
            }
        });
        Utils.TransAlphaAnim(libItemArray[position], 0, 0, LibListHeight, LibListHeight, 0, 0, 000);
        Utils.delayMin(8 * position, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                int i = position;
                libItemArray[position].setY(i * LibListHeight);
                Utils.TransAlphaAnim(libItemArray[i], 0, 0, LibListHeight, 0, 0, 1, 400);
//                if (libItemArray[i].getY() < MainActivity.screenHeight){
//                    Utils.TransAlphaAnim(libItemArray[i], 0, 0, LibListHeight, 0, 0, 1, 400);
//                } else {
//                    Utils.TransAlphaAnim(libItemArray[i], 0, 0, 0, 0, 1, 1, 000);
//                }
            }
        });
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
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            lib_lst_rl = (RelativeLayout)itemView.findViewById(R.id.lib_lst_rl);
            lib_titleview = (TextView)itemView.findViewById(R.id.lib_titleview);
            lib_subtitleview = (TextView)itemView.findViewById(R.id.lib_subtitleview);
            lib_imgview = (ImageView)itemView.findViewById(R.id.lib_imgview);
            view = itemView;
            Utils.delayMin(10, new Utils.DelayCallback() {
                @Override
                public void afterDelay() {
                    MainActivity_Library.Lib_LisItem_Height = view.getHeight();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailActivity_Library.class);
        intent.putExtra("libraryeUrl" ,urlLibArray[view.getId()]);
        intent.putExtra("libraryeTitle" ,MainActivity_Library.libTitleNames[view.getId()]);
        view.getContext().startActivity(intent);
    }


    public static void LibItemIntroAnim(Context context){
        for (int i = 0; i < libItemArray.length; i++) {
//            if (libItemArray[i].getY() > 0){
////                libItemHeight = libItemArray[i].getHeight();
//                Log.v("ssssssss", ""+String.valueOf(libItemArray[i].getHeight()));
////                libItemArray[i].setTranslationY(libItemHeight);
//            }
        }
    }

}
