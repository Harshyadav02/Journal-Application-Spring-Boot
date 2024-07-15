/* This file contians logics */


package com.harsh.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.harsh.journalApp.entity.JournalEntry;
import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.repository.JournalEntryRepository;

@Component
public class JournalServiceEntry {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository; 
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User  user =  userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry); 
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }
    public JournalEntry findById(ObjectId id){
        
        return journalEntryRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public void deleteById(ObjectId id ,String userName){
       try{
        User  user =  userService.findByUserName(userName);
        boolean removed = user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        if(removed)
        {
            userService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }
       }catch(Exception e){
        throw new RuntimeException("Error while deleting entry",e);
       }
        
    }

}
