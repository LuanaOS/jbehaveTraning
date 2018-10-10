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
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.fail;

public class UserStep {

    @Then("an user with the data $emailAddress $firstName $lastName $userName $roles is created")
    public void createUser(String email, String firstName, String lastName, String userName, List<String> roles) throws ApiException {

    //1) Montar o UserRequest com os dados do user
        //code

    //2) chamada da API UserApi recebendo UserResponse
        //code

    //3) fazer os asserts se os dados do UserRequest é igual ao dados do UserResponse
        //code


    //4) adiconar no contexto caso tenha sucesso
        //code

    }

    @Then("an user cannot be created without field $field")
    public void createUserWithoutFirstName(String field) throws ApiException {

    //1) Montar o UserRequest com os dados do user
        UserRequest userRequest = buildUserAttributes();
        String sessionToken = "testSessionToken";

        String path = null;
    //1.1) Endpoint firstName is required (error 400)
        if (field.equals("firstName")) {
            userRequest.getUserAttributes().setFirstName(null);
            path = "/v2/5bab8fe53100000f006543eb";
        }
    //1.2) Endpoint userName is required (error 400)
        //code

    //1.3) Endpoint invalid roles (error 400)
        //code

    //1.4) Endpoint invalid session token (error 401)
        //code

        //2) chamada da UserApi criando um user recebendo UserResponse, se falhar retorna erro
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