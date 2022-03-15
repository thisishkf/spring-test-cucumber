package com.thisishkf.springcucumber.util;

import com.thisishkf.springcucumber.model.request.SetLuckNumberRequest;
import io.cucumber.java.sl.In;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class LuckNumberClient {

    private final String SERVER_URL = "http://localhost";
    private final String ROUTING_PREFIX = "/luckyNumber";
    @LocalServerPort
    private int port;
    private URI uri = new URI(SERVER_URL);


    private final RestTemplate restTemplate = new RestTemplate();

    public LuckNumberClient() throws URISyntaxException {
    }

    public ResponseEntity setNumber(int number){
        final String URL = SERVER_URL + ":" + port + "/" + ROUTING_PREFIX ;
        SetLuckNumberRequest request = new SetLuckNumberRequest();
        request.setNumber(number);
        return restTemplate.postForEntity(uri.resolve(URL).toString(), request, Boolean.class);
    }

    public ResponseEntity<Integer> randomNumber(){
        final String URL = SERVER_URL + ":" + port + "/" + ROUTING_PREFIX + "/random";
        return restTemplate.postForEntity(uri.resolve(URL).toString(), null, Integer.class);
    }

    public ResponseEntity<Integer> getNumber() {
        final String URL = SERVER_URL + ":" + port + "/" + ROUTING_PREFIX;
        return restTemplate.getForEntity(uri.resolve(URL).toString(), Integer.class);
    }

    public ResponseEntity<Boolean> testLucky() {
        final String URL = SERVER_URL + ":" + port + "/" + ROUTING_PREFIX + "/test";
        return restTemplate.getForEntity(uri.resolve(URL).toString(), Boolean.class);
    }
}
