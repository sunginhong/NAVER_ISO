package com.example.naver.naver_iso;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.naver.naver_iso.MainActivity.classMainItemArray;

/**
 * Created by Naver on 2018. 8. 7..
 */

public class RecyclerViewAdapter_Main extends RecyclerView.Adapter<RecyclerViewAdapter_Main.MyViewHolder> implements View.OnClickListener {
    ArrayList<String[]> arrayList1;
    ArrayList<Class<?>[]> arrayList2;
    ArrayList<CardView[]> arrayList3;
    static Context context;
    public static boolean mainclick = false;
    static Animation cardImageIn, cardImageOut;

    public static View itemView_view;
    public static List<View> itemView_view_items = new ArrayList<View>();

    public static VideoView rv_mainVideoView_resume;
    private static int TYPE_HEADER = 0;
    private static int TYPE_FOOTER = 3;
    MediaController mediaController;
    public static MediaPlayer mMediaPlayer;
    public static int mainViewItemN = 0;
    public static View view_d;
    public static int mainCardHeight = 0;
    public static int selectIndex = 0;

    static VideoView vieoViewArray[] = new VideoView[mainViewItemN];

//    static List<ImageView> mainCardImgArray = new ArrayList<ImageView>();
    static ArrayList<ImageView> mainCardImgArray = new ArrayList<ImageView>();
    static ArrayList<View> main_cardArray = new ArrayList<View>();
    static List<VideoView> vieoViewArray_List = new ArrayList<VideoView>();

    public RecyclerViewAdapter_Main (Context context, ArrayList<String[]> list, ArrayList<Class<?>[]> className) {
        arrayList1 = list;
        arrayList2 = className;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_card_fragmaent, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();
        MyViewHolder myViewHolder = holder;

        String[] detail = arrayList1.get(position);
        Class<?>[] className = arrayList2.get(position);

        holder.rv_mainTextView.setText(detail[1]);
        holder.rv_mainSubTextView.setText(detail[2]);
        holder.rv_mainView.setBackgroundColor(Color.parseColor(detail[3]));

        Class<?> classNameF = className[0];
        classMainItemArray[position] = classNameF;

        holder.rv_mainLL.setId(position);
        holder.rv_mainLL.setOnClickListener(this);

        MainActivity.lstMaincardArray[position] = holder.itemView;

        if (position == 0){ Picasso.with(context).load(MainActivity.URL_THUMB_IMG+"main_thumb_00.png").into(holder.rv_mainImageView); }
        if (position == 1){ Picasso.with(context).load(MainActivity.URL_THUMB_IMG+"main_thumb_01.png").into(holder.rv_mainImageView); }
        if (position == 2){ Picasso.with(context).load(MainActivity.URL_THUMB_IMG+"main_thumb_02.png").into(holder.rv_mainImageView); }
    }

    @Override
    public int getItemCount() {
        mainViewItemN = arrayList1.size();
        return arrayList1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView rv_cardView;
        private RelativeLayout rv_mainRl;
        private LinearLayout rv_mainLL;
        private LinearLayout rv_mainLL_item;
        private View rv_mainView;
        private TextView rv_mainTextView;
        private TextView rv_mainSubTextView;
        private View rv_mainCaseLineView;
        private ImageView rv_mainImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView_view = itemView;
            rv_cardView = (CardView)itemView.findViewById(R.id.rv_cardView);
            rv_mainRl = (RelativeLayout)itemView.findViewById(R.id.rv_mainRl);
            rv_mainLL = (LinearLayout)itemView.findViewById(R.id.rv_mainLL);
            rv_mainLL_item = (LinearLayout) itemView.findViewById(R.id.rv_mainLL_item);
            rv_mainView = (View) itemView.findViewById(R.id.rv_mainView);
            rv_mainTextView = (TextView)itemView.findViewById(R.id.rv_mainTextView);
            rv_mainSubTextView = (TextView)itemView.findViewById(R.id.rv_mainSubTextView);
            rv_mainCaseLineView = (View) itemView.findViewById(R.id.rv_mainCaseLineView);
            rv_mainImageView = (ImageView) itemView.findViewById(R.id.rv_mainImageView);
            mainCardImgArray.add(rv_mainImageView);
//            main_cardArray.add(itemView_view);
        }
    }


    @Override
    public void onClick(View view) {
        if (!MainActivity.sidemenuActive){
            if (!mainclick){
                mainclick = true;
                mainCardHeight = Utils.dpToPx(85);;
                view_d = view;
                selectIndex = view_d.getId();

                Utils.AlphaAnim(view_d, 1, 0, 100);

                Utils.delayMin(0, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                Pair.create((View)view_d, context.getString(R.string.transition_maincard)) );

                        Intent intent = new Intent(view_d.getContext(), classMainItemArray[view_d.getId()]);
                        view_d.getContext().startActivity(intent, options.toBundle());
                    }
                });
            }
        }
    }



    public static void reset() {
        if (mainclick){
            Utils.AlphaAnim(view_d, 0, 1, 400);
            view_d.setAlpha(1);
            mainclick = false;

        }
    }
}

