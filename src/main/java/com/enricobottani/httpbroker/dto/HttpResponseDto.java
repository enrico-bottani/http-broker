package com.enricobottani.httpbroker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class HttpResponseDto {
    int status;
    String raw;
    List<String> setcookie;

    public String toString() {
        return String.format(
                "status: %s\n" +
                        "JSESSIONID: %s\n" +
                        "RAW:\n%s",
                status, setcookie, raw);
    }
}
