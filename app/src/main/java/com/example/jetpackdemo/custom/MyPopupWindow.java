package com.example.jetpackdemo.custom;

import android.view.View;
import android.widget.PopupWindow;

public class MyPopupWindow  extends PopupWindow {
    public MyPopupWindow(View contentView, int width, int height, boolean b) {
        super(contentView, width, height, b);
    }

    @Override
    public void showAsDropDown(View anchor) {
//        if (Build.VERSION.SDK_INT >= 24) {
//            Rect rect = new Rect();
//            anchor.getGlobalVisibleRect(rect);
//            int heightPixels = anchor.getResources().getDisplayMetrics().heightPixels;
//            int h = heightPixels - rect.bottom;
//            setHeight(h);
//        }
        super.showAsDropDown(anchor);
    }
}
