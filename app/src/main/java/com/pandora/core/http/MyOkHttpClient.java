package com.pandora.core.http;

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

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/5/14.
 */

public class MyOkHttpClient {
    private static final long TIMEOUT = 60;

    private static OkHttpClient mOkHttpClient;

    private MyOkHttpClient() {
    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new TokenRequestInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                .addNetworkInterceptor(new LoggingInterceptor())
        ;

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
            if (!BuildConfig.FLAVOR.equals("product")) {
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
        mOkHttpClient = httpClientBuilder.build();

        return mOkHttpClient;
    }
}
