package tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerLoginTest {

    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:8081/login").toString(), String.class);
        assertEquals("login", response.getBody());
    }

}
