package com.prabhakar.contactmanagementservice.repository;

import com.prabhakar.contactmanagementservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ContactManagementRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findContactByContactNumber(BigInteger contactNumber);

    Optional<Contact> findContactByEmail(String email);

}
