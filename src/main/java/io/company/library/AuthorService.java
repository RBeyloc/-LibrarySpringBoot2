package io.company.library;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Optional<Iterable<Author>> getAllAuthors() {
        return Optional.of(authorRepository.findAll());
    }

    public Optional<Author> createAuthor(Author author){
        return Optional.of(authorRepository.save(author));
    }

    public Optional<Author> findAuthorById(Long id){
        return authorRepository.findById(id);
    }

    public Optional<Author> deleteAuthorById(Long id){
        //Find out IF this id-author IS in our DB
        Optional<Author> authorFound = authorRepository.findById(id);
        if(authorFound.isPresent()) {
            authorRepository.deleteById(id);
            return Optional.of(authorFound.get());
        } else {
            return null;
        }
    }

    public Optional<Author> updateAuthor(Author author) {
        Optional<Author> authorFound = authorRepository.findById(author.getAuthorId());
        if(authorFound.isPresent()) {
            return Optional.of(authorRepository.save(author));
        } else {
            return null;
        }
    }

    public Optional<Iterable<Author>> findAuthorsByLastName(String lastName) {
        return authorRepository.findAuthorsByLastName(lastName);
    }
}