package com.manning.sbip.ch04;

import org.apache.coyote.Response;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Type;
import java.util.Map;

@Component
public class DogsApiHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        try{
            ParameterizedTypeReference<Map<String, String>> reference =
                    new ParameterizedTypeReference<Map<String, String>>() {};
            ResponseEntity<Map<String, String>> responseEntity = new RestTemplate()
                    .exchange("https://dog.ceo/api/breeds/image/random", HttpMethod.GET, null, reference);
            if(responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                return Health.up().withDetails(responseEntity.getBody()).build();
            }
            else {
                return Health.down().withDetail("status", responseEntity.getStatusCode()).build();
            }
        }
        catch (RestClientException ex) {
            return Health.down().withException(ex).build();
        }
    }
}
