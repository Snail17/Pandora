package com.pandora.core.glide;

import android.util.Log;


import com.pandora.BuildConfig;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by cain on 2017/9/8.
 */

public class UnsafeOkHttpClient {

    private static final long TIMEOUT = 30;
    /**
     *
     * @return s
     */
    public static OkHttpClient getUnsafeOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        //https
        try {

            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

            };

            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            if (!BuildConfig.FLAVOR.equals("product")){
                httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory());
            }

        } catch (NoSuchAlgorithmException e) {
            Log.i(TAG, e.getMessage());
        } catch (KeyManagementException e) {
            Log.i(TAG, e.getMessage());
        }


        httpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        OkHttpClient client = httpClientBuilder.build();

        return client;
    }
}
