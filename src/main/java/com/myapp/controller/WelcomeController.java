package com.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class WelcomeController {

    @GetMapping
    public String getHelloWorld() {
        return "GET Hello, World!";
    }


    @PostMapping
    public String postHelloWorld() {
        return "POST Hello, World!";
    }
}


