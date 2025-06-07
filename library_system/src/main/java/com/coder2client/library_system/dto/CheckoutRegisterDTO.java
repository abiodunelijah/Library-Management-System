package com.coder2client.library_system.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRegisterDTO {

    private Long id;
    private Long memberId;
    private Long bookId;
    private String checkout;
    private String dueDate;
    private String returnDate;
    private Double overdueFine;


}
