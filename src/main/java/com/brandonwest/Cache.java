package com.brandonwest;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;


@Path("Cache")
public class Cache {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String cacheUrl(String url){

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            String uniqueID = UUID.randomUUID().toString();

            ResponseMessage message = new ResponseMessage(response);
            ResponseHolder.instance.getResponses().put(uniqueID,message);
            return uniqueID;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "An exception was thrown";
    }

    @GET
    @Path("{Id}")
    @Produces(MediaType.WILDCARD)
    public Response getCachedUrl(@PathParam("Id") String id) {
        //Get the item referenced by the Id
        ResponseMessage response = ResponseHolder.instance.getResponses().get(id);

        //If its not in the map it should return null
        if(response == null){
            return Response.noContent().build();
        }

        response.hitCount++;
        //Wasn't sure how to get a response builder back other than these methods,
        // but the ok status is overwritten by whatever it was before
        Response.ResponseBuilder cachedResponse = Response.ok(response.responseBody);

        //Add in all the headers
        for(Header header : response.headers){
            cachedResponse.header(header.getName(),header.getValue());
        }

        //Set the appropriate status
        cachedResponse.status(response.statusCode);

        return cachedResponse.build();
    }

    //This was just for random testing, having a easy to remember GET that isn't a webpage
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String randomGet() {
        return UUID.randomUUID().toString();
    }
}
