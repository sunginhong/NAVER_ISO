package com.example.naver.naver_iso;

import android.content.Context;
import android.view.View;

public class ScrollHederAnim {
    Context context;

    public ScrollHederAnim() {

    }

    public static void HeaderShow(View item, int HeaderPosY1, int HeaderPosY2) {
        Utils.TransAnim(item, 0, 0, HeaderPosY1, HeaderPosY2, 300 );
    }
    public static void HeaderHide(View item, int HeaderPosY1, int HeaderPosY2) {
        Utils.TransAnim(item, 0, 0, HeaderPosY1, HeaderPosY2, 300 );
    }
}

