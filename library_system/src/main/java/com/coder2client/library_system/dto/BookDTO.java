package com.coder2client.library_system.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer yearOfPublication;
    private String placeOfPublication;
    private Integer noOfAvailableCopies;
    private String barcodeNumber;


}
