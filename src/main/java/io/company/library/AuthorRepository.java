package io.company.library;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository <Author, Long> {

    Optional<Iterable<Author>> findAuthorsByLastName(String lastName);
}

