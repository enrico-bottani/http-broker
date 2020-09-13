package com.enricobottani.httpbroker.controller.api;

import com.enricobottani.httpbroker.dto.HttpRequestDto;
import com.enricobottani.httpbroker.dto.HttpResponseDto;
import com.enricobottani.httpbroker.service.HttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/2020-11/get")
public class HttpGetRequestController {
    HttpRequestService getRequestService;
    @Autowired
    public HttpGetRequestController(HttpRequestService requestService){
        this.getRequestService = requestService;
    }
    @PostMapping("/")
    public HttpResponseDto sendGetRequest(@RequestBody HttpRequestDto request) {
        return getRequestService.sendGetRequest(request);
    }
    @GetMapping("/echo")
    public String echo(@RequestBody String request) {
        return request;
    }
}
