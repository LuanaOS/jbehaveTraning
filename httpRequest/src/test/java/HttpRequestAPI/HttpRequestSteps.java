package HttpRequestAPI;

import org.jbehave.core.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestSteps {
    @Given("a system state")
    public void givenASystemState() {
        URL u = null;
        Map<String, String> parameters = new HashMap<String, String>();

        //parameters.put("emailAddress", "janedoe@symphony.com");
        try{
            u = new URL("http://www.mocky.io/v2/5baba27f3100005500654488");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        HttpRequestAPI test = new HttpRequestAPI(u, parameters);
        System.out.println(test.responseConnection());

    }
}
