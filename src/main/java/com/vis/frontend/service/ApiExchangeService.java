package com.vis.frontend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Component
public class ApiExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = Logger.getLogger("Point");

    public String createURL(String domain, String... paths) {
        StringBuilder builder = new StringBuilder(domain);
        for (String path : paths) {
            builder.append(path);
        }
        return builder.toString();
    }

    public <T> ResponseEntity<T> post(HttpServletRequest httpRequest, String url, Object request, Class<T> classType, MultiValueMap<String,Object> body) {
        HttpEntity<Object> requestEntity = createEntity(httpRequest, request, MediaType.APPLICATION_JSON_UTF8, body);
        logger.info("URL API Exchange: " + url);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, classType);
        return response;
    }
    public <T> ResponseEntity<T> postFile(HttpServletRequest httpRequest, String url, Object request, Class<T> classType) {
        HttpEntity<Object> requestEntity = createFileEntity(httpRequest, request);
        logger.info("URL API Exchange: " + url);
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.postForEntity(url, requestEntity, classType);
        return response;
    }

    public <T> ResponseEntity<T> getForEntity(HttpServletRequest httpRequest, String url, Object request, Class<T> classType) {
        HttpEntity<Object> requestEntity = createEntity(request);
        logger.info("URL API Exchange: " + url);
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.postForEntity(url, requestEntity, classType);
        return response;
    }

    private HttpEntity<Object> createFileEntity(HttpServletRequest httpRequest, Object data) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", data);
        HttpHeaders headers = transferFile(httpRequest);
        return new HttpEntity<>(body, headers);
    }

    private HttpEntity<Object> createEntity(HttpServletRequest httpRequest, Object data, MediaType mediaType, MultiValueMap<String, Object> body) {
        HttpHeaders headers = createHeaders(httpRequest, data, mediaType);
        return new HttpEntity<>(data, headers);
    }

    private HttpEntity<Object> createEntity(Object data) {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<Object>(data, headers);
    }

    private HttpHeaders transfer(HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", httpServletRequest.getHeader("User_Agent"));
        headers.set("X-Forwarded-For", httpServletRequest.getRemoteAddr());
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String transactionId = httpServletRequest.getHeader("transaction_id");
        headers.set("transaction_id", transactionId);

        return headers;
    }

    private HttpHeaders createHeaders(HttpServletRequest httpServletRequest, Object data, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", httpServletRequest.getHeader("User_Agent"));
        headers.set("X-Forwarded-For", httpServletRequest.getRemoteAddr());
        headers.setContentType(mediaType);
        String transactionId = httpServletRequest.getHeader("transaction_id");
        headers.set("transaction_id", transactionId);
        return headers;
    }

    private HttpHeaders transferFile(HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", httpServletRequest.getHeader("User_Agent"));
        headers.set("X-Forwarded-For", httpServletRequest.getRemoteAddr());
        headers.setContentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA + ";boundary=" + Long.toHexString(System.currentTimeMillis())));
        String transactionId = httpServletRequest.getHeader("transaction_id");
        headers.set("transaction_id", transactionId);

        return headers;
    }

    public <T> ResponseEntity<T> get(HttpServletRequest httpRequest, String url, Class<T> classType, MediaType mediaType, MultiValueMap<String, Object> body) {
        HttpEntity<?> requestEntity = createEntity(httpRequest, null, mediaType, body);
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.exchange( url, HttpMethod.GET, requestEntity , String.class);
        return response;
    }


    public <T> ResponseEntity<T> get(HttpServletRequest httpRequest, String url, Class<T> classType, MediaType mediaType) {
        HttpEntity<?> requestEntity = createEntity(httpRequest, null, mediaType, null);
        ResponseEntity<T> response = (ResponseEntity<T>) restTemplate.exchange( url, HttpMethod.GET, requestEntity , String.class);
        return response;
    }

}
