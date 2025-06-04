package com.coder2client.library_system.repository;

import com.coder2client.library_system.dto.BookDTO;
import com.coder2client.library_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //find a book by title
    List<Book> findBooksByTitleContainingIgnoreCase(String title);

    //find a book by title and author
    List<Book> findBooksByTitleAndAuthorContainingIgnoreCase(String title, String author);
}
