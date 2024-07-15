package com.harsh.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            List<User> allUserDetails = userService.getAll();
            if(allUserDetails!=null){
                return new ResponseEntity<>(allUserDetails,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
