package com.ataccama.hiring.archive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestCallService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl;

    public AbstractRestCallService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected <T> ResponseEntity<T> callGet(String url, Class<T> responseType) {
        // NOTE: this method might contain more complex code
        return this.restTemplate.getForEntity(this.baseUrl + url, responseType);
    }
}
