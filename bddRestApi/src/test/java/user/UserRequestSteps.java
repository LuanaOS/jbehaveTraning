package user;

import apis.HttpRequestAPI;
import apis.User;
import apis.UserAttributes;
import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.jbehave.core.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UserRequestSteps {
    @Given("a request for an user to be created")
    public void requestCreatUser() {

    }

    @When("create an user")
    public void createUser(UserAttributes userattributes, StringArray roles) {
        User user = new User(userattributes, roles);
    }

    @Then("verify if the data of the user is right")
    public boolean verifyUserData() {
        URL u = null;
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("emailAddress", "janedoe@symphony.com");
        try{
            u = new URL("http://www.mocky.io/v2/5baba27f3100005500654488");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        HttpRequestAPI test = new HttpRequestAPI(u, parameters);
        System.out.println(test.responseConnection());
    }
}