package com.pandora.core.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pandora.R;

/**
 * Created by Administrator on 2018/5/9.
 */

public class CustomLoadingUtil {

    private static ImageView imClose;
    /**
     *
     */
    public static Dialog loadingDialog;

    private static Dialog createLoading(Context context, String msg, boolean isCancelable, final ILoadingClose iLoadingClose) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.custom_loading, null);             // 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout_dialog_view);// 加载布局

        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.iv_image_loading);
        TextView tipTextView = (TextView) v.findViewById(R.id.tv_text_loading);   // 提示文字
        imClose = (ImageView) v.findViewById(R.id.iv_close_loading);


        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        if (!TextUtils.isEmpty(msg)) {
            tipTextView.setText(msg);// 设置加载信息
        }

        if (loadingDialog == null) {
            loadingDialog = new Dialog(context, R.style.MyLoadingStyle);
        }

        // 创建自定义样式dialog
        loadingDialog.setContentView(layout);
        loadingDialog.setCancelable(isCancelable);
        loadingDialog.setCanceledOnTouchOutside(false);

        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        //window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        imClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideDialog();
                iLoadingClose.loadingclose();
            }
        });
        return loadingDialog;
    }


    public static Dialog showWaitDialog(Context context, boolean isCancelable, ILoadingClose iLoadingClose) {
        return createLoading(context, null, isCancelable, iLoadingClose);
    }

    public static void showLoading() {
        if (loadingDialog != null) {
            loadingDialog.isShowing();
        }
    }

    public static boolean isShowLoad() {
        if (loadingDialog != null) {
            return loadingDialog.isShowing();
        }
        return false;
    }

    public static void closeDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    public static void hideDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.hide();
        }
    }

    public static void setImCloseVisible() {
        imClose.setVisibility(View.VISIBLE);
    }


    public interface ILoadingClose {
        /**
         *
         */
        void loadingclose();
    }

}
