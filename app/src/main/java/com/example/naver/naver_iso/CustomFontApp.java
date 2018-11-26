package com.example.naver.naver_iso;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

public class CustomFontApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "notosans_regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "notosans_bold.ttf"))
                .addItalic(Typekit.createFromAsset(this, "notosans_italic.ttf"))
                .add("superior_Title_Bold",Typekit.createFromAsset(this, "superior_title_bold.otf"));
    }
}