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
}
