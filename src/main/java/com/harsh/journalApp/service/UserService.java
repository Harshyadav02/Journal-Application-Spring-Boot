package com.harsh.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.repository.UserEntryRepository;

@Component
public class UserService {
    
    @Autowired
    public UserEntryRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {

        return userRepository.findAll();
    }

    public User findById(ObjectId id) {

        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id) {

        userRepository.deleteById(id);

    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
