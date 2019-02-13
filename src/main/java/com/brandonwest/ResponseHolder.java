package com.brandonwest;

import java.util.HashMap;
import java.util.Map;

//Local holding of cached responses.
//In order for non memory based storage You'd need some sort of database connection
//So get Responses would instead be an interface to pull from the database and
//you'd need a new method putResponses to place things into the database.

//You could also cache them here and periodically on a timer update the database.
//which would cut down on the database calls but would leave you able to lose some data
public enum ResponseHolder {
    instance;
    private Map<String, ResponseMessage> responses = new HashMap<>();

    public Map<String, ResponseMessage> getResponses(){
        return responses;
    }
}
