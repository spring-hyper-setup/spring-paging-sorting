package com.haons.java.springpagingsorting.controller;

import com.haons.java.springpagingsorting.entity.CustomerEntity;
import com.haons.java.springpagingsorting.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/findAll")
    public Page<CustomerEntity> findAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        //  Set-up the sorting direction
        Sort.Direction direction = sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        //  Set-up sort by field
        Sort sort = Sort.by(direction, sortBy);
        //  Set-up the pageable object
        Pageable pageable = PageRequest.of(page, size, sort);
        return customerService.findAllCustomers(pageable);
    }

}
