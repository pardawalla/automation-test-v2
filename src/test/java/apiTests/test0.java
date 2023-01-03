package apiTests;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.testng.Assert;
import org.testng.annotations.Test;

// Required for HTTP requests
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.StatusLine;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.List;
import java.util.Map;

public class test0 {

  

    // Get request
    // Reference:
    // https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0
    public static String get(String url) {
        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // POST request
    // Reference(s):
    // https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0
    // https://jsontostring.com/
    public static Response post(String url) throws IOException {
        // String result = null;
        Response resp = null;
        Request request = Request.post(url).setHeader("Content-type", "application/json");
        // JSON String
        String jsonRequest = "{\"ExistingListingId\":123,\"Category\":\"ABC\",\"Title\":\"ABC\",\"Subtitle\":\"ABC\",\"Description\":[\"ABC\",\"ABC\"],\"StartPrice\":123.0,\"ReservePrice\":123.0,\"BuyNowPrice\":123.0,\"Duration\":0,\"EndDateTime\":\"\\/Date(1514764800)\\/\",\"Pickup\":0,\"PickupSuburbId\":123,\"IsBrandNew\":false,\"AuthenticatedMembersOnly\":false,\"IsClassified\":false,\"OpenHomes\":[{\"Start\":\"\\/Date(1514764800)\\/\",\"End\":\"\\/Date(1514764800)\\/\"},{\"Start\":\"\\/Date(1514764800)\\/\",\"End\":\"\\/Date(1514764800)\\/\"}]}";
        StringEntity jsonRequestEntity = new StringEntity((jsonRequest));
        request = request.body(jsonRequestEntity);
        try {
            // result = request.execute().returnContent().asString();
            resp = request.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return result;
        return resp;
    }

    String testUrl = "https://api.tmsandbox.co.nz/v1/Charities.json";
    // At the moment no JSON parser. If the charity is there, it'll have to be
    // in the format `,"Description":"St John",`. This way we can make sure
    // the value is from the `Description` key/value pair, and not from the
    // Tagline, ImageSource etc, key/value pair.
    String expectedCharityDescription = ",\"Description\":\"St John\",";

    @Test()
    public void CharityContainsStJohn() throws JsonMappingException, JsonProcessingException {

        String respString = get(testUrl);
        System.out.println(respString);
        Assert.assertTrue(respString.contains(expectedCharityDescription));

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Reference:
        // https://stackoverflow.com/questions/36519974/can-not-deserialize-instance-of-java-util-hashmap-out-of-start-array-token
        List<Map<String, Object>> charities = mapper.readValue(respString,
                new TypeReference<List<Map<String, Object>>>() {
                });
        System.out.println("charities is: " + charities);

        // for (String Description: charities.keySet()) {
        // String key = Description.toString();
        // String value = charities.get(Description).toString();
        // System.out.println(key + "" + value);
        // }
    }

    // References:
    // https://stackoverflow.com/questions/14024625/how-to-get-httpclient-returning-status-code-and-response-body
    // https://stackoverflow.com/questions/64819417/java-cannot-resolve-method-getentity-in-httpresponse
    @Test()
    public void TestPOST() throws IOException, ParseException {
        Response resp = post("https://httpbin.org/anything");
        ClassicHttpResponse httpResp = (ClassicHttpResponse) resp.returnResponse();
        int statusCode = httpResp.getCode();
        String statusReasonPhrase = httpResp.getReasonPhrase();
        String responseBody = EntityUtils.toString(httpResp.getEntity(), StandardCharsets.UTF_8);


        System.out.println("\nStatu Code:" + statusCode + "\nreason phrase:" + statusReasonPhrase + "\n\n body:" + responseBody);
        //System.out.println(resp.returnResponse().getCode());

    }

}