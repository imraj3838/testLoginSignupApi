package com.rajlee.api.testsignuplogin.service;

import com.rajlee.api.testsignuplogin.model.customer;
import com.rajlee.api.testsignuplogin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CustomerserviceImpl implements CustomerService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<String> registerUser(customer customer) {
        customer savedCustomer = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPwd);
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @Override
    public ResponseEntity<String> loginValidate(customer customer) {
        String email1 = customer.getEmail();
        String pass = customer.getPassword();
        ResponseEntity response = null;
        List<customer> c =  customerRepository.findByEmail(email1);
        if(c.size()>0){
          boolean b = passwordEncoder.matches(pass,c.get(0).getPassword());
          if(b){
              response = ResponseEntity
                      .status(HttpStatus.CREATED)
                      .body("Given user details are successfully registered");
          }
        }
        return response;
    }
}
