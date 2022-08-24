package com.prabhakar.contactmanagementservice.security;

import com.prabhakar.contactmanagementservice.exception.CustomException;
import com.prabhakar.contactmanagementservice.model.Contact;
import com.prabhakar.contactmanagementservice.repository.ContactManagementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactManagementService {

    private final ContactManagementRepository repository;

    public ContactManagementService(ContactManagementRepository repository) {
        this.repository = repository;
    }

    public Optional<Contact> getContactById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new CustomException("No contact found", HttpStatus.NOT_FOUND)));
    }

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public Contact saveContact(Contact contact) {

        repository.findContactByContactNumber(contact.getContactNumber())
                .ifPresent(val -> {
                    throw new CustomException("Contact Number already exists",
                            HttpStatus.BAD_REQUEST);
                });

        repository.findContactByEmail(contact.getEmail())
                .ifPresent(val -> {
                    throw new CustomException("Email already exists",
                            HttpStatus.BAD_REQUEST);
                });

        return repository.save(contact);
    }

    public Contact updateContact(Contact contact, Long id) {

        Contact contactDetails = repository.findById(id)
                .orElseThrow(() -> new CustomException("Contact not found",
                        HttpStatus.NOT_FOUND));

        if (contact.getName() != null &&
                contact.getName().length() > 0
                && !Objects.equals(contact.getName(), contactDetails.getName())) {
            contactDetails.setName(contact.getName());
        } else if (contact.getContactNumber() != null &&
                !Objects.equals(contact.getContactNumber(), contactDetails.getContactNumber())) {
            contactDetails.setContactNumber(contact.getContactNumber());
        } else if (contact.getEmail() != null &&
                contact.getEmail().length() > 0 &&
                !Objects.equals(contact.getEmail(), contactDetails.getEmail())) {
            contactDetails.setEmail(contact.getEmail());
        }

        return repository.save(contactDetails);
    }

    public void removeContact(Long id) {
        repository.deleteById(id);
    }

}
