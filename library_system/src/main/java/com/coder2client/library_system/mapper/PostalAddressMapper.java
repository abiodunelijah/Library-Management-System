package com.coder2client.library_system.mapper;

import com.coder2client.library_system.dto.PostalAddressDTO;
import com.coder2client.library_system.entity.PostalAddress;

public class PostalAddressMapper {

    //map Entity to DTO
    public static PostalAddressDTO mapToPostalAddressDTO(PostalAddress postalAddress){

        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();

        postalAddressDTO.setId(postalAddress.getId());
        postalAddressDTO.setStreetName(postalAddress.getStreetName());
        postalAddressDTO.setStreetNumber(postalAddress.getStreetNumber());
        postalAddressDTO.setZipCode(postalAddress.getZipcode());
        postalAddressDTO.setPlaceName(postalAddress.getPlaceName());
        postalAddressDTO.setCountry(postalAddress.getCountry());
        postalAddressDTO.setAdditionalInfo(postalAddress.getAdditionalInfo());

        return postalAddressDTO;
    }


    //map DTO to Entity
    public static PostalAddress mapToPostAddressEntity(PostalAddressDTO postalAddressDTO){
        PostalAddress postalAddress = new PostalAddress();

        postalAddress.setId(postalAddressDTO.getId());
        postalAddress.setStreetName(postalAddressDTO.getStreetName());
        postalAddress.setStreetNumber(postalAddressDTO.getStreetNumber());
        postalAddress.setZipcode(postalAddressDTO.getZipCode());
        postalAddress.setPlaceName(postalAddressDTO.getPlaceName());
        postalAddress.setCountry(postalAddressDTO.getCountry());
        postalAddress.setAdditionalInfo(postalAddressDTO.getAdditionalInfo());

        return postalAddress;
    }
}
