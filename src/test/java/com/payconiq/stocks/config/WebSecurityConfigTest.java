package com.payconiq.stocks.config;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to check if unathorized access is done or not.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSecurityConfigTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("unauthorizedUsername")
    private String username;
    @Value("dummyPassword")
    private String password;

    @BeforeEach
    void initRestTemplate() {
        restTemplate = restTemplate.withBasicAuth(username, password);
    }

    /**
     * GET Controller check for unauthorized access.
     */
    @Test
    public void testGetAllWithUnauthorizedAccess() {
        ResponseEntity<List<StockResponse>> response = restTemplate.exchange("/api/stocks",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StockResponse>>() {});
       assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    /**
     * POST Controller check for unauthorized access.
     */
    @Test
    public void testPostWithUnauthorizedAccess() {
        ResponseEntity<List<StockResponse>> response = restTemplate.exchange("/api/stocks",
                HttpMethod.POST, null, new ParameterizedTypeReference<List<StockResponse>>() {});
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
