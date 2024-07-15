package com.harsh.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.service.UserService;


@RestController
@RequestMapping("/public")

public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("health-checker")
    public String healthCheker(){

        return "OK";
    } 

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        
        return userService.saveEntry(user);
    }
}