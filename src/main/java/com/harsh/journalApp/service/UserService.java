package com.harsh.journalApp.service;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.repository.UserEntryRepository;
import com.mongodb.MongoWriteException;

@Component
public class UserService {
    
    @Autowired
    public UserEntryRepository userRepository;
    // @Autowired
    // private PasswordEncoder passwordEncoder;
    
    public ResponseEntity<?> saveEntry(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
        catch(MongoWriteException e){
            return new ResponseEntity<>("username already exist",HttpStatus.CONFLICT);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
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
