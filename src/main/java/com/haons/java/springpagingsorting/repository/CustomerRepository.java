package com.haons.java.springpagingsorting.repository;

import com.haons.java.springpagingsorting.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
