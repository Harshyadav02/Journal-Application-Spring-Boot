package com.harsh.journalApp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {

        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        
        userService.saveEntry(user);
    }

    @GetMapping("id/{userId}")
    public User getUserById(@PathVariable ObjectId userId) {

        return userService.findById(userId);
    }

    @DeleteMapping("id/{userId}")
    public void deleteUserById(@PathVariable ObjectId userId) {

        userService.deleteById(userId);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {

        User userInfo = userService.findByUserName(user.getUserName());
        if(userInfo!=null){
            userInfo.setPassword(user.getPassword());
            userInfo.setUserName(user.getUserName());
            userService.saveEntry(userInfo);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
