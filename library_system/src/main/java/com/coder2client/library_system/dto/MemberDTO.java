package com.coder2client.library_system.dto;

import com.coder2client.library_system.entity.PostalAddress;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private PostalAddressDTO postalAddress;
    private String email;
    private String phoneNumber;
    private String barcodeNumber;
    private String membershipStarted;
    private String membershipEnded;
    private Boolean isActive;

}
