package io.company.library.repository;

import io.company.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository <Book, Long> {

    Optional<Iterable<Book>> findBooksByTitle(String title);

    Optional<Book> deleteBookByTitle(String title);

}

