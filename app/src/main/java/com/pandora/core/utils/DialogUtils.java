package com.pandora.core.utils;

import android.content.DialogInterface;

import com.pandora.core.utils.widget.CustomDialog;
import com.pandora.model.PandoraApplication;

/**
 * Created by Administrator on 2018/5/9.
 */

public class DialogUtils {
    /**
     * 使用 默认取消 确认按钮的 弹出框
     *
     * @param imageResource
     * @param title
     * @param content
     * @param onDialogClick
     */
    public static void showConfirmDialog(int imageResource, String title, String content, final OnDialogClick onDialogClick) {
        new CustomDialog.Builder(PandoraApplication.getInstance().getApplicationContext())
                .setImageIcon(imageResource)
                .setTitleText(title)
                .setContentText(content)
                .setCancelText("取消")
                .setConfirmText("确定")
                .setNegativeButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (onDialogClick != null) {
                            onDialogClick.cancelClick();
                        }
                    }
                })
                .setPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (onDialogClick == null) {
                            onDialogClick.confirmClick();
                        }
                    }
                })
                .build()
                .show();
    }


    /**
     * 所有文字都是指定
     *
     * @param imageResource
     * @param title
     * @param content
     * @param cancelTet
     * @param confirmText
     * @param onDialogClick
     */
    public static void showConfirmDialog(int imageResource, String title, String content,
                                         String cancelTet, String confirmText, final OnDialogClick onDialogClick) {
        new CustomDialog.Builder(PandoraApplication.getInstance().getApplicationContext())
                .setImageIcon(imageResource)
                .setTitleText(title)
                .setContentText(content)
                .setCancelText(cancelTet)
                .setConfirmText(confirmText)
                .setNegativeButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (onDialogClick != null) {
                            onDialogClick.cancelClick();
                        }
                    }
                })
                .setPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (onDialogClick == null) {
                            onDialogClick.confirmClick();
                        }
                    }
                })
                .build()
                .show();
    }


    /**
     * 只有确定按钮
     *
     * @param imageResource
     * @param title
     * @param content
     * @param confirmText
     * @param onDialogClick
     */
    public static void showConfirmDialog(int imageResource, String title, String content, String confirmText, final OnDialogClick onDialogClick) {
        new CustomDialog.Builder(PandoraApplication.getInstance().getApplicationContext())
                .setImageIcon(imageResource)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirmText)
                .setPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (onDialogClick == null) {
                            onDialogClick.confirmClick();
                        }
                    }
                })
                .build()
                .show();
    }


    public interface OnDialogClick {
        void cancelClick();

        void confirmClick();
    }
}
