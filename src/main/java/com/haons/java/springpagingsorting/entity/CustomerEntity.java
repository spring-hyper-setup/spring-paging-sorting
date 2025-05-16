package com.haons.java.springpagingsorting.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "customer_001")
@DynamicUpdate
@DynamicInsert
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(columnDefinition = "varchar(255) comment 'first name'", nullable = false)
    private String firstName;

    @Column(columnDefinition = "varchar(255) comment 'last name'", nullable = false)
    private String lastName;


    @Column(name = "email", columnDefinition = "varchar(255) comment 'email address'", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(255) comment 'user password'", nullable = false)
    private String password;

    @Column(columnDefinition = "timestamp comment 'registration date'", nullable = false)
    private Timestamp registerDate;

    @Column(columnDefinition = "varchar(255) comment 'phone number'", nullable = false)
    private String phoneNumber;
}
