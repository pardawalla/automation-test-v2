package automation.test;
import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
// import io.restassured.RestAssured.*;
// import io.restassured.matcher.RestAssuredMatchers.*;
// import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.netty.handler.codec.http.HttpResponse;

public class APItm {

    // @Test public void verifycharity(){
    //     get("https://api.tmsandbox.co.nz/v1/Charities.json").then().body("Description", equalto("St John"));
    // }

    @Test
    public void myTest () throws ClientProtocolException, IOException{

        // //Given
        // HttpUriRequest request = new HttpGet(("https://api.tmsandbox.co.nz/v1/Charities.json"));

        // HttpResponse response = HttpClientResponseHandler.c

        
    }
}
