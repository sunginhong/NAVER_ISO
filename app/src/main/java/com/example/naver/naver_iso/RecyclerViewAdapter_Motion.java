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

import static com.example.naver.naver_iso.MainActivity_Motion.urlMotionArray;

public class RecyclerViewAdapter_Motion extends RecyclerView.Adapter<RecyclerViewAdapter_Motion.MyViewHolder> implements View.OnClickListener  {
    ArrayList<String[]> arrayList;
    Context c;
    static View motionItemArray[] = new View[MainActivity_Motion.motionItemLength];
    private  MyViewHolder view;
    private float motionListHeight = 0;

    public RecyclerViewAdapter_Motion(Context c, ArrayList<String[]> list) {
        arrayList = list;
        this.c = c;
    }

    @Override
    public RecyclerViewAdapter_Motion.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_motion_fragment, parent, false);
        return new RecyclerViewAdapter_Motion.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Motion.MyViewHolder holder, final int position) {
        view = holder;
        String[] detail = arrayList.get(position);

        holder.motion_titleview.setText(detail[0]);
        holder.motion_subtitleview.setText(detail[1]);

        Uri motionThumb = Uri.parse(detail[2]);
        Picasso.with(c).load(motionThumb).into(holder.motion_imgview);

        holder.motion_lst_rl.setId(position);
        holder.motion_lst_rl.setOnClickListener(this);

        urlMotionArray[position] = detail[3];
        MainActivity_Motion.motionTitleNames[position] = detail[0];

        motionItemArray[position] = holder.motion_lst_rl;

        Utils.delayMin(5, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                motionListHeight = view.motion_lst_rl.getHeight()/1;
            }
        });
//        Utils.TransAlphaAnim(motionItemArray[position], 0, 0, motionListHeight, motionListHeight, 0, 0, 000);
        Utils.delayMin(8 * position, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                int i = position;
//                motionItemArray[position].setY(i * motionListHeight);
//                Utils.TransAlphaAnim(motionItemArray[i], 0, 0, motionListHeight, 0, 0, 1, 400);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Context c;

        private RelativeLayout motion_lst_rl;
        private ImageView motion_imgview;
        private TextView motion_titleview;
        private TextView motion_subtitleview;

        public MyViewHolder(View itemView) {
            super(itemView);
            motion_lst_rl = (RelativeLayout)itemView.findViewById(R.id.motion_lst_rl);
            motion_imgview = (ImageView)itemView.findViewById(R.id.motion_imgview);
            motion_titleview = (TextView)itemView.findViewById(R.id.motion_titleview);
            motion_subtitleview = (TextView)itemView.findViewById(R.id.motion_subtitleview);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailActivity_Motion.class);
        intent.putExtra("motionUrl" ,urlMotionArray[view.getId()]);
        intent.putExtra("motionTitle" ,MainActivity_Motion.motionTitleNames[view.getId()]);
        view.getContext().startActivity(intent);
    }
}
