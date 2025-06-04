package com.coder2client.library_system.service;

import com.coder2client.library_system.dto.BookDTO;
import com.coder2client.library_system.entity.Book;
import com.coder2client.library_system.mapper.BookMapper;
import com.coder2client.library_system.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final EntityManager entityManager;


    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = BookMapper.mapToBookEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToBookDTO(savedBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {

        Optional<Book> bookById = bookRepository.findById(id);
        Book book = bookById.get();
        return BookMapper.mapToBookDTO(book);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        // find the existing book by id
        Optional<Book> bookById = bookRepository.findById(bookDTO.getId());

        //partial update of the book
        Book bookToUpdate = bookById.get();
        updateBookEntityFromDTO(bookToUpdate, bookDTO);

        //save the book
        Book savedBook = bookRepository.save(bookToUpdate);

        return BookMapper.mapToBookDTO(savedBook);
    }

    @Override
    public void deleteteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> findBookByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitleContainingIgnoreCase(title);
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findBookByTitleAndAuthor(String title, String author) {
        List<Book> titleAndAuthor = bookRepository.findBooksByTitleAndAuthorContainingIgnoreCase(title, author);
        return titleAndAuthor.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findBooksByCriteria(String title, String author, String isbn, String barcodeNumber) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        List<Predicate>predicateList = new ArrayList<>();

        if (title !=null && !title.isEmpty()){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(bookRoot.get("title")), "%" + title.toLowerCase() + "%"));
        }

        if (author !=null && !author.isEmpty()){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(bookRoot.get("author")), "%" +author.toLowerCase() + "%"));
        }

        if (isbn !=null && !isbn.isEmpty()){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(bookRoot.get("isbn")), "%" +isbn.toLowerCase() + "%"));
        }

        if (barcodeNumber !=null && !barcodeNumber.isEmpty()){
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(bookRoot.get("barcodeNumber")), "%" +barcodeNumber.toLowerCase() + "%"));
        }

        criteriaQuery.where(predicateList.toArray(predicateList.toArray(new Predicate[0])));

        List<Book> result = entityManager.createQuery(criteriaQuery).getResultList();

        return result.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    private void updateBookEntityFromDTO(Book bookToUpdate, BookDTO bookDTO) {

        if (bookDTO.getTitle() != null){
            bookToUpdate.setTitle(bookDTO.getTitle());
        }

        if (bookDTO.getIsbn() != null){
            bookToUpdate.setIsbn(bookDTO.getIsbn());
        }
        if (bookDTO.getAuthor() != null){
            bookToUpdate.setAuthor(bookDTO.getAuthor());
        }
        if (bookDTO.getPublisher() != null){
            bookToUpdate.setPublisher(bookDTO.getPublisher());
        }
        if (bookDTO.getYearOfPublication() !=null){
            bookToUpdate.setYearOfPublication(bookDTO.getYearOfPublication());
        }
        if (bookDTO.getPlaceOfPublication() !=null){
            bookToUpdate.setPlaceOfPublication(bookDTO.getPlaceOfPublication());
        }
        if (bookDTO.getNoOfAvailableCopies() !=null){
            bookToUpdate.setNoOfAvailableCopies(bookDTO.getNoOfAvailableCopies());
        }
        if (bookDTO.getBarcodeNumber() !=null){
            bookToUpdate.setBarcodeNumber(bookDTO.getBarcodeNumber());
        }
    }

}
