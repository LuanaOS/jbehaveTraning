package steps.user;

import org.jbehave.core.annotations.Then;

import java.util.List;

public class UserStep {

    @Then("an user $user with the data $emailAddress $firstName $lastName $userName $roles is created")
    public void createUser(String user, String email, String firstName, String lastName, String userName, List<String> roles){

        //Montar o request
        //chamada da API UserApi recebendo UserResponse
        //fazer os asserts se o que foi enviado é igual ao dados do UserResponse
        //adiconar no contexto caso tenha sucesso

    }

}