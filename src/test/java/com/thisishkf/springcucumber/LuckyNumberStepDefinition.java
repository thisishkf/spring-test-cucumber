package com.thisishkf.springcucumber;

import com.thisishkf.springcucumber.util.LuckNumberClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class LuckyNumberStepDefinition {

    @Autowired
    private LuckNumberClient client;
    private final int TESTING_NUMBER = 1;

    @Then("^Number is not lucky")
    public void number_someNumber_is_not_lucky(){
        ResponseEntity<Boolean> result = client.testLucky();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertFalse(result.getBody());
    }

    @Then("^Number is lucky")
    public void number_someNumber_is_lucky(){
        ResponseEntity<Boolean> result = client.testLucky();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue( result.getBody());
    }

    @Given("^I set (\\d+) to lucky number$")
    public void i_set_someNumber_to_lucky_number(final int someNumber){
        ResponseEntity<Boolean> result = client.setNumber(someNumber);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @When("^I random the number$")
    public void i_random_the_number(){
        ResponseEntity<Integer> result = client.randomNumber();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Then("^Number is not (\\d+)$")
    public void number_is_not_someNumber(final int someNumber){
        ResponseEntity<Integer> result = client.getNumber();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotEquals(TESTING_NUMBER, result.getBody());
    }



}
