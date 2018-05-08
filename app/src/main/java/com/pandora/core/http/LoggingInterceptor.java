package com.pandora.core.http;


import com.boc.gmsa.core.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 *
 */
public class LoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     *
     * @param chain s
     * @return s
     * @throws IOException s
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        logRequest(request);
        Response response = chain.proceed(request);
        logResponse(response);
        return response;
    }

    /**
     *
     * @param request s
     * @throws IOException s
     */
    private void logRequest(Request request) throws IOException {

        LogUtils.e(request.method() + "   " + request.url());
        Headers headers = request.headers();
        for (int i = 0, count = headers.size(); i < count; i++) {
            String name = headers.name(i);
            // Skip headers from the request body as they are explicitly logged above.
            if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                LogUtils.e("request->"+name + ": " + headers.value(i));
            }
        }


        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = request.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            LogUtils.e(buffer.readString(charset));

            LogUtils.e("--> END " + request.method()
                    + " (" + request.body().contentLength() + "-byte body)");
        }

    }

    /**
     *
     * @param response s
     * @throws IOException s
     */
    private void logResponse(Response response) throws IOException {
        if (response != null) {

            LogUtils.e(response.code() + "  " + response.message());

            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                LogUtils.e(headers.name(i) + ": " + headers.value(i));
            }
            long contentLength = response.body().contentLength();

            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = response.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            if (contentLength != 0) {
                LogUtils.e("");
                LogUtils.e("Response->json:"+buffer.clone().readString(charset));
            }

        } else {
            LogUtils.e("Response is null");
        }
    }

}