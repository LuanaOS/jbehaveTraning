package steps.user;

import apis.ApiException;
import apis.user.UserApi;
import apis.user.model.UserAttributes;
import apis.user.model.UserRequest;
import apis.user.model.UserResponse;
import client.model.Envelope;

import org.apache.http.*;

import org.jbehave.core.annotations.*;
import org.junit.Assert;
import utils.ApplicationContext;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.fail;

public class UserStep {
    //
    public static ApplicationContext appContxt = new ApplicationContext();

    @Then("an user with the data $emailAddress $firstName $lastName $userName $roles is created")
    public void createUser(String email, String firstName, String lastName, String userName, List<String> roles) throws ApiException {

    //1) Create the UserRequest with the user data
        //code

    //2) call the UserApi receiving UserResponse
        //code

    //3) do the asserts between the data of UserRequest with UserResponse
        //code


    //4) if the asserts is successful add user in the ApplicationContext
        //code

    }

    @Then("an user cannot be created without field $field")
    public void createUserWithoutFirstName(String field) throws ApiException {

    //1) Create the UserRequest with the user data
        UserRequest userRequest = buildUserAttributes();
        String sessionToken = new String();
        String path = new String();

    //1.1) Endpoint firstName is required (error 400)
        if (field.equals("firstName")) {
            userRequest.getUserAttributes().setFirstName(null);
            sessionToken = "testSessionToken";
            path = "/v2/5bab8fe53100000f006543eb";
        }
    //1.2) Endpoint userName is required (error 400)
        //code

    //1.3) Endpoint invalid roles (error 400)
        //code

    //1.4) Endpoint invalid session token (error 401)
        //code

        //2) call the UserApi receiving UserResponse
        UserApi userApi = new UserApi();

        try {
            UserResponse userResponse = userApi.createUser(sessionToken, new Envelope<>(userRequest), path);
            fail("Expect code error: " + Response.Status.BAD_REQUEST);
        } catch (ApiException e){
            Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, e.getErrorCode());
        }

    }

    private void assertUser(UserRequest userRequest, UserResponse userResponse) {
        Assert.assertEquals("Email should be valid", userResponse.getUserAttributes().getEmailAddress(), userRequest.getUserAttributes().getEmailAddress());
        Assert.assertEquals("First name should be valid", userResponse.getUserAttributes().getFirstName(), userRequest.getUserAttributes().getFirstName());
        Assert.assertEquals("Last name should be valid", userResponse.getUserAttributes().getLastName(), userRequest.getUserAttributes().getLastName());
        Assert.assertEquals("Roles should be valid", userResponse.getRoles(), userRequest.getRoles());
    }

    private UserRequest buildUserAttributes(String email, String firstName, String lastName, String userName, List<String> roles) {
        UserRequest userRequest = new UserRequest();
        UserAttributes userAttributes = new UserAttributes(email, firstName, lastName, userName);
        userRequest.setUserAttributes(userAttributes);
        userRequest.setRoles(roles);
        return userRequest;
    }

    private UserRequest buildUserAttributes() {
        UserRequest userRequest = new UserRequest();
        UserAttributes userAttributes = new UserAttributes("janedoe@symphony.com", "Jane", "Doe", "janedoe");
        userRequest.setUserAttributes(userAttributes);

        List<String> roles = new ArrayList<>();
        roles.add("INDIVIDUAL");
        userRequest.setRoles(roles);
        return userRequest;
    }


}