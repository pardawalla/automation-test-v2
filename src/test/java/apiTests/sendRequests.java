package apiTests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

// Required for HTTP requests
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;

// import java.util.List;
// import java.util.Map;

public class sendRequests {

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
    // https://www.tabnine.com/code/java/methods/org.apache.http.client.fluent.Request/setHeader
    public static Response post(String url) throws IOException {
        // String result = null;
        Response resp = null;
        Request request = Request.post(url).setHeader("Content-type", "application/json");

        // Crrently using a fake token as proof of concenpt to show how the 
        // The real bearer tokens can be securely stored and retrieved from either a file or a system env variables.
        // See the commented out tests `ReadBearerTokenFromFile()` to see how they can be retrieved from a file
        // and `ReadBearerTokenFromEnv()` below to see how they can be retrieved from a system environment variable. 
        // Which method is used depends on risk the user is willing to take and/or convenience the user is willing to give up.   
        String fakeBearerToken = "myRandomBearerTokeneyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        request.setHeader("Authorization", "Bearer " + fakeBearerToken);

        // JSON parameter string
        // The https://jsontostring.com/ site can be easily used to covert a JSON object to a JSON String. 
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

        // // Not required. Code to test JSON deserialisation/
        // ObjectMapper mapper = new ObjectMapper();
        // mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // // Reference:
        // //
        // https://stackoverflow.com/questions/36519974/can-not-deserialize-instance-of-java-util-hashmap-out-of-start-array-token
        // List<Map<String, Object>> charities = mapper.readValue(respString,
        // new TypeReference<List<Map<String, Object>>>() {
        // });
        // System.out.println("charities is: " + charities);

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

        System.out.println(
                "\nStatus Code:" + statusCode + "\nreason phrase:" + statusReasonPhrase + "\n\n body:" + responseBody);
        Assert.assertEquals(statusCode, 200);

    }

    // How Bearer Tokens can be securly stored and retrived.

    // Method One: from a file: The idea is to have a data.txt_template file in the
    // repo.
    // On cloning the repo the user will rename the file to data.txt and add the
    // bearer token in there.
    // Since data.txt is in the .gitignore folder the risk of the bearer token being
    // added to the repo and
    // being uploaded will be very low.
    // Assumption: The file data.txt_template has been changed to data.txt and has
    // the bearer token in it.
    // References:
    // https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
    // https://reactgo.com/java-convert-string-to-path/
    /*
     * @Test()
     * public void ReadBearerTokenFromFile() throws IOException {
     * String bearerToken = Files.readString(Paths.get(
     * "/Users/hussain/repos/automation-test-v2/automation-test-v2/src/test/resources/data.txt"
     * ), StandardCharsets.UTF_8);
     * System.out.println(bearerToken);
     * }
     */

    // Method two: Using env variables.
    // Assumption: An environment variable called MY_BEARER_TOKEN has been created.
    // You might need to reload the editor/project/terminal after setting the token.
    // Reference: https://www.tutorialspoint.com/java/lang/system_getenv_string.htm
    /*
     * @Test()
     * public void ReadBearerTokenFromEnv() {
     * String bearerToken = System.getenv("MY_BEARER_TOKEN");
     * System.out.println(bearerToken);
     * }
     */
}