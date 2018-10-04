package HttpRequestAPI;

import apis.HttpRequestAPI;
import org.jbehave.core.annotations.*;

import com.jayway.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestSteps {
    String url = new String();
    Map<String, String> parameters = new HashMap<String, String>();
    HttpRequestAPI http = new HttpRequestAPI();

    @Given("I perform a GET request to the server APP_Id $app_id APP_Key $app_key looking up for $ingredient")
    public void getrequestToServer(String app_id, String app_key, String ingredient) {
        parameters.put("app_id", app_id);
        parameters.put("app_key", app_key);
        parameters.put("ingr", ingredient);

        url = "https://api.edamam.com/api/food-database/parser?";

        //Connect
        http.connectAPI(url, parameters);
    }

    @When("I verify the status code is $code")
    public void checkingStatusCode(int code){
        //getStatus
        int status = http.getStatus();
        Assert.assertEquals(status,code);
    }

    @Then("I check the body of the response has the value of $ingredient")
    public void checkingTheResponse(String ingredient){
        JsonPath result = http.responseConnection();

        Assert.assertEquals(result.getString("text"), ingredient);
        System.out.println("\n-->Ingredient: " + result.get("text"));
        System.out.println("-->Label: " + result.get("parsed.food.label") + "\n");
//        System.out.println(result.get("hints.food.nutrients"));
    }

    @Then("I check which error it is")
    public void checkingTheError(){
        System.out.println("\n!!! Error: " + http.getStatus() + " Message: " + http.messageError() + "\n");
    }
}
