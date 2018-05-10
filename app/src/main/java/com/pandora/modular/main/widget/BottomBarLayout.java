package com.pandora.modular.main.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pandora.R;
import com.pandora.modular.main.bean.TabEntity;

import java.util.List;


/**
 * Created by Administrator on 2018/5/8.
 */

public class BottomBarLayout extends LinearLayout implements View.OnClickListener {

    private int textColor;
    private int textSize;
    private int textSelectColor;
    private boolean isShowDot;
    private int dotCount;


    private int[] itemIcon;
    private int[] itemSelectIcon;

    private LinearLayout mLinearLayout;

    private List<TabEntity> mTabs;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onTabClick(int position);
    }


    public BottomBarLayout(Context context) {
        this(context, null);
    }

    public BottomBarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,  AttributeSet attrs) {
        mLinearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.container_layout, null, false);
        addView(mLinearLayout);
        initAttr(context, attrs);
    }

    private void initAttr(Context context,  AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarLayout);
        textColor = typedArray.getColor(R.styleable.BottomBarLayout_text_color, Color.BLACK);
        textSize = typedArray.getDimensionPixelSize(R.styleable.BottomBarLayout_text_size, 0);
        textSelectColor = typedArray.getColor(R.styleable.BottomBarLayout_text_select_color, Color.BLUE);
        isShowDot = typedArray.getBoolean(R.styleable.BottomBarLayout_is_show_dot, false);
        dotCount = typedArray.getInteger(R.styleable.BottomBarLayout_dot_count, 0);

        setTextColor(textColor);
        setTextSelectColor(textSelectColor);
        setTextSize(textSize);

        typedArray.recycle();
    }

    public void setTabList(Context context, int defaultPosition) {
        for (int i = 0, length = mTabs.size(); i < length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_bottom_layout, mLinearLayout, false);
            ImageView image = (ImageView) view.findViewById(R.id.item_image);
            TextView text = (TextView) view.findViewById(R.id.item_text);
            image.setImageResource(mTabs.get(i).getNormalIconId());
            text.setText(mTabs.get(i).getText());
            text.setTextColor(getTextColor());
            view.setId(i);
            // TODO: 之后可以处理是否显示小红点，以及小红点的数据量
            if (mTabs.get(i).isShowPoint()) {

            }
            view.setOnClickListener(this);
            mLinearLayout.addView(view);
            if (i == defaultPosition) {
                showTab(defaultPosition, view);
            }
        }
    }

    public void showTab(int position) {
        clearStatus();
        TextView text = (TextView) mLinearLayout.getChildAt(position).findViewById(R.id.item_text);
        ImageView image = (ImageView) mLinearLayout.getChildAt(position).findViewById(R.id.item_image);
        text.setTextColor(getTextSelectColor());
        image.setImageResource(mTabs.get(position).getSelectIconId());
    }

    /**
     * 设置tab的点击状态
     *
     * @param position tab位置
     * @param view     控件
     */
    public void showTab(int position, View view) {
        clearStatus();
        TextView text = (TextView) view.findViewById(R.id.item_text);
        text.setTextColor(getTextSelectColor());
        ImageView icon = (ImageView) view.findViewById(R.id.item_image);
        icon.setImageResource(mTabs.get(position).getSelectIconId());
    }

    public void clearStatus() {
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View itemView = mLinearLayout.getChildAt(i);
            ImageView icon = (ImageView) itemView.findViewById(R.id.item_image);
            TextView text = (TextView) itemView.findViewById(R.id.item_text);
            text.setTextColor(textColor);
            if (i < mTabs.size()) {
                icon.setImageResource(mTabs.get(i).getNormalIconId());
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener == null) {
            return;
        }
        mOnItemClickListener.onTabClick(v.getId());
    }

    public List<TabEntity> getTabs() {
        return mTabs;
    }

    public void setTabs(List<TabEntity> tabs) {
        mTabs = tabs;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public int[] getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int[] itemIcon) {
        this.itemIcon = itemIcon;
    }

    public int[] getItemSelectIcon() {
        return itemSelectIcon;
    }

    public void setItemSelectIcon(int[] itemSelectIcon) {
        this.itemSelectIcon = itemSelectIcon;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextSelectColor() {
        return textSelectColor;
    }

    public void setTextSelectColor(int textSelectColor) {
        this.textSelectColor = textSelectColor;
    }

    public boolean isShowDot() {
        return isShowDot;
    }

    public void setShowDot(boolean showDot) {
        isShowDot = showDot;
    }

    public int getDotCount() {
        return dotCount;
    }

    public void setDotCount(int dotCount) {
        this.dotCount = dotCount;
    }


}
