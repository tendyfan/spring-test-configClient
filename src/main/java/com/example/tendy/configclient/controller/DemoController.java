package com.example.tendy.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tendy
 * @create 2019-11-28 17:33
 **/
@RestController
public class DemoController {

    @Value("${com.example.tendy.config.samples.demo}")
    private String demo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        return demo;
    }

}
