package com.chegg.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class BlogService {
    private final HttpClient client = HttpClientBuilder.create().build();
    private static final String URL = "https://jsonblob.com/api/jsonBlob";

    public HttpResponse post(String json) throws IOException {
        HttpPost request = new HttpPost(URL);
        try {
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            request.setHeader("contentType", "application/json");
            request.setEntity(requestEntity);
            return client.execute(request);
        } finally {
            request.releaseConnection();
        }
    }

    public HttpResponse get(String blogId) throws IOException {
        HttpGet httpGet = new HttpGet(URL + "/" + blogId);
        try {
            httpGet.setHeader("contentType", "application/json");
            return client.execute(httpGet);
        } finally {
            httpGet.releaseConnection();
        }
    }

    public HttpResponse put(String blogId, String json) throws IOException {
        HttpPut httpPut = new HttpPut(URL + "/" + blogId);
        try {
            StringEntity putEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPut.setHeader("contentType", "application/json");
            httpPut.setEntity(putEntity);
            return client.execute(httpPut);
        } finally {
            httpPut.releaseConnection();
        }
    }

    public HttpResponse delete(String blogId) throws IOException {
        HttpDelete httpDelete = new HttpDelete(URL + "/" + blogId);
        try {
            httpDelete.setHeader("contentType", "application/json");
            return client.execute(httpDelete);
        } finally {
            httpDelete.releaseConnection();
        }
    }
}
