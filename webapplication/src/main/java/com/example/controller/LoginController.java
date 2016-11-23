package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String login(@RequestParam(name="firstName") String firstName, @RequestParam(name="password") String password){
        return "Ok";
    }
}
