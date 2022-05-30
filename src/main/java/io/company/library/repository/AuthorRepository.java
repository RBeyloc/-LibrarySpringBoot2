package io.company.library.repository;

import io.company.library.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository <Author, Long> {

    Optional<Iterable<Author>> findAuthorsByLastName(String lastName);
}

