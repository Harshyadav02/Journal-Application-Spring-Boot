/* Router files */
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

    @GetMapping("{userName}") // getmethod
    public ResponseEntity<?> getAllJournalOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);

        List<JournalEntry> allJournalEntries = user.getJournalEntries();

        if (allJournalEntries != null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}") // post method
    /*
     * 1) @RequestBody defines that take data from the post request,
     * 
     * 2) JournalEntry is type of data i.e the data will come in json only but auto
     * serialization is done by @RestController
     * and convert to JournalEntry type and will store in variable entries.
     * 
     */
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entries, @PathVariable String userName) {

        try {
            journalServiceEntry.saveEntry(entries, userName);
            return new ResponseEntity<>(entries, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {

        JournalEntry journalEntry = journalServiceEntry.findById(id);
        // System.out.println(journalEntry);

        if (journalEntry != null) {
            System.out.println(journalEntry.getContent());
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateById(
        @PathVariable ObjectId id,
        @PathVariable String userName, 
        @RequestBody JournalEntry newEntries
        ){

        JournalEntry oldEntry  = journalServiceEntry.findById(id);

        if(oldEntry !=null){
            oldEntry.setTitle(newEntries.getTitle() !=null && !newEntries.getTitle().equals("") ? newEntries.getTitle() : oldEntry.getTitle());
            System.out.println(oldEntry);
            oldEntry.setContent(newEntries.getContent() !=null && !newEntries.getContent().equals("") ? newEntries.getContent() : oldEntry.getContent());
            journalServiceEntry.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

    @DeleteMapping("id/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String userName) {
        journalServiceEntry.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}