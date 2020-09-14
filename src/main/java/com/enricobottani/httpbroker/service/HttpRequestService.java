package com.enricobottani.httpbroker.service;

import com.enricobottani.httpbroker.dto.HttpRequestDto;
import com.enricobottani.httpbroker.dto.HttpResponseDto;
import com.enricobottani.httpbroker.dto.Parameters;
import com.enricobottani.httpbroker.dto.SetCookie;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.net.*;
import java.io.*;;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class HttpRequestService {
    public HttpResponseDto sendGetRequest(HttpRequestDto request) {
        HttpURLConnection connection = null;
        try {

            URL oracle = new URL(request.getUrl());
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            return HttpResponseDto.builder().status(200).raw(response.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public HttpResponseDto sendPostRequest(HttpRequestDto request) {
        HttpURLConnection connection = null;
        try {
            var body = HttpRequest.BodyPublishers.ofString(Parameters.build(request.getParameters()));

            HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
                    .followRedirects(HttpClient.Redirect.NEVER)
                    .build();
            var httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(request.getUrl()))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .timeout(Duration.ofSeconds(5))
                    .POST(body)
                    .build();

            var httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = httpResponse.body();
            var setCookie = SetCookie.parse(httpResponse.headers().firstValue("set-cookie").get());
            return HttpResponseDto.builder()
                    .status(httpResponse.statusCode())
                    .setcookie(setCookie)
                    .raw(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
