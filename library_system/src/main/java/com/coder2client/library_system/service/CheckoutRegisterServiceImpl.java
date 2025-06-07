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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CheckoutRegisterServiceImpl implements CheckoutRegisterService {

    @Value("${library.loanPeriodInDays}")
    private int loanPeriodInDays;

    @Value("${library.overdueFineRate}")
    private double overdueFineRate;

    private final CheckoutRegisterRepository checkoutRegisterRepository;
    private CheckoutRegisterMapper checkoutRegisterMapper;

    @Override
    public CheckoutRegisterDTO createRegister(CheckoutRegisterDTO checkoutRegisterDTO) {

        CheckoutRegister checkoutRegister = checkoutRegisterMapper.mapDtoToEntity(checkoutRegisterDTO);

        //calculate due date
        LocalDate dueDate = createDueDate(checkoutRegister.getCheckoutDate());
        checkoutRegister.setDueDate(dueDate);

        CheckoutRegister register = checkoutRegisterRepository.save(checkoutRegister);
        return checkoutRegisterMapper.mapEntityToDTO(register);
    }

    @Override
    public List<CheckoutRegisterDTO> getAllCheckoutRegisters() {

        List<CheckoutRegister> checkoutRegisters = checkoutRegisterRepository.findAll();

        return checkoutRegisters.stream()
                .map(checkoutRegisterMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CheckoutRegisterDTO getCheckoutRegisterById(Long id) {
        Optional<CheckoutRegister> optionalCheckoutRegisterId = checkoutRegisterRepository.findById(id);
        CheckoutRegister checkoutRegister = optionalCheckoutRegisterId.get();
        return checkoutRegisterMapper.mapEntityToDTO(checkoutRegister);
    }

    @Override
    public CheckoutRegisterDTO updateCheckoutRegister(CheckoutRegisterDTO checkoutRegisterDTO) {
        //find the existing checkout register by id
        Optional<CheckoutRegister> checkoutRegisterOptional = checkoutRegisterRepository.findById(checkoutRegisterDTO.getId());
        CheckoutRegister checkoutRegisterToUpdate = checkoutRegisterOptional.get();

        // do partial update
        updateCheckoutRegisterFromDTO(checkoutRegisterToUpdate, checkoutRegisterDTO);

        //calculate overdue fine
        calculateOverdueFine(checkoutRegisterToUpdate);

        // save updated register to the DB
        CheckoutRegister checkoutRegister = checkoutRegisterRepository.save(checkoutRegisterToUpdate);

        //return register DTO using mapper
        return checkoutRegisterMapper.mapEntityToDTO(checkoutRegister);
    }

    @Override
    public void deleteCheckoutRegister(Long id) {
        checkoutRegisterRepository.deleteById(id);
    }

    @Override
    public List<CheckoutRegisterDTO> getCheckoutRegisterMemberById(Long memberId) {
        List<CheckoutRegister> byMemberId = checkoutRegisterRepository.findByMemberId(memberId);
        return byMemberId.stream()
                .map(checkoutRegisterMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckoutRegisterDTO> getCheckoutRegisterBookById(Long bookId) {
        List<CheckoutRegister> byBookId = checkoutRegisterRepository.findByBookId(bookId);
        return byBookId.stream().map(checkoutRegisterMapper::mapEntityToDTO).collect(Collectors.toList());
    }

    private void calculateOverdueFine(CheckoutRegister checkoutRegister) {

        if (checkoutRegister.getReturnDate() != null) {
            checkoutRegister.getReturnDate().isAfter(checkoutRegister.getDueDate());
        }
            long daysOverdue = ChronoUnit.DAYS.between(checkoutRegister.getDueDate(), checkoutRegister.getReturnDate());

            double overdueFine = daysOverdue * overdueFineRate;
            checkoutRegister.setOverdueFine(overdueFine);

    }

    private void updateCheckoutRegisterFromDTO(CheckoutRegister checkoutRegisterToUpdate, CheckoutRegisterDTO checkoutRegisterDTO) {
        // the agent can either prolong the book or return the book
        if (checkoutRegisterDTO.getDueDate() != null){
            checkoutRegisterToUpdate.setDueDate(LocalDate.parse(checkoutRegisterDTO.getDueDate()));
        }

        if (checkoutRegisterDTO.getReturnDate() != null){
            checkoutRegisterToUpdate.setReturnDate(LocalDate.parse(checkoutRegisterDTO.getReturnDate()));
        }
    }

    private LocalDate createDueDate(LocalDate checkoutDate) {
        return checkoutDate.plusDays(loanPeriodInDays);
    }
}
