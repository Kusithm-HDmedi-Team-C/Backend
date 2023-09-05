package com.example.kusithms_hdmedi_project.global.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApiController {
    @RequestMapping("/")
    public String hdMedi() {
        return "hdMedi-Team-C-test";
    }
}
