package com.harsh.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.journalApp.entity.User;

public interface UserEntryRepository extends MongoRepository<User,ObjectId>{
    
    User findByUserName(String userName);
    void deleteByuserName(String userName);
}
