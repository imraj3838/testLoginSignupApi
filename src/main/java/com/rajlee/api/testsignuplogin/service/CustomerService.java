package com.rajlee.api.testsignuplogin.service;

import com.rajlee.api.testsignuplogin.model.customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public ResponseEntity<String> registerUser(customer customer);

    public ResponseEntity<String> loginValidate(customer customer);
}
