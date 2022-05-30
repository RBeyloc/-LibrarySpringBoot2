package io.company.library.controller;

import io.company.library.model.Author;
import io.company.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("apiLibrary/authors")
public class AuthorRestController {

    @Autowired
    AuthorService authorservice;

    //here we are creating our end-point rest API Author
    //CRUD: read
    @GetMapping("authors")
    public ResponseEntity<Iterable<Author>> getAllAuthors() {
        Optional<Iterable<Author>> authorsRetrieved = authorservice.getAllAuthors();

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "authors");

        if (authorsRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(authorsRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: read, find one author by id
    @GetMapping(path = "getAuthor")
    public ResponseEntity<Author> findAuthorById(@RequestParam Long authorId) {
        Optional<Author> authorFound = authorservice.findAuthorById(authorId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getAuthor");

        if (authorFound.isPresent()) {
            headers.add("operationStatus", "found");
            return ResponseEntity.accepted().headers(headers).body(authorFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: create
    @PostMapping(path = "addBAuthor", consumes = "application/JSON")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Optional<Author> authorCreated = authorservice.createAuthor(author);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "addAuthor");

        if (authorCreated.isPresent()) {
            headers.add("operationStatus", "created");
            return ResponseEntity.accepted().headers(headers).body(authorCreated.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: delete book by id
    @DeleteMapping(path = "deleteAuthor")
    public ResponseEntity<Author> deleteAuthor(@RequestParam Long authorId) {
        Optional<Author> authorFound = authorservice.deleteAuthorById(authorId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteAuthor");

        if (authorFound.isPresent()) {
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(authorFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: update
    @PutMapping(path = "updateAuthor", consumes = "application/JSON")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        Optional<Author> authorFound = authorservice.findAuthorById(author.getAuthorId());
        Optional<Author> authorUpdate = authorFound;

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateAuthor");

        if (!authorFound.isPresent()) {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else if (author.equals(authorFound.get())) {
            headers.add("operationStatus", "no data to update");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            Boolean mustUpdate = false;
            if(!author.getFirstName().equals(authorFound.get().getFirstName()) && !author.getFirstName().equals("")) {
                authorUpdate.get().setFirstName(author.getFirstName());
                headers.add("firstName", "to be updated");
                mustUpdate = true;
            }
            if(!author.getLastName().equals(authorFound.get().getLastName()) && !author.getLastName().equals("")) {
                authorUpdate.get().setLastName(author.getLastName());
                headers.add("lastName", "to be updated");
                mustUpdate = true;
            }
            if(!author.getDob().equals(authorFound.get().getDob()) && !author.getDob().equals("")) {
                authorUpdate.get().setDob(author.getDob());
                headers.add("Date of Birth", "to be updated");
                mustUpdate = true;
            }
            if((author.getBooks() != authorFound.get().getBooks()) && (!author.getBooks().isEmpty())) {
                authorUpdate.get().setBooks(author.getBooks());
                headers.add("books", "to be updated");
                mustUpdate = true;
            }
            if (mustUpdate) {
                headers.add("operationStatus", "updated");
                return ResponseEntity.accepted().headers(headers).body(authorservice.updateAuthor(authorUpdate.get()).get());
            } else {
                headers.add("operationStatus", "no valid data to update");
                return ResponseEntity.accepted().headers(headers).body(null);
            }
        }
    }

    //CRUD: read, find one authors by lastname
    @GetMapping(path = "getAuthorsByLastName")
    public ResponseEntity<Iterable<Author>> findAuthorsByLastName(@RequestParam String lastName) {
        Optional<Iterable<Author>> authorsFound = authorservice.findAuthorsByLastName(lastName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getAuthorsByLastName");

        if (authorsFound.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(authorsFound.get());
        } else {
            headers.add("operationStatus", "not Found");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

}
