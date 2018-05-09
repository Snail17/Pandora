package com.pandora.core.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pandora.R;


/**
 * @description： 封装好的通用标题栏控件
 */
public class CustomTitlebar extends RelativeLayout implements OnClickListener {

    /**
     * 标题栏的根布局
     */
    private RelativeLayout mRelativeLayout;
    /**
     * 标题栏的左边按返回图标
     */
    private ImageView mIvLeft;
    /**
     * 标题栏的小红点
     */
    private ImageView mIvRedDot;
    /**
     * 标题栏的右边图标
     */
    private ImageView mIvRight;
    /**
     * 标题栏左边按钮文字
     */
    private TextView mTvLeft;

    /**
     * 右边按钮的文字颜色
     */
    private int left_button_textColor;
    /**
     * 右边保存按钮的文字大小
     */
    private int left_button_textSize;
    /**
     * 标题栏文字标题
     */
    private TextView mTvTilte;
    /**
     * 标题栏的背景颜色
     */
    private int title_background_color;
    /**
     * 标题栏的显示的标题文字
     */
    private String title_text;
    /**
     * 标题栏的显示的标题文字颜色
     */
    private int title_textColor;
    /**
     * 标题栏的显示的标题文字大小
     */
    private int title_textSize;

    /**
     * 标题栏的顶部分割线
     */
    private View line;
    /**
     * 标题栏的右边按钮文字
     */
    private TextView mTvRight;
    /**
     * 右边保存按钮的资源图片
     */
    private int right_button_image_id;
    /**
     * 右边保存按钮的文字
     */
    private String right_button_text;
    /**
     * 右边按钮的文字颜色
     */
    private int right_button_textColor;
    /**
     * 右边保存按钮的文字大小
     */
    private int right_button_textSize;

    /**
     * 返回按钮上显示的文字
     */
    private String left_button_text;
    /**
     * 返回按钮的资源图片
     */
    private int left_button_imageId;


    private TitleBarOnClickListener mTitleBarOnClickListener;

    public CustomTitlebar(Context context) {
        this(context, null);
    }

    public CustomTitlebar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    private void initView(Context context) {
        /**加载布局文件*/
        View.inflate(context, R.layout.custom_actionbar, this);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relay_background);
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mIvRedDot = (ImageView) findViewById(R.id.iv_red_dot);
        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvTilte = (TextView) findViewById(R.id.tv_title);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        line = findViewById(R.id.line);
    }


    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitlebar);
        /**返回按钮相关*/
        left_button_imageId = typedArray.getResourceId(R.styleable.CustomTitlebar_left_button_image, 0);
        left_button_text = typedArray.getString(R.styleable.CustomTitlebar_left_button_text);
        left_button_textColor = typedArray.getColor(R.styleable.CustomTitlebar_left_button_textColor, Color.BLACK);
        left_button_textSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitlebar_left_button_textSize, sp2px(context, 14));

        /**标题相关*/
        title_background_color = typedArray.getColor(R.styleable.CustomTitlebar_title_background, Color.WHITE);
        title_text = typedArray.getString(R.styleable.CustomTitlebar_title_text);
        title_textColor = typedArray.getColor(R.styleable.CustomTitlebar_title_textColor, Color.BLACK);
        title_textSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitlebar_title_textSize, sp2px(context, 14));

        /**右边保存按钮相关*/
        right_button_image_id = typedArray.getResourceId(R.styleable.CustomTitlebar_right_button_image, 0);
        right_button_text = typedArray.getString(R.styleable.CustomTitlebar_right_button_text);
        right_button_textColor = typedArray.getColor(R.styleable.CustomTitlebar_right_button_textColor, Color.BLACK);
        right_button_textSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitlebar_right_button_textSize, sp2px(context, 14));

        /**分割线*/
        typedArray.getBoolean(R.styleable.CustomTitlebar_show_line, true);

        /**小红点*/
        typedArray.getBoolean(R.styleable.CustomTitlebar_show_red_dot, false);

        /**设置值*/
        setTitleBarBackground(title_background_color);
        setTilte(title_text);
        setTitleTextSize(title_textSize);
        setTitle_textColor(title_textColor);

        setLeftIcon(left_button_imageId);
        setTvLeft(left_button_text);
        setTvLeftTextSize(left_button_textSize);
        setTvLeftTextColor(left_button_textColor);

        setRightIcon(right_button_image_id);
        setTvRight(right_button_text);
        setTvRightTextColor(right_button_textColor);
        setTvRightTextSize(right_button_textSize);

        typedArray.recycle();
    }

    /**
     *
     * @param tilte s
     */
    public void setTilte(String tilte) {
        if (TextUtils.isEmpty(tilte)) {
            mTvTilte.setVisibility(GONE);
        } else {
            mTvTilte.setText(tilte);
            mTvTilte.setVisibility(VISIBLE);
        }
    }

    /**
     *
     * @param textSize s
     */
    public void setTitleTextSize(int textSize) {
        mTvTilte.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    /**
     *
     * @param textColor s
     */
    public void setTitle_textColor(int textColor) {
        mTvTilte.setTextColor(textColor);
    }

    /**
     *
     * @param text s
     */
    public void setTvLeft(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvLeft.setVisibility(GONE);
        } else {
            mTvLeft.setVisibility(VISIBLE);
            mTvLeft.setText(text);
        }
    }

    /**
     *
     * @param textSize s
     */
    public void setTvLeftTextSize(int textSize) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    /**
     *
     * @param textColor s
     */
    public void setTvLeftTextColor(int textColor) {
        mTvLeft.setTextColor(textColor);
    }

    /**
     *
     * @param text  s
     */
    public void setTvRight(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvRight.setVisibility(GONE);
        } else {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(text);
        }
    }

    /**
     * 设置右侧文字大小
     *
     * @param textSize s
     */
    public void setTvRightTextSize(int textSize) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    /**
     * 设置右侧文字颜色
     *
     * @param textColor s
     */
    public void setTvRightTextColor(int textColor) {
        mTvRight.setTextColor(textColor);
    }

    /**
     * 设置左边按钮图片资源
     *
     * @param resId s
     */
    public void setLeftIcon(int resId) {
        if (resId == 0) {
            mIvLeft.setVisibility(View.GONE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);
            mIvLeft.setImageResource(resId);
        }
    }

    /**
     * 设置小红点是否显示
     *
     * @param isVisible s
     */
    public void setLeftRedDotVisible(boolean isVisible) {
        if (isVisible) {
            mIvRedDot.setVisibility(View.VISIBLE);
        } else {
            mIvRedDot.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边按钮图片资源
     *
     * @param resId s
     */
    public void setRightIcon(int resId) {
        if (resId == 0) {
            mIvRight.setVisibility(View.GONE);
        } else {
            mIvRight.setVisibility(View.VISIBLE);
            mIvRight.setImageResource(resId);
        }
    }

    /**
     *
     * @param listener a
     */
    public void setAction(TitleBarOnClickListener listener) {
        mIvLeft.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
        mTvLeft.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
        mTitleBarOnClickListener = listener;
    }

    /**
     * 设置是否显示分割线
     *
     * @param visibility s
     */
    public void setLineIsVisible(int visibility) {
        line.setVisibility(visibility);
    }

    /**
     * 设置是否显示右边按钮
     *
     * @param show_right_button s
     */
    public void setShow_right_button(boolean show_right_button) {
        mTvRight.setVisibility(show_right_button ? VISIBLE : INVISIBLE);
        mIvRight.setVisibility(show_right_button ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置是否显示左边按钮
     *
     * @param show_left_button s
     */
    public void setShow_left_button(boolean show_left_button) {
        mIvLeft.setVisibility(show_left_button ? VISIBLE : INVISIBLE);
        mIvLeft.setVisibility(show_left_button ? VISIBLE : INVISIBLE);
    }

    /**
     * 设置标题栏背景色
     *
     * @param resId z
     */
    public void setTitleBarBackground(int resId) {
        mRelativeLayout.setBackgroundColor(resId);
    }

    /**
     *
     * @param context s
     * @param dpValue s
     * @return s
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     *
     * @param context s
     * @param spValue s
     * @return s
     */
    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    /**
     *  s
     */
    public interface TitleBarOnClickListener {
        /**
         *
         * @param view a
         */
        void performAction(View view);
    }

    @Override
    public void onClick(View v) {
        mTitleBarOnClickListener.performAction(v);
    }
}
