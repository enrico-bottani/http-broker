package com.enricobottani.httpbroker.controller.api;

import com.enricobottani.httpbroker.dto.HttpRequestDto;
import com.enricobottani.httpbroker.dto.HttpResponseDto;
import com.enricobottani.httpbroker.service.HttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/2020-11/post")
public class HttpPostRequestController {
    HttpRequestService getRequestService;
    @Autowired
    public HttpPostRequestController(HttpRequestService requestService){
        this.getRequestService = requestService;
    }
    @PostMapping("/")
    public HttpResponseDto sendPostRequest(@RequestBody HttpRequestDto request) {
        return getRequestService.sendPostRequest(request);
    }
    @GetMapping("/echo")
    public String echo(@RequestBody String request) {
        return request;
    }
}