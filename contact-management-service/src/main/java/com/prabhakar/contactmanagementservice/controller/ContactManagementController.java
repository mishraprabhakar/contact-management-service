package com.prabhakar.contactmanagementservice.controller;

import com.prabhakar.contactmanagementservice.model.Contact;
import com.prabhakar.contactmanagementservice.security.ContactManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contact")
public class ContactManagementController {

    private final ContactManagementService contactManagementService;

    public ContactManagementController(ContactManagementService contactManagementService) {
        this.contactManagementService = contactManagementService;
    }

    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactManagementService.saveContact(contact));
    }

    @GetMapping
    public ResponseEntity<?> getContacts() {
        return ResponseEntity.ok(contactManagementService.getAllContacts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact, @PathVariable("id") Long id) {
        return ResponseEntity.ok(contactManagementService.updateContact(contact, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContactDetail(@PathVariable("id") Long id) {
        contactManagementService.removeContact(id);
        return ResponseEntity.ok("Contact deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contactManagementService.getContactById(id));
    }

}
