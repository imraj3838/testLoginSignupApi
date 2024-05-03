package com.rajlee.api.testsignuplogin.repository;

import com.rajlee.api.testsignuplogin.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<customer, Long> {

    List<customer> findByEmail(String username);
}
