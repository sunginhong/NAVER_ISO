package com.example.naver.naver_iso;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainVp_MyPagerAdapter extends PagerAdapter implements View.OnClickListener {
    List<String> items = new ArrayList<String>();
    List<View> scrappedView = new ArrayList<View>();
    ArrayList<String[]> arrayList;
    Context c;
    private LayoutInflater mInflater;
    private Pools.SimplePool< View > mMyViewPool;
    private static final int MAX_POOL_SIZE = 10;

    public View view_d;
    private int selectIndex;


    public MainVp_MyPagerAdapter(Context c, ArrayList<String[]> list) {
        arrayList = list;
        this.c = c;
        mInflater = LayoutInflater.from(c);
        mMyViewPool = new Pools.SynchronizedPool< >(MAX_POOL_SIZE);
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public Fragment getItem(int position) { // (3)
        return MainVp_Fragment.newInstance("" + position);
    }

    @Override
    public int getCount() {
        return MainActivity.PAGE_ITEM_COUNT;
    }

    @Override
    public float getPageWidth(int position) {
        return MainActivity.MAIN_PAGE_WIDTH_RATE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = mInflater.inflate(R.layout.activity_main_pager_fragment, null);

        String[] detail = arrayList.get(position);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.activity_main_pager_fragment, container, false);

        RelativeLayout vpLayout = (RelativeLayout) view.findViewById(R.id.main_vp_vpLayout);
        LinearLayout main_vp_textll = (LinearLayout) view.findViewById(R.id.main_vp_textll);

        CardView main_vp_cardcotain = (CardView) view.findViewById(R.id.main_vp_cardcotain);
        TextView titleTv = (TextView) view.findViewById(R.id.main_vp_titleTv);
        titleTv.setText(detail[0]);
        TextView subTitleTv = (TextView) view.findViewById(R.id.main_vp_subTitleTv);
        subTitleTv.setText(detail[1]);
        ImageView ImgView = (ImageView) view.findViewById(R.id.main_vp_ImgView);
        Picasso.with(c).load(detail[2]).into(ImgView);

        MainActivity.pvArray[position] = vpLayout;
        MainActivity.bgImgArray[position] = ImgView;
        MainActivity.main_vp_cardcotainArray[position] = main_vp_cardcotain;

        MainActivity.titleMainRecentArray[position] = detail[0];
        MainActivity.imgMainRecentArray[position] = detail[2];
        MainActivity.urlMainRecentArray[position] = detail[3];
        MainActivity.main_vp_textllArray[position] = main_vp_textll;

        ((ViewPager)container).addView(view, position);
        main_vp_cardcotain.setOnClickListener(this);
        main_vp_cardcotain.setId(position);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager)container).removeView((ViewGroup)view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void onClick(View view) {
        if (!MainActivity.sidemenuActive){
            view_d = view;
            selectIndex = view_d.getId();

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) c,
                    Pair.create((View)view_d, c.getString(R.string.transition_pagecard)) );
            Intent intent = new Intent(view_d.getContext(), DetailActivity_Recent.class);
            intent.putExtra("recentTitle" ,MainActivity.titleMainRecentArray[view_d.getId()]);
            intent.putExtra("recentImg" ,MainActivity.imgMainRecentArray[view_d.getId()]);
            intent.putExtra("recentUrl" ,MainActivity.urlMainRecentArray[view_d.getId()]);
            view_d.getContext().startActivity(intent, options.toBundle());
        }
    }

}