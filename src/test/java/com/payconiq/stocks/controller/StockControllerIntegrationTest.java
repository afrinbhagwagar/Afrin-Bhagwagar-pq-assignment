package com.payconiq.stocks.controller;

import com.payconiq.stocks.model.StockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @BeforeEach
    void initRestTemplate() {
        restTemplate = restTemplate.withBasicAuth(username, password);
    }

    @Test
    public void testGetAllPositive() {
        ResponseEntity<List<StockResponse>> response = restTemplate.exchange("/api/stocks",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StockResponse>>() {});
        assertEquals(5, response.getBody().size());
        assertEquals("Stock3", response.getBody().get(2).getName());
        assertEquals(20.0, response.getBody().get(3).getCurrentPrice());
    }

    @Test
    void testGetByIdPositive() {
        StockResponse response = restTemplate.getForObject("/api/stocks/2", StockResponse.class);
        assertEquals(2, response.getId());
        assertEquals("Stock2", response.getName());
        assertEquals(45, response.getCurrentPrice());
    }
}
