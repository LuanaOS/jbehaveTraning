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

        parameters.put("name", "Felipe");
        try{
            u = new URL("http://www.mocky.io/v2/5bab95f83100004c00654436");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        HttpRequestAPI test = new HttpRequestAPI(u, parameters);
        System.out.println(test.responseConnection());

    }
}
