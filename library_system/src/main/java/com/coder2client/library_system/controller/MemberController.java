package com.coder2client.library_system.controller;

import com.coder2client.library_system.dto.MemberDTO;
import com.coder2client.library_system.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("addMember")
    //url http://localhost:8081/api/v1/members/addMember
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO){

        MemberDTO addedMember = memberService.addMember(memberDTO);

        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    @GetMapping("allMembers")
    //url http://localhost:8081/api/v1/members/allMembers
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        List<MemberDTO> allMembers = memberService.getAllMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }

    @GetMapping("member/{id}")
    //url http://localhost:8081/api/v1/members/member/2
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id){
        MemberDTO memberById = memberService.getMemberById(id);
        return new ResponseEntity<>(memberById, HttpStatus.OK);
    }

    @PatchMapping("updateMember/{id}")
    //url http://localhost:8081/api/v1/members/updateMember/1
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO){
        memberDTO.setId(id);
        MemberDTO memberToUpdate = memberService.updateMember(memberDTO);
        return new ResponseEntity<>(memberToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("deleteMember/{id}")
    //url http://localhost:8081/api/v1/members/deleteMember/2
    public ResponseEntity<String> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return new ResponseEntity<>("Member deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("search")
    //url http://localhost:8081/api/v1/members/search?firstName=doe&lastName=john
    public ResponseEntity<List<MemberDTO>> searchMembers(@RequestParam(required = false) Long cardNumber,
                                                   @RequestParam(required = false) String firstName,
                                                   @RequestParam(required = false) String lastName,
                                                   @RequestParam(required = false) String barcodeNumber){
        List<MemberDTO> membersByCriteria = memberService.findMembersByCriteria(cardNumber, firstName, lastName, barcodeNumber);
        return new ResponseEntity<>(membersByCriteria, HttpStatus.OK);
    }

}
