package com.sai.core.utils.http;

import com.sai.core.constants.enums.HttpMethod;
import com.sai.core.dto.HttpResultData;
import com.sai.core.utils.SaiStringUtils;
import okhttp3.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OkHttp3Client implements HttpClient {

    private static ConnectionPool defaultConnectionPool;
    public static OkHttpClient defaultClient;

    public OkHttp3Client() {
        defaultConnectionPool = createConnectionPool(20, 30L, TimeUnit.SECONDS);
        defaultClient = createOkHttpClient(defaultConnectionPool);
    }

    public OkHttp3Client(Integer maxIdleConnections,
                         Long keepAliveDuration,
                         TimeUnit timeUnit) {
        defaultConnectionPool = createConnectionPool(maxIdleConnections,
                keepAliveDuration,
                timeUnit);
        defaultClient = createOkHttpClient(defaultConnectionPool);
    }

    public ConnectionPool createConnectionPool(Integer maxIdleConnections,
                                               Long keepAliveDuration,
                                               TimeUnit timeUnit
    ) {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, timeUnit);
    }

    public OkHttpClient createOkHttpClient(ConnectionPool connectionPool) {
        return new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .build();
    }

    public static Request.Builder createRequest(OkHttpClient client,
                                                String url,
                                                Map<String, String> headers,
                                                Map<String, String> params) {
        if (SaiStringUtils.isEmpty(url)) {
            throw new NullPointerException("url is null");
        }
        HttpUrl.Builder httpUrlBuilder = Objects
                .requireNonNull(HttpUrl.parse(url), "HttpUrl pares error")
                .newBuilder();
        if (params != null) {
            params.forEach(httpUrlBuilder::addQueryParameter);
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers != null) {
            headers.forEach(headerBuilder::add);
        }


        Request.Builder builder = new Request.Builder();
        builder.url(httpUrlBuilder.build())
                .headers(headerBuilder.build());

        return builder;
    }

    public static HttpResultData post(OkHttpClient client,
                                      String url,
                                      Map<String, String> headers,
                                      String bodyContent,
                                      Map<String, String> params)
            throws Exception {


        Request.Builder builder = createRequest(client, url, headers, params);
        RequestBody body = RequestBody.create(bodyContent, MEDIA_TYPE_JSON);
        builder.post(body);
        Request request = builder.build();

        return defaultResponse(client, request);
    }

    public static HttpResultData get(OkHttpClient client,
                                     String url,
                                     Map<String, String> headers,
                                     Map<String, String> params)
            throws Exception {
        Request.Builder builder = createRequest(client, url, headers, params);
        builder.get();
        Request request = builder.build();
        return defaultResponse(client, request);
    }

    public static HttpResultData defaultResponse(OkHttpClient client,
                                                 Request request) throws Exception {
        try (Response response = client.newCall(request).execute()) {
            Map<String, String> respHeadersResult = new HashMap<>();
            Headers respHeaders = response.headers();
            for (String name : respHeaders.names()) {
                respHeadersResult.put(name, respHeaders.get(name));
            }

            Object respBody = response.body();

            return HttpResultData.successWithHeaders(respBody, response.code(), respHeadersResult);
        }
    }

}
