package com.enricobottani.httpbroker.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GetRequest {
    private String url;
    private Map<String, String> parameters;
    private Map<String, String> headers;
}
