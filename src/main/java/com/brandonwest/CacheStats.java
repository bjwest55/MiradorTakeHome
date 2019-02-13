package com.brandonwest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Path("CacheStats")
public class CacheStats {
    @GET
    @Path("{Id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCachedUrl(@PathParam("Id") String id) {
        ResponseMessage response = ResponseHolder.instance.getResponses().get(id);

        Date today = new Date();
        long diff = today.getTime() - response.initialCacheDate.getTime();
        long days = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS) + 1;
        long hitRate = response.hitCount / days;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(response.initialCacheDate);
        return  "HitCount: " + response.hitCount + ", HitRate: " + hitRate + " Hits a day, initial cache date: " + strDate ;
    }
}
