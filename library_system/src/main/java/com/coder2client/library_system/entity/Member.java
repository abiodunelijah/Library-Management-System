package com.coder2client.library_system.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postal_address_id")
    private PostalAddress postalAddress;


    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate membershipStarted;

    private LocalDate membershipEnd;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private String barcodeNumber;


}
