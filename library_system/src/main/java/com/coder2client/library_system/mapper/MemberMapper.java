package com.coder2client.library_system.mapper;

import com.coder2client.library_system.dto.MemberDTO;
import com.coder2client.library_system.entity.Member;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemberMapper {

    //map entity to DTO

    public static MemberDTO mapMemberEntityToDTO(Member member){
         MemberDTO memberDTO = new MemberDTO();

         memberDTO.setId(member.getId());
         memberDTO.setFirstName(member.getFirstName());
         memberDTO.setLastName(member.getLastName());

         //store Local Date as string
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        if (member.getDateOfBirth() != null){
            memberDTO.setDateOfBirth(member.getDateOfBirth().format(timeFormatter));
        }

        if (member.getPostalAddress() != null) {
            memberDTO.setPostalAddress(PostalAddressMapper.mapToPostalAddressDTO(member.getPostalAddress()));
        }

        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhoneNumber(member.getPhoneNumber());
        memberDTO.setBarcodeNumber(member.getBarcodeNumber());

        if (member.getMembershipStarted() != null){
            memberDTO.setMembershipStarted(member.getMembershipStarted().format(timeFormatter));
        }

        if (member.getMembershipEnd() != null){
            memberDTO.setMembershipEnded(member.getMembershipEnd().format(timeFormatter));
        }

        memberDTO.setIsActive(member.getIsActive());

        return memberDTO;
    }

    //map DTO to entity

    public static Member mapMemberDTOToEntity(MemberDTO memberDTO){

        Member member = new Member();

        member.setId(memberDTO.getId());
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());

        //map string from DTO to LocalDate in entity

       if (memberDTO.getDateOfBirth() != null){
           member.setDateOfBirth(LocalDate.parse(memberDTO.getDateOfBirth()));
       }

        if (memberDTO.getPostalAddress() != null){
            member.setPostalAddress(PostalAddressMapper.mapToPostAddressEntity(memberDTO.getPostalAddress()));
        }

        member.setEmail(memberDTO.getEmail());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setBarcodeNumber(memberDTO.getBarcodeNumber());
        member.setMembershipStarted(LocalDate.parse(memberDTO.getMembershipStarted()));

        if (memberDTO.getMembershipEnded() != null){
            member.setMembershipEnd(LocalDate.parse(memberDTO.getMembershipEnded()));
        }

        member.setIsActive(memberDTO.getIsActive());

        return member;
    }
}
