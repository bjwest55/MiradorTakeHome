package com.brandonwest;

import org.junit.Test;

import static io.restassured.RestAssured.given;

//Just some really really simple acceptance.
//Would probably want some more detailed tests.
//One that actually goes through the whole use case.
public class CacheTests {

    @Test
    public void testGetCachedUrl(){
        given().when().get("http://localhost:8080/MiradorTakeHome_war_exploded/rest/Cache/fakeId").then().statusCode(204);
    }

    @Test
    public void testCacheUrl(){
        given().body("https://google.com").when().post("http://localhost:8080/MiradorTakeHome_war_exploded/rest/Cache").then().statusCode(200);
    }

}
