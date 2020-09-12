package com.enricobottani.httpbroker.service;

import com.enricobottani.httpbroker.dto.GetRequest;
import com.enricobottani.httpbroker.dto.GetResponse;
import com.enricobottani.httpbroker.dto.Parameters;
import org.springframework.stereotype.Service;

import java.net.*;
import java.io.*;
@Service
public class GetRequestService {
    public GetResponse sendGetRequest(GetRequest request) {
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
            return GetResponse.builder().status(200).raw(response.toString()).build();
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
