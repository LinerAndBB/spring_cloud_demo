package com.lagou.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server")
public class ServerConfigController {

    @Value("${server.port}")
    private String serverport;

    @RequestMapping("query")
    public String findServerPort(){
        return serverport;
    }
}
