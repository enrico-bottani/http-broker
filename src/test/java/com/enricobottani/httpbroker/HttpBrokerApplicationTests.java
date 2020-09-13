package com.enricobottani.httpbroker;

import com.enricobottani.httpbroker.controller.api.HttpPostRequestController;
import com.enricobottani.httpbroker.dto.HttpRequestDto;
import com.enricobottani.httpbroker.service.HttpRequestService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HttpBrokerApplicationTests {

    @Test
    void contextLoads() throws IOException {
        URL oracle = new URL("http://192.168.1.1/");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    @Test
    public void convertMapToJson() {
        Map<String, String> elements = new HashMap<>();
        elements.put("Key1", "Value1");
        elements.put("Key2", "Value2");
        elements.put("Key3", "Value3");

        JSONObject json = new JSONObject(elements);

        System.out.println(json);
    }

    @Test
    public void testSendPostRequest() {
        System.out.println(new HttpPostRequestController(new HttpRequestService()).sendPostRequest(
                HttpRequestDto.builder()
                        .url("http://localhost:8080/oauth-proxy/set-token")
                        .parameters(Map.of("username", "anna@gmail.com", "token", "abc"))
                        .build())
        );
    }


    @Test
    public void testSendPostRequestLogin() {
        System.out.println(new HttpPostRequestController(new HttpRequestService()).sendPostRequest(
                HttpRequestDto.builder()
                        .url("http://localhost:8080/login")
                        .parameters(Map.of("username", "anna@gmail.com", "password", "1234"))
                        .build())
        );
    }
}
