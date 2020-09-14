package com.enricobottani.httpbroker.controller.template;

import com.enricobottani.httpbroker.controller.api.HttpPostRequestController;
import com.enricobottani.httpbroker.dto.HttpRequestDto;
import com.enricobottani.httpbroker.dto.SetCookie;
import com.enricobottani.httpbroker.service.HttpRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class TemplateController {
    @GetMapping("/redirect")
    public String getCourses(@ModelAttribute("model") ModelMap model, HttpServletResponse response) {
        var httpResponse = new HttpPostRequestController(new HttpRequestService()).sendPostRequest(
                HttpRequestDto.builder()
                        .url("http://localhost:8080/login")
                        .parameters(Map.of("username", "anna@gmail.com", "password", "1234"))
                        .build());

        Cookie cookie = new Cookie("JSESSIONID", httpResponse.getSetcookie().getKvAttributes().get("JSESSIONID"));
        //add cookie to response
        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "http://localhost:8080/");
        return "redirect";
    }
    @GetMapping("/")
    public String index(@ModelAttribute("model") ModelMap model) {
        return "index";
    }
}