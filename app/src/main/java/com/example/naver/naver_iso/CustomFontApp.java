package com.example.naver.naver_iso;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

public class CustomFontApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NanumSquareRoundR.ttf"))
                .addBold(Typekit.createFromAsset(this, "NanumSquareRoundB.ttf"))
                .addBoldItalic(Typekit.createFromAsset(this, "NanumSquareRoundL.ttf"));

    }
}

