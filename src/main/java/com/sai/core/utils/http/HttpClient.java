package com.sai.core.utils.http;

import okhttp3.MediaType;

public interface HttpClient {
    MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");
}
