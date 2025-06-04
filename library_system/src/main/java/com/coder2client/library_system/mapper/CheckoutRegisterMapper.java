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
@AllArgsConstructor
public class CheckoutRegisterMapper {

    private static MemberRepository memberRepository;
    private static BookRepository bookRepository;

    //map Entity to DTO

    public static CheckoutRegisterDTO mapEntityToDTO(CheckoutRegister checkoutRegister){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

       return CheckoutRegisterDTO.builder()
               .id(checkoutRegister.getId())
               .memberId(checkoutRegister.getMember().getId())
               .bookId(checkoutRegister.getBook().getId())
               .checkout(checkoutRegister.getCheckoutDate().format(dateTimeFormatter))
               .dueDate(checkoutRegister.getDueDate().format(dateTimeFormatter))
               .returnDate(checkoutRegister.getReturnDate().format(dateTimeFormatter))
               .overdueFine(checkoutRegister.getOverdueFine())
               .build();
    }

    //map DTO Entity
    public static CheckoutRegister mapDtoToEntity(CheckoutRegisterDTO checkoutRegisterDTO){
        Member member = new Member();
        if (checkoutRegisterDTO.getId() != null){
            member = memberRepository.findById(checkoutRegisterDTO.getId()).get();
        }

        Book book = new Book();
        if (checkoutRegisterDTO.getId() != null){
            book = bookRepository.findById(checkoutRegisterDTO.getBookId()).get();
        }


        return CheckoutRegister.builder()
                .id(checkoutRegisterDTO.getId())
                .member(member)
                .book(book)
                .checkoutDate(LocalDate.parse(checkoutRegisterDTO.getCheckout()))
                .dueDate(LocalDate.parse(checkoutRegisterDTO.getDueDate()))
                .returnDate(LocalDate.parse(checkoutRegisterDTO.getReturnDate()))
                .overdueFine(checkoutRegisterDTO.getOverdueFine())
                .build();
    }
}
