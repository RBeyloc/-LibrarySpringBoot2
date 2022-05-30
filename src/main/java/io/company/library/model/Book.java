package io.company.library.model;

//https://projectlombok.org/features/all
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.company.library.model.Author;
import lombok.*;
import javax.persistence.*;


@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="Book")
@Table(name="BOOK_TABLE")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private long bookId;
    @Column(name = "BOOK_TITLE")
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="AUTHOR_FK")
    private Author author;

    @Column(name = "NUMBER_PAGES")
    private int pages;
    @Column(name = "PUBLISHED_YEAR")
    private int publishedYear;
    @Column(name = "ISBN")
    private String isbn;

    public Book(String title, int pages, int publishedYear, String isbn) {
        this.title = title;
        this.pages = pages;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }

    public Book(String title, Author author, int pages, int publishedYear, String isbn) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }
}