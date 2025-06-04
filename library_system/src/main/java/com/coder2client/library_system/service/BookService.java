package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.BookDTO;
import java.util.List;
import com.coder2client.library_system.entity.Book;

public interface BookService {

     BookDTO addBook(BookDTO bookDTO);

     List<BookDTO> getAllBooks();

     BookDTO getBookById(Long id);

     BookDTO updateBook(BookDTO bookDTO);

     void deleteteBook(Long id);

     List<BookDTO> findBookByTitle(String title);

     List<BookDTO> findBookByTitleAndAuthor(String title, String author);

     List<BookDTO> findBooksByCriteria(String title, String author, String isn, String barcodeNumber);
}
