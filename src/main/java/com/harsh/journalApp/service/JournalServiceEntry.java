/* This file contians logics */


package com.harsh.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harsh.journalApp.entity.JournalEntry;
import com.harsh.journalApp.repository.JournalEntryRepository;

@Component
public class JournalServiceEntry {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository; 

    public void saveEntry(JournalEntry journalEntry){
        
        journalEntryRepository.save(journalEntry); 
    }
    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }
    public JournalEntry findById(ObjectId id){
        
        return journalEntryRepository.findById(id).orElse(null);
    }
    public void deleteById(ObjectId id){

        journalEntryRepository.deleteById(id);
        
    }

}
