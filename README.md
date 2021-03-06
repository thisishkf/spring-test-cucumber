# spring-test-cucumber

* Read from other language: [English](README.md), [繁體中文](README-zh-TW.md)

In order to ensure the continuous-quality, integration testing is always introduced to the projects.

> It’s simple. Whether open source or commercial, our collaboration tools will boost your engineering team's performance by employing Behavior-Driven Development (BDD). And with our world-class training, take it to places it’s never been. 

## Table of Content

- [spring-test-cucumber](#spring-test-cucumber)
    - [Table of Content](#Table-of-Content)
    - [Project Set ups](#Project-Set-ups)
        - [API server](#API-server)
        - [Cucumber Test](#Cucumber-Test)
        - [Versions](#versions)
    - [Given-When-Then Strategy](#Given-When-Then-Strategy)
    - [Implementation](#Implementation)
        - [Create Testing Server](#Create-Testing-Server)
        - [Writing our GWT test cases](#Writing-our-GWT-test-cases)
        - [Configure Cucumber](#Configure-Cucumber)
        - [Implement the testing steps](#Implement-the-testing-steps)
    - [Related Reading](#Related-Reading)

## Project Set ups
 
### API server

we will be using Java SpringBoot to develop a simple API server

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
### Cucumber Test

We will be using SpringBoot Test and cucumber

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-spring</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
```

### versions

```xml
<properties>
    <java.version>11</java.version>
    <cucumber.version>6.8.0</cucumber.version>
</properties>
```

## Given When Then Strategy

> TODO

## Implementation

### Create Testing Server

In `CucumberSpringConfiguration.java`, we are using annotation to create a testing server, and wiring the cucumber context to the testing server.

```
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
```

### Writing our GWT test cases

As mentioned in the above [Given-When-Then Strategy](#Given-When-Then-Strategy), we are using GWT Strategy for the cucumber test.
Elements in `.feature` file will be applied to cucumber test automatically.

GWT Example:
```
Feature: Is number lucky
    Check a number is lucky

    Scenario: 1 is not lucky
        Given I set 1 to lucky number
        Then Number is not lucky
``` 

### Configure Cucumber

We are going to use junit runner to start a Cucumber test. Also, the feature directory have to configured. 

Example from `LuckNumberTest.java`
```java
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class LuckNumberTest { }
```

### Implement the testing steps

As we are doing end-to-end integration test for the application behavior, we will send a real http request to our test server.It is recommended to create helper class for the requests. Example: `util/LuckNumberClient.java`.

For the implementation, we will define the actual behavior for all element in feature file.

Using `Scenario: 1 is not lucky` as example, there is a `Given` and a `Then`:

```
 @Given("^I set (\\d+) to lucky number$")
 public void i_set_someNumber_to_lucky_number(final int someNumber){
         ResponseEntity<Boolean> result = client.setNumber(someNumber);
         assertEquals(HttpStatus.OK, result.getStatusCode());
 }
 
 @Then("^Number is not lucky")
 public void number_someNumber_is_not_lucky(){
     ResponseEntity<Boolean> result = client.testLucky();
     assertEquals(HttpStatus.OK, result.getStatusCode());
     assertFalse(result.getBody());
 }
```

## Related Reading

[Cucumber Official Website](https://cucumber.io/)

[GivenWhenThen by Martin Fowler](https://martinfowler.com/bliki/GivenWhenThen.html)