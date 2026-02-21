package com.sai.core.utils.http;

public class HttpClient1 {

//    private static final String ENCODING_UTF8 = "UTF-8";
//    private static volatile PoolingHttpClientConnectionManager httpPools = null;
//    private static volatile CloseableHttpClient httpClient = null;
//
//    private static CloseableHttpClient getHttpClient() {
//        if (httpClient == null) {
//            Registry<ConnectionSocketFactory> socketFactoryRegisty = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https", SSLConnectionSocketFactory.getSystemSocketFactory()).build();
//
//            HttpConnectionFactory<ManagedHttpClientConnection> connFactory = null;
//
//            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
//
//            httpPools = null;
//            SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
//
//            httpPools.setDefaultSocketConfig(defaultSocketConfig);
//            httpPools.setMaxTotal(300);
//            httpPools.setDefaultMaxPerRoute(200);
////            httpPools.setValidateAfterInactivity(5000);
//
//            RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(5000)
//                    .setConnectionRequestTimeout(2000).build();
//            httpClient = HttpClients.custom().setConnectionManager(httpPools).setConnectionManagerShared(false)
//                    .evictIdleConnections(TimeValue.ofMinutes(60)).evictExpiredConnections()
//                    .setDefaultRequestConfig(defaultRequestConfig)
//                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
//                    .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
//                    .setRetryStrategy(new DefaultHttpRequestRetryStrategy(0, null)).build();
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        httpClient.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        return httpClient;
//    }
//
//    public static ResultCode<String> post(String url, Map<String, String> paramMap) {
//        HttpPost post = new HttpPost(url);
//        if (!MapUtils.isEmpty(paramMap)) {
//            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//            Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
//            while (it.hasNext()) {
//                Entry<String, String> entry = it.next();
//                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//            UrlEncodedFormEntity uefEntity = null;
//            try {
//                uefEntity = new UrlEncodedFormEntity(formParams, Charset.forName("utf-8"));
//                post.setEntity(uefEntity);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        CloseableHttpClient httpClient = getHttpClient();
//        CloseableHttpResponse response;
//        String resultStr = null;
//        String resultCode = null;
//        try {
//            response = httpClient.execute(post);
//            Integer statusline = response.getCode();
//            if (statusline == 200) {
//                resultCode = StatusConstant.RESULT_SUCCESS_CODE;
//            } else {
//                resultCode = statusline + "";
//            }
//
//            resultStr = EntityUtils.toString(response.getEntity());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new ResultCode<String>(resultCode + "", null, resultStr);
//    }
//
//    public static ResultCode<String> get(String url, Map<String, String> paramMap) {
//        HttpGet get = new HttpGet(url);
//
//        String resultStr = null;
//        String resultCode = null;
//        try {
//            if (!MapUtils.isEmpty(paramMap)) {
//                url = url + "?" + EntityUtils.toString(getUrlEncodedFormEntity(paramMap, ENCODING_UTF8));
//            }
//            CloseableHttpClient httpClient = getHttpClient();
//            CloseableHttpResponse response;
//            response = httpClient.execute(get);
//            int statusline = response.getCode();
//            if (statusline == 200) {
//                resultCode = StatusConstant.RESULT_SUCCESS_CODE;
//            } else {
//                resultCode = statusline + "";
//            }
//
//            resultStr = EntityUtils.toString(response.getEntity());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new ResultCode<String>(resultCode + "", null, resultStr);
//    }
//
//    public static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap, String encode) {
//        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//        Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Entry<String, String> entry = it.next();
//            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        UrlEncodedFormEntity uefEntity = null;
//        try {***********
//            uefEntity = new UrlEncodedFormEntity(formParams, Charset.forName("UTF-8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return uefEntity;
//    }
}
