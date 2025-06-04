package com.coder2client.library_system.controller;

import com.coder2client.library_system.dto.PostalAddressDTO;
import com.coder2client.library_system.service.PostalAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postal-addresses")
public class PostalAddressController {

    private final PostalAddressService postalAddressService;

    public PostalAddressController(PostalAddressService postalAddressService) {
        this.postalAddressService = postalAddressService;
    }

    @PostMapping("create-address")
    //url http://localhost:8081/api/v1/postal-addresses/create-address
    public ResponseEntity<PostalAddressDTO> createPostalAddress(@RequestBody PostalAddressDTO postalAddressDTO){
        PostalAddressDTO postalAddress = postalAddressService.createPostalAddress(postalAddressDTO);

        return new ResponseEntity<>(postalAddress, HttpStatus.CREATED);
    }

    @GetMapping("addresses")
    //url http://localhost:8081/api/v1/postal-addresses/addresses
    public ResponseEntity<List<PostalAddressDTO>> getAllPostalAddresses(){
        List<PostalAddressDTO> allPostalAddresses = postalAddressService.getAllPostalAddresses();

        return new ResponseEntity<>(allPostalAddresses, HttpStatus.OK);
    }

    @GetMapping("{id}")
    //url http://localhost:8081/api/v1/postal-addresses/2
    public ResponseEntity<PostalAddressDTO> getPostalAddressById(@PathVariable Long id){
        PostalAddressDTO postalAddressById = postalAddressService.getPostalAddressById(id);
        return new ResponseEntity<>(postalAddressById, HttpStatus.OK);
    }

    @PatchMapping("update/{id}")
    //url http://localhost:8081/api/v1/postal-addresses/update/1
    public ResponseEntity<PostalAddressDTO> updatePostalAddress(@PathVariable Long id, @RequestBody PostalAddressDTO postalAddressDTO){
        postalAddressDTO.setId(id);

        PostalAddressDTO postalAddress = postalAddressService.updatePostalAddress(postalAddressDTO);

        return new ResponseEntity<>(postalAddress, HttpStatus.OK);
    }

    @DeleteMapping("deletePostalAddress/{id}")
    //url http://localhost:8081/api/v1/postal-addresses/deletePostalAddress/1
    public ResponseEntity<String> deletePostalAddress(@PathVariable Long id){
        postalAddressService.deletePostalAddress(id);
        return new ResponseEntity<>("Postal Address deleted successfully.", HttpStatus.OK);
    }

}
