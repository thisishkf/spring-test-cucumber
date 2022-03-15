# spring-test-cucumber

In order to ensure the continuous-quality, integration testing is always introduced to the projects.

> It’s simple. Whether open source or commercial, our collaboration tools will boost your engineering team's performance by employing Behavior-Driven Development (BDD). And with our world-class training, take it to places it’s never been. 

## Project Set-ups

We are using:
 
1. Java SpringBoot for our API server.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
2. Cucumber Test
```xml
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

```xml
<properties>
    <java.version>11</java.version>
    <cucumber.version>6.8.0</cucumber.version>
</properties>
```

## Given-When-Then Strategy


## Related Reading

[Cucumber Official Website](https://cucumber.io/)
[GivenWhenThen by Martin Fowler](https://martinfowler.com/bliki/GivenWhenThen.html)