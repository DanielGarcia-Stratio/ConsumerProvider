package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
public class ConsumerController {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getVisit")
    public String getFullVisit(@RequestParam("code") String visit){
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type","application/json");

    ResponseEntity<String> responseEntity = restTemplate.exchange(
            "http://localhost:8080/visits?code=" + visit,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);
    return responseEntity.getBody();
}

}
