package com.rajlee.api.testsignuplogin.controller;

import com.rajlee.api.testsignuplogin.model.customer;
import com.rajlee.api.testsignuplogin.repository.CustomerRepository;
import com.rajlee.api.testsignuplogin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody customer customer) {
        return customerService.registerUser(customer);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginvalidate(@RequestBody customer customer){
//        return customerService.loginValidate(customer);
//
//    }
    @RequestMapping("/user")
    public customer getUserDetailsAfterLogin(Authentication authentication) {
        List<customer> customers = customerRepository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }


    @RestController
    public class LogoutController {

        @PostMapping("/logout")
        public ResponseEntity<String> logout() {
            try {
                // Invalidate the current user's authentication session
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
                return ResponseEntity.ok().body("Logout successful");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during logout");
            }
        }
    }

}
