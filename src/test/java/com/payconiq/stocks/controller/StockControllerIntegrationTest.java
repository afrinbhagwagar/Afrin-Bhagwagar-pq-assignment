package com.payconiq.stocks.controller;

import com.payconiq.stocks.model.StockResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.security.user.name}")
    private String username;
    @Value("${spring.security.user.password}")
    private String password;

    private static HttpHeaders headers;

    @BeforeEach
    void initRestTemplate() {restTemplate = restTemplate.withBasicAuth(username, password);}

    @BeforeAll
    static void setHeaders(){
        headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
    }

    @Test
    void testPostPositive() throws JSONException {
        JSONObject inputStockRequest = new JSONObject();
        inputStockRequest.put("name", "StockNamePostPositive");
        inputStockRequest.put("currentPrice", 78.18);

        HttpEntity<String> httpEntity = new HttpEntity<>(inputStockRequest.toString(), headers);

        ResponseEntity<StockResponse> response = restTemplate.exchange("/api/stocks", HttpMethod.POST, httpEntity, StockResponse.class);

        assertEquals("StockNamePostPositive", response.getBody().getName());
        assertEquals(78.18, response.getBody().getCurrentPrice());
    }

    @Test
    void testGetAllPositive() {
        ResponseEntity<List<StockResponse>> response = restTemplate.exchange("/api/stocks",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StockResponse>>() {});
        assertEquals(5, response.getBody().size());
        assertEquals("Stock3", response.getBody().get(2).getName());
        assertEquals(20.0, response.getBody().get(3).getCurrentPrice());
    }

    @Test
    public void testGetAllPaginationPositive() {
        ResponseEntity<List<StockResponse>> response = restTemplate.exchange("/api/stocks?pageNo=1&pageSize=4",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StockResponse>>() {});
        assertEquals(2, response.getBody().size());
        assertEquals("Stock5", response.getBody().get(0).getName());
        assertEquals(640.45, response.getBody().get(1).getCurrentPrice());
    }

    @Test
    void testGetByIdPositive() {
        StockResponse response = restTemplate.getForObject("/api/stocks/2", StockResponse.class);
        assertEquals(2, response.getId());
        assertEquals("Stock2", response.getName());
        assertEquals(45, response.getCurrentPrice());
    }

    @Test
    void testPatchPositive() throws JSONException {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        JSONObject inputStockRequest = new JSONObject();
        inputStockRequest.put("currentPrice", 2100);

        HttpEntity<String> httpEntity = new HttpEntity<>(inputStockRequest.toString(), headers);

        ResponseEntity<StockResponse> response = restTemplate.exchange("/api/stocks/5", HttpMethod.PATCH, httpEntity, StockResponse.class);
        assertEquals(2100, response.getBody().getCurrentPrice());
    }

    @Test
    void testPatchWhenIDNotPresent() throws JSONException {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        JSONObject inputStockRequest = new JSONObject();
        inputStockRequest.put("currentPrice", 38);

        HttpEntity<String> httpEntity = new HttpEntity<>(inputStockRequest.toString(), headers);

        ResponseEntity<StockResponse> response = restTemplate.exchange("/api/stocks/9", HttpMethod.PATCH, httpEntity, StockResponse.class);
        assertEquals(500, response.getStatusCodeValue());
    }

    @Test
    void testDeleteByIdPositive() {
        StockResponse responseBeforeDel = restTemplate.getForObject("/api/stocks/6", StockResponse.class);
        assertEquals(6, responseBeforeDel.getId());
        assertEquals("Stock6", responseBeforeDel.getName());
        assertEquals(640.45, responseBeforeDel.getCurrentPrice());

        restTemplate.delete("/api/stocks/6");
        ResponseEntity<StockResponse> responseAfterDelete = restTemplate.getForEntity("/api/stocks/6", StockResponse.class);

        assertEquals(404, responseAfterDelete.getStatusCodeValue());
    }
}
