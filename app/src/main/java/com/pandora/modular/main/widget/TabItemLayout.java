package com.pandora.modular.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 自定义tabItem控件
 */
public class TabItemLayout extends LinearLayout {


    /**
     * 构造方法1
     *
     * @param context 上下文
     */
    public TabItemLayout(Context context) {
        super(context);
    }

    /**
     * 构造方法2
     *
     * @param context 上下文
     * @param attrs   属性的配置
     */
    public TabItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 测量的方法
     *
     * @param widthMeasureSpec  宽度
     * @param heightMeasureSpec 高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(getScreenWidth() / 4, heightSize);
    }


    /**
     * 获得屏幕的宽度
     *
     * @return 屏宽
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}