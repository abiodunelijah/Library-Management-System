package com.coder2client.library_system.controller;

import com.coder2client.library_system.dto.CheckoutRegisterDTO;
import com.coder2client.library_system.service.CheckoutRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/registers")
public class CheckoutRegisterController {

    private final CheckoutRegisterService checkoutRegisterService;

    @PostMapping("createRegister")
    //url http://localhost:8081/api/v1/registers/createRegister
    public ResponseEntity<CheckoutRegisterDTO> createRegister(@RequestBody CheckoutRegisterDTO checkoutRegisterDTO){
        CheckoutRegisterDTO registerDTO = checkoutRegisterService.createRegister(checkoutRegisterDTO);
        return new ResponseEntity<>(registerDTO, HttpStatus.CREATED);
    }

    @GetMapping("checkoutRegisters")
    public ResponseEntity<List<CheckoutRegisterDTO>> getAllCheckoutRegisters(){
        List<CheckoutRegisterDTO> allCheckoutRegisters = checkoutRegisterService.getAllCheckoutRegisters();
        return new ResponseEntity<>(allCheckoutRegisters, HttpStatus.OK);
    }

    @GetMapping("registerById/{id}")
    public ResponseEntity<CheckoutRegisterDTO> getCheckoutRegisterById(@PathVariable Long id){
        CheckoutRegisterDTO checkoutRegisterById = checkoutRegisterService.getCheckoutRegisterById(id);
        return new ResponseEntity<>(checkoutRegisterById, HttpStatus.OK);
    }

    @PatchMapping("updateCheckoutRegister/{id}")
    //url http://localhost:8081/api/v1/registers/updateCheckoutRegister/1
    public ResponseEntity<CheckoutRegisterDTO> updateCheckoutRegister(@PathVariable Long id, @RequestBody CheckoutRegisterDTO checkoutRegisterDTO){
        checkoutRegisterDTO.setId(id);
        CheckoutRegisterDTO updatedCheckoutRegister = checkoutRegisterService.updateCheckoutRegister(checkoutRegisterDTO);
        return new ResponseEntity<>(updatedCheckoutRegister, HttpStatus.OK);
    }

    @DeleteMapping("deleteRegister/{id}")
    //url http://localhost:8081/api/v1/registers/deleteRegister/1
    public ResponseEntity<String> deleteRegister(@PathVariable Long id){
        checkoutRegisterService.deleteCheckoutRegister(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("member/{memberId}")
    //url http://localhost:8081/api/v1/registers/member/1
    public ResponseEntity<List<CheckoutRegisterDTO>> getCheckoutRegisterMember(@PathVariable Long memberId){
        List<CheckoutRegisterDTO> checkoutRegisterMemberById = checkoutRegisterService.getCheckoutRegisterMemberById(memberId);
        return new ResponseEntity<>(checkoutRegisterMemberById, HttpStatus.OK);
    }
    @GetMapping("book/{bookId}")
    //url http://localhost:8081/api/v1/registers/member/1
    public ResponseEntity<List<CheckoutRegisterDTO>> getCheckoutRegisterBook(@PathVariable Long bookId){
        List<CheckoutRegisterDTO> checkoutRegisterBookById = checkoutRegisterService.getCheckoutRegisterMemberById(bookId);
        return new ResponseEntity<>(checkoutRegisterBookById, HttpStatus.OK);
    }
}
