package com.example.naver.naver_iso.and_dev;

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

import com.example.naver.naver_iso.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Naver on 2018. 8. 2..
 */

public class ParallaxVP02_MyPagerAdapter extends PagerAdapter {
    List<String> items = new ArrayList<String>();
    List<View> scrappedView = new ArrayList<View>();
    ArrayList<String[]> arrayList;
    Context c;
    private LayoutInflater mInflater;
    private Pools.SimplePool< View > mMyViewPool;
    private static final int MAX_POOL_SIZE = 10;

    public ParallaxVP02_MyPagerAdapter(Context c, ArrayList<String[]> list) {
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
        return ParallaxVP02_PagerFragment.newInstance("" + position);
    }

    @Override
    public int getCount() {
        return ParallaxVP02_MainActivity.ITEM_COUNT;
    }

    @Override
    public float getPageWidth(int position) {
        return 1.0f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = mInflater.inflate(R.layout.parallaxvp02_activiry_fragment, null);

        String[] detail = arrayList.get(position);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.parallaxvp02_activiry_fragment, container, false);

        RelativeLayout vpLayout = (RelativeLayout) view.findViewById(R.id.vp02_vpLayout);

        TextView titleTv = (TextView) view.findViewById(R.id.vp02_titleTv);
        titleTv.setText(detail[1]);
        TextView subTitleTv = (TextView) view.findViewById(R.id.vp02_subTitleTv);
        subTitleTv.setText(detail[2]);
        ImageView ImgView = (ImageView) view.findViewById(R.id.vp02_ImgView);
        int ImgViewArr = parseInt(detail[3]);
        ImgView.setImageResource(ImgViewArr);

        RelativeLayout vp02_RL = (RelativeLayout) view.findViewById(R.id.vp02_RL);
        ParallaxVP02_MainActivity.vp02RL_Array[parseInt(detail[0])] = vp02_RL;
        vp02_RL.setId(parseInt(detail[0]));

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