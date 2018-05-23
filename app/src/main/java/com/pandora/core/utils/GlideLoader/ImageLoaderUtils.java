package com.pandora.core.utils.GlideLoader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pandora.R;
import com.pandora.modular.PandoraApplication;


/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {

    static RequestOptions requestOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.bingbing)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.bingbing);

    /**
     * @param context   context
     * @param imageView imageView
     * @param url       url
     */
    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.bingbing)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.bingbing);
        if (url != null) {
            Glide.with(PandoraApplication.getInstance().getApplicationContext())
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView);
        }

    }


    /**
     * @param context   context
     * @param imageView imageView
     * @param resId     resID
     */
    public static void displayRound(Context context, ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        requestOptions.transform(new GlideRoundTransformUtil());
        Glide.with(context).load(resId)
                .apply(requestOptions)
                .into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        requestOptions.transform(new GlideRoundTransformUtil());
        Glide.with(context).load(url)
                .apply(requestOptions)
                .into(imageView);
    }

}
