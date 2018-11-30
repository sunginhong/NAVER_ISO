package com.example.naver.naver_iso;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


public class MainVp_MyPagerAdapter extends PagerAdapter {
    List<String> items = new ArrayList<String>();
    List<View> scrappedView = new ArrayList<View>();
    ArrayList<String[]> arrayList;
    Context c;
    private LayoutInflater mInflater;
    private Pools.SimplePool< View > mMyViewPool;
    private static final int MAX_POOL_SIZE = 10;


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
        return 1.0f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = mInflater.inflate(R.layout.activity_main_pager_fragment, null);

        String[] detail = arrayList.get(position);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.activity_main_pager_fragment, container, false);

        RelativeLayout vpLayout = (RelativeLayout) view.findViewById(R.id.main_vp_vpLayout);


        TextView titleTv = (TextView) view.findViewById(R.id.main_vp_titleTv);
        titleTv.setText(detail[1]);
        TextView subTitleTv = (TextView) view.findViewById(R.id.main_vp_subTitleTv);
        subTitleTv.setText(detail[2]);
        ImageView ImgView = (ImageView) view.findViewById(R.id.main_vp_ImgView);
        Picasso.with(c).load(detail[3]).into(ImgView);

        MainActivity.pvArray[parseInt(detail[0])] = vpLayout;
        MainActivity.bgImgArray[parseInt(detail[0])] = ImgView;

        ((ViewPager)container).addView(view, position);

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
}