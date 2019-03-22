package com.example.naver.naver_iso;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /** 나눔 폰트를 기본으로 설정한다 */
        FontOverride.setDefaultFont(this, "monospace", "nanum_gothic.ttf");
    }
}