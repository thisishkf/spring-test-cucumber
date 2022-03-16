# spring-test-cucumber

* 更多文檔語言: [English](README.md), [繁體中文](README-zh-TW.md)

In order to ensure the continuous-quality, integration testing is always introduced to the projects.

> It’s simple. Whether open source or commercial, our collaboration tools will boost your engineering team's performance by employing Behavior-Driven Development (BDD). And with our world-class training, take it to places it’s never been. 

## 目錄

- [spring-test-cucumber](#spring-test-cucumber)
    - [目錄](#目錄)
    - [項目配置](#項目配置)
        - [API 服務器](#API-服務器)
        - [測試配件](#測試配件)
        - [配件版本](#配件版本)
    - [Given-When-Then Strategy](#Given-When-Then)
    - [實現](#實現)
        - [生成測試用服務器](#生成測試用服務器)
        - [編寫我們的測試用例](#編寫我們的測試用例)
        - [配設 Cucumber](#配設-Cucumber)
        - [實現測試案步驟](#實現測試案步驟)
    - [相關閱讀](#相關閱讀)

## 項目配置
 
### API 服務器

我們將會使用 Java SpringBoot 來開發一個簡單的API 服務器。

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
### 測試配件

我們將會使用 SpringBoot 及 cucumber 的測試配件

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

### 配件版本

```xml
<properties>
    <java.version>11</java.version>
    <cucumber.version>6.8.0</cucumber.version>
</properties>
```

## Given When Then 

> TODO

## 實現

### 生成測試用服務器

在 `CucumberSpringConfiguration.java`, 我們會利用註解來生成服務器, 調用測試用服務器到cucumber context.

```
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
```

### 編寫我們的測試用例

在 [Given-When-Then Strategy](#Given-When-Then-Strategy)提到, 我們會用 GWT 方式來設計我們的測試用例.
所有在`.feature` 的元素都會被cucumber自動掃描及測試.

GWT 例子:
```
Feature: Is number lucky
    Check a number is lucky

    Scenario: 1 is not lucky
        Given I set 1 to lucky number
        Then Number is not lucky
``` 

### 配設 Cucumber

我們會利用 junit runner 執行Cucumber測試, 並把feature 檔案配設到cucumber 中。 

`LuckNumberTest.java`例子:

```java
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class LuckNumberTest { }
```

### 實現測試案步驟

在端到端集成測試中, 我們想測試到系統的表現, 所以我們會傳送真實的HTTP 請求到測試服務器中. 請求最好是寫到輔助工具中. 倒如: `util/LuckNumberClient.java`.

以 `Scenario: 1 is not lucky` 為例子, 有一個 `Given` 及一個 `Then`:

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

## 相關閱讀

[Cucumber官方網站](https://cucumber.io/)

[GivenWhenThen by Martin Fowler](https://martinfowler.com/bliki/GivenWhenThen.html)