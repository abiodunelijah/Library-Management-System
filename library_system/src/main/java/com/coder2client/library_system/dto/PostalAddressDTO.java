package com.coder2client.library_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostalAddressDTO {

    private Long id;
    private String streetName;
    private String streetNumber;
    private String zipCode;
    private String placeName;
    private String country;
    private String additionalInfo;


}
