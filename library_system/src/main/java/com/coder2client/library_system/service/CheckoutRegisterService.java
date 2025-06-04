package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.CheckoutRegisterDTO;

import java.util.List;

public interface CheckoutRegisterService {
    CheckoutRegisterDTO createRegister(CheckoutRegisterDTO checkoutRegisterDTO);
    List<CheckoutRegisterDTO> getAllCheckoutRegisters();
    CheckoutRegisterDTO getCheckoutRegisterById(Long id);
}
