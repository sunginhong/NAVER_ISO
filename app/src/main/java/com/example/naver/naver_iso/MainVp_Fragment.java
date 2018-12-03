package com.example.naver.naver_iso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainVp_Fragment extends Fragment {
    private static final String ARG_COUNT = "count";
    private static final String ARG_PARAM2 = "param2";

    private String mCount;


    public MainVp_Fragment() {
        // Required empty public constructor
    }

    public static MainVp_Fragment newInstance(String param1) {
        MainVp_Fragment fragment = new MainVp_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_COUNT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCount = getArguments().getString(ARG_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_pager_fragment, container, false);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getActivity().setTitle("count : " + mCount);
        }
    }

}
