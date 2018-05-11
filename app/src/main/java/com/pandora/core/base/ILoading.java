package com.pandora.core.base;

/**
 *
 */
public interface ILoading {

    /**
     *
     * @param tip s
     */
    void showLoading(String tip);

    /**
     *
     * @param tip s
     */
    void showLoadingWithClose(String tip);

    /**
     * s
     */
    void hideLoading();

}
