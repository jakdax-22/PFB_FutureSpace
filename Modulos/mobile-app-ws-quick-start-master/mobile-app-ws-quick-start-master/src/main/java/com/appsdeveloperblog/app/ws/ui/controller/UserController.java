package com.appsdeveloperblog.app.ws.ui.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users") //http://localhosr:8080/
public class UserController {
    public String getUser(){
        return "get user was called";
    }
}
