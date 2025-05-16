package com.haons.java.springpagingsorting.service;

import com.haons.java.springpagingsorting.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface CustomerService {
    // Method to find all customers with pagination
    Page<CustomerEntity> findAllCustomers(Pageable pageable);


}
