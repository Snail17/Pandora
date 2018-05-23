package com.pandora.core.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pandora.R;

/**
 * Created by Administrator on 2018/5/9.
 */

public class CustomDialog extends Dialog {


    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    public static class Builder {
        private Context context;
        private int imageIcon;
        private String titleText;
        private String contentText;
        private String cancelText;
        private String confirmText;
        private String isShowCancel;
        private OnClickListener negativeButtonClickListener;
        private OnClickListener positiveButtonClickListener;

        private CustomDialog mCustomDialog;

        public Builder(Context context) {
            this.context = context;
            mCustomDialog = new CustomDialog(context);
        }

        public Builder setImageIcon(int imageIcon) {
            this.imageIcon = imageIcon;
            return this;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public Builder setCancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder setConfirmText(String confirmText) {
            this.confirmText = confirmText;
            return this;
        }

        public Builder setIsShowCancel(String isShowCancel) {
            this.isShowCancel = isShowCancel;
            return this;
        }

        public Builder setNegativeButtonClickListener(OnClickListener negativeButtonClickListener) {
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        public Builder setPositiveButtonClickListener(OnClickListener positiveButtonClickListener) {
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        public CustomDialog build() {
            View layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.custom_dialog, null, false);
            ImageView imageIV = (ImageView) layout.findViewById(R.id.iv_image_dialog);
            TextView titleTV = (TextView) layout.findViewById(R.id.tv_title_dialog);
            TextView contentTV = (TextView) layout.findViewById(R.id.tv_content_dialog);
            TextView cancelBtn = (TextView) layout.findViewById(R.id.btn_cancel_dialog);
            TextView confirmBtn = (TextView) layout.findViewById(R.id.btn_confirm_dialog);

            if (imageIcon != 0) {
                imageIV.setImageResource(imageIcon);
                imageIV.setVisibility(View.VISIBLE);
            } else {
                imageIV.setVisibility(View.GONE);
            }


            if (titleText != null) {
                titleTV.setText(titleText);
                titleTV.setVisibility(View.VISIBLE);
            }

            if (contentText != null) {
                contentTV.setText(contentText);
                contentTV.setVisibility(View.VISIBLE);
            }
            if (cancelText != null) {
                cancelBtn.setText(cancelText);
                cancelBtn.setVisibility(View.VISIBLE);
                if (negativeButtonClickListener != null) {
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(mCustomDialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                cancelBtn.setVisibility(View.GONE);
            }
            if (confirmText != null) {
                confirmBtn.setText(confirmText);
                confirmBtn.setVisibility(View.VISIBLE);
                if (positiveButtonClickListener != null) {
                    confirmBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(mCustomDialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                confirmBtn.setVisibility(View.GONE);
            }
            mCustomDialog.setContentView(layout);
            mCustomDialog.setCanceledOnTouchOutside(false);
            return mCustomDialog;
        }
    }


}
