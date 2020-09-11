package com.enricobottani.httpbroker.controller.api;

import com.enricobottani.httpbroker.dto.GetRequest;
import com.enricobottani.httpbroker.dto.GetResponse;
import com.enricobottani.httpbroker.service.GetRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/2020-11/get")
public class HttpRequestController {
    GetRequestService getRequestService;
    @Autowired
    public HttpRequestController(GetRequestService requestService){
        this.getRequestService = requestService;
    }
    @GetMapping("/")
    public GetResponse sendGetRequest(@RequestBody GetRequest request) {
        return getRequestService.sendGetRequest(request);
    }
}
