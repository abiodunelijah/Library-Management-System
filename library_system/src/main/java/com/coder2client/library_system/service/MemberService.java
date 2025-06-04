package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.MemberDTO;

import java.util.List;

public interface MemberService {

    MemberDTO addMember(MemberDTO memberDTO);
    List<MemberDTO> getAllMembers();
    MemberDTO getMemberById(Long id);
    MemberDTO updateMember(MemberDTO memberDTO);
    void deleteMember(Long id);
    List<MemberDTO> findMembersByCriteria(Long id, String firstName, String lastName, String barcodeNumber);
}
