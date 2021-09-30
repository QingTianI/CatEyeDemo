package com.example.jetpackdemo.custom;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

public class PopupWindowManager {
    private MyPopupWindow pw;

    /**
     * 构造私有
     */
    private PopupWindowManager() {
    }


    /**
     * 匿名内部内实现单例
     */
    private static class PopupWindowManagerHolder {
        private static final PopupWindowManager INSTANCE = new PopupWindowManager();
    }

    public static PopupWindowManager getInstance() {
        return PopupWindowManagerHolder.INSTANCE;
    }

    /**
     * 初始宽度
     */
    private static final int POP_WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    /**
     * 初始高度
     */
    private static final int POP_HEIGHT = ViewGroup.LayoutParams.WRAP_CONTENT;

    /**
     * 初始化 使用默认宽高
     *
     * @param contentView PopupWindow需要展示的view
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PopupWindowManager init(View contentView) {
        return init(contentView, POP_WIDTH, POP_HEIGHT);
    }

    /**
     * 初始化 使用自定义宽高
     *
     * @param contentView PopupWindow需要展示的view
     * @param width       宽
     * @param height      高
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PopupWindowManager init(View contentView, int width, int height) {
        pw = new MyPopupWindow(contentView, width, height, true);
        pw.setOutsideTouchable(true);
        pw.setFocusable(false);
        pw.setElevation(0);
        pw.setTouchable(true);
        return this;
    }

    /**
     * 设置关闭监听F
     *
     * @param listener
     */
    public void setPopDismissListener(MyPopupWindow.OnDismissListener listener) {
        if (null == pw) {
            return;
        }
        pw.setOnDismissListener(listener);
    }

    /**
     * 显示在指定的targetView的指定位置 无偏移量
     *
     * @param targetView 目标view
     * @param gravity    位置
     */
    public void showAtLocation(View targetView, int gravity) {
        showAtLocation(targetView, gravity, 0, 0);
    }

    /**
     * 显示在指定的targetView的指定位置 带偏移量
     *
     * @param targetView 目标view
     * @param gravity    位置
     * @param x          x轴偏移量
     * @param y          y轴偏移量
     */
    public void showAtLocation(View targetView, int gravity, int x, int y) {
        if (null == pw) {
            return;
        }
        pw.showAtLocation(targetView, gravity, x, y);
    }

    /**
     * 显示在指定的targetView底部 无偏移量
     *
     * @param targetView 目标view
     */
    public void showAsDropDown(View targetView) {
        if (null == pw) {
            return;
        }
        pw.showAsDropDown(targetView);
    }

    /**
     * 显示在指定的targetView底部 有偏移量
     *
     * @param targetView 目标view
     * @param offX       x轴偏移量
     * @param offY       y轴偏移量
     */
    public void showAsDropDown(View targetView, int offX, int offY) {
        if (null == pw) {
            return;
        }
        pw.showAsDropDown(targetView, offX, offY);
    }

    /**
     * 显示在指定的targetView的指定位置
     *
     * @param targetView 目标view
     * @param offX       x轴偏移量
     * @param offY       y轴偏移量
     * @param gravity    位置
     */
    public void showAsDropDown(View targetView, int offX, int offY, int gravity) {
        if (null == pw) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            pw.showAsDropDown(targetView, offX, offY, gravity);
        }
    }

    /**
     * 关闭
     */
    public void dismiss() {
        if (null == pw) {
            return;
        }
        pw.dismiss();
    }

    public  void ToggleWindow(MyPopupWindow popupWindow, View targetView,PopupWindowManager.onWindowStatusChanged statusChanged) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
            statusChanged.onDismiss();

        } else {
            statusChanged.onShow();
        }
    }

    public interface onWindowStatusChanged {
        void onDismiss();

        void onShow();
    }

}
