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

    @Then("an user with the data $emailAddress $firstName $lastName $userName $roles is created")
    public void createUser(String email, String firstName, String lastName, String userName, List<String> roles) throws ApiException {
        //1) Montar o UserRequest com os dados do user
        UserRequest userRequest = buildUserAttributes(email, firstName, lastName, userName, roles);
        String sessionToken = "testSessionToken";


        //2) chamada da API UserApi recebendo UserResponse
        UserApi userApi = new UserApi();
        UserResponse userResponse = userApi.createUser(sessionToken, new Envelope<>(userRequest), "/v2/5baba27f3100005500654488");

        //3) fazer os asserts se os dados do UserRequest é igual ao dados do UserResponse
        assertUser(userRequest, userResponse);


        //4) adiconar no contexto caso tenha sucesso
        ApplicationContext appContxt = new ApplicationContext();
        appContxt.addAdminApiUserReference(userResponse.getUserSystemInfo().getId().toString(), userResponse);

    }

    @Then("an user cannot be created without field $field")
    public void createUserWithoutFirstName(String field) throws ApiException {

        //1) Montar o UserRequest com os dados do user
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
        if (field.equals("userName")){
            userRequest.getUserAttributes().setUserName(null);
            sessionToken = "testSessionToken";
            path = "/v2/5bab90183100004c006543ef";
        }
        //1.3) Endpoint invalid roles (error 400)
        if (field.equals("roles")){
            userRequest.setRoles(null);
            sessionToken = "testSessionToken";
            path = "/v2/5bab92893100004e00654418";
        }

        //1.4) Endpoint invalid session token (error 401)
        if (field.equals("sessionToken")){
            path = "/v2/5bab907131000034006543f3";
        }

        //2) chamada da API UserApi recebendo UserResponse
        UserApi userApi = new UserApi();

        try {
            UserResponse userResponse = userApi.createUser(sessionToken, new Envelope<>(userRequest), path);
            fail("Expect code error: " + Response.Status.BAD_REQUEST);
        } catch (ApiException e){
            if (field.equals("sessionToken"))
                Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getErrorCode());
            else
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