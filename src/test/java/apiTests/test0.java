package apiTests;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author https://www.wdbyte.com
 *         Reference:
 *         https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0
 */
public class test0 {

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

    String testUrl = "https://api.tmsandbox.co.nz/v1/Charities.json";
    String expectedCharityDescription = ",\"Description\":\"St John\",";
    @Test()
    public void CharityContainsStJohn() {
        
        String respString = get(testUrl);
        System.out.println(respString);
        Assert.assertTrue(respString.contains(expectedCharityDescription));
    }

}