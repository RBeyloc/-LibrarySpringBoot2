package io.company.library.service;

import java.util.Optional;

import io.company.library.model.Book;
import io.company.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Optional<Iterable<Book>> getAllBooks() {
        return Optional.of(bookRepository.findAll());
    }

    public Optional<Book> createBook(Book book){
        return Optional.of(bookRepository.save(book));
    }

    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }

    public Optional<Book> deleteBookById(Long id){
        //Find out IF this id-book IS in our DB
        Optional<Book> bookFound = bookRepository.findById(id);
        if(bookFound.isPresent()) {
            bookRepository.deleteById(id);
            return Optional.of(bookFound.get());
        } else {
            return null;
        }
    }

    public Optional<Book> updateBook(Book book) {
        Optional<Book> bookFound = bookRepository.findById(book.getBookId());
        if(bookFound.isPresent()) {
            return Optional.of(bookRepository.save(book));
        } else {
            return null;
        }
    }

    public Optional<Iterable<Book>> findBooksByTitle(String title){
        return bookRepository.findBooksByTitle(title);
    }

    public Optional<Book> deleteBookByTitle(String title){
        return bookRepository.deleteBookByTitle(title);
    }

    public int count() {
        return (int) bookRepository.count();
    }

}