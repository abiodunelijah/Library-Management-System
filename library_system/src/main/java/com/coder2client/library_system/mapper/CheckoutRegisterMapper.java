package com.coder2client.library_system.mapper;

import com.coder2client.library_system.dto.CheckoutRegisterDTO;
import com.coder2client.library_system.entity.Book;
import com.coder2client.library_system.entity.CheckoutRegister;
import com.coder2client.library_system.entity.Member;
import com.coder2client.library_system.repository.BookRepository;
import com.coder2client.library_system.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Component
@Builder
@AllArgsConstructor
public class CheckoutRegisterMapper {

    private  MemberRepository memberRepository;
    private  BookRepository bookRepository;

    //map Entity to DTO

    public CheckoutRegisterDTO mapEntityToDTO(CheckoutRegister checkoutRegister){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

        CheckoutRegisterDTO checkoutRegisterDTO = new CheckoutRegisterDTO();
        checkoutRegisterDTO.setId(checkoutRegister.getId());
        checkoutRegisterDTO.setMemberId(checkoutRegister.getMember().getId());
        checkoutRegisterDTO.setBookId(checkoutRegister.getBook().getId());

        checkoutRegisterDTO.setCheckout(checkoutRegister.getCheckoutDate().format(dateTimeFormatter));
        checkoutRegisterDTO.setDueDate(checkoutRegister.getDueDate().format(dateTimeFormatter));
        checkoutRegisterDTO.setReturnDate(checkoutRegister.getReturnDate().format(dateTimeFormatter));
        checkoutRegisterDTO.setOverdueFine(checkoutRegister.getOverdueFine());

        return checkoutRegisterDTO;

    }

    //map DTO Entity
    public CheckoutRegister mapDtoToEntity(CheckoutRegisterDTO checkoutRegisterDTO){
        CheckoutRegister checkoutRegister = new CheckoutRegister();
        checkoutRegister.setId(checkoutRegisterDTO.getId());

        if (checkoutRegisterDTO.getMemberId() !=null) {
            Member member = memberRepository.findById(checkoutRegisterDTO.getMemberId()).get();
            checkoutRegister.setMember(member);
        }
        if (checkoutRegisterDTO.getBookId() != null) {
            Book book = bookRepository.findById(checkoutRegisterDTO.getBookId()).get();
            checkoutRegister.setBook(book);
        }

        checkoutRegister.setCheckoutDate(LocalDate.parse(checkoutRegisterDTO.getCheckout()));
        checkoutRegister.setDueDate(LocalDate.parse(checkoutRegisterDTO.getDueDate()));
        if (checkoutRegisterDTO.getReturnDate() != null){
            checkoutRegister.setReturnDate(LocalDate.parse(checkoutRegisterDTO.getReturnDate()));
        }

        checkoutRegister.setOverdueFine(checkoutRegisterDTO.getOverdueFine());

        return checkoutRegister;

    }
}
