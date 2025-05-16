package com.haons.java.springpagingsorting.service.impl;

import com.haons.java.springpagingsorting.entity.CustomerEntity;
import com.haons.java.springpagingsorting.repository.CustomerRepository;
import com.haons.java.springpagingsorting.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerSericeImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public Page<CustomerEntity> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
