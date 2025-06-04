package com.coder2client.library_system.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CheckoutRegisterDTO {

    private Long id;
    private Long memberId;
    private Long bookId;
    private String checkout;
    private String dueDate;
    private String returnDate;
    private Double overdueFine;


}
