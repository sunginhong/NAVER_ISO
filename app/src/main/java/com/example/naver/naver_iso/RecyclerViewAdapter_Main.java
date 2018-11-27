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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import static com.example.naver.naver_iso.MainActivity.classMainItemArray;
import static java.lang.Integer.parseInt;

/**
 * Created by Naver on 2018. 8. 7..
 */

public class RecyclerViewAdapter_Main extends RecyclerView.Adapter<RecyclerViewAdapter_Main.MyViewHolder> implements View.OnClickListener {
    ArrayList<String[]> arrayList1;
    ArrayList<Class<?>[]> arrayList2;
    static Context context;

    public static View itemView_view;
    public static List<View> itemView_view_items = new ArrayList<View>();

    public static VideoView rv_mainVideoView_resume;
    private static int TYPE_HEADER = 0;
    private static int TYPE_FOOTER = 3;
    MediaController mediaController;
    public static MediaPlayer mMediaPlayer;
    public static int mainViewItemN = 0;
    public static View view_d;

    static VideoView vieoViewArray[] = new VideoView[mainViewItemN];
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
//        int ImgViewArr = parseInt(detail[2]);
//        holder.rv_mainLottieView.setImageResource(ImgViewArr);
        holder.rv_mainSubTextView.setText(detail[2]);

//        holder.rv_mainLottieView.setAnimation(detail[3]);
//        holder.rv_mainLottieView.pauseAnimation();
//        holder.rv_mainLottieView.loop(true);
//        holder.rv_mainLottieView.playAnimation();

        holder.rv_mainView.setBackgroundColor(Color.parseColor(detail[3]));
        holder.rv_mainCaseLineView.setBackgroundColor(Color.parseColor(detail[3]));

        Class<?> classNameF = className[0];
        classMainItemArray[parseInt(detail[0])] = classNameF;

        holder.rv_mainLL.setId(parseInt(detail[0]));
        holder.rv_mainLL.setOnClickListener(this);

//        MediaController ctrl = new MediaController(context);
//        ctrl.setVisibility(View.GONE);
//        holder.rv_mainVideoView.setMediaController(ctrl);
//        if (position == 0){ holder.rv_mainVideoView.setVideoURI(Uri.parse("android.resource://com.example.naver.naver_iso/" + R.raw.videointeraction )); }
//        if (position == 1){ holder.rv_mainVideoView.setVideoURI(Uri.parse("android.resource://com.example.naver.naver_iso/" + R.raw.videomotion )); }
//        if (position == 2){ holder.rv_mainVideoView.setVideoURI(Uri.parse("android.resource://com.example.naver.naver_iso/" + R.raw.videoabout )); }
//        if (position == 3){ holder.rv_mainVideoView.setVideoURI(Uri.parse("android.resource://com.example.naver.naver_iso/" + R.raw.videoabout )); }
//
////        holder.rv_mainVideoView.start();
//        vieoViewArray_List.add(holder.rv_mainVideoView);
//
//        holder.rv_mainVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mMediaPlayer = mp;
//                mMediaPlayer.setLooping(true);
//                mMediaPlayer.start();
//            }
//        });
//
//        holder.rv_mainVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//            }
//        });

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
        private View rv_mainView;
        private TextView rv_mainTextView;
        private TextView rv_mainSubTextView;
//        private LottieAnimationView rv_mainLottieView;
        private View rv_mainCaseLineView;
        private ImageView rv_mainImageView;
//        private VideoView rv_mainVideoView;
//        private WebView rv_mainWebview;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView_view = itemView;
            rv_cardView = (CardView)itemView.findViewById(R.id.rv_cardView);
            rv_mainRl = (RelativeLayout)itemView.findViewById(R.id.rv_mainRl);
            rv_mainLL = (LinearLayout)itemView.findViewById(R.id.rv_mainLL);
            rv_mainView = (View) itemView.findViewById(R.id.rv_mainView);
            rv_mainTextView = (TextView)itemView.findViewById(R.id.rv_mainTextView);
            rv_mainSubTextView = (TextView)itemView.findViewById(R.id.rv_mainSubTextView);
//            rv_mainLottieView = (LottieAnimationView)itemView.findViewById(R.id.rv_mainLottieView);
            rv_mainCaseLineView = (View) itemView.findViewById(R.id.rv_mainCaseLineView);
            rv_mainImageView = (ImageView) itemView.findViewById(R.id.rv_mainImageView);
//            rv_mainVideoView = (VideoView) itemView.findViewById(R.id.rv_mainVideoView);
        }
    }

    @Override
    public void onClick(View view) {
        view_d = view;
        Utils.AlphaAnim(view_d, 1, 0, 200);

        view_d.bringToFront();

        ActivityOptions options = null;
        options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, view_d, context.getString(R.string.transition_maincard));
        Intent intent = new Intent(view_d.getContext(), classMainItemArray[view_d.getId()]);
        view_d.getContext().startActivity(intent, options.toBundle());
    }

    public static void reset() {
        Utils.AlphaAnim(view_d, 0, 1, 400);
    }
}

