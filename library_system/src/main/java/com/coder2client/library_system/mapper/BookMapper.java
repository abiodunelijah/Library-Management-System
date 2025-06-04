package com.coder2client.library_system.mapper;

import com.coder2client.library_system.dto.BookDTO;
import com.coder2client.library_system.entity.Book;

public class BookMapper {

    //method to map entity to dto
    public static BookDTO mapToBookDTO(Book book){

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setYearOfPublication(book.getYearOfPublication());
        bookDTO.setPlaceOfPublication(book.getPlaceOfPublication());
        bookDTO.setNoOfAvailableCopies(book.getNoOfAvailableCopies());
        bookDTO.setBarcodeNumber(book.getBarcodeNumber());

        return bookDTO;
    }

    //method to map dto to entity

    public static Book mapToBookEntity(BookDTO bookDTO){

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublisher(bookDTO.getPublisher());
        book.setTitle(bookDTO.getTitle());
        book.setYearOfPublication(bookDTO.getYearOfPublication());
        book.setPlaceOfPublication(bookDTO.getPlaceOfPublication());
        book.setNoOfAvailableCopies(bookDTO.getNoOfAvailableCopies());
        book.setBarcodeNumber(bookDTO.getBarcodeNumber());

        return book;
    }
    
}
