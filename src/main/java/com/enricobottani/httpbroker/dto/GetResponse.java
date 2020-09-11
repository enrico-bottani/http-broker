package com.enricobottani.httpbroker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetResponse {
    int status;
    String raw;
}
