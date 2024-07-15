/* Router files */
package com.harsh.journalApp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.journalApp.entity.JournalEntry;
import com.harsh.journalApp.entity.User;
import com.harsh.journalApp.service.JournalServiceEntry;
import com.harsh.journalApp.service.UserService;

@RestController
@RequestMapping("/journal/")
public class JournalEntryController {

    @Autowired
    private JournalServiceEntry journalServiceEntry;

    @Autowired
    private UserService userService;

    @GetMapping // getmethod
    public ResponseEntity<?> getAllJournalOfUser() {
        System.out.println("Inside the getAllJournal");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        User user = userService.findByUserName(userName);
        List<JournalEntry> allJournalEntries = user.getJournalEntries();

        if (allJournalEntries != null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping 
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entries) {
        try {

            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            
            journalServiceEntry.saveEntry(entries, userName);
            return new ResponseEntity<>(entries, HttpStatus.CREATED);
        }catch(BadCredentialsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        } 
        catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> authenticatedUserId= user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        
        if(!authenticatedUserId.isEmpty()){
            JournalEntry journalEntry = journalServiceEntry.findById(id);
            if (journalEntry != null) {
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntries){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> authenticatedUserId= user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!authenticatedUserId.isEmpty()){
            JournalEntry journalEntry = journalServiceEntry.findById(id);
            if (journalEntry != null) {
                journalEntry.setTitle(newEntries.getTitle() !=null && !newEntries.getTitle().equals("") ? newEntries.getTitle() : journalEntry.getTitle());
                journalEntry.setContent(newEntries.getContent() !=null && !newEntries.getContent().equals("") ? newEntries.getContent() : journalEntry.getContent());
                journalServiceEntry.saveEntry(journalEntry);
                return new ResponseEntity<>(journalEntry,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        journalServiceEntry.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}