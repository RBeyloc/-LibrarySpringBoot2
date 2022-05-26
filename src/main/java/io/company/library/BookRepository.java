package io.company.library;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository <Book, Long> {

    Optional<Iterable<Book>> findBooksByTitle(String title);

    Optional<Book> deleteBookByTitle(String title);

}

