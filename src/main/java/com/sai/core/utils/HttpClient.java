package com.sai.core.utils;

import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultCode;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class HttpClient {

	private static final String ENCODING_UTF8 = "UTF-8";
	private static volatile PoolingHttpClientConnectionManager httpPools = null;
	private static volatile CloseableHttpClient httpClient = null;

	private static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			Registry<ConnectionSocketFactory> socketFactoryRegisty = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", SSLConnectionSocketFactory.getSystemSocketFactory()).build();

			HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
					DefaultHttpRequestWriterFactory.INSTANCE, DefaultHttpResponseParserFactory.INSTANCE);

			DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;

			httpPools = new PoolingHttpClientConnectionManager(socketFactoryRegisty, connFactory, dnsResolver);
			SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();

			httpPools.setDefaultSocketConfig(defaultSocketConfig);
			httpPools.setMaxTotal(300);
			httpPools.setDefaultMaxPerRoute(200);
			httpPools.setValidateAfterInactivity(5000);

			RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(5000)
					.setConnectionRequestTimeout(2000).build();
			httpClient = HttpClients.custom().setConnectionManager(httpPools).setConnectionManagerShared(false)
					.evictIdleConnections(60, TimeUnit.SECONDS).evictExpiredConnections()
					.setConnectionTimeToLive(60, TimeUnit.SECONDS).setDefaultRequestConfig(defaultRequestConfig)
					.setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
					.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
					.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						httpClient.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		return httpClient;
	}

	public static ResultCode<String> post(String url, Map<String, String> paramMap) {
		HttpPost post = new HttpPost(url);
		if (!MapUtils.isEmpty(paramMap)) {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity uefEntity = null;
			try {
				uefEntity = new UrlEncodedFormEntity(formParams, ENCODING_UTF8);
				post.setEntity(uefEntity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response;
		String resultStr = null;
		String resultCode = null;
		try {
			response = httpClient.execute(post);
			StatusLine statusline = response.getStatusLine();
			if (statusline.getStatusCode() == 200) {
				resultCode = StatusConstant.RESULT_SUCCESS_CODE;
			} else {
				resultCode = statusline.getStatusCode() + "";
			}

			resultStr = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResultCode<String>(resultCode + "", null, resultStr);
	}

	public static ResultCode<String> get(String url, Map<String, String> paramMap) {
		HttpGet get = new HttpGet();

		String resultStr = null;
		String resultCode = null;
		try {
			if (!MapUtils.isEmpty(paramMap)) {
				url = url + "?" + EntityUtils.toString(getUrlEncodedFormEntity(paramMap, ENCODING_UTF8));
			}
			get.setURI(new URI(url));
			CloseableHttpClient httpClient = getHttpClient();
			CloseableHttpResponse response;
			response = httpClient.execute(get);
			StatusLine statusline = response.getStatusLine();
			if (statusline.getStatusCode() == 200) {
				resultCode = StatusConstant.RESULT_SUCCESS_CODE;
			} else {
				resultCode = statusline.getStatusCode() + "";
			}

			resultStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResultCode<String>(resultCode + "", null, resultStr);
	}

	public static UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap, String encode) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity = null;
		try {
			uefEntity = new UrlEncodedFormEntity(formParams, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return uefEntity;
	}
}
