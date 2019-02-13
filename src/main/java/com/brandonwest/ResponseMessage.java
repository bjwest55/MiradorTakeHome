package com.brandonwest;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

public class ResponseMessage {
    public String responseBody;
    public Header[] headers;
    public int statusCode;

    //These are for stats. They could easily be more granular like a log of hits
    public Date initialCacheDate;
    public int hitCount;

    public ResponseMessage(HttpResponse response) throws IOException {
        headers = response.getAllHeaders();
        try {
            responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        statusCode = response.getStatusLine().getStatusCode();
        initialCacheDate = new Date();
        hitCount = 0;
    }
}
