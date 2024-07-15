package com.harsh.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.repository.UserEntryRepository;
import com.harsh.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  UserEntryRepository userEntryRepository;
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @DeleteMapping
    public void deleteUserById() {
        System.out.println("Inside delete route");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        userEntryRepository.deleteByuserName(userName);
        
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        User userInfo = userService.findByUserName(username); 
        // String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(user.getPassword());
        userInfo.setUserName(user.getUserName());
        userEntryRepository.save(userInfo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
