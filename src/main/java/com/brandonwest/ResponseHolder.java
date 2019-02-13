package com.brandonwest;

import java.util.HashMap;
import java.util.Map;

//Local holding of cached responses.
//TODO expand this to use a database of some sort instead.
public enum ResponseHolder {
    instance;
    private Map<String, ResponseMessage> responses = new HashMap<>();

    public Map<String, ResponseMessage> getResponses(){
        return responses;
    }
}
