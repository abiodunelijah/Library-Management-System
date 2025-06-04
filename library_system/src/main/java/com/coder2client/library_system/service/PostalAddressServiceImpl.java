package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.PostalAddressDTO;
import com.coder2client.library_system.entity.PostalAddress;
import com.coder2client.library_system.mapper.PostalAddressMapper;
import com.coder2client.library_system.repository.PostalAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostalAddressServiceImpl implements PostalAddressService {

    private final PostalAddressRepository postalAddressRepository;


    @Override
    public PostalAddressDTO createPostalAddress(PostalAddressDTO postalAddressDTO) {

        PostalAddress postalAddress = PostalAddressMapper.mapToPostAddressEntity(postalAddressDTO);

        var savedPostalAddress = postalAddressRepository.save(postalAddress);

        return PostalAddressMapper.mapToPostalAddressDTO(savedPostalAddress);
    }

    @Override
    public List<PostalAddressDTO> getAllPostalAddresses() {
        List<PostalAddress> postalAddresses = postalAddressRepository.findAll();

        return postalAddresses.stream()
                .map(PostalAddressMapper::mapToPostalAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostalAddressDTO getPostalAddressById(Long id) {

        Optional<PostalAddress> optionalPostalAddress = postalAddressRepository.findById(id);

        PostalAddress postalAddress = optionalPostalAddress.get();

        return PostalAddressMapper.mapToPostalAddressDTO(postalAddress);
    }

    @Override
    public PostalAddressDTO updatePostalAddress(PostalAddressDTO postalAddressDTO) {
        //find existing postal address by id
        Optional<PostalAddress> optionalPostalAddress = postalAddressRepository.findById(postalAddressDTO.getId());
        PostalAddress postalAddressToUpdate = optionalPostalAddress.get();

        //do a partial update of the postal Address
        updatePostalAddressEntityFromDTO(postalAddressToUpdate,postalAddressDTO);

        //save the updated postal address
        PostalAddress savedPostalAddress = postalAddressRepository.save(postalAddressToUpdate);

        //return postal address DTO using mapper
        return PostalAddressMapper.mapToPostalAddressDTO(savedPostalAddress);
    }

    @Override
    public void deletePostalAddress(Long id) {
        postalAddressRepository.deleteById(id);
    }

    public void updatePostalAddressEntityFromDTO(PostalAddress postalAddressToUpdate, PostalAddressDTO postalAddressDTO) {
        if (postalAddressDTO.getStreetName() != null){
            postalAddressToUpdate.setStreetName(postalAddressToUpdate.getStreetName());
        }
        if(postalAddressDTO.getStreetNumber() != null){
            postalAddressToUpdate.setStreetNumber(postalAddressDTO.getStreetNumber());
        }

        if (postalAddressDTO.getZipCode() != null){
            postalAddressToUpdate.setZipcode(postalAddressDTO.getZipCode());
        }

        if (postalAddressDTO.getPlaceName() != null){
            postalAddressToUpdate.setPlaceName(postalAddressDTO.getPlaceName());
        }

        if (postalAddressDTO.getCountry() != null){
            postalAddressToUpdate.setCountry(postalAddressDTO.getCountry());
        }

        if (postalAddressDTO.getAdditionalInfo() != null){
            postalAddressToUpdate.setAdditionalInfo(postalAddressDTO.getAdditionalInfo());
        }
    }
}
