package com.pandora.modular.home.util;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done);

}
