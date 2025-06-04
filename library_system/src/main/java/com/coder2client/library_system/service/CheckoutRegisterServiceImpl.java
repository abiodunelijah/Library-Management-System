package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.CheckoutRegisterDTO;
import com.coder2client.library_system.entity.CheckoutRegister;
import com.coder2client.library_system.mapper.CheckoutRegisterMapper;
import com.coder2client.library_system.repository.CheckoutRegisterRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CheckoutRegisterServiceImpl implements CheckoutRegisterService {

    @Value("${library.loanPeriodInDays}")
    private int loanPeriodInDays;

    private final CheckoutRegisterRepository checkoutRegisterRepository;

    @Override
    public CheckoutRegisterDTO createRegister(CheckoutRegisterDTO checkoutRegisterDTO) {

        CheckoutRegister checkoutRegister = CheckoutRegisterMapper.mapDtoToEntity(checkoutRegisterDTO);

        //calculate due date
        LocalDate dueDate = createDueDate(checkoutRegister.getCheckoutDate());
        checkoutRegister.setDueDate(dueDate);

        CheckoutRegister register = checkoutRegisterRepository.save(checkoutRegister);
        return CheckoutRegisterMapper.mapEntityToDTO(register);
    }

    @Override
    public List<CheckoutRegisterDTO> getAllCheckoutRegisters() {

        List<CheckoutRegister> checkoutRegisters = checkoutRegisterRepository.findAll();

        return checkoutRegisters.stream()
                .map(CheckoutRegisterMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CheckoutRegisterDTO getCheckoutRegisterById(Long id) {
        Optional<CheckoutRegister> optionalCheckoutRegisterId = checkoutRegisterRepository.findById(id);
        CheckoutRegister checkoutRegister = optionalCheckoutRegisterId.get();
        return CheckoutRegisterMapper.mapEntityToDTO(checkoutRegister);
    }

    private LocalDate createDueDate(LocalDate checkoutDate) {
        return checkoutDate.plusDays(loanPeriodInDays);
    }
}
