package com.example.naver.naver_iso;

import android.support.v7.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 0;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
