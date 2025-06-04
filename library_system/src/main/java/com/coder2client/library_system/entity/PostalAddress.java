package com.coder2client.library_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="postal_addresses")
public class PostalAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String streetNumber;

    private String zipcode;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String country;

    private String additionalInfo;
}
