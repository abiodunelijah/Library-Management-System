package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.PostalAddressDTO;

import java.util.List;

public interface PostalAddressService {

    PostalAddressDTO createPostalAddress(PostalAddressDTO postalAddressDTO);
    List<PostalAddressDTO> getAllPostalAddresses();
    PostalAddressDTO getPostalAddressById(Long id);
    PostalAddressDTO updatePostalAddress(PostalAddressDTO postalAddressDTO);
    void deletePostalAddress(Long id);
}
