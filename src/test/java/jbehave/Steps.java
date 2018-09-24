package jbehave;

import org.jbehave.core.annotations.*;

import java.sql.Driver;

public class Steps {

    int x;

    @Given("a variable x with value $value")
    public void givenXValue(@Named("value") int value) {
        x = value;
    }

    @When("I multiply x by $value")
    public void whenImultiplyXBy(@Named("value") int value) {
        x = x * value;
    }

    @Then("x should equal $value")
    public void thenXshouldBe(@Named("value") int value) {
        if (value != x)
            throw new RuntimeException("x is " + x + ", but should be " + value);
    }

}
