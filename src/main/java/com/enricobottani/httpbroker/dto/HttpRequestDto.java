package com.enricobottani.httpbroker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class HttpRequestDto {
    private String url;
    private Map<String, String> parameters;
    private Map<String, String> headers;
}
